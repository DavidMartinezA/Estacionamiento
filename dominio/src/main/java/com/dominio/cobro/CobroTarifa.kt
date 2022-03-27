package com.dominio.cobro

import com.dominio.usuario.servicio.ServicioUsuario

abstract class CobroTarifa {

    companion object{
        const val HORAS_EN_EL_DIA= 24
    }
    protected abstract val valorHora: Int
    protected abstract val valorDia: Int
    private var tarifaParqueoTotal = 0

    open fun cobroTarifa(duracionServicioEstacionamiento:Int,servicioUsuario: ServicioUsuario): Int {

        var horasCobro= 0
        var diasEnHoras = 0
        val calculoCobro = (duracionServicioEstacionamiento / HORAS_EN_EL_DIA).toString()
        var diasCobro = 0

        if (duracionServicioEstacionamiento > 0) {
            when (duracionServicioEstacionamiento) {
                in 0..8 -> {
                    tarifaParqueoTotal = duracionServicioEstacionamiento * valorHora
                }
                in 9..24 -> {
                    tarifaParqueoTotal = valorDia
                }
                in 25..216 -> {
                    diasCobro = calculoCobro[0].toString().toInt()
                    diasEnHoras = diasCobro * HORAS_EN_EL_DIA
                    horasCobro = duracionServicioEstacionamiento - diasEnHoras

                    if (horasCobro >= 9) {
                        horasCobro = valorDia
                    } else {
                        horasCobro *= valorHora
                    }
                    diasCobro *= valorDia
                    tarifaParqueoTotal = diasCobro + horasCobro
                }
                in 217..2376 -> {
                    val diasCobroDecena = calculoCobro[0].toString()
                    val diasCobroUnidad = calculoCobro[1].toString()
                    diasCobro = (diasCobroDecena + diasCobroUnidad).toInt()
                    diasEnHoras = diasCobro * HORAS_EN_EL_DIA
                    horasCobro = duracionServicioEstacionamiento - diasEnHoras

                    if (horasCobro >= 9) {
                        horasCobro = valorDia
                    } else {
                        horasCobro *= valorHora
                    }
                    diasCobro *= valorDia
                    tarifaParqueoTotal = diasCobro + horasCobro
                }
                else -> { //Edge Case
                    tarifaParqueoTotal = 0
                }
            }
        }
        return tarifaParqueoTotal
    }
}