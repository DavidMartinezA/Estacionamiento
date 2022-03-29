package com.estacionamiento.servicio

import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.usuario.modelo.UsuarioVehiculo

abstract class ServicioEstacionamiento(
    var usuarioVehiculo: UsuarioVehiculo,
    private val repositorioEstacionamiento: RepositorioEstacionamiento,
) {


    fun guardarUsuario() {

        if (!repositorioEstacionamiento.usuarioExiste(usuarioVehiculo)) {
            repositorioEstacionamiento.guardarUsuario(usuarioVehiculo)
        }
    }

    fun eliminarUsuario() {

        if (repositorioEstacionamiento.usuarioExiste(usuarioVehiculo)) {
            repositorioEstacionamiento.eliminarUsuario(usuarioVehiculo)
        }
    }

    fun consultarListaUsuarios(): ArrayList<UsuarioVehiculo> {
        return repositorioEstacionamiento.listaUsuarios()
    }

}