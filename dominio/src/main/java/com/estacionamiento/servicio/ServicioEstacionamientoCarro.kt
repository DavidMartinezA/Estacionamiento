package com.estacionamiento.servicio

import com.estacionamiento.modelo.EstacionamientoCarro
import com.excepciones.IngresoNoPermitidoRestriccionExcepcion
import com.excepciones.UsuarioNoExisteExcepcion
import com.excepciones.UsuarioYaExisteExcepcion
import com.usuario.modelo.UsuarioVehiculoCarro
import com.usuario.repositorio.RepositorioUsuarioVehiculoCarro

class ServicioEstacionamientoCarro(
    var estacionamiento: EstacionamientoCarro,
    val repositorioUsuarioVehiculoCarro: RepositorioUsuarioVehiculoCarro,
) {

    private val usuarioVehiculoCarro = estacionamiento.usuarioVehiculo as UsuarioVehiculoCarro

    suspend fun eliminarUsuario() {
        if (repositorioUsuarioVehiculoCarro.usuarioExiste(usuarioVehiculoCarro)) {
            repositorioUsuarioVehiculoCarro.eliminarUsuario(usuarioVehiculoCarro)
        } else {
            throw UsuarioNoExisteExcepcion()
        }
    }

    suspend fun consultarListaUsuarios(): List<UsuarioVehiculoCarro> {
        return repositorioUsuarioVehiculoCarro.listaUsuarios()
    }

    suspend fun ingresoUsuarioEstacionamiento(diaDeLaSemana: Int) {

        estacionamiento.usuarioVehiculo.horaFechaIngresoUsuario = estacionamiento.horaFechaIngresoUsuario

        val espacioDisponibleEnEstacionamiento: Boolean
        val usuarioNoExiste = !repositorioUsuarioVehiculoCarro.usuarioExiste(usuarioVehiculoCarro)

        if (usuarioNoExiste) {
            espacioDisponibleEnEstacionamiento = consultaDisponibilidadEstacionamiento()
        } else {
            throw UsuarioYaExisteExcepcion()
        }
        if (espacioDisponibleEnEstacionamiento && !estacionamiento.restriccionDeIngreso(diaDeLaSemana)) {
            repositorioUsuarioVehiculoCarro.guardarUsuario(usuarioVehiculoCarro)
        } else {
            throw IngresoNoPermitidoRestriccionExcepcion()
        }
    }

    suspend fun consultaDisponibilidadEstacionamiento(): Boolean {
        var existeEspacio = false
        val listaUsuarioVehiculo: List<UsuarioVehiculoCarro> = repositorioUsuarioVehiculoCarro.listaUsuarios()

        if (listaUsuarioVehiculo.size < estacionamiento.capacidadEstacionamiento) {
            existeEspacio = true
        }
        return existeEspacio
    }

}