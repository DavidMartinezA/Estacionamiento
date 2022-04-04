package com.usuario.modelo

import com.usuario.excepcion.FormatoPlacaExcepcion
import org.junit.Assert
import org.junit.Test

class UsuarioVehiculoCarroTest {

    @Test
    fun initValidacionDeFormatoPlaca_ingresaSoloSeisNumeros_lanzarUnaExcepcion() {

        //Arrange
        val placa = "123456"

        //Act
        //Assert
        Assert.assertThrows(FormatoPlacaExcepcion::class.java) {
            UsuarioVehiculoCarro(placa)
        }
    }

    @Test
    fun initValidacionDeFormatoPlaca_ingresaSoloSeisLetras_lanzarUnaExcepcion() {

        //Arrange
        val placa = "abcdef"

        //Act
        //Assert
        Assert.assertThrows(FormatoPlacaExcepcion::class.java) {
            UsuarioVehiculoCarro(placa)
        }
    }

    @Test
    fun initValidacionDeFormatoPlaca_placaVaciaTextoSinCaracteres_lanzarUnaExcepcion() {

        //Arrange
        val placa = ""

        //Act
        //Assert
        Assert.assertThrows(FormatoPlacaExcepcion::class.java) {
            UsuarioVehiculoCarro(placa)
        }
    }

    @Test
    fun initValidacionDeFormatoPlaca_soloSeisLetrasMayusculasYMinusculas_lanzarUnaExcepcion() {

        //Arrange
        val placa = "AbCdEf"

        //Act
        //Assert
        Assert.assertThrows(FormatoPlacaExcepcion::class.java) {
            UsuarioVehiculoCarro(placa)
        }
    }

    @Test
    fun initValidacionDeFormatoPlaca_ingresarMasDeSeisCampos_lanzarUnaExcepcion() {

        //Arrange
        val placa = "HSU-531C"

        //Act
        //Assert
        Assert.assertThrows(FormatoPlacaExcepcion::class.java) {
            UsuarioVehiculoCarro(placa)
        }
    }

    @Test
    fun initValidacionDeFormatoPlaca_ingresarMenosDeCincoCampos_lanzarUnaExcepcion() {

        //Arrange
        val placa = "AVC1"

        //Act
        //Assert
        Assert.assertThrows(FormatoPlacaExcepcion::class.java) {
            UsuarioVehiculoCarro(placa)
        }
    }

    @Test
    fun initValidacionDeFormatoPlaca_ingresarSoloLetrasMayusculas_lanzarUnaExcepcion() {

        //Arrange
        val placa = "HSUCTU"

        //Act
        //Assert
        Assert.assertThrows(FormatoPlacaExcepcion::class.java) {
            UsuarioVehiculoCarro(placa)
        }
    }

    @Test
    fun initValidacionDeFormatoPlaca_ingresarSoloSeisLetrasMinusculas_lanzarUnaExcepcion() {

        //Arrange
        val placa = "abcdef"

        //Act
        //Assert
        Assert.assertThrows(FormatoPlacaExcepcion::class.java) {
            UsuarioVehiculoCarro(placa)
        }
    }

    @Test
    fun initValidacionDeFormatoPlaca_ingresaCaracteresEspeciales_lanzarUnaExcepcion() {

        //Arrange
        val placa = "ab*531"

        //Act
        //Assert
        Assert.assertThrows(FormatoPlacaExcepcion::class.java) {
            UsuarioVehiculoCarro(placa)
        }
    }


    @Test
    fun initValidacionDeFormatoPlaca_formatoAlfanumericoCorrectoConSeparadorCaracterEspecial_LanzarUnaExcepcion() {

        //Arrange
        val placa = "abc*531"

        //Act
        //Assert
        Assert.assertThrows(FormatoPlacaExcepcion::class.java) {
            UsuarioVehiculoCarro(placa)
        }

    }

    @Test
    fun initValidacionDeFormatoPlaca_formatoAlfanumericoCorrectoLetraMinusculaYMayuscula_formatoPlacaValido() {

        //Act
        val usuarioCarroTest = UsuarioVehiculoCarro("hSu531")

        //Assert
        Assert.assertNotNull(usuarioCarroTest)

    }

    @Test
    fun initValidacionDeFormatoPlaca_formatoAlfanumericoCorrectoLetraMinusculaConSeparadorGuion_formatoPlacaValido() {

        //Act
        val usuarioCarroTest = UsuarioVehiculoCarro("hsu-531")

        //Assert
        Assert.assertNotNull(usuarioCarroTest)

    }

    @Test
    fun initValidacionDeFormatoPlaca_formatoAlfanumericoCorrectoLetraMayusculaConSeparador_formatoPlacaValido() {

        //Act
        val usuarioCarroTest = UsuarioVehiculoCarro("HSU-531")

        //Assert
        Assert.assertNotNull(usuarioCarroTest)

    }

}