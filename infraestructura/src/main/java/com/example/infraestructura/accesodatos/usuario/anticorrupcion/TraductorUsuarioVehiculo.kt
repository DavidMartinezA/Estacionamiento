package com.example.infraestructura.accesodatos.usuario.anticorrupcion

import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro
import com.usuario.modelo.UsuarioVehiculoMoto

class TraductorUsuarioVehiculo {

    fun desdeDominioUnUsuario(usuarioVehiculo: UsuarioVehiculoCarro): EntidadDatosUsuarioVehiculo {
        return EntidadDatosUsuarioVehiculo(
            placaVehiculo = usuarioVehiculo.placaVehiculo,
            tipoDeVehiculo = usuarioVehiculo.tipoDeVehiculo,
            altoCilindraje = false,
            horaFechaIngresoUsuario = usuarioVehiculo.horaFechaIngresoUsuario)
    }

    fun desdeDominioUnUsuario(usuarioVehiculo: UsuarioVehiculoMoto): EntidadDatosUsuarioVehiculo {
        return EntidadDatosUsuarioVehiculo(
            placaVehiculo = usuarioVehiculo.placaVehiculo,
            tipoDeVehiculo = usuarioVehiculo.tipoDeVehiculo,
            altoCilindraje = usuarioVehiculo.cilindrajeAlto,
            horaFechaIngresoUsuario = usuarioVehiculo.horaFechaIngresoUsuario)
    }

    fun desdeUnUsuarioCarroADominio(usuarioVehiculo: EntidadDatosUsuarioVehiculo): UsuarioVehiculoCarro {
        return UsuarioVehiculoCarro(usuarioVehiculo.placaVehiculo).also {
            it.horaFechaIngresoUsuario = usuarioVehiculo.horaFechaIngresoUsuario
        }
    }

    fun desdeUnUsuarioMotoADominio(usuarioVehiculo: EntidadDatosUsuarioVehiculo): UsuarioVehiculoMoto {
        return UsuarioVehiculoMoto(usuarioVehiculo.placaVehiculo, usuarioVehiculo.altoCilindraje).also {
            it.horaFechaIngresoUsuario = usuarioVehiculo.horaFechaIngresoUsuario
        }
    }
}