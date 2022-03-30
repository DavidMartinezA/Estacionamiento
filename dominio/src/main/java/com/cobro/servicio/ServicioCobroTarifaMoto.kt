package com.cobro.servicio

import com.cobro.modelo.CobroTarifaMoto
import com.estacionamiento.modelo.EstacionamientoMoto
import com.estacionamiento.repositorio.RepositorioEstacionamiento
import java.time.LocalDate

class ServicioCobroTarifaMoto(
    override val estacionamiento: EstacionamientoMoto,
    override val horaFechaSalidaUsuarioUsuario: LocalDate,
    override val repositorioEstacionamiento: RepositorioEstacionamiento,
    val cobroTarifa: CobroTarifaMoto,
) :
    ServicioCobroTarifa(estacionamiento,
        horaFechaSalidaUsuarioUsuario,
        repositorioEstacionamiento) {
    override var tarifaCobroServicioEstacionamiento = 0

    override fun registroCobroDuracionServicio(): Int {

        val usuarioExiste =
            repositorioEstacionamiento.usuarioExiste(estacionamiento.usuarioVehiculo)

        if (usuarioExiste) {
            cobroTarifa.cobroTarifa(duracionServicioEstacionamiento)
        }
        return tarifaCobroServicioEstacionamiento
    }


}