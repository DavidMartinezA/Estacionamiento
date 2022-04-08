package com.usuario.modelo

import java.time.LocalDateTime

class UsuarioVehiculoCarro(placaVehiculo: String) : UsuarioVehiculo(placaVehiculo) {

    override val tipoDeVehiculo: String = "Carro"
    override var horaFechaIngresoUsuario = LocalDateTime.now()
}