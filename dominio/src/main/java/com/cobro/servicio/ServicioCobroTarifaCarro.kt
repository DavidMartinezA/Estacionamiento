package com.cobro.servicio

import com.cobro.modelo.CobroTarifaCarro
import com.estacionamiento.modelo.EstacionamientoCarro
import com.estacionamiento.repositorio.RepositorioEstacionamiento
import java.time.LocalDate

class ServicioCobroTarifaCarro(
    override val estacionamiento: EstacionamientoCarro,
    override val horaFechaSalidaUsuarioUsuario: LocalDate,
    override val repositorioEstacionamiento: RepositorioEstacionamiento,
    val cobroTarifa: CobroTarifaCarro,
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