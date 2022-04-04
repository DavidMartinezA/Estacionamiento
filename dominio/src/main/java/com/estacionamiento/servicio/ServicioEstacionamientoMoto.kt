package com.estacionamiento.servicio

import com.estacionamiento.modelo.EstacionamientoMoto
import com.excepciones.IngresoNoPermitidoRestriccionExcepcion
import com.excepciones.UsuarioNoExisteExcepcion
import com.excepciones.UsuarioYaExisteExcepcion
import com.usuario.modelo.UsuarioVehiculoMoto
import com.usuario.repositorio.RepositorioUsuarioVehiculoMoto

class ServicioEstacionamientoMoto(
    var estacionamiento: EstacionamientoMoto,
    val repositorioUsuarioVehiculoMoto: RepositorioUsuarioVehiculoMoto,
) {

    private val usuarioVehiculoMoto = estacionamiento.usuarioVehiculo as UsuarioVehiculoMoto

    suspend fun eliminarUsuario() {
        if (repositorioUsuarioVehiculoMoto.usuarioExiste(usuarioVehiculoMoto)) {
            repositorioUsuarioVehiculoMoto.eliminarUsuario(usuarioVehiculoMoto)
        } else {
            throw UsuarioNoExisteExcepcion()
        }
    }

    suspend fun consultarListaUsuarios(): List<UsuarioVehiculoMoto> {
        return repositorioUsuarioVehiculoMoto.listaUsuarios()
    }

    suspend fun ingresoUsuarioEstacionamiento(diaDeLaSemana: Int) {

        estacionamiento.usuarioVehiculo.horaFechaIngresoUsuario = estacionamiento.horaFechaIngresoUsuario

        val espacioDisponibleEnEstacionamiento: Boolean
        val usuarioNoExiste = !repositorioUsuarioVehiculoMoto.usuarioExiste(usuarioVehiculoMoto)

        if (usuarioNoExiste) {
            espacioDisponibleEnEstacionamiento = consultaDisponibilidadEstacionamiento()
        } else {
            throw UsuarioYaExisteExcepcion()
        }
        if (espacioDisponibleEnEstacionamiento && !estacionamiento.restriccionDeIngreso(diaDeLaSemana)) {
            repositorioUsuarioVehiculoMoto.guardarUsuario(usuarioVehiculoMoto)
        } else {
            throw IngresoNoPermitidoRestriccionExcepcion()
        }
    }

    suspend fun consultaDisponibilidadEstacionamiento(): Boolean {
        var existeEspacio = false
        val listaUsuarioVehiculo: List<UsuarioVehiculoMoto> = repositorioUsuarioVehiculoMoto.listaUsuarios()
        if (listaUsuarioVehiculo.size < estacionamiento.capacidadEstacionamiento) {
            existeEspacio = true
        }
        return existeEspacio
    }

}