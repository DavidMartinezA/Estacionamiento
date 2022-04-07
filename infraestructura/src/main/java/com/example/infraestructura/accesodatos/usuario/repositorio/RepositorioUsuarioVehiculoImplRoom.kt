package com.example.infraestructura.accesodatos.usuario.repositorio

import com.example.infraestructura.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculo
import com.example.infraestructura.accesodatos.usuario.dao.UsuarioVehiculoDao
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro
import com.usuario.modelo.UsuarioVehiculoMoto
import com.usuario.repositorio.RepositorioUsuarioVehiculo

class RepositorioUsuarioVehiculoImplRoom(
    val usuarioVehiculoDao: UsuarioVehiculoDao,
) : RepositorioUsuarioVehiculo {

    private val traductorUsuarioVehiculo = TraductorUsuarioVehiculo()

    override suspend fun usuarioExiste(usuarioVehiculo: UsuarioVehiculo): Boolean {
        return usuarioVehiculoDao.usuarioExiste(usuarioVehiculo.placaVehiculo)
    }

    override suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traduccionDelUsuario = (usuarioVehiculo as? UsuarioVehiculoCarro)?.let {
            traductorUsuarioVehiculo.desdeDominioUnUsuario(it)
        } ?: (usuarioVehiculo as? UsuarioVehiculoMoto)?.let {
            traductorUsuarioVehiculo.desdeDominioUnUsuario(it)
        }

        traduccionDelUsuario?.let {
            usuarioVehiculoDao.insertarUsuarioVehiculo(it)
        }
    }

    override suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traduccionDelUsuario = (usuarioVehiculo as? UsuarioVehiculoCarro)?.let {
            traductorUsuarioVehiculo.desdeDominioUnUsuario(usuarioVehiculo)
        } ?: (usuarioVehiculo as? UsuarioVehiculoMoto)?.let {
            traductorUsuarioVehiculo.desdeDominioUnUsuario(usuarioVehiculo)
        }

        traduccionDelUsuario?.let {
            usuarioVehiculoDao.borrarUsuarioVehiculo(it)
        }
    }

    override suspend fun listaUsuarios(): List<UsuarioVehiculo> {
        val result = arrayListOf<UsuarioVehiculo>()
        usuarioVehiculoDao.listaUsuariosVehiculo().forEach { vehiculo ->
            if (vehiculo.tipoDeVehiculo == "Carro") {
                result.add(traductorUsuarioVehiculo.desdeUnUsuarioCarroADominio(vehiculo))
            } else {
                result.add(traductorUsuarioVehiculo.desdeUnUsuarioMotoADominio(vehiculo))
            }
        }
        return result
    }
}
