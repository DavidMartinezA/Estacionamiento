package com.cobro.modelo

import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoMoto

class CobroTarifaMoto: CobroTarifa() {
    override val valorHora = 500
    override val valorDia = 4000


    companion object {
        private const val COBRO_ADICIONAL_ALTO_CILINDRAJE = 2000
    }

    override fun cobroTarifa(
        duracionServicioEstacionamiento: Int,
        usuarioVehiculo: UsuarioVehiculo,
    ): Int {

        var tarifaParqueoTotal = super.cobroTarifa(duracionServicioEstacionamiento, usuarioVehiculo)
        val usuarioIngresa: UsuarioVehiculoMoto = usuarioVehiculo as UsuarioVehiculoMoto

        if (tarifaParqueoTotal != 0 && usuarioIngresa.cilindrajeAlto) {
            tarifaParqueoTotal += COBRO_ADICIONAL_ALTO_CILINDRAJE
        }
        return tarifaParqueoTotal
    }
}