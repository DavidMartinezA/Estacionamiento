package com.example.usuario.modelo

import org.junit.Assert
import org.junit.Test

class UsuarioVehiculoMotoTest {

    @Test
    fun initValidacionDeFormatoPlaca_formatoAlfanumericoCorrectoMotoCincoCamposAltoCilindrajeFalso_retornaCilindrajeAltoFalso() {

        //Arrange
        val placa = "hSu53"
        val altoCilindraje = false

        //Act
        val usuarioMotoTest = UsuarioVehiculoMoto(placa, altoCilindraje)

        //Assert
        Assert.assertFalse(usuarioMotoTest.cilindrajeAlto)

    }

    @Test
    fun initValidacionDeFormatoPlaca_formatoAlfanumericoCorrectoMotoAltoCilindrajeVerdadero_retornaCilindrajeAltoVerdadero() {

        //Arrange
        val placa = "hSu53"
        val altoCilindraje = true

        //Act
        val usuarioMotoTest = UsuarioVehiculoMoto(placa, altoCilindraje)

        //Assert
        Assert.assertTrue(usuarioMotoTest.cilindrajeAlto)

    }

    @Test
    fun initValidacionDeFormatoPlaca_formatoAlfanumericoCorrectoMotoUltimoCampoLetraAltoCilindrajeVerdadero_retornaCilindrajeAltoverdadero() {

        //Arrange
        val placa = "hSu53c"
        val altoCilindraje = true

        //Act
        val usuarioMotoTest = UsuarioVehiculoMoto(placa, altoCilindraje)

        //Assert
        Assert.assertTrue(usuarioMotoTest.cilindrajeAlto)

    }

    @Test
    fun usuarioMotoPlaca_FormatoAlfanumericoCorrectoAltoCilindrajePorDefectoFalso_retornaCilindrajeAltoFalso() {

        //Arrange
        val placa = "hSu-53c"

        //Act
        val usuarioMotoTest = UsuarioVehiculoMoto(placa, false)

        //Assert
        Assert.assertFalse(usuarioMotoTest.cilindrajeAlto)

    }
}