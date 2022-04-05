package com.example.infraestructura.accesodatos.usuario.anticorrupcion

import com.example.infraestructura.accesodatos.usuario.entidadbasedatos.EntidadDatosUsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculo


class TraductorUsuarioVehiculo {

    fun desdeDominioUnUsuarioVehiculo(usuarioVehiculo: UsuarioVehiculo): EntidadDatosUsuarioVehiculo {
        return EntidadDatosUsuarioVehiculo(usuarioVehiculo.placaVehiculo, usuarioVehiculo.horaFechaIngresoUsuario,
            usuarioVehiculo.tipoDeVehiculo, usuarioVehiculo.cilindrajeAlto)
    }

}