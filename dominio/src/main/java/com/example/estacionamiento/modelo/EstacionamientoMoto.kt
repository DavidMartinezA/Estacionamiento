package com.example.estacionamiento.modelo

import com.example.usuario.modelo.UsuarioVehiculo

class EstacionamientoMoto(usuarioVehiculo: UsuarioVehiculo) : Estacionamiento(usuarioVehiculo) {

    override val capacidadEstacionamiento = 10
    override val tipoDeUsuario = usuarioVehiculo.tipoDeVehiculo
}