package com.cobro.servicio

import com.cobro.modelo.CobroTarifaMoto
import com.estacionamiento.modelo.EstacionamientoMoto
import com.usuario.modelo.UsuarioVehiculoMoto
import com.usuario.repositorio.RepositorioUsuarioVehiculoMoto
import java.time.LocalDateTime

class ServicioCobroTarifaMoto(
    private val repositorioUsuarioVehiculoMoto: RepositorioUsuarioVehiculoMoto,
    private val estacionamientoMoto: EstacionamientoMoto,
    private val cobroTarifa: CobroTarifaMoto,
    horaFechaSalidaUsuarioUsuario: LocalDateTime,
) : ServicioCobroTarifa(estacionamientoMoto.horaFechaIngresoUsuario, horaFechaSalidaUsuarioUsuario) {

    override fun cobroDuracionServicio(): Int {
        var tarifaCobroServicioEstacionamiento = 0

        val usuarioExiste = repositorioUsuarioVehiculoMoto.usuarioExiste(estacionamientoMoto.usuarioVehiculo as UsuarioVehiculoMoto)

        if (usuarioExiste) {
            tarifaCobroServicioEstacionamiento = cobroTarifa.cobroTarifa(duracionServicioEstacionamiento())
        }
        return tarifaCobroServicioEstacionamiento
    }
}
