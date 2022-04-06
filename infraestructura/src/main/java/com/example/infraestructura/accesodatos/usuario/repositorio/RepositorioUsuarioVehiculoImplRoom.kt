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
        val traduccionDelUsuario = traductorUsuarioVehiculo.desdeDominioUnUsuario(usuarioVehiculo)
        return baseDatosUsuarioVehiculo.usuarioVehiculoDao().usuarioExiste(traduccionDelUsuario.placaVehiculo)
    }

    override suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traduccionDelUsuario = traductorUsuarioVehiculo.desdeDominioUnUsuario(usuarioVehiculo)

        baseDatosUsuarioVehiculo.usuarioVehiculoDao().insertarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traduccionDelUsuario = traductorUsuarioVehiculo.desdeDominioUnUsuario(usuarioVehiculo)

        baseDatosUsuarioVehiculo.usuarioVehiculoDao().borrarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun listaUsuarios(): List<UsuarioVehiculo> {
        val result = arrayListOf<UsuarioVehiculo>()
        baseDatosUsuarioVehiculo.usuarioVehiculoDao().listaUsuariosVehiculo().forEach { vehiculo ->
            result.add(traductorUsuarioVehiculo.desdeUnUsuarioCarroADominio(vehiculo))
        }
        return result

    }


}