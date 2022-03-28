package com.dominio.cobro.modelo

import com.dominio.usuario.modelo.Usuario
import com.dominio.usuario.modelo.UsuarioMoto

class CobroTarifaMoto: CobroTarifa() {
    override val valorHora = 500
    override val valorDia = 4000


    companion object {
        private const val COBRO_ADICIONAL_ALTO_CILINDRAJE = 2000
    }

    override fun cobroTarifa(duracionServicioEstacionamiento: Int, usuario: Usuario): Int {

        var tarifaParqueoTotal = super.cobroTarifa(duracionServicioEstacionamiento, usuario)
        val usuarioIngresa: UsuarioMoto = usuario as UsuarioMoto

        if (tarifaParqueoTotal != 0 && usuarioIngresa.cilindrajeAlto) {
            tarifaParqueoTotal += COBRO_ADICIONAL_ALTO_CILINDRAJE
        }
        return tarifaParqueoTotal
    }
}