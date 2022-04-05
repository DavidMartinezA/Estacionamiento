package com.example.infraestructura.accesodatos.usuario.anticorrupcion

import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculoCarro
import com.usuario.modelo.UsuarioVehiculoCarro


class TraductorUsuarioVehiculoCarro {

    fun desdeDominioUnUsuarioVehiculoCarro(usuarioVehiculo: UsuarioVehiculoCarro): EntidadDatosUsuarioVehiculoCarro {
        return EntidadDatosUsuarioVehiculoCarro(usuarioVehiculo.placaVehiculo, usuarioVehiculo.horaFechaIngresoUsuario,
            usuarioVehiculo.tipoDeVehiculo)
    }

    fun desdeUnUsuarioCarroADominio(usuarioVehiculoCarro: EntidadDatosUsuarioVehiculoCarro): UsuarioVehiculoCarro {
        return UsuarioVehiculoCarro(usuarioVehiculoCarro.placaVehiculo).also {
            it.horaFechaIngresoUsuario = usuarioVehiculoCarro.horaFechaIngresoUsuario
        }
    }
}