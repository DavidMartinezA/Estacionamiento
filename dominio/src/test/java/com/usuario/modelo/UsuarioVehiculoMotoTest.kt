package com.usuario.modelo

import org.junit.Test

class UsuarioVehiculoMotoTest {

    @Test
    fun usuarioMotoPlaca_FormatoAlfanumericoCorrectoMotoCincoCamposAltoCilindrajeFalso_CilindrajeAltoFalse() {

        //Arrange
        val placa = "hSu53"
        val altoCilindraje = false

        //Act
        val usuarioMotoTest = UsuarioVehiculoMoto(placa, altoCilindraje)

        //Assert
        assert(!usuarioMotoTest.cilindrajeAlto)

    }

    @Test
    fun usuarioMotoPlaca_FormatoAlfanumericoCorrectoMotoAltoCilindrajeTrue_CilindrajeAltoTrue() {

        //Arrange
        val placa = "hSu53"
        val altoCilindraje = true

        //Act
        val usuarioMotoTest = UsuarioVehiculoMoto(placa, altoCilindraje)

        //Assert
        assert(usuarioMotoTest.cilindrajeAlto)

    }

    @Test
    fun usuarioMotoPlaca_FormatoAlfanumericoCorrectoMotoUltimoCampoLetraAltoCilindrajeTrue_CilindrajeAltoTrue() {

        //Arrange
        val placa = "hSu53c"
        val altoCilindraje = true

        //Act
        val usuarioMotoTest = UsuarioVehiculoMoto(placa, altoCilindraje)

        //Assert
        assert(usuarioMotoTest.cilindrajeAlto)

    }

    @Test
    fun usuarioMotoPlaca_FormatoAlfanumericoCorrectoAltoCilindrajePorDefectoFalse_CilindrajeAltoFalse() {

        //Arrange
        val placa = "hSu-53c"

        //Act
        val usuarioMotoTest = UsuarioVehiculoMoto(placa)

        //Assert
        assert(!usuarioMotoTest.cilindrajeAlto)

    }
}