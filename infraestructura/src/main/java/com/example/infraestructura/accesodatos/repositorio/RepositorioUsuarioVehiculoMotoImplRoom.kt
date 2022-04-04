package com.example.infraestructura.accesodatos.repositorio

import com.example.infraestructura.accesodatos.anticorrupcion.TraductorUsuarioVehiculoMoto
import com.example.infraestructura.accesodatos.basededatos.BaseDatosUsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoMoto
import com.usuario.repositorio.RepositorioUsuarioVehiculoMoto

class RepositorioUsuarioVehiculoMotoImplRoom(
    val baseDatosUsuarioVehiculo: BaseDatosUsuarioVehiculo,
) : RepositorioUsuarioVehiculoMoto {

    private val traductorUsuarioVehiculo = TraductorUsuarioVehiculoMoto()

    override fun usuarioExiste(usuarioVehiculo: UsuarioVehiculoMoto): Boolean {
        return true // todo
    }

    override suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculoMoto) {
        val traduccionDelUsuario = traductorUsuarioVehiculo.desdeDominioUnUsuarioMoto(usuarioVehiculo)

        baseDatosUsuarioVehiculo.usuarioMotoDao().insertarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculoMoto) {
        val traducctorUsuarioVehiculo = TraductorUsuarioVehiculoMoto()
        val traduccionDelUsuario = traducctorUsuarioVehiculo.desdeDominioUnUsuarioMoto(usuarioVehiculo)

        baseDatosUsuarioVehiculo.usuarioMotoDao().borrarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun listaUsuarios(): List<UsuarioVehiculoMoto> {
        val result = arrayListOf<UsuarioVehiculoMoto>()
        baseDatosUsuarioVehiculo.usuarioMotoDao().listaUsuariosVehiculo().forEach { moto ->
            result.add(traductorUsuarioVehiculo.desdeUnUsuarioMotoADominio(moto))
        }
        return result
    }
}