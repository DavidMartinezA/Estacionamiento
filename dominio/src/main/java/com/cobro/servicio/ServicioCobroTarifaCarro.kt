package com.cobro.servicio

import com.cobro.modelo.CobroTarifaCarro
import com.estacionamiento.modelo.EstacionamientoCarro
import com.usuario.modelo.UsuarioVehiculoCarro
import com.usuario.repositorio.RepositorioUsuarioVehiculoCarro
import java.time.LocalDateTime

class ServicioCobroTarifaCarro(
    private val repositorioUsuarioVehiculoCarro: RepositorioUsuarioVehiculoCarro,
    private val estacionamientoCarro: EstacionamientoCarro,
    private val cobroTarifa: CobroTarifaCarro,
    horaFechaSalidaUsuarioUsuario: LocalDateTime,
) : ServicioCobroTarifa(estacionamientoCarro.horaFechaIngresoUsuario, horaFechaSalidaUsuarioUsuario) {

    override fun cobroDuracionServicio(): Int {
        var tarifaCobroServicioEstacionamiento = 0

        val usuarioExiste = repositorioUsuarioVehiculoCarro.usuarioExiste(estacionamientoCarro.usuarioVehiculo as UsuarioVehiculoCarro)

        if (usuarioExiste) {
            tarifaCobroServicioEstacionamiento = cobroTarifa.cobroTarifa(duracionServicioEstacionamiento())
        }
        return tarifaCobroServicioEstacionamiento
    }
}
