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

}


