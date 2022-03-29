package com.estacionamiento.servicio

import com.cobro.modelo.CobroTarifaCarro
import com.estacionamiento.modelo.EstacionamientoMoto
import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.excepciones.IngresoNoPermitidoRestriccionExcepcion
import com.excepciones.UsuarioNoExisteExcepcion
import com.usuario.modelo.UsuarioVehiculoMoto
import java.time.LocalDateTime

class ServicioEstacionamientoMoto(
    private var usuarioVehiculoMoto: UsuarioVehiculoMoto,
    repositorioEstacionamiento: RepositorioEstacionamiento,
) : ServicioEstacionamiento(usuarioVehiculoMoto, repositorioEstacionamiento) {

    private var espacioDisponibleEnEstacionamiento = false

    override fun ingresoUsuarioEstacionamiento() {

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
            guardarUsuario()
        } else {
            throw IngresoNoPermitidoRestriccionExcepcion()
        }
    }

    override fun salidaDeUsuariosEstacionamiento() {

        if (repositorioEstacionamiento.usuarioExiste(usuarioVehiculo)) {

            val usuario = usuarioVehiculo as UsuarioVehiculoMoto
            CobroTarifaCarro(usuario).cobroTarifa(CobroTarifaCarro(usuarioVehiculo)
                .duracionServicioEstacionamiento())
            eliminarUsuario()
        } else {
            throw UsuarioNoExisteExcepcion()
        }
    }

}