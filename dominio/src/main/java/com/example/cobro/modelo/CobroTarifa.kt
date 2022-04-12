package com.example.cobro.modelo

import com.example.estacionamiento.modelo.Estacionamiento

abstract class CobroTarifa(protected var estacionamiento: Estacionamiento) {

    companion object {

        private const val HORAS_EN_EL_DIA = 24
    }

    protected abstract val valorHora: Int
    protected abstract val valorDia: Int
    private var tarifaParqueoTotal = 0

    open fun cobroTarifa(duracionServicioEstacionamiento: Int): Int {

        var horasCobro: Int
        val diasEnHoras: Int
        val calculoCobro =
            (duracionServicioEstacionamiento / HORAS_EN_EL_DIA).toString()
        var diasCobro: Int

        if (duracionServicioEstacionamiento > 0) {
            when (duracionServicioEstacionamiento) {
                in 0..8 -> {
                    tarifaParqueoTotal = (duracionServicioEstacionamiento * valorHora)
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