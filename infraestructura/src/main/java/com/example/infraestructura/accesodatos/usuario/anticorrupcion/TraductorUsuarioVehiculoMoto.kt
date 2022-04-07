package com.example.infraestructura.accesodatos.usuario.anticorrupcion

import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculoMoto
import com.usuario.modelo.UsuarioVehiculoMoto

class TraductorUsuarioVehiculoMoto {

    fun desdeDominioUnUsuarioMoto(usuarioVehiculo: UsuarioVehiculoMoto): EntidadDatosUsuarioVehiculoMoto {
        return EntidadDatosUsuarioVehiculoMoto(0,
            placaVehiculo = usuarioVehiculo.placaVehiculo,
            tipoDeVehiculo = usuarioVehiculo.tipoDeVehiculo,
            cilindrajeAlto = usuarioVehiculo.cilindrajeAlto,
            horaFechaIngresoUsuario = usuarioVehiculo.horaFechaIngresoUsuario)
    }


    fun desdeUnUsuarioMotoADominio(usuarioVehiculoMoto: EntidadDatosUsuarioVehiculoMoto): UsuarioVehiculoMoto {
        return UsuarioVehiculoMoto(usuarioVehiculoMoto.placaVehiculo, usuarioVehiculoMoto.cilindrajeAlto).also {
            it.horaFechaIngresoUsuario = usuarioVehiculoMoto.horaFechaIngresoUsuario
        }
    }
}