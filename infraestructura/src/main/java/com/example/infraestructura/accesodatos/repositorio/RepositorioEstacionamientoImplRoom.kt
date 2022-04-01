package com.example.infraestructura.accesodatos.repositorio

import com.example.infraestructura.accesodatos.basededatos.BaseDatosUsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.repositorio.RepositorioUsuarioVehiculo

class RepositorioEstacionamientoImplRoom(
    val baseDatosUsuarioVehiculo: BaseDatosUsuarioVehiculo,
) : RepositorioUsuarioVehiculo {

    override fun usuarioExiste(usuarioVehiculo: UsuarioVehiculo): Boolean {
        TODO("Not yet implemented")
    }

    override fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        TODO("Not yet implemented")
    }

    override fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        TODO("Not yet implemented")
    }

    override fun listaUsuarios(): ArrayList<UsuarioVehiculo> {
        TODO("Not yet implemented")
    }
}