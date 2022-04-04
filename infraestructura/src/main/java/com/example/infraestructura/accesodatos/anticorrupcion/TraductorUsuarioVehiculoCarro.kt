package com.example.infraestructura.accesodatos.anticorrupcion

import com.example.infraestructura.accesodatos.entidadbasedatos.EntidadDatosUsuarioCarro
import com.usuario.modelo.UsuarioVehiculoCarro


class TraductorUsuarioVehiculoCarro {

    fun desdeDominioUnUsuarioCarro(usuarioVehiculoCarro: UsuarioVehiculoCarro): EntidadDatosUsuarioCarro {
        return EntidadDatosUsuarioCarro(usuarioVehiculoCarro.placaVehiculo, usuarioVehiculoCarro.horaFechaIngresoUsuario)
    }

    fun desdeUnUsuarioCarroADominio(usuarioVehiculoCarro: EntidadDatosUsuarioCarro): UsuarioVehiculoCarro {
        return UsuarioVehiculoCarro(usuarioVehiculoCarro.placaVehiculo).also {
            it.horaFechaIngresoUsuario = usuarioVehiculoCarro.horaFechaIngresoUsuario
        }
    }
}