package com.usuario.modelo

class UsuarioVehiculoCarro(placaVehiculo: String) : UsuarioVehiculo(placaVehiculo) {

    override var tipoDeVehiculo: String = "Carro"
    override var cilindrajeAlto: Boolean = false
}