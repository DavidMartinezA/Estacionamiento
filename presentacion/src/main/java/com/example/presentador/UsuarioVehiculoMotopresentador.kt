package com.example.presentador

import android.content.Context

import com.example.cobro.modelo.CobroTarifaMoto
import com.example.cobro.servicio.ServicioCobroTarifa
import com.example.estacionamiento.modelo.EstacionamientoMoto
import com.example.estacionamiento.servicio.ServicioEstacionamiento
import com.example.usuario.modelo.UsuarioVehiculoMoto
import java.time.LocalDateTime

class UsuarioVehiculoMotopresentador(contexto: Context) {

    private var repositorioRoomMoto = RepositorioUsuarioVehiculoMotoRoom(contexto)

    suspend fun setIngresarUsuario(usuarioIngresado: String, altoCilindraje: Boolean) {

        val usuarioVehiculoMoto = UsuarioVehiculoMoto(usuarioIngresado, altoCilindraje)
        val servicioEstacionamiento =
            ServicioEstacionamiento(EstacionamientoMoto(usuarioVehiculoMoto), repositorioRoomMoto)

        servicioEstacionamiento.ingresoUsuarioEstacionamiento(LocalDateTime.now().dayOfWeek.value)

    }

    suspend fun getCobroServicio(usuarioIngresado: String, altoCilindraje: Boolean): Int {// todo existe un problema aqui

        val usuarioVehiculoMoto = UsuarioVehiculoMoto(usuarioIngresado, altoCilindraje)
        val estacionamiento = EstacionamientoMoto(usuarioVehiculoMoto)
        val cobroServicio = CobroTarifaMoto()
        val servicioTarifa =
            ServicioCobroTarifa(ServicioEstacionamiento(estacionamiento, repositorioRoomMoto), cobroServicio)

        return servicioTarifa.cobroDuracionServicio()
    }

    suspend fun setEliminarUsuario(usuarioIngresado: String, altoCilindraje: Boolean) {

        val usuarioVehiculoMoto = UsuarioVehiculoMoto(usuarioIngresado, altoCilindraje)
        val servicioEstacionamiento =
            ServicioEstacionamiento(EstacionamientoMoto(usuarioVehiculoMoto), repositorioRoomMoto)

        servicioEstacionamiento.eliminarUsuario()
    }
}