package com.estacionamiento.modelo

import com.usuario.modelo.UsuarioVehiculo
import java.time.LocalDateTime

class EstacionamientoMoto(usuarioVehiculo: UsuarioVehiculo, horaFechaIngreso: LocalDateTime) :
    Estacionamiento(usuarioVehiculo, horaFechaIngreso) {

    override val capacidadEstacionamiento = 10
    override val tipoDeUsuario = usuarioVehiculo.tipoDeVehiculo
}