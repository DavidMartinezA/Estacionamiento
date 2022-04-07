package com.cobro.modelo

import com.estacionamiento.modelo.EstacionamientoMoto
import com.usuario.modelo.UsuarioVehiculoMoto
import org.junit.Assert
import org.junit.Test

class CobroTarifaMotoTest {

    @Test
    fun cobroTarifa_duracionDiezHorasMotoCilindrajeAltoVerdadero_retornaCobroDeTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu53c", true)
        val estacionamiento = EstacionamientoMoto(usuario)
        val cobroTarifa = CobroTarifaMoto(estacionamiento)
        val duracionServicio = 10

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 6000)

    }

    @Test
    fun cobroTarifa_duracionDiezHorasMotoCilindrajeAltoFalso_retornaCobroDeTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu53c", false)
        val estacionamiento = EstacionamientoMoto(usuario)
        val cobroTarifa = CobroTarifaMoto(estacionamiento)
        val duracionServicio = 10

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 4000)
    }

    @Test
    fun cobroTarifa_duracionCincoDiasCilindrajeAltoFalse_retornaCobroDeTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu53c", false)
        val estacionamiento = EstacionamientoMoto(usuario)
        val cobroTarifa = CobroTarifaMoto(estacionamiento)
        val duracionServicio = 120

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 20000)
    }

    @Test
    fun cobroTarifa_duracionCincoDiasMasNueveHorasCilindrajeAltoVerdadero_retornaCobroDeTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu53c", true)
        val estacionamiento = EstacionamientoMoto(usuario)
        val cobroTarifa = CobroTarifaMoto(estacionamiento)
        val duracionServicio = 129

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 26000)
    }

    @Test
    fun cobroTarifa_duracionDiezDiasMasNueveHorasCilindrajeAltoFalse_retornaCobroDeTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu53c", false)
        val estacionamiento = EstacionamientoMoto(usuario)
        val cobroTarifa = CobroTarifaMoto(estacionamiento)
        val duracionServicio = 249

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 44000)
    }

    @Test
    fun cobroTarifa_duracionDiezDiasMasSieteHorasCilindrajeAltoFalse_retornaCobroDeTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu53c", false)
        val estacionamiento = EstacionamientoMoto(usuario)
        val cobroTarifa = CobroTarifaMoto(estacionamiento)
        val duracionServicio = 247

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 43500)
    }

    @Test
    fun cobroTarifaMoto_duracionCasoExtremoCilindrajeAltFalse_retornaCobroDeTarifaCero() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu53c", false)
        val estacionamiento = EstacionamientoMoto(usuario)
        val cobroTarifa = CobroTarifaMoto(estacionamiento)
        val duracionServicio = 2475

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 0)
    }

}


