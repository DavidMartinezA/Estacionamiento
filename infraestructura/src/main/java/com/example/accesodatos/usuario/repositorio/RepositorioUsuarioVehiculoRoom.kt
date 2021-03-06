package com.example.accesodatos.usuario.repositorio

import com.example.accesodatos.compartido.basededatos.BaseDatosUsuarioVehiculo
import com.example.accesodatos.usuario.anticorrupcion.TraductorUsuarioVehiculo
import com.example.estacionamiento.excepcion.UsuarioNoExisteExcepcion
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
        return traductorUsuarioVehiculo.desdeBaseDatosADominio(usuarioVehiculoDao.usuarioPorPlaca(placa))
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
            val usuarioBorrar = usuarioVehiculoDao.usuarioPorPlaca(placa)
            usuarioVehiculoDao.borrar(usuarioBorrar)
        } else {
            throw UsuarioNoExisteExcepcion()
        }
    }
}