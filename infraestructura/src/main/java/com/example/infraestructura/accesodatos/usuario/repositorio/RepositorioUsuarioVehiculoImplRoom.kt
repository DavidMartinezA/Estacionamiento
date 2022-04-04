package com.example.infraestructura.accesodatos.usuario.repositorio

import com.example.infraestructura.accesodatos.compartido.basededatos.BaseDatosUsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.repositorio.RepositorioUsuarioVehiculo

class RepositorioUsuarioVehiculoImplRoom(
    val baseDatosUsuarioVehiculo: BaseDatosUsuarioVehiculo,
) : RepositorioUsuarioVehiculo {

    /* private val traducctorUsuarioVehiculo = TraductorUsuarioVehiculo()

    override fun usuarioExiste(usuarioVehiculo: UsuarioVehiculo): Boolean {
        return true // todo
    }

    override suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo) {

        val traduccionDelUsuario = traducctorUsuarioVehiculo.desdeDominioUnUsuarioVehiculo(usuarioVehiculo)
        baseDatosUsuarioVehiculo.usuarioVehiculoDao().insertarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traduccionDelUsuario = traducctorUsuarioVehiculo.desdeDominioUnUsuarioVehiculo(usuarioVehiculo)

        baseDatosUsuarioVehiculo.usuarioVehiculoDao().borrarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun listaUsuarios(): List<UsuarioVehiculo> {
        return baseDatosUsuarioVehiculo.usuarioVehiculoDao().listaUsuariosVehiculo()
    }*/
    override fun usuarioExiste(usuarioVehiculo: UsuarioVehiculo): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        TODO("Not yet implemented")
    }

    override suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        TODO("Not yet implemented")
    }

    override suspend fun listaUsuarios(): List<UsuarioVehiculo> {
        TODO("Not yet implemented")
    }
}