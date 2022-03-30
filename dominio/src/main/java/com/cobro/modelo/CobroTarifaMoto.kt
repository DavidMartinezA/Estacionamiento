package com.cobro.modelo

import com.estacionamiento.modelo.Estacionamiento
import com.usuario.modelo.UsuarioVehiculoMoto

class CobroTarifaMoto(estacionamiento: Estacionamiento) : CobroTarifa(estacionamiento) {

    override val valorHora = 500
    override val valorDia = 4000

    companion object {
        private const val COBRO_ADICIONAL_ALTO_CILINDRAJE = 2000
    }

    override fun cobroTarifa(duracionServicioEstacionamiento: Int): Int {

        var tarifaParqueoTotal = super.cobroTarifa(duracionServicioEstacionamiento)
        val usuarioIngresa = estacionamiento.usuarioVehiculo as UsuarioVehiculoMoto

        if (tarifaParqueoTotal != 0 && usuarioIngresa.cilindrajeAlto) {
            tarifaParqueoTotal += COBRO_ADICIONAL_ALTO_CILINDRAJE
        }
        return tarifaParqueoTotal
    }
}