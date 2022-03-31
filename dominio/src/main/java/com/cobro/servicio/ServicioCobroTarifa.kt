package com.cobro.servicio

import com.cobro.modelo.CobroTarifa
import com.estacionamiento.servicio.ServicioEstacionamiento
import java.time.Duration
import java.time.LocalDateTime

abstract class ServicioCobroTarifa(
    protected val servicioEstacionamiento: ServicioEstacionamiento,
    protected val cobroTarifa: CobroTarifa,
) {

    protected abstract var tarifaCobroServicioEstacionamiento: Int
    protected abstract var horaFechaSalidaUsuarioUsuario: LocalDateTime

    abstract fun cobroDuracionServicio(): Int

    fun duracionServicioEstacionamiento(): Int {

        val calculoDuracionServicio =
            Duration.between(servicioEstacionamiento.estacionamiento.horaFechaIngresoUsuario,
                horaFechaSalidaUsuarioUsuario).dividedBy(60).dividedBy(60)

        var horasServicioEstacionamiento = calculoDuracionServicio.seconds
        if (calculoDuracionServicio.nano >= 0) {
            horasServicioEstacionamiento++
        }
        return horasServicioEstacionamiento.toInt()
    }

}
