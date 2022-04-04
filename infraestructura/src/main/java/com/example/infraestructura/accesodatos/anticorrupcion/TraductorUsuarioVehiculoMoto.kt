package com.example.infraestructura.accesodatos.anticorrupcion

import com.example.infraestructura.accesodatos.entidadbasedatos.EntidadDatosUsuarioMoto
import com.usuario.modelo.UsuarioVehiculoMoto


class TraductorUsuarioVehiculoMoto {

    fun desdeDominioUnUsuarioMoto(usuarioVehiculoMoto: UsuarioVehiculoMoto): EntidadDatosUsuarioMoto {
        return EntidadDatosUsuarioMoto(
            usuarioVehiculoMoto.placaVehiculo,
            usuarioVehiculoMoto.horaFechaIngresoUsuario,
            usuarioVehiculoMoto.cilindrajeAlto
        )
    }

    fun desdeUnUsuarioMotoADominio(usuarioVehiculoMoto: EntidadDatosUsuarioMoto): UsuarioVehiculoMoto {
        return UsuarioVehiculoMoto(
            usuarioVehiculoMoto.placaVehiculo,
            usuarioVehiculoMoto.altoCilindraje
        ).also {
            it.horaFechaIngresoUsuario = usuarioVehiculoMoto.horaFechaIngresoUsuario
        }
    }
}