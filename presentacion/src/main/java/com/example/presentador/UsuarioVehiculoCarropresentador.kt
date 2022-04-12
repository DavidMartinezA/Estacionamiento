package com.example.presentador

import android.content.Context
import com.example.cobro.modelo.CobroTarifaMoto
import com.example.cobro.servicio.ServicioCobroTarifa
import com.example.estacionamiento.modelo.EstacionamientoCarro
import com.example.estacionamiento.servicio.ServicioEstacionamiento
import com.example.example.infraestructura.accesodatos.usuario.repositorio.RepositorioUsuarioVehiculoCarroRoom
import com.example.usuario.modelo.UsuarioVehiculoCarro
import java.time.LocalDateTime

class UsuarioVehiculoCarropresentador(contexto: Context) {

    private var repositorioRoomCarro = RepositorioUsuarioVehiculoCarroRoom(contexto)

    suspend fun setIngresarUsuario(usuarioIngresado: String) {

        val usuarioVehiculoCarro = UsuarioVehiculoCarro(usuarioIngresado)
        val servicioEstacionamiento =
            ServicioEstacionamiento(EstacionamientoCarro(usuarioVehiculoCarro), repositorioRoomCarro)

        servicioEstacionamiento.ingresoUsuarioEstacionamiento(LocalDateTime.now().dayOfWeek.value)

    }

    suspend fun getCobroServicio(usuarioIngresado: String): Int {// todo existe un problema aqui

        var servicioTarifa = 0
        if (repositorioRoomCarro.usuarioVehiculoDao.comprobacionUsuarioExiste(usuarioIngresado)) {
            val usuarioVehiculoCarro = UsuarioVehiculoCarro(usuarioIngresado)
            val estacionamiento = EstacionamientoCarro(usuarioVehiculoCarro)
            val cobroServicio = CobroTarifaMoto(estacionamiento)

            val servicio =
                ServicioCobroTarifa(ServicioEstacionamiento(estacionamiento, repositorioRoomCarro), cobroServicio, LocalDateTime.now())

            servicioTarifa = servicio.cobroDuracionServicio()
        }


        return servicioTarifa
    }

    suspend fun setEliminarUsuario(usuarioIngresado: String) {

        val usuarioVehiculoCarro = UsuarioVehiculoCarro(usuarioIngresado)
        val servicioEstacionamiento =
            ServicioEstacionamiento(EstacionamientoCarro(usuarioVehiculoCarro), repositorioRoomCarro)

        servicioEstacionamiento.eliminarUsuario()
    }
}