package com.estacionamiento.servicio

import com.estacionamiento.modelo.Estacionamiento
import com.excepciones.IngresoNoPermitidoRestriccionExcepcion
import com.excepciones.UsuarioNoExisteExcepcion
import com.excepciones.UsuarioYaExisteExcepcion
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.repositorio.RepositorioUsuarioVehiculo

class ServicioEstacionamiento(
    var estacionamiento: Estacionamiento,
    val repositorioUsuarioVehiculo: RepositorioUsuarioVehiculo,
) {

    fun eliminarUsuario() {
        if (repositorioUsuarioVehiculo.usuarioExiste(estacionamiento.usuarioVehiculo)) {
            repositorioUsuarioVehiculo.eliminarUsuario(estacionamiento.usuarioVehiculo)
        } else {
            throw UsuarioNoExisteExcepcion()
        }
    }

    fun consultarListaUsuarios(): ArrayList<UsuarioVehiculo> {
        return repositorioUsuarioVehiculo.listaUsuarios()
    }

    fun ingresoUsuarioEstacionamiento(diaDeLaSemana: Int) {

        estacionamiento.usuarioVehiculo.horaFechaIngresoUsuario =
            estacionamiento.horaFechaIngresoUsuario

        val espacioDisponibleEnEstacionamiento: Boolean
        val usuarioNoExiste =
            !repositorioUsuarioVehiculo.usuarioExiste(estacionamiento.usuarioVehiculo)
        if (usuarioNoExiste) {
            espacioDisponibleEnEstacionamiento = consultaDisponibilidadEstacionamiento()
        } else {
            throw UsuarioYaExisteExcepcion()
        }
        if (espacioDisponibleEnEstacionamiento && !estacionamiento.restriccionDeIngreso(
                diaDeLaSemana)
        ) {
            repositorioUsuarioVehiculo.guardarUsuario(estacionamiento.usuarioVehiculo)
        } else {
            throw IngresoNoPermitidoRestriccionExcepcion()
        }
    }

    fun consultaDisponibilidadEstacionamiento(): Boolean {
        var existeEspacio = false
        val listaUsuarioVehiculo: ArrayList<UsuarioVehiculo> =
            repositorioUsuarioVehiculo.listaUsuarios()
        if (listaUsuarioVehiculo.size < estacionamiento.capacidadEstacionamiento) {
            existeEspacio = true
        }
        return existeEspacio
    }

}