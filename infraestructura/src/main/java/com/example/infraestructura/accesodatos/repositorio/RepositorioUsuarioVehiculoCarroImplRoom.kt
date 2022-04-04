package com.example.infraestructura.accesodatos.repositorio

import com.example.infraestructura.accesodatos.anticorrupcion.TraductorUsuarioVehiculoCarro
import com.example.infraestructura.accesodatos.basededatos.BaseDatosUsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro
import com.usuario.repositorio.RepositorioUsuarioVehiculoCarro

class RepositorioUsuarioVehiculoCarroImplRoom(
    val baseDatosUsuarioVehiculo: BaseDatosUsuarioVehiculo,
) : RepositorioUsuarioVehiculoCarro {

    private val traductorUsuarioVehiculo = TraductorUsuarioVehiculoCarro()

    override fun usuarioExiste(usuarioVehiculo: UsuarioVehiculoCarro): Boolean {
        return true // todo
    }

    override suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculoCarro) {
        val traduccionDelUsuario = traductorUsuarioVehiculo.desdeDominioUnUsuarioCarro(usuarioVehiculo)

        baseDatosUsuarioVehiculo.usuarioCarroDao().insertarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculoCarro) {
        val traduccionDelUsuario = traductorUsuarioVehiculo.desdeDominioUnUsuarioCarro(usuarioVehiculo)

        baseDatosUsuarioVehiculo.usuarioCarroDao().borrarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun listaUsuarios(): List<UsuarioVehiculoCarro> {
        val result = arrayListOf<UsuarioVehiculoCarro>()
        baseDatosUsuarioVehiculo.usuarioCarroDao().listaUsuariosVehiculo().forEach { carro ->
            result.add(traductorUsuarioVehiculo.desdeUnUsuarioCarroADominio(carro))
        }
        return result
    }
}