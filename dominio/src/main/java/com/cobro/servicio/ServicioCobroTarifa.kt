package com.cobro.servicio

import com.cobro.modelo.CobroTarifa
import com.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.estacionamiento.servicio.ServicioEstacionamiento
import java.time.Duration
import java.time.LocalDateTime

class ServicioCobroTarifa(
    private val servicioEstacionamiento: ServicioEstacionamiento,
    private val cobroTarifa: CobroTarifa,
    private val horaFechaSalidaUsuarioUsuario: LocalDateTime,
) {

    private var tarifaCobroServicioEstacionamiento = 0


    private fun duracionServicioEstacionamiento(): Int {

        val calculoDuracionServicio =
            Duration.between(servicioEstacionamiento.estacionamiento.horaFechaIngresoUsuario, // pedirlo al cobro tarifa
                horaFechaSalidaUsuarioUsuario).dividedBy(60).dividedBy(60)

        var horasServicioEstacionamiento = calculoDuracionServicio.seconds
        if (calculoDuracionServicio.nano >= 0) {
            horasServicioEstacionamiento++
        }
        return horasServicioEstacionamiento.toInt()
    }

    suspend fun cobroDuracionServicio(): Int {

        val usuarioExiste =
            servicioEstacionamiento.repositorioUsuarioVehiculo.usuarioExiste(servicioEstacionamiento.estacionamiento.usuarioVehiculo)

        if (usuarioExiste) {
            tarifaCobroServicioEstacionamiento =
                cobroTarifa.cobroTarifa(duracionServicioEstacionamiento())
        } else {
            throw UsuarioNoExisteExcepcion()
        }
        return tarifaCobroServicioEstacionamiento
    }

}
