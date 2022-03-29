package com.cobro.modelo

import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoMoto

class CobroTarifaMoto(usuarioVehiculo: UsuarioVehiculo) : CobroTarifa(usuarioVehiculo) {

    override val valorHora = 500
    override val valorDia = 4000

    companion object {
        private const val COBRO_ADICIONAL_ALTO_CILINDRAJE = 2000
    }

    override fun cobroTarifa(duracionServicioEstacionamiento: Int): Int {

        var tarifaParqueoTotal = super.cobroTarifa(duracionServicioEstacionamiento)
        val usuarioIngresa = usuarioVehiculo as UsuarioVehiculoMoto

        if (tarifaParqueoTotal != 0 && usuarioIngresa.cilindrajeAlto) {
            tarifaParqueoTotal += COBRO_ADICIONAL_ALTO_CILINDRAJE
        }
        return tarifaParqueoTotal
    }
}