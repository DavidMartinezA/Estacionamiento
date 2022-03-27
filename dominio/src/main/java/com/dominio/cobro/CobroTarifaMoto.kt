package com.dominio.cobro

import com.dominio.excepciones.TipoDeUsuarioNoAdmitidoExcepcion
import com.dominio.usuario.modelo.UsuarioMoto
import com.dominio.usuario.servicio.ServicioUsuario

class CobroTarifaMoto: CobroTarifa() {
    override val valorHora = 1000
    override val valorDia = 8000


    companion object {
        private const val COBRO_ADICIONAL_ALTO_CILINDRAJE = 2000
    }

    override fun cobroTarifa(duracionServicioEstacionamiento: Int, servicioUsuario: ServicioUsuario): Int {
        var tarifaParqueoTotal = super.cobroTarifa(duracionServicioEstacionamiento, servicioUsuario)

        if ((servicioUsuario.usuario as? UsuarioMoto)?.cilindrajeAlto == true) {
            tarifaParqueoTotal += COBRO_ADICIONAL_ALTO_CILINDRAJE
        }else{
            throw  TipoDeUsuarioNoAdmitidoExcepcion()
        }
        return tarifaParqueoTotal
    }
}