package com.estacionamiento.modelo

import com.usuario.modelo.UsuarioVehiculoCarro
import java.time.LocalDateTime

class EstacionamientoCarro(usuarioVehiculo: UsuarioVehiculoCarro, horaFechaIngreso: LocalDateTime) :
    Estacionamiento(usuarioVehiculo, horaFechaIngreso) {

    override val capacidadEstacionamiento = 20

}