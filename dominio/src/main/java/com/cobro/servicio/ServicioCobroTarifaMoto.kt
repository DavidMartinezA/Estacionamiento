package com.cobro.servicio

import com.cobro.modelo.CobroTarifa
import com.estacionamiento.servicio.ServicioEstacionamiento
import java.time.LocalDateTime

class ServicioCobroTarifaMoto(
    servicioEstacionamiento: ServicioEstacionamiento,
    override var horaFechaSalidaUsuarioUsuario: LocalDateTime,
    cobroTarifa: CobroTarifa,
) :
    ServicioCobroTarifa(servicioEstacionamiento, cobroTarifa) {
    override var tarifaCobroServicioEstacionamiento = 0

    override fun cobroDuracionServicio(): Int {

        val usuarioExiste =
            servicioEstacionamiento.repositorioEstacionamiento.usuarioExiste(servicioEstacionamiento.estacionamiento.usuarioVehiculo)

        if (usuarioExiste) {
            cobroTarifa.cobroTarifa(duracionServicioEstacionamiento())
        }
        return tarifaCobroServicioEstacionamiento
    }


}