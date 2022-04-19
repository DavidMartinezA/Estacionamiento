package com.example.usuario.modelo

import java.time.LocalDateTime

class UsuarioVehiculoCarro(placaVehiculo: String) : UsuarioVehiculo(placaVehiculo) {

    private val hora = LocalDateTime.now()
    override var cilndrajeAlto: Boolean = false
    override var tipoDeVehiculo: String = "Carro"
    override var horaFechaIngresoUsuario: LocalDateTime = hora
}