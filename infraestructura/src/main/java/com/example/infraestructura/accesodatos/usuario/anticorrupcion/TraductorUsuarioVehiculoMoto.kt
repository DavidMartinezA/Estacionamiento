package com.example.infraestructura.accesodatos.usuario.anticorrupcion

import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculoMoto
import com.usuario.modelo.UsuarioVehiculoMoto


class TraductorUsuarioVehiculoMoto {

    fun desdeDominioUnUsuarioVehiculoMoto(usuarioVehiculo: UsuarioVehiculoMoto): EntidadDatosUsuarioVehiculoMoto {
        return EntidadDatosUsuarioVehiculoMoto(usuarioVehiculo.placaVehiculo,
            usuarioVehiculo.cilindrajeAlto,
            usuarioVehiculo.horaFechaIngresoUsuario,
            usuarioVehiculo.tipoDeVehiculo)
    }

    fun desdeUnUsuarioMotoADominio(usuarioVehiculoMoto: EntidadDatosUsuarioVehiculoMoto): UsuarioVehiculoMoto {
        return UsuarioVehiculoMoto(usuarioVehiculoMoto.placaVehiculo).also {
            it.horaFechaIngresoUsuario = usuarioVehiculoMoto.horaFechaIngresoUsuario
        }
    }
}