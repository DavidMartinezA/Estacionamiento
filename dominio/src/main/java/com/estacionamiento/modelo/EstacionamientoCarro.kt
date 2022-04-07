package com.estacionamiento.modelo

import com.usuario.modelo.UsuarioVehiculo

class EstacionamientoCarro(usuarioVehiculo: UsuarioVehiculo) : Estacionamiento(usuarioVehiculo) {

    override val capacidadEstacionamiento = 20
    override val tipoDeUsuario = usuarioVehiculo.tipoDeVehiculo
}