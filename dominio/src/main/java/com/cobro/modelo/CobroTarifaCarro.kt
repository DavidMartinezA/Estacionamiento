package com.cobro.modelo

import com.estacionamiento.modelo.Estacionamiento

class CobroTarifaCarro(estacionamiento: Estacionamiento) : CobroTarifa(estacionamiento) {

    override val valorHora = 1000
    override val valorDia = 8000
}