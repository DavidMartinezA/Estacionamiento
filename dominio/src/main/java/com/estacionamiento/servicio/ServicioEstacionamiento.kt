package com.estacionamiento.servicio

import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.usuario.modelo.UsuarioVehiculo

abstract class ServicioEstacionamiento(
    protected var usuarioVehiculo: UsuarioVehiculo,
    protected val repositorioEstacionamiento: RepositorioEstacionamiento,
) {

    protected fun guardarUsuario() {

        if (!repositorioEstacionamiento.usuarioExiste(this.usuarioVehiculo)) {
            repositorioEstacionamiento.guardarUsuario(this.usuarioVehiculo)
        }
    }

    protected fun eliminarUsuario() {

        if (repositorioEstacionamiento.usuarioExiste(usuarioVehiculo)) {
            repositorioEstacionamiento.eliminarUsuario(usuarioVehiculo)
        }
    }

    fun consultarListaUsuarios(): ArrayList<UsuarioVehiculo> {
        return repositorioEstacionamiento.listaUsuarios()
    }

    abstract fun ingresoUsuarioEstacionamiento()


    abstract fun salidaDeUsuariosEstacionamiento()

}