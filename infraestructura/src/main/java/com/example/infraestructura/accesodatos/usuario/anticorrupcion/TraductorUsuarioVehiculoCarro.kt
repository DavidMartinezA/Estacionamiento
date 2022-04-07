package com.example.infraestructura.accesodatos.usuario.anticorrupcion

import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculoCarro
import com.usuario.modelo.UsuarioVehiculoCarro

class TraductorUsuarioVehiculoCarro {

    fun desdeDominioUnUsuarioCarro(usuarioVehiculo: UsuarioVehiculoCarro): EntidadDatosUsuarioVehiculoCarro {
        return EntidadDatosUsuarioVehiculoCarro(0,
            placaVehiculo = usuarioVehiculo.placaVehiculo,
            tipoDeVehiculo = usuarioVehiculo.tipoDeVehiculo,
            horaFechaIngresoUsuario = usuarioVehiculo.horaFechaIngresoUsuario)
    }

    fun desdeUnUsuarioCarroADominio(usuarioVehiculoCarro: EntidadDatosUsuarioVehiculoCarro): UsuarioVehiculoCarro {
        return UsuarioVehiculoCarro(usuarioVehiculoCarro.placaVehiculo).also {
            it.horaFechaIngresoUsuario = usuarioVehiculoCarro.horaFechaIngresoUsuario
        }
    }

}