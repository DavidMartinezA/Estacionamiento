package com.example.infraestructura.accesodatos.usuario.repositorio

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.example.infraestructura.accesodatos.compartido.basededatos.BaseDatosUsuarioVehiculo
import com.example.infraestructura.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculoCarro
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.repositorio.RepositorioUsuarioVehiculo

class RepositorioUsuarioVehiculoCarroRoom(
) : RepositorioUsuarioVehiculo {

    val context = ApplicationProvider.getApplicationContext<Context>()
    val entidadBaseDatos = Room.databaseBuilder(
        context, BaseDatosUsuarioVehiculo::class.java,
        "baseDatos")
        .build()
    val usuarioVehiculoDao = entidadBaseDatos.usuarioVehiculoDao()

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
