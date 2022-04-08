package com.example.infraestructura.accesodatos.usuario.repositorio

import com.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.example.infraestructura.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculoMoto
import com.example.infraestructura.accesodatos.usuario.dao.UsuarioVehiculoDao
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.repositorio.RepositorioUsuarioVehiculo

class RepositorioUsuarioVehiculoMotoRoom(
    val usuarioVehiculoDao: UsuarioVehiculoDao,
) : RepositorioUsuarioVehiculo {


    private val traductorUsuarioVehiculo = TraductorUsuarioVehiculoMoto()

    override suspend fun listaUsuarios(): List<UsuarioVehiculo> {
        return traductorUsuarioVehiculo.listaDesdeBaseDatosADominio(usuarioVehiculoDao.listaUsuarios())
    }

    override suspend fun usuarioExiste(usuarioVehiculo: UsuarioVehiculo): Boolean {
        return usuarioVehiculoDao.comprobacionUsuarioExiste(usuarioVehiculo.placaVehiculo)
    }

    override suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo) {

        val entidad = traductorUsuarioVehiculo.desdeDominioABaseDatos(usuarioVehiculo)
        usuarioVehiculoDao.insertar(entidad)

    }

    override suspend fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculo) {

        val entidad = traductorUsuarioVehiculo.desdeDominioABaseDatos(usuarioVehiculo)
        if (usuarioVehiculoDao.comprobacionUsuarioExiste(usuarioVehiculo.placaVehiculo)) {
            usuarioVehiculoDao.borrar(entidad)
        } else {
            throw UsuarioNoExisteExcepcion()
        }
    }

}
