package com.example.estacionamiento.servicio

import com.example.estacionamiento.excepcion.IngresoNoPermitidoRestriccionExcepcion
import com.example.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.example.estacionamiento.excepcion.UsuarioYaExisteExcepcion
import com.example.estacionamiento.modelo.Estacionamiento
import com.example.usuario.modelo.UsuarioVehiculo
import com.example.usuario.repositorio.RepositorioUsuarioVehiculo

class ServicioEstacionamiento(
    var estacionamiento: Estacionamiento,
    val repositorioUsuarioVehiculo: RepositorioUsuarioVehiculo,
) {

    suspend fun consultarListaUsuarios(): List<UsuarioVehiculo> {
        return repositorioUsuarioVehiculo.listaUsuarios()
    }

    suspend fun consultaDisponibilidadEstacionamiento(): Boolean {
        var existeEspacio = false
        val listaUsuarioVehiculo: List<UsuarioVehiculo> =
            repositorioUsuarioVehiculo.listaUsuarios().filter { usuario -> usuario.tipoDeVehiculo == estacionamiento.tipoDeUsuario }
        if (listaUsuarioVehiculo.size < estacionamiento.capacidadEstacionamiento) {
            existeEspacio = true
        }
        return existeEspacio
    }

    suspend fun ingresoUsuarioEstacionamiento(diaDeLaSemana: Int) {

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

    suspend fun eliminarUsuario() {

        if (repositorioUsuarioVehiculo.usuarioExiste(estacionamiento.usuarioVehiculo)) {
            repositorioUsuarioVehiculo.eliminarUsuario(estacionamiento.usuarioVehiculo)
        } else {
            throw UsuarioNoExisteExcepcion()
        }
    }

}