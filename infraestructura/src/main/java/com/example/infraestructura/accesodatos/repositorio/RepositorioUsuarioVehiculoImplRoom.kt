package com.example.infraestructura.accesodatos.repositorio

import com.example.infraestructura.accesodatos.anticorrupcion.TraductorUsuarioVehiculo
import com.example.infraestructura.accesodatos.basededatos.BaseDatosUsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.repositorio.RepositorioUsuarioVehiculo

class RepositorioUsuarioVehiculoImplRoom(
    val baseDatosUsuarioVehiculo: BaseDatosUsuarioVehiculo,
) : RepositorioUsuarioVehiculo {

    override fun usuarioExiste(usuarioVehiculo: UsuarioVehiculo): Boolean {
        return true // todo
    }

    override suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traducctorUsuarioVehiculo = TraductorUsuarioVehiculo()
        val traduccionDelUsuario = traducctorUsuarioVehiculo.desdeDominioUnUsuarioVehiculo(usuarioVehiculo)

        baseDatosUsuarioVehiculo.usuarioVehiculoDao().insertarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traducctorUsuarioVehiculo = TraductorUsuarioVehiculo()
        val traduccionDelUsuario = traducctorUsuarioVehiculo.desdeDominioUnUsuarioVehiculo(usuarioVehiculo)

        baseDatosUsuarioVehiculo.usuarioVehiculoDao().borrarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun listaUsuarios(): List<UsuarioVehiculo> {
        return baseDatosUsuarioVehiculo.usuarioVehiculoDao().listaUsuariosVehiculo()
    }
}