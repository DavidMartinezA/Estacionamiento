package com.example.infraestructura.accesodatos.usuario.anticorrupcion

import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoMoto

class TraductorUsuarioVehiculoMoto {


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
            val usuarioDominio = UsuarioVehiculoMoto(it.placaVehiculo)
            usuarioDominio.horaFechaIngresoUsuario = it.horaFechaIngresoUsuario
            usuarioDominio.cilindrajeAlto = it.cilindrajeAlto
            listaDominio.add(usuarioDominio)
        }
        return listaDominio
    }
}