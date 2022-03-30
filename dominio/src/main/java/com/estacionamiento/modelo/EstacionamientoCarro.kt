package com.estacionamiento.modelo

import com.usuario.modelo.UsuarioVehiculo
import java.time.LocalDate

class EstacionamientoCarro(usuarioVehiculo: UsuarioVehiculo, horaFechaIngreso: LocalDate) :
    Estacionamiento(usuarioVehiculo, horaFechaIngreso) {

    override val capacidadEstacionamiento = 20

}