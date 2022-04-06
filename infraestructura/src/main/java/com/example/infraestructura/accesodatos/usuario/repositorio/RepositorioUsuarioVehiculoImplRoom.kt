package com.example.infraestructura.accesodatos.usuario.repositorio

import com.example.infraestructura.accesodatos.compartido.basededatos.BaseDatosUsuarioVehiculo
import com.example.infraestructura.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculoCarro
import com.example.infraestructura.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculoMoto
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro
import com.usuario.modelo.UsuarioVehiculoMoto
import com.usuario.repositorio.RepositorioUsuarioVehiculo

class RepositorioUsuarioVehiculoImplRoom(
    val baseDatosUsuarioVehiculo: BaseDatosUsuarioVehiculo,
) : RepositorioUsuarioVehiculo {

    private val traductorUsuarioVehiculoCarro = TraductorUsuarioVehiculoCarro()
    private val traductorUsuarioVehiculoMoto = TraductorUsuarioVehiculoMoto()

    override suspend fun usuarioExiste(usuarioVehiculo: UsuarioVehiculo): Boolean {
        var existe = false
        when (usuarioVehiculo) {
            is UsuarioVehiculoCarro -> {
                val traduccionDelUsuario = traductorUsuarioVehiculoCarro.desdeDominioUnUsuario(usuarioVehiculo)
                existe = baseDatosUsuarioVehiculo.usuarioVehiculoCarroDao().usuarioExiste(traduccionDelUsuario.placaVehiculo)
            }
            is UsuarioVehiculoMoto -> {
                val traduccionDelUsuario = traductorUsuarioVehiculoMoto.desdeDominioUnUsuario(usuarioVehiculo)
                existe = baseDatosUsuarioVehiculo.usuarioVehiculoMotoDao().usuarioExiste(traduccionDelUsuario.placaVehiculo)
            }

        }
        return existe

    }

    override suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traduccionDelUsuario = traductorUsuarioVehiculoCarro.desdeDominioUnUsuario(usuarioVehiculo)

        baseDatosUsuarioVehiculo.usuarioVehiculoCarroDao().insertarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traduccionDelUsuario = traductorUsuarioVehiculoCarro.desdeDominioUnUsuario(usuarioVehiculo)

        baseDatosUsuarioVehiculo.usuarioVehiculoCarroDao().borrarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun listaUsuarios(): List<UsuarioVehiculo> {
        val result = arrayListOf<UsuarioVehiculo>()
        baseDatosUsuarioVehiculo.usuarioVehiculoCarroDao().listaUsuariosVehiculo().forEach { vehiculo ->
            result.add(traductorUsuarioVehiculoCarro.desdeUnUsuarioCarroADominio(vehiculo))
        }
        return result

    }


}