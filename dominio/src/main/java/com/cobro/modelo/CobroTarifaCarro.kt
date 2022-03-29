package com.cobro.modelo

import com.usuario.modelo.UsuarioVehiculo

class CobroTarifaCarro(usuarioVehiculo: UsuarioVehiculo) : CobroTarifa(usuarioVehiculo) {

    override val valorHora = 1000
    override val valorDia = 8000
}