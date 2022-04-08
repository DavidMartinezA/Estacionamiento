package com.example.infraestructura.accesodatos.usuario.repositorio

import com.example.infraestructura.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculo
import com.example.infraestructura.accesodatos.usuario.dao.UsuarioVehiculoDao
import com.example.infraestructura.accesodatos.usuario.excepcion.TipoDeVehiculoExcepcion
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro
import com.usuario.modelo.UsuarioVehiculoMoto
import com.usuario.repositorio.RepositorioUsuarioVehiculo

class RepositorioUsuarioVehiculoImplRoom(
    val usuarioVehiculoDao: UsuarioVehiculoDao,
) : RepositorioUsuarioVehiculo {

    private val traductorUsuarioVehiculo = TraductorUsuarioVehiculo()

    override suspend fun usuarioExiste(usuarioVehiculo: UsuarioVehiculo): Boolean {
        return usuarioVehiculoDao.comprobacionUsuarioExiste(usuarioVehiculo.placaVehiculo)
    }

    override suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traduccionDelUsuario = (usuarioVehiculo as? UsuarioVehiculoCarro)?.let {
            traductorUsuarioVehiculo.desdeDominioUnUsuario(it)
        } ?: (usuarioVehiculo as? UsuarioVehiculoMoto)?.let {
            traductorUsuarioVehiculo.desdeDominioUnUsuario(it)
        }

        traduccionDelUsuario?.let {
            usuarioVehiculoDao.insertar(it)
        } ?: throw TipoDeVehiculoExcepcion()
    }

    override suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val traduccionDelUsuario = (usuarioVehiculo as? UsuarioVehiculoCarro)?.let {
            traductorUsuarioVehiculo.desdeDominioUnUsuario(usuarioVehiculo)
        } ?: (usuarioVehiculo as? UsuarioVehiculoMoto)?.let {
            traductorUsuarioVehiculo.desdeDominioUnUsuario(usuarioVehiculo)
        }

        traduccionDelUsuario?.let {
            usuarioVehiculoDao.borrar(it)
        } ?: throw TipoDeVehiculoExcepcion()

        /*
         when (usuarioVehiculo) {
          is UsuarioVehiculoMoto -> {
              traductorUsuarioVehiculo.desdeDominioUnUsuario(usuarioVehiculo)

          }
          is UsuarioVehiculoCarro -> {
              traductorUsuarioVehiculo.desdeDominioUnUsuario(usuarioVehiculo)

          }
          else -> {
              throw Exception("no es de ningun tipo")
          }
      }
      */

    }

    override suspend fun listaUsuarios(): List<UsuarioVehiculo> {
        val result = arrayListOf<UsuarioVehiculo>()
        usuarioVehiculoDao.listaUsuarios().forEach { vehiculo ->
            if (vehiculo.tipoDeVehiculo == "Carro") {
                result.add(traductorUsuarioVehiculo.desdeUnUsuarioCarroADominio(vehiculo))
            } else {
                result.add(traductorUsuarioVehiculo.desdeUnUsuarioMotoADominio(vehiculo))
            }
        }
        return result
    }
}
