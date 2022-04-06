package com.example.infraestructura.accesodatos.usuario.anticorrupcion

import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioCarro
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro

class TraductorUsuarioVehiculoCarro {

    fun desdeDominioUnUsuario(usuarioVehiculo: UsuarioVehiculo): EntidadDatosUsuarioCarro {
        return EntidadDatosUsuarioCarro(usuarioVehiculo.placaVehiculo, usuarioVehiculo.horaFechaIngresoUsuario
        )
    }

    fun desdeUnUsuarioCarroADominio(usuarioVehiculo: EntidadDatosUsuarioCarro): UsuarioVehiculo {
        return UsuarioVehiculoCarro(usuarioVehiculo.placaVehiculo).also {
            it.horaFechaIngresoUsuario = usuarioVehiculo.horaFechaIngresoUsuario
        }
    }
}