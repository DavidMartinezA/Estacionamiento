package com.usuario.modelo

import com.excepciones.FormatoPlacaExcepcion
import org.junit.Assert
import org.junit.Test

class UsuarioVehiculoCarroTest {

    private val mensajeEsperado = "Formato De Placa No Valido"

    @Test
    fun usuarioCarroPlaca_SoloNumeros_LanzarExcepcion() {

        //Arrange
        val placa = "123456"

        //Act
        try {

            val usuarioCarroTest = UsuarioVehiculoCarro(placa)
            Assert.fail()
        } catch (placaExcepcion: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, placaExcepcion.message)
        }
    }

    @Test
    fun usuarioCarroPlaca_SoloLetras_LanzarExcepcion() {

        //Arrange
        val placa = "abcdef"

        //Act
        try {

            val usuarioCarroTest = UsuarioVehiculoCarro(placa)
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    @Test
    fun usuarioCarroPlaca_CamposVacios_LanzarExcepcion() {

        //Arrange
        val placa = ""
        //Act
        try {

            val usuarioCarroTest = UsuarioVehiculoCarro(placa)
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    @Test
    fun usuarioCarroPlaca_SoloLetrasMayusculasYMinusculas_LanzarExcepcion() {

        //Act
        try {

            val usuarioCarroTest = UsuarioVehiculoCarro("AbCdEf")
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    @Test
    fun usuarioCarroPlaca_IngresarMasDeSeisCampos_LanzarExcepcion() {

        //Act
        try {

            val usuarioCarroTest = UsuarioVehiculoCarro("HSU-531C")
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    @Test
    fun usuarioCarroPlaca_IngresarMenosDeCincoCampos_LanzarExcepcion() {

        //Act
        try {

            val usuarioCarroTest = UsuarioVehiculoCarro("AVC1")
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    @Test
    fun usuarioCarroPlaca_IngresarSoloLetrasMayusculas_LanzarExcepcion() {

        //Act
        try {

            val usuarioCarroTest = UsuarioVehiculoCarro("HSUCTU")
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    @Test
    fun usuarioCarroPlaca_IngresarSoloLetrasMinusculas_LanzarExcepcion() {

        //Act
        try {

            val usuarioCarroTest = UsuarioVehiculoCarro("abcdef")
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    @Test
    fun usuarioCarroPlaca_CaracteresEspeciales_LanzarExcepcion() {

        //Act
        try {

            val usuarioCarroTest = UsuarioVehiculoCarro("ab*c531")
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }


    @Test
    fun usuarioCarroPlaca_FormatoAlfanumericoCorrectoConSeparadorCaracterEspecial_LanzarExcepcion() {

        //Act
        try {

            val usuarioCarroTest = UsuarioVehiculoCarro("abc*531")
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }

    }

    @Test
    fun usuarioCarroPlaca_FormatoAlfanumericoCorrectoLetraMinusculaYMayuscula_PalacaValida() {

        //Act
        val usuarioCarroTest = UsuarioVehiculoCarro("hSu531")

        //Assert
        Assert.assertNotNull(usuarioCarroTest)

    }

    @Test
    fun usuarioCarroPlaca_FormatoAlfanumericoCorrectoLetraMinusculaConSeparadorGuion_PalacaValida() {

        //Act
        val usuarioCarroTest = UsuarioVehiculoCarro("hsu-531")

        //Assert
        Assert.assertNotNull(usuarioCarroTest)

    }

    @Test
    fun usuarioCarroPlaca_FormatoAlfanumericoCorrectoLetraMayusculaConSeparador_PalacaValida() {

        //Act
        val usuarioCarroTest = UsuarioVehiculoCarro("HSU-531")

        //Assert
        Assert.assertNotNull(usuarioCarroTest)

    }

}