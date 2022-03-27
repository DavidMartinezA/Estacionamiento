package com.dominio.estacionamiento

import com.dominio.usuario.modelo.UsuarioCarro
import org.junit.Test

class EstacionamientoCarrosTest {

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialADiaRestringido_RestringidoTrue() {

        //Arrange
        val usuarioCarrosTest = UsuarioCarro("Asu531")
        val diaDeLaSemana = 2
        val estacionamiento = EstacionamientoCarros()

        //Act
        val restringido = estacionamiento.restriccionDeIngreso(usuarioCarrosTest, diaDeLaSemana)

        //Assert
        assert(restringido)
    }

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialDiferenteADiaRestringido_RestringidoFalse() {


        //Arrange
        val usuarioCarrosTest = UsuarioCarro("Hsu531")
        val diaDeLaSemana = 5
        val estacionamiento = EstacionamientoCarros()

        //Act
        val restringido = estacionamiento.restriccionDeIngreso(usuarioCarrosTest, diaDeLaSemana)

        //Assert
        assert(!restringido)
    }

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialDiferenteADiaNoRestringido_RestringidoFalse() {


        //Arrange
        val usuarioCarrosTest = UsuarioCarro("Hsu531")
        val diaDeLaSemana = 7
        val estacionamiento = EstacionamientoCarros()

        //Act
        val restringido = estacionamiento.restriccionDeIngreso(usuarioCarrosTest, diaDeLaSemana)

        //Assert
        assert(!restringido)
    }

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialADiaNoRestringido_RestringidoFalse() {


        //Arrange
        val usuarioCarrosTest = UsuarioCarro("Asu531")
        val diaDeLaSemana = 1
        val estacionamiento = EstacionamientoCarros()

        //Act
        val restringido = estacionamiento.restriccionDeIngreso(usuarioCarrosTest, diaDeLaSemana)

        //Assert
        assert(!restringido)
    }
}