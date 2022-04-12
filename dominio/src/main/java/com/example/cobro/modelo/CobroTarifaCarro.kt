package com.example.cobro.modelo

import com.example.estacionamiento.modelo.Estacionamiento

class CobroTarifaCarro(estacionamiento: Estacionamiento) : com.example.cobro.modelo.CobroTarifa(estacionamiento) {

    override val valorHora = 1000
    override val valorDia = 8000
}