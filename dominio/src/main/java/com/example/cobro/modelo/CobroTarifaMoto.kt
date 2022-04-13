package com.example.cobro.modelo

import com.example.usuario.modelo.UsuarioVehiculo
import com.example.usuario.modelo.UsuarioVehiculoMoto


class CobroTarifaMoto : CobroTarifa() {

    override val valorHora = 500
    override val valorDia = 4000

    companion object {
        private const val COBRO_ADICIONAL_ALTO_CILINDRAJE = 2000
    }

    override fun cobroTarifa(usuarioVehiculo: UsuarioVehiculo, duracionServicioEstacionamiento: Int): Int {

        var tarifaParqueoTotal = super.cobroTarifa(usuarioVehiculo, duracionServicioEstacionamiento)
        val usuarioIngresa = usuarioVehiculo as UsuarioVehiculoMoto

        if (tarifaParqueoTotal != 0 && usuarioIngresa.cilindrajeAlto) {
            tarifaParqueoTotal += COBRO_ADICIONAL_ALTO_CILINDRAJE
        }
        return tarifaParqueoTotal
    }
}