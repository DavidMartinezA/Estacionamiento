package com.usuario.modelo

class UsuarioVehiculoMoto(placaVehiculo: String, var cilindrajeAlto: Boolean = false) :
    UsuarioVehiculo(placaVehiculo) {

    override var tipoDeVehiculo: String = "Moto"
}