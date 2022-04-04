package com.usuario.modelo

import com.usuario.excepcion.FormatoPlacaExcepcion
import org.junit.Assert
import org.junit.Test

class UsuarioVehiculoCarroTest {

    private val mensajeEsperado = "Formato De Placa No Valido"

    @Test
    fun constructor_soloNumeros_lanzarExcepcion() {

        //Arrange
        val placa = "123456"

        //Act
        //Assert
        Assert.assertThrows(FormatoPlacaExcepcion::class.java) {
            UsuarioVehiculoCarro(placa)
        }
    }

    @Test
    fun usuarioCarroPlaca_soloLetras_lanzarExcepcion() {

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
    fun usuarioCarroPlaca_placaVacia_lanzarExcepcion() {

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
    fun usuarioCarroPlaca_soloLetrasMayusculasYMinusculas_lanzarExcepcion() {

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
    fun usuarioCarroPlaca_ingresarMasDeSeisCampos_lanzarExcepcion() {

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
    fun usuarioCarroPlaca_ingresarMenosDeCincoCampos_lanzarExcepcion() {

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
    fun usuarioCarroPlaca_ingresarSoloLetrasMayusculas_lanzarExcepcion() {

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
    fun usuarioCarroPlaca_ingresarSoloLetrasMinusculas_lanzarExcepcion() {

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
    fun usuarioCarroPlaca_caracteresEspeciales_lanzarExcepcion() {

        //Act
        try {

            val usuarioCarroTest = UsuarioVehiculoCarro("ab*531")
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