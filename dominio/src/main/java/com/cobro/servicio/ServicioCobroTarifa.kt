package com.cobro.servicio

import java.time.Duration
import java.time.LocalDateTime

abstract class ServicioCobroTarifa(
    private val horaFechaIngresoUsuarioUsuario: LocalDateTime,
    private val horaFechaSalidaUsuarioUsuario: LocalDateTime,
) {

    abstract fun cobroDuracionServicio(): Int

    fun duracionServicioEstacionamiento(): Int {

        val calculoDuracionServicio = Duration.between(horaFechaIngresoUsuarioUsuario, // pedirlo al cobro tarifa
            horaFechaSalidaUsuarioUsuario)
            .dividedBy(60) // Convertir segundos en minutos
            .dividedBy(60) // convertir minutos en horas

        var horasServicioEstacionamiento = calculoDuracionServicio.seconds
        if (calculoDuracionServicio.nano >= 0) {
            horasServicioEstacionamiento++
        }
        return horasServicioEstacionamiento.toInt()
    }
}
