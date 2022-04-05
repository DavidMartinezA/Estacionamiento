package com.example.infraestructura.accesodatos.usuario.repositorio

import com.example.infraestructura.accesodatos.compartido.basededatos.BaseDatosUsuarioVehiculo
import com.example.infraestructura.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.repositorio.RepositorioUsuarioVehiculo

class RepositorioUsuarioVehiculoImplRoom(
    val baseDatosUsuarioVehiculo: BaseDatosUsuarioVehiculo,
) : RepositorioUsuarioVehiculo {

    private val traductorUsuarioVehiculo = TraductorUsuarioVehiculo()

    override suspend fun usuarioExiste(usuarioVehiculo: UsuarioVehiculo): Boolean {
        val traduccionDelUsuario = traductorUsuarioVehiculo.desdeDominioUnUsuarioVehiculo(usuarioVehiculo)
        return baseDatosUsuarioVehiculo.usuarioVehiculoDao().usuarioExiste(traduccionDelUsuario.placaVehiculo)
    }

    override suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traduccionDelUsuario = traductorUsuarioVehiculo.desdeDominioUnUsuarioVehiculo(usuarioVehiculo)

        baseDatosUsuarioVehiculo.usuarioVehiculoDao().insertarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traduccionDelUsuario = traductorUsuarioVehiculo.desdeDominioUnUsuarioVehiculo(usuarioVehiculo)

        baseDatosUsuarioVehiculo.usuarioVehiculoDao().borrarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun listaUsuarios(): List<UsuarioVehiculo> {
        return baseDatosUsuarioVehiculo.usuarioVehiculoDao().listaUsuarios()

    }

    override suspend fun listaUsuariosMotos(): List<UsuarioVehiculo> {
        return baseDatosUsuarioVehiculo.usuarioVehiculoDao().listaUsuariosVehiculoMotos()
    }

    override suspend fun listaUsuariosCarros(): List<UsuarioVehiculo> {
        return baseDatosUsuarioVehiculo.usuarioVehiculoDao().listaUsuariosVehiculoCarros()
    }

}