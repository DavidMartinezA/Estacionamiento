package com.dominio.usuario.modelo

import com.example.modelo.usuario.Excepciones.FormatoPlacaExcepcion
import com.example.modelo.usuario.modelo.UsuarioCarro
import org.junit.Assert
import org.junit.Test

class UsuarioCarroTest {

    @Test
    fun usuarioCarroPlaca_SoloNumeros_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"
        val placa = "123456"

        //Act
        try {

            val usuarioCarroTest = UsuarioCarro(placa)
            Assert.fail()
        } catch (placaExcepcion: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, placaExcepcion.message)
        }
    }

    @Test
    fun usuarioCarroPlaca_SoloLetras_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"
        val placa = "abcdef"

            //Act
        try {

            val usuarioCarroTest = UsuarioCarro(placa)
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    @Test
    fun usuarioCarroPlaca_CamposVacios_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"
        val placa = ""
        //Act
        try {

            val usuarioCarroTest = UsuarioCarro(placa)
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    @Test
    fun usuarioCarroPlaca_SoloLetrasMayusculasYMinusculas_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"

        //Act
        try {

            val usuarioCarroTest = UsuarioCarro("AbCdEf")
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    @Test
    fun usuarioCarroPlaca_IngresarMasDeSeisCampos_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"

        //Act
        try {

            val usuarioCarroTest = UsuarioCarro("AVC531C")
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    @Test
    fun usuarioCarroPlaca_IngresarMenosDeCincoCampos_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"

        //Act
        try {

            val usuarioCarroTest = UsuarioCarro("AVC1")
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    @Test
    fun usuarioCarroPlaca_IngresarSoloLetrasMayusculas_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"

        //Act
        try {

            val usuarioCarroTest = UsuarioCarro("HSUCTU")
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    @Test
    fun usuarioCarroPlaca_IngresarSoloLetrasMinusculas_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"

        //Act
        try {

            val usuarioCarroTest = UsuarioCarro("abcdef")
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    @Test
    fun usuarioCarroPlaca_CaracteresEspeciales_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"

        //Act
        try {

            val usuarioCarroTest = UsuarioCarro("ab*c531")
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }


    @Test
    fun usuarioCarroPlaca_FormatoAlfanumericoCorrectoConSeparadorCaracterEspecial_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"

        //Act
        try {

            val usuarioCarroTest = UsuarioCarro("abc*531")
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }

    }

    @Test
    fun usuarioCarroPlaca_FormatoAlfanumericoCorrectoLetraMinusculaYMayuscula_PalacaValida() {

        //Arrange
        val usuarioCarroTest: UsuarioCarro

        //Act
        usuarioCarroTest = UsuarioCarro("hSu531")

        //Assert
        Assert.assertNotNull(usuarioCarroTest)

    }

    @Test
    fun usuarioCarroPlaca_FormatoAlfanumericoCorrectoLetraMinusculaConSeparadorGuion_PalacaValida() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"

        //Act
        val usuarioCarroTest = UsuarioCarro("hsu-531")

        //Assert
        Assert.assertNotNull(usuarioCarroTest)

    }

    @Test
    fun usuarioCarroPlaca_FormatoAlfanumericoCorrectoLetraMayusculaConSeparador_PalacaValida() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"

        //Act
        val usuarioCarroTest = UsuarioCarro("HSU-531")

        //Assert
        Assert.assertNotNull(usuarioCarroTest)

    }

}