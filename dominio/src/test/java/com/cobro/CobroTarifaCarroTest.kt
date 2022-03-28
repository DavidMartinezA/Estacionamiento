package com.cobro

import com.cobro.modelo.CobroTarifaCarro
import com.usuario.UsuarioCarro
import org.junit.Test

class CobroTarifaCarroTest {

    @Test
    fun cobroTarifa_ParametroCorrecto_CobroTarifa() {

        //Arrange
        val cobro = CobroTarifaCarro()
        val usuario = UsuarioCarro("hsu531")
        val duracionServicio = 27
        //Act
        val tarifa = cobro.cobroTarifa(duracionServicio, usuario)
        //Assert
        assert(tarifa == 11000)
    }

    @Test
    fun cobroTarifa_ParametroNegativo_CobroTarifa() {

        //Arrange
        val cobro = CobroTarifaCarro()
        val usuario = UsuarioCarro("hsu531")
        val duracionServicio = -10
        //Act
        val tarifa = cobro.cobroTarifa(duracionServicio, usuario)
        //Assert
        assert(tarifa == 0)
    }


    @Test
    fun cobroTarifa_CobroPorHoras_tarifaParqueo() {

        //Arrange
        val cobro = CobroTarifaCarro()
        val usuario = UsuarioCarro("hsu531")
        val duracionServicio = 6
        //Act
        val tarifa = cobro.cobroTarifa(duracionServicio, usuario)
        //Assert
        assert(tarifa == 6000)
    }

    @Test
    fun cobroTarifa_CobroPorDias_tarifaParqueo() {

        //Arrange
        val cobro = CobroTarifaCarro()
        val usuario = UsuarioCarro("hsu531")
        val duracionServicio = 48
        //Act
        val tarifa = cobro.cobroTarifa(duracionServicio, usuario)
        //Assert
        assert(tarifa == 16000)
    }


    @Test
    fun cobroTarifa_SalidaCarroUnDia_tarifaParqueo() {

        //Arrange
        val cobro = CobroTarifaCarro()
        val usuario = UsuarioCarro("hsu531")
        val duracionServicio = 9
        //Act
        val tarifa = cobro.cobroTarifa(duracionServicio, usuario)
        //Assert
        assert(tarifa == 8000)
    }

    @Test
    fun cobroTarifa_HorasEgdeCaseCarro_tarifaParqueo() {

        //Arrange
        val cobro = CobroTarifaCarro()
        val usuario = UsuarioCarro("hsu531")
        val duracionServicio = 2376
        //Act
        val tarifa = cobro.cobroTarifa(duracionServicio, usuario)
        //Assert
        assert(tarifa == 792000)
    }

}

