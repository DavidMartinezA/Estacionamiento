package com.estacionamiento.servicio

import com.cobro.modelo.CobroTarifaCarro
import com.cobro.modelo.CobroTarifaMoto
import com.estacionamiento.modelo.EstacionamientoMoto
import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.excepciones.IngresoNoPermitidoRestriccionExcepcion
import com.excepciones.UsuarioNoExisteExcepcion
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoMoto
import java.time.Duration
import java.time.LocalDateTime
import kotlin.math.ceil

class ServicioEstacionamientoMoto(
    var usuarioVehiculoMoto: UsuarioVehiculoMoto,
    val repositorioEstacionamiento: RepositorioEstacionamiento,
) : ServicioEstacionamiento(usuarioVehiculoMoto, repositorioEstacionamiento) {

    var espacioDisponibleEnEstacionamiento = false

    fun ingresoUsuarioEstacionamiento() {

        val estacionamiento = EstacionamientoMoto(usuarioVehiculoMoto, this)
        val diaDeLaSemana = LocalDateTime.now().dayOfWeek.value
        val usuarioNoExiste = !repositorioEstacionamiento.usuarioExiste(usuarioVehiculoMoto)

        if (usuarioNoExiste) {
            espacioDisponibleEnEstacionamiento = estacionamiento.consultarCapacidad()
        }

        if (espacioDisponibleEnEstacionamiento && !estacionamiento.restriccionDeIngreso(
                diaDeLaSemana)
        ) {
            usuarioVehiculo.fechaIngreso = LocalDateTime.now().toLocalDate()
            repositorioEstacionamiento.guardarUsuario(usuarioVehiculo)
        } else {
            throw IngresoNoPermitidoRestriccionExcepcion()
        }
    }

    fun salidaDeUsuariosEstacionamiento(usuarioVehiculo: UsuarioVehiculo): Int {

        val cobroTarifa: Int
        val fechaSalida = LocalDateTime.now().toLocalDate()
        if (repositorioEstacionamiento.usuarioExiste(usuarioVehiculo)) {

            val duracionServicioEstacionamiento =
                ceil(Duration.between(usuarioVehiculo.fechaIngreso, fechaSalida).toHours()
                    .toString()
                    .toDouble()).toInt()

            if (usuarioVehiculo is UsuarioVehiculoMoto) {
                cobroTarifa =
                    CobroTarifaMoto().cobroTarifa(duracionServicioEstacionamiento, usuarioVehiculo)
                repositorioEstacionamiento.eliminarUsuario(usuarioVehiculo)
            } else {
                cobroTarifa =
                    CobroTarifaCarro().cobroTarifa(duracionServicioEstacionamiento, usuarioVehiculo)
                repositorioEstacionamiento.eliminarUsuario(usuarioVehiculo)
            }
        } else {
            throw UsuarioNoExisteExcepcion()
        }
        return cobroTarifa
    }


}