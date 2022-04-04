package com.estacionamiento.modelo

import com.usuario.modelo.UsuarioVehiculoMoto
import java.time.LocalDateTime

class EstacionamientoMoto(usuarioVehiculo: UsuarioVehiculoMoto, horaFechaIngreso: LocalDateTime) :
    Estacionamiento(usuarioVehiculo, horaFechaIngreso) {

    override val capacidadEstacionamiento = 10

}