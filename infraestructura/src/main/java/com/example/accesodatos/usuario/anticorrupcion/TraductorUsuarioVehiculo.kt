package com.example.accesodatos.usuario.anticorrupcion

import com.example.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculo
import com.example.usuario.modelo.UsuarioVehiculo
import com.example.usuario.modelo.UsuarioVehiculoCarro
import com.example.usuario.modelo.UsuarioVehiculoMoto

class TraductorUsuarioVehiculo {

    fun desdeDominioABaseDatos(usuarioVehiculo: UsuarioVehiculo): EntidadDatosUsuarioVehiculo {
        return EntidadDatosUsuarioVehiculo(
            placaVehiculo = usuarioVehiculo.placaVehiculo,
            tipoDeVehiculo = usuarioVehiculo.tipoDeVehiculo,
            cilindrajeAlto = usuarioVehiculo.cilndrajeAlto,
            horaFechaIngresoUsuario = usuarioVehiculo.horaFechaIngresoUsuario)
    }

    fun desdeBaseDatosADominio(entidadDatosUsuarioVehiculo: EntidadDatosUsuarioVehiculo): UsuarioVehiculo {
        return when (entidadDatosUsuarioVehiculo.tipoDeVehiculo) {
            "Carro" -> {
                UsuarioVehiculoCarro(entidadDatosUsuarioVehiculo.placaVehiculo).also {
                    it.tipoDeVehiculo = entidadDatosUsuarioVehiculo.tipoDeVehiculo
                    it.horaFechaIngresoUsuario = entidadDatosUsuarioVehiculo.horaFechaIngresoUsuario
                }
            }
            else -> {
                UsuarioVehiculoMoto(entidadDatosUsuarioVehiculo.placaVehiculo, entidadDatosUsuarioVehiculo.cilindrajeAlto).also {
                    it.tipoDeVehiculo = entidadDatosUsuarioVehiculo.tipoDeVehiculo
                    it.horaFechaIngresoUsuario = entidadDatosUsuarioVehiculo.horaFechaIngresoUsuario
                }
            }
        }
    }

    fun listaDesdeBaseDatosADominio(listaEntidadBaseDatos: List<EntidadDatosUsuarioVehiculo>): List<UsuarioVehiculo> {

        val listaDominio = ArrayList<UsuarioVehiculo>()
        listaEntidadBaseDatos.map {
            val usuarioDominio = UsuarioVehiculoCarro(it.placaVehiculo)
            usuarioDominio.tipoDeVehiculo = it.tipoDeVehiculo
            usuarioDominio.cilndrajeAlto = it.cilindrajeAlto
            usuarioDominio.horaFechaIngresoUsuario = it.horaFechaIngresoUsuario
            listaDominio.add(usuarioDominio)
        }
        return listaDominio
    }

}