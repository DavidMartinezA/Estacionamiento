package com.cobro.servicio

import com.cobro.modelo.CobroTarifaMoto
import com.estacionamiento.servicio.ServicioEstacionamiento
import java.time.LocalDate

class ServicioCobroTarifaMoto(
    override val servicioEstacionamiento: ServicioEstacionamiento,
    override var horaFechaSalidaUsuarioUsuario: LocalDate,
    private val cobroTarifa: CobroTarifaMoto,
) :
    ServicioCobroTarifa(servicioEstacionamiento) {
    override var tarifaCobroServicioEstacionamiento = 0

    override fun registroCobroDuracionServicio(): Int {

        val usuarioExiste =
            servicioEstacionamiento.repositorioEstacionamiento.usuarioExiste(servicioEstacionamiento.estacionamiento.usuarioVehiculo)

        if (usuarioExiste) {
            cobroTarifa.cobroTarifa(duracionServicioEstacionamiento())
        }
        return tarifaCobroServicioEstacionamiento
    }


}