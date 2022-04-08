package com.example.infraestructura.accesodatos.usuario.anticorrupcion

import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro

class TraductorUsuarioVehiculoCarro {

    fun desdeDominioABaseDatos(usuarioVehiculo: UsuarioVehiculo): EntidadDatosUsuarioVehiculo {
        return EntidadDatosUsuarioVehiculo(
            placaVehiculo = usuarioVehiculo.placaVehiculo,
            tipoDeVehiculo = usuarioVehiculo.tipoDeVehiculo,
            cilindrajeAlto = false,
            horaFechaIngresoUsuario = usuarioVehiculo.horaFechaIngresoUsuario)
    }

    fun desdeBaseDatosADominio(usuarioVehiculo: EntidadDatosUsuarioVehiculo): UsuarioVehiculoCarro {
        return UsuarioVehiculoCarro(usuarioVehiculo.placaVehiculo).also {
            it.horaFechaIngresoUsuario = usuarioVehiculo.horaFechaIngresoUsuario
        }
    }

    fun listaDesdeBaseDatosADominio(listaEntidadBaseDatos: List<EntidadDatosUsuarioVehiculo>): List<UsuarioVehiculo> {

        val listaDominio = ArrayList<UsuarioVehiculo>()
        listaEntidadBaseDatos.map {
            val usuarioDominio = UsuarioVehiculoCarro(it.placaVehiculo)
            usuarioDominio.horaFechaIngresoUsuario = it.horaFechaIngresoUsuario
            listaDominio.add(usuarioDominio)
        }
        return listaDominio
    }

}