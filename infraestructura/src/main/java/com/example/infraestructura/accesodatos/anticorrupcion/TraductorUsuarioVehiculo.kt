package com.example.infraestructura.accesodatos.anticorrupcion

import com.example.infraestructura.accesodatos.entidadbasedatos.EntidadDatosUsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculo


class TraductorUsuarioVehiculo {

    fun desdeDominioUnUsuarioVehiculo(usuarioVehiculo: UsuarioVehiculo): EntidadDatosUsuarioVehiculo {
        return EntidadDatosUsuarioVehiculo(usuarioVehiculo.placaVehiculo)
    }

}