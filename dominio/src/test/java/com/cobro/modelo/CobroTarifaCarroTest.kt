package com.cobro.modelo

import com.estacionamiento.modelo.EstacionamientoCarro
import com.usuario.modelo.UsuarioVehiculoCarro
import org.junit.Test
import java.time.LocalDateTime

class CobroTarifaCarroTest {

    private val usuario = UsuarioVehiculoCarro("hsu531")
    private val estacionamiento = EstacionamientoCarro(usuario, LocalDateTime.now())
    private val cobroTarifa = CobroTarifaCarro(estacionamiento)

    @Test
    fun cobroTarifaCarro_DuracionServicioCorrecto_CobroDeTarifa() {

        //Arrange
        val duracionServicio = 27

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 11000)
    }


    @Test
    fun cobroTarifaCarro_ParametroNegativo_CobroDeTarifaCero() {

        //Arrange
        val duracionServicio = -27

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 0)
    }

    @Test
    fun cobroTarifaCarro_DuracionServicoEnHoras_CobroDeTarifa() {

        //Arrange
        val duracionServicio = 5

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 5000)
    }

    @Test
    fun cobroTarifaCarro_DuracionServicioEnDias_CobroDeTarifa() {

        //Arrange
        val duracionServicio = 48 // son dos dias en horas

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 16000)
    }

    @Test
    fun cobroTarifaCarro_DuracionServicioEnHorasYDias_CobroDeTarifa() {

        //Arrange
        val duracionServicio = 50 // son dos dias en horas

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 18000)
    }

    @Test
    fun cobroTarifaCarro_SalidaCarroUnDia_CobroDeTarifa() {

        //Arrange
        val duracionServicio = 24

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 8000)
    }

    @Test
    fun cobroTarifaCarro_SalidaCarroUnDiaYHoras_CobroDeTarifa() {

        //Arrange
        val duracionServicio = 33

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 16000)
    }

    @Test
    fun cobroTarifaCarro_HorasDeElDiaYDias_tarifaParqueo() {

        //Arrange
        val duracionServicio = 249

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 88000)
    }

    @Test
    fun cobroTarifaCarro_HorasEgdeCaseCarro_tarifaParqueo() {

        //Arrange
        val duracionServicio = 2376

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 792000)
    }

    @Test
    fun cobroTarifaCarro_HorasEgdeCaseDosCarro_tarifaParqueo() {

        //Arrange
        val duracionServicio = 3000

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 0)
    }

}

