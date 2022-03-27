package com.dominio.cobro.modelo

import com.dominio.excepciones.TipoDeUsuarioNoAdmitidoExcepcion
import com.dominio.usuario.modelo.Usuario
import com.dominio.usuario.modelo.UsuarioMoto
import com.dominio.usuario.servicio.ServicioUsuario

class CobroTarifaMoto: CobroTarifa() {
    override val valorHora = 1000
    override val valorDia = 8000


    companion object {
        private const val COBRO_ADICIONAL_ALTO_CILINDRAJE = 2000
    }

    public override fun cobroTarifa(duracionServicioEstacionamiento: Int, usuario: Usuario): Int {
        var tarifaParqueoTotal = super.cobroTarifa(duracionServicioEstacionamiento, usuario)

        if ((usuario as? UsuarioMoto)?.cilindrajeAlto == true) {
            tarifaParqueoTotal += COBRO_ADICIONAL_ALTO_CILINDRAJE
        }else{
            throw  TipoDeUsuarioNoAdmitidoExcepcion()
        }
        return tarifaParqueoTotal
    }
}