package com.example.infraestructura.accesodatos.usuario.repositorio

import com.example.infraestructura.accesodatos.compartido.basededatos.BaseDatosUsuarioVehiculo
import com.example.infraestructura.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculoCarro
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro
import com.usuario.repositorio.RepositorioUsuarioVehiculo

class RepositorioUsuarioVehiculoCarroImplRoom(
    val baseDatosUsuarioVehiculo: BaseDatosUsuarioVehiculo,
) : RepositorioUsuarioVehiculo {

    private val traductorUsuarioVehiculo = TraductorUsuarioVehiculoCarro()

    override suspend fun usuarioExiste(usuarioVehiculo: UsuarioVehiculo): Boolean {
        return baseDatosUsuarioVehiculo.usuarioVehiculoCarroDao().usuarioExiste(usuarioVehiculo.placaVehiculo)
    }

    override suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traduccionDelUsuario = traductorUsuarioVehiculo.desdeDominioUnUsuarioCarro(usuarioVehiculo as UsuarioVehiculoCarro)

        baseDatosUsuarioVehiculo.usuarioVehiculoCarroDao().insertarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traduccionDelUsuario = traductorUsuarioVehiculo.desdeDominioUnUsuarioCarro(usuarioVehiculo as UsuarioVehiculoCarro)

        baseDatosUsuarioVehiculo.usuarioVehiculoCarroDao().borrarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun listaUsuarios(): List<UsuarioVehiculoCarro> {
        val result = arrayListOf<UsuarioVehiculoCarro>()
        baseDatosUsuarioVehiculo.usuarioVehiculoCarroDao().listaUsuariosVehiculo().forEach { carro ->
            result.add(traductorUsuarioVehiculo.desdeUnUsuarioCarroADominio(carro))
        }
        return result
    }
}
