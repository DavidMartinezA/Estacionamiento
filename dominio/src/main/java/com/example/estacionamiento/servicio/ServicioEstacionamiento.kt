package com.example.estacionamiento.servicio

import com.example.estacionamiento.excepcion.IngresoNoPermitidoRestriccionExcepcion
import com.example.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.example.estacionamiento.excepcion.UsuarioYaExisteExcepcion
import com.example.estacionamiento.modelo.Estacionamiento
import com.example.usuario.modelo.UsuarioVehiculo
import com.example.usuario.repositorio.RepositorioUsuarioVehiculo
import javax.inject.Inject

class ServicioEstacionamiento @Inject constructor(val repositorioUsuarioVehiculo: RepositorioUsuarioVehiculo) {

    suspend fun consultarListaUsuarios(): List<UsuarioVehiculo> {
        return repositorioUsuarioVehiculo.listaUsuarios()
    }

    suspend fun consultaDisponibilidadEstacionamiento(estacionamiento: Estacionamiento): Boolean {
        var existeEspacio = false
        val listaUsuarioVehiculo: List<UsuarioVehiculo> =
            repositorioUsuarioVehiculo.listaUsuarios().filter { usuario -> usuario.tipoDeVehiculo == estacionamiento.tipoDeUsuario }
        if (listaUsuarioVehiculo.size < estacionamiento.capacidadEstacionamiento) {
            existeEspacio = true
        }
        return existeEspacio
    }

    suspend fun ingresoUsuarioEstacionamiento(estacionamiento: Estacionamiento, diaDeLaSemana: Int) {
        val espacioDisponibleEnEstacionamiento: Boolean
        val usuarioNoExiste =
            !repositorioUsuarioVehiculo.usuarioExiste(estacionamiento.usuarioVehiculo.placaVehiculo)
        if (usuarioNoExiste) {
            espacioDisponibleEnEstacionamiento = consultaDisponibilidadEstacionamiento(estacionamiento)
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

    suspend fun eliminarUsuario(placa: String) {

        if (repositorioUsuarioVehiculo.usuarioExiste(placa)) {
            repositorioUsuarioVehiculo.eliminarUsuario(placa)
        } else {
            throw UsuarioNoExisteExcepcion()
        }
    }

}