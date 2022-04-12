package com.example.example.infraestructura.accesodatos.usuario.repositorio

import android.content.Context
import androidx.room.Room
import com.example.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.example.example.infraestructura.accesodatos.compartido.basededatos.BaseDatosUsuarioVehiculo
import com.example.example.infraestructura.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculoCarro
import com.example.usuario.modelo.UsuarioVehiculo
import com.example.usuario.repositorio.RepositorioUsuarioVehiculo

class RepositorioUsuarioVehiculoCarroRoom(contexto: Context) : RepositorioUsuarioVehiculo {
    val baseDatosUsuario = Room.databaseBuilder(
        contexto, BaseDatosUsuarioVehiculo::class.java,
        "baseDatos")
        .build()

    val usuarioVehiculoDao = baseDatosUsuario.usuarioVehiculoDao()

    private val traductorUsuarioVehiculo = TraductorUsuarioVehiculoCarro()

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
