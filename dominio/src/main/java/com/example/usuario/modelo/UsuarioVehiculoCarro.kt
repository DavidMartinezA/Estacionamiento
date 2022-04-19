package com.example.usuario.modelo

import java.time.LocalDateTime

class UsuarioVehiculoCarro(placaVehiculo: String) : UsuarioVehiculo(placaVehiculo) {

    override var cilndrajeAlto: Boolean = false
    override var tipoDeVehiculo: String = "Carro"
    override var horaFechaIngresoUsuario: LocalDateTime = LocalDateTime.now()
}