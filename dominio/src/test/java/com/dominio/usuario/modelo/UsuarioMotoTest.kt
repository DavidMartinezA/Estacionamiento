package com.dominio.usuario.modelo

import com.dominio.usuario.modelo.UsuarioMoto
import org.junit.Test

class UsuarioMotoTest {

    @Test
    fun usuarioMotoPlaca_FormatoAlfanumericoCorrectoMotoCincoCamposAltoCilindrajeFalso_CilindrajeAltoFalse() {

        //Arrange
        val placa = "hSu53"
        val altoCilindraje = false

        //Act
        val usuarioMotoTest= UsuarioMoto(placa, altoCilindraje)

        //Assert
        assert(!usuarioMotoTest.cilindrajeAlto)

    }

    @Test
    fun usuarioMotoPlaca_FormatoAlfanumericoCorrectoMotoAltoCilindrajeTrue_CilindrajeAltoTrue() {

        //Arrange
        val placa = "hSu53"
        val altoCilindraje = true

        //Act
        val usuarioMotoTest= UsuarioMoto(placa, altoCilindraje)

        //Assert
        assert(usuarioMotoTest.cilindrajeAlto)

    }

    @Test
    fun usuarioMotoPlaca_FormatoAlfanumericoCorrectoMotoUltimoCampoLetraAltoCilindrajeTrue_CilindrajeAltoTrue() {

        //Arrange
        val placa = "hSu53c"
        val altoCilindraje = true

        //Act
        val usuarioMotoTest= UsuarioMoto(placa, altoCilindraje)

        //Assert
        assert(usuarioMotoTest.cilindrajeAlto)

    }

    @Test
    fun usuarioMotoPlaca_FormatoAlfanumericoCorrectoAltoCilindrajePorDefectoFalse_CilindrajeAltoFalse() {

        //Arrange
        val placa = "hSu-53c"

        //Act
        val usuarioMotoTest= UsuarioMoto(placa)

        //Assert
        assert(!usuarioMotoTest.cilindrajeAlto)

    }
}