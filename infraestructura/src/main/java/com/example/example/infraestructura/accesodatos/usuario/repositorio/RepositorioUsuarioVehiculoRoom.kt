package com.example.example.infraestructura.accesodatos.usuario.repositorio

import android.content.Context
import com.example.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.example.example.infraestructura.accesodatos.compartido.basededatos.BaseDatos
import com.example.example.infraestructura.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculo
import com.example.usuario.modelo.UsuarioVehiculo
import com.example.usuario.repositorio.RepositorioUsuarioVehiculo

class RepositorioUsuarioVehiculoRoom(contexto: Context) : RepositorioUsuarioVehiculo {

    private val usuarioVehiculoDao = BaseDatos(contexto).obtenerInstancia().usuarioVehiculoDao()
    private val traductorUsuarioVehiculo = TraductorUsuarioVehiculo()

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
        if (usuarioVehiculoDao.comprobacionUsuarioExiste(usuarioVehiculo.placaVehiculo)) {
            val usuarioBorrar = usuarioVehiculoDao.buscarUsuario(usuarioVehiculo.placaVehiculo)
            usuarioVehiculoDao.borrar(usuarioBorrar)
        } else {
            throw UsuarioNoExisteExcepcion()
        }
    }
}
