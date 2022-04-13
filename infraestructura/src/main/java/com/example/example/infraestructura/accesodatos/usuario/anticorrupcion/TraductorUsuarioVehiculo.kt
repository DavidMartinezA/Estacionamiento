package com.example.example.infraestructura.accesodatos.usuario.anticorrupcion

import com.example.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculo
import com.example.usuario.modelo.UsuarioVehiculo
import com.example.usuario.modelo.UsuarioVehiculoCarro

class TraductorUsuarioVehiculo {

    fun desdeDominioABaseDatos(usuarioVehiculo: UsuarioVehiculo): EntidadDatosUsuarioVehiculo {
        return EntidadDatosUsuarioVehiculo(
            placaVehiculo = usuarioVehiculo.placaVehiculo,
            tipoDeVehiculo = usuarioVehiculo.tipoDeVehiculo,
            cilindrajeAlto = usuarioVehiculo.cilndrajeAlto,
            horaFechaIngresoUsuario = usuarioVehiculo.horaFechaIngresoUsuario)
    }

    fun listaDesdeBaseDatosADominio(listaEntidadBaseDatos: List<EntidadDatosUsuarioVehiculo>): List<UsuarioVehiculo> {

        val listaDominio = ArrayList<UsuarioVehiculo>()
        listaEntidadBaseDatos.map {
            val usuarioDominio = UsuarioVehiculoCarro(it.placaVehiculo)
            usuarioDominio.horaFechaIngresoUsuario = it.horaFechaIngresoUsuario
            usuarioDominio.cilndrajeAlto = it.cilindrajeAlto
            listaDominio.add(usuarioDominio)
        }
        return listaDominio
    }

}