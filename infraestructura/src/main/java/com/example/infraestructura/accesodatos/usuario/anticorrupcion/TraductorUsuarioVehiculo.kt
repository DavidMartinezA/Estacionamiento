package com.example.infraestructura.accesodatos.usuario.anticorrupcion

import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioEstacionamiento
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro

class TraductorUsuarioVehiculo {

    fun desdeDominioUnUsuario(usuarioVehiculo: UsuarioVehiculo): EntidadDatosUsuarioEstacionamiento {
        return EntidadDatosUsuarioEstacionamiento(usuarioVehiculo.placaVehiculo, usuarioVehiculo.horaFechaIngresoUsuario,
            usuarioVehiculo.tipoDeVehiculo)
    }

    fun desdeUnUsuarioCarroADominio(usuarioVehiculo: EntidadDatosUsuarioEstacionamiento): UsuarioVehiculo {
        return UsuarioVehiculoCarro(usuarioVehiculo.placaVehiculo).also {
            it.horaFechaIngresoUsuario = usuarioVehiculo.horaFechaIngresoUsuario
        }
    }
}