package com.example.example.infraestructura.accesodatos.usuario.repositorio

import com.example.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.example.example.infraestructura.accesodatos.compartido.basededatos.BaseDatosUsuarioVehiculo
import com.example.example.infraestructura.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculo
import com.example.usuario.modelo.UsuarioVehiculo
import com.example.usuario.repositorio.RepositorioUsuarioVehiculo
import javax.inject.Inject

class RepositorioUsuarioVehiculoRoom @Inject constructor(baseDatosUsuarioVehiculo: BaseDatosUsuarioVehiculo) :
    RepositorioUsuarioVehiculo {

    private val usuarioVehiculoDao = baseDatosUsuarioVehiculo.usuarioVehiculoDao()
    private val traductorUsuarioVehiculo = TraductorUsuarioVehiculo()

    override suspend fun listaUsuarios(): List<UsuarioVehiculo> {
        return traductorUsuarioVehiculo.listaDesdeBaseDatosADominio(usuarioVehiculoDao.listaUsuarios())
    }

    override suspend fun usuarioPorPlaca(placa: String): UsuarioVehiculo {
        if (usuarioVehiculoDao.comprobacionUsuarioExiste(placa)) {
            return traductorUsuarioVehiculo.desdeBaseDatosADominio(usuarioVehiculoDao.buscarUsuario(placa))
        } else {
            throw UsuarioNoExisteExcepcion()
        }
    }

    override suspend fun usuarioExiste(placa: String): Boolean {
        return usuarioVehiculoDao.comprobacionUsuarioExiste(placa)
    }

    override suspend fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo) {
        val entidad = traductorUsuarioVehiculo.desdeDominioABaseDatos(usuarioVehiculo)
        usuarioVehiculoDao.insertar(entidad)
    }

    override suspend fun eliminarUsuario(placa: String) {
        if (usuarioVehiculoDao.comprobacionUsuarioExiste(placa)) {
            val usuarioBorrar = usuarioVehiculoDao.buscarUsuario(placa)
            usuarioVehiculoDao.borrar(usuarioBorrar)
        } else {
            throw UsuarioNoExisteExcepcion()
        }
    }
}