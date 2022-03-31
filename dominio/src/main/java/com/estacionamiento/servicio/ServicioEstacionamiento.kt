package com.estacionamiento.servicio

import com.estacionamiento.modelo.Estacionamiento
import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.excepciones.IngresoNoPermitidoRestriccionExcepcion
import com.excepciones.UsuarioNoExisteExcepcion
import com.excepciones.UsuarioYaExisteExcepcion
import com.usuario.modelo.UsuarioVehiculo

abstract class ServicioEstacionamiento(
    var estacionamiento: Estacionamiento,
    val repositorioEstacionamiento: RepositorioEstacionamiento,
) {

    abstract fun consultaDisponibilidadEstacionamiento(): Boolean

    fun guardarUsuario() {
        if (!repositorioEstacionamiento.usuarioExiste(estacionamiento.usuarioVehiculo)) {
            repositorioEstacionamiento.guardarUsuario(estacionamiento.usuarioVehiculo)
        } else {
            throw UsuarioYaExisteExcepcion()
        }
    }

    fun eliminarUsuario() {
        if (repositorioEstacionamiento.usuarioExiste(estacionamiento.usuarioVehiculo)) {
            repositorioEstacionamiento.eliminarUsuario(estacionamiento.usuarioVehiculo)
        } else {
            throw UsuarioNoExisteExcepcion()
        }
    }

    fun consultarListaUsuarios(): ArrayList<UsuarioVehiculo> {
        return repositorioEstacionamiento.listaUsuarios()
    }

    fun ingresoUsuarioEstacionamiento(diaDeLaSemana: Int) {

        var espacioDisponibleEnEstacionamiento = false
        val usuarioNoExiste =
            !repositorioEstacionamiento.usuarioExiste(estacionamiento.usuarioVehiculo)
        if (usuarioNoExiste) {
            espacioDisponibleEnEstacionamiento = consultaDisponibilidadEstacionamiento()
        }
        if (espacioDisponibleEnEstacionamiento && !estacionamiento.restriccionDeIngreso(
                diaDeLaSemana)
        ) {
            repositorioEstacionamiento.guardarUsuario(estacionamiento.usuarioVehiculo)
        } else {
            throw IngresoNoPermitidoRestriccionExcepcion()
        }
    }

}