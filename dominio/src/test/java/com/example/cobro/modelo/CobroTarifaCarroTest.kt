package com.example.cobro.modelo

import com.example.usuario.modelo.UsuarioVehiculoCarro
import org.junit.Assert
import org.junit.Test

class CobroTarifaCarroTest {

    private val usuario = UsuarioVehiculoCarro("hsu531")
    private val cobroTarifa = CobroTarifaCarro()

    @Test
    fun cobroTarifa_duracionServicioEnteroPositivoVeitisieteHoras_retornaCobroDeTarifa() {

        //Arrange
        val duracionServicio = 27

        //Act
        val tarifa = cobroTarifa.cobroTarifa(usuario, duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 11000)

    }

    @Test
    fun cobroTarifa_duracionServicioParametroNegativoMenosVeitisiteHoras_retornaCobroDeTarifaCero() {

        //Arrange
        val duracionServicio = -27

        //Act
        val tarifa = cobroTarifa.cobroTarifa(usuario, duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 0)
    }

    @Test
    fun cobroTarifa_duracionServicioCincoHoras_retornaCobroDeTarifa() {

        //Arrange
        val duracionServicio = 5

        //Act
        val tarifa = cobroTarifa.cobroTarifa(usuario, duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 5000)
    }

    @Test
    fun cobroTarifa_duracionServicioDosDias_retornaCobroDeTarifa() {

        //Arrange
        val duracionServicio = 48

        //Act
        val tarifa = cobroTarifa.cobroTarifa(usuario, duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 16000)
    }

    @Test
    fun cobroTarifa_duracionServicioDosDiasYDosHoras_retornaCobroDeTarifa() {

        //Arrange
        val duracionServicio = 50

        //Act
        val tarifa = cobroTarifa.cobroTarifa(usuario, duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 18000)
    }

    @Test
    fun cobroTarifa_duracionDeServicioVeinticuatroHoras_retornaCobroDeTarifa() {

        //Arrange
        val duracionServicio = 24

        //Act
        val tarifa = cobroTarifa.cobroTarifa(usuario, duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 8000)
    }

    @Test
    fun cobroTarifao_duracionServicioUnDiaYNueveHoras_retornaCobroDeTarifa() {

        //Arrange
        val duracionServicio = 33

        //Act
        val tarifa = cobroTarifa.cobroTarifa(usuario, duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 16000)
    }

    @Test
    fun cobroTarifaCarro_duracionServicioDiezDiasYNueveHoras_retornaCobroDeTarifa() {

        //Arrange
        val duracionServicio = 249

        //Act
        val tarifa = cobroTarifa.cobroTarifa(usuario, duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 88000)
    }

    @Test
    fun cobroTarifa_duaracionHorasCasoExtremo_retornaCobroDeTarifa() {

        //Arrange
        val duracionServicio = 2376

        //Act
        val tarifa = cobroTarifa.cobroTarifa(usuario, duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 792000)
    }

    @Test
    fun cobroTarifa_duaracionHorasCasoExtremo_retornaCobroDeTarifaCero() {

        //Arrange
        val duracionServicio = 3000

        //Act
        val tarifa = cobroTarifa.cobroTarifa(usuario, duracionServicio)

        //Assert
        Assert.assertEquals(tarifa, 0)
    }

}

