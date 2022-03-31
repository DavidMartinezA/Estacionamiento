package com.cobro.modelo

import com.estacionamiento.modelo.EstacionamientoMoto
import com.usuario.modelo.UsuarioVehiculoMoto
import org.junit.Test

import java.time.LocalDateTime

class CobroTarifaMotoTest {

    @Test
    fun cobroTarifaMoto_DuracionCorrectaCilindrajeAltoTrue_CobroDeTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu53c", true)
        val estacionamiento = EstacionamientoMoto(usuario, LocalDateTime.now())
        val cobroTarifa = CobroTarifaMoto(estacionamiento)
        val duracionServicio = 10

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 6000)
    }

    @Test
    fun cobroTarifaMoto_DuracionCorrectaCilindrajeAltFalse_CobroDeTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu53c", false)
        val estacionamiento = EstacionamientoMoto(usuario, LocalDateTime.now())
        val cobroTarifa = CobroTarifaMoto(estacionamiento)
        val duracionServicio = 10

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 4000)
    }

    @Test
    fun cobroTarifaMoto_DuracionCincoDiasCilindrajeAltFalse_CobroDeTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu53c", false)
        val estacionamiento = EstacionamientoMoto(usuario, LocalDateTime.now())
        val cobroTarifa = CobroTarifaMoto(estacionamiento)
        val duracionServicio = 120

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 20000)
    }

    @Test
    fun cobroTarifaMoto_DuracionCincoDiasMasNueveHorasCilindrajeAltFalse_CobroDeTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu53c", false)
        val estacionamiento = EstacionamientoMoto(usuario, LocalDateTime.now())
        val cobroTarifa = CobroTarifaMoto(estacionamiento)
        val duracionServicio = 129

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 24000)
    }

    @Test
    fun cobroTarifaMoto_DuracionDiezDiasMasNueveHorasCilindrajeAltFalse_CobroDeTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu53c", false)
        val estacionamiento = EstacionamientoMoto(usuario, LocalDateTime.now())
        val cobroTarifa = CobroTarifaMoto(estacionamiento)
        val duracionServicio = 249

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 44000)
    }

    @Test
    fun cobroTarifaMoto_DuracionDiezDiasMasSieteHorasCilindrajeAltFalse_CobroDeTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu53c", false)
        val estacionamiento = EstacionamientoMoto(usuario, LocalDateTime.now())
        val cobroTarifa = CobroTarifaMoto(estacionamiento)
        val duracionServicio = 247

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 43500)
    }

    @Test
    fun cobroTarifaMoto_DuracionCasoExtremoCilindrajeAltFalse_CobroDeTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu53c", false)
        val estacionamiento = EstacionamientoMoto(usuario, LocalDateTime.now())
        val cobroTarifa = CobroTarifaMoto(estacionamiento)
        val duracionServicio = 2475

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 0)
    }

}


