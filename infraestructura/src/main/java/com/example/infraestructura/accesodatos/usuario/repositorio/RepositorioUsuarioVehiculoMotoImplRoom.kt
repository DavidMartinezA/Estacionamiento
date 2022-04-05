package com.example.infraestructura.accesodatos.usuario.repositorio

import com.example.infraestructura.accesodatos.compartido.basededatos.BaseDatosUsuarioVehiculo
import com.example.infraestructura.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculoMoto
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoMoto
import com.usuario.repositorio.RepositorioUsuarioVehiculo

class RepositorioUsuarioVehiculoMotoImplRoom(
    val baseDatosUsuarioVehiculo: BaseDatosUsuarioVehiculo,
) : RepositorioUsuarioVehiculo {

    private val traductorUsuarioVehiculo = TraductorUsuarioVehiculoMoto()

    override suspend fun usuarioExiste(usuarioVehiculo: UsuarioVehiculo): Boolean {
        val traduccionDelUsuario = traductorUsuarioVehiculo.desdeDominioUnUsuarioVehiculoMoto(usuarioVehiculo as UsuarioVehiculoMoto)
        return baseDatosUsuarioVehiculo.usuarioVehiculoMotoDao().usuarioExiste(traduccionDelUsuario.placaVehiculo)
    }

    override suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traduccionDelUsuario = traductorUsuarioVehiculo.desdeDominioUnUsuarioVehiculoMoto(usuarioVehiculo as UsuarioVehiculoMoto)

        baseDatosUsuarioVehiculo.usuarioVehiculoMotoDao().insertarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traduccionDelUsuario = traductorUsuarioVehiculo.desdeDominioUnUsuarioVehiculoMoto(usuarioVehiculo as UsuarioVehiculoMoto)

        baseDatosUsuarioVehiculo.usuarioVehiculoMotoDao().borrarUsuarioVehiculo(traduccionDelUsuario)
    }

    override suspend fun listaUsuarios(): List<UsuarioVehiculo> {
        val resultado = arrayListOf<UsuarioVehiculo>()
        baseDatosUsuarioVehiculo.usuarioVehiculoMotoDao().listaUsuariosVehiculo().forEach { moto ->
            resultado.add(traductorUsuarioVehiculo.desdeUnUsuarioMotoADominio(moto))
        }
        return resultado
    }

}