package com.example.infraestructura.accesodatos.usuario.anticorrupcion

import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioMoto
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro
import com.usuario.modelo.UsuarioVehiculoMoto

class TraductorUsuarioVehiculoMoto {

    fun desdeDominioUnUsuario(usuarioVehiculo: UsuarioVehiculoMoto): EntidadDatosUsuarioMoto {
        return EntidadDatosUsuarioMoto(usuarioVehiculo.placaVehiculo, usuarioVehiculo.cilindrajeAlto,
            usuarioVehiculo.horaFechaIngresoUsuario
        )
    }

    fun desdeUnUsuarioCarroADominio(usuarioVehiculo: EntidadDatosUsuarioMoto): UsuarioVehiculo {
        return UsuarioVehiculoCarro(usuarioVehiculo.placaVehiculo).also {
            it.horaFechaIngresoUsuario = usuarioVehiculo.horaFechaIngresoUsuario
        }
    }
}