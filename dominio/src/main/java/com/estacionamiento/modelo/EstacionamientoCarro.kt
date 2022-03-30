package com.estacionamiento.modelo

import com.usuario.modelo.UsuarioVehiculo
import java.time.LocalDateTime

class EstacionamientoCarro(usuarioVehiculo: UsuarioVehiculo, horaFechaIngreso: LocalDateTime) :
    Estacionamiento(usuarioVehiculo, horaFechaIngreso) {

    override val capacidadEstacionamiento = 20

}