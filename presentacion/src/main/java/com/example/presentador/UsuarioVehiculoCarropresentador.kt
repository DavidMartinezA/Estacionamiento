package com.example.presentador

import android.content.Context
import com.example.cobro.modelo.CobroTarifaMoto
import com.example.cobro.servicio.ServicioCobroTarifa
import com.example.estacionamiento.modelo.EstacionamientoCarro
import com.example.estacionamiento.servicio.ServicioEstacionamiento
import com.example.example.infraestructura.accesodatos.usuario.repositorio.RepositorioUsuarioVehiculoRoom
import com.example.usuario.modelo.UsuarioVehiculoCarro
import com.example.usuario.modelo.UsuarioVehiculoMoto
import java.time.LocalDateTime

class UsuarioVehiculoCarropresentador(contexto: Context) {

    private var repositorioRoom = RepositorioUsuarioVehiculoRoom(contexto)

    suspend fun setIngresarUsuario(usuarioIngresado: String) {

        val usuarioVehiculoCarro = UsuarioVehiculoCarro(usuarioIngresado)
        val servicioEstacionamiento =
            ServicioEstacionamiento(EstacionamientoCarro(usuarioVehiculoCarro), repositorioRoom)

        servicioEstacionamiento.ingresoUsuarioEstacionamiento(LocalDateTime.now().dayOfWeek.value)

    }

    suspend fun getCobroServicio(usuarioIngresado: String): Int {// todo existe un problema aqui

        var servicioTarifa = 0
        val usuario = UsuarioVehiculoMoto(usuarioIngresado)

        if (repositorioRoom.usuarioExiste(usuarioIngresado)) {
            val usuario = repositorioRoom.buscarUsuario(usuarioIngresado)
            val estacionamiento = EstacionamientoCarro(usuario)
            val cobroServicio = CobroTarifaMoto()

            val servicio =
                ServicioCobroTarifa(ServicioEstacionamiento(estacionamiento, repositorioRoom), cobroServicio)

            servicioTarifa = servicio.cobroDuracionServicio()
        }

        return servicioTarifa
    }

    suspend fun setEliminarUsuario(usuarioIngresado: String) {
        val usuario = UsuarioVehiculoCarro(usuarioIngresado)
        repositorioRoom.eliminarUsuario(usuario)
    }
}