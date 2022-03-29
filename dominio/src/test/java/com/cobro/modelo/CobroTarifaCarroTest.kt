package com.cobro.modelo

import com.usuario.modelo.UsuarioVehiculoCarro
import org.junit.Test

class CobroTarifaCarroTest {

    val cobroTarifa = CobroTarifaCarro()

    @Test
    fun cobroTarifa_ParametroCorrecto_CobroTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoCarro("hsu531")
        val duracionServicio = 27

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio, usuario)

        //Assert
        assert(tarifa == 11000)
    }

    @Test
    fun cobroTarifa_ParametroNegativo_CobroTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoCarro("hsu531")
        val duracionServicio = -10

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio, usuario)

        //Assert
        assert(tarifa == 0)
    }


    @Test
    fun cobroTarifa_CobroPorHoras_tarifaParqueo() {

        //Arrange
        val usuario = UsuarioVehiculoCarro("hsu531")
        val duracionServicio = 6

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio, usuario)

        //Assert
        assert(tarifa == 6000)
    }

    @Test
    fun cobroTarifa_CobroPorDias_tarifaParqueo() {

        //Arrange
        val usuario = UsuarioVehiculoCarro("hsu531")
        val duracionServicio = 48

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio, usuario)

        //Assert
        assert(tarifa == 16000)
    }

    @Test
    fun cobroTarifa_SalidaCarroUnDia_tarifaParqueo() {

        //Arrange
        val usuario = UsuarioVehiculoCarro("hsu531")
        val duracionServicio = 9

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio, usuario)

        //Assert
        assert(tarifa == 8000)
    }

    @Test
    fun cobroTarifa_HorasEgdeCaseCarro_tarifaParqueo() {

        //Arrange
        val usuario = UsuarioVehiculoCarro("hsu531")
        val duracionServicio = 2376

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio, usuario)

        //Assert
        assert(tarifa == 792000)
    }

}

