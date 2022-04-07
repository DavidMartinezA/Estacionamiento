package com.estacionamiento.servicio

import com.estacionamiento.excepcion.IngresoNoPermitidoRestriccionExcepcion
import com.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.estacionamiento.excepcion.UsuarioYaExisteExcepcion
import com.estacionamiento.modelo.Estacionamiento
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.repositorio.RepositorioUsuarioVehiculo
import java.time.LocalDateTime

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

    suspend fun ingresoUsuarioEstacionamiento(diaDeLaSemana: Int, horaFechaIngresoUsuario: LocalDateTime) {

        estacionamiento.usuarioVehiculo.horaFechaIngresoUsuario = horaFechaIngresoUsuario

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