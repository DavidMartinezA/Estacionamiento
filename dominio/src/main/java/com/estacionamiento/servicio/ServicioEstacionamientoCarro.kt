package com.estacionamiento.servicio

import com.cobro.modelo.CobroTarifaCarro
import com.estacionamiento.modelo.EstacionamientoCarro
import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.excepciones.IngresoNoPermitidoRestriccionExcepcion
import com.excepciones.UsuarioNoExisteExcepcion
import com.usuario.modelo.UsuarioVehiculoCarro
import java.time.LocalDateTime

class ServicioEstacionamientoCarro(
    private var usuarioVehiculoCarro: UsuarioVehiculoCarro,
    repositorioEstacionamiento: RepositorioEstacionamiento,
) : ServicioEstacionamiento(usuarioVehiculoCarro, repositorioEstacionamiento) {

    override fun ingresoUsuarioEstacionamiento() {

        var espacioDisponibleEnEstacionamiento = false
        val estacionamiento = EstacionamientoCarro(usuarioVehiculoCarro, this)
        val diaDeLaSemana = LocalDateTime.now().dayOfWeek.value
        val usuarioNoExiste = !repositorioEstacionamiento.usuarioExiste(usuarioVehiculoCarro)

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

    override fun salidaDeUsuariosEstacionamiento() {

        if (repositorioEstacionamiento.usuarioExiste(usuarioVehiculoCarro)) {

            CobroTarifaCarro(usuarioVehiculoCarro).cobroTarifa(CobroTarifaCarro(usuarioVehiculoCarro)
                .duracionServicioEstacionamiento())
            eliminarUsuario()
        } else {
            throw UsuarioNoExisteExcepcion()
        }
    }
}