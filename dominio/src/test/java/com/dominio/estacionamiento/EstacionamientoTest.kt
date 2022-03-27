package com.dominio.estacionamiento

import com.dominio.estacionamiento.modelo.Estacionamiento
import com.dominio.usuario.modelo.UsuarioCarro
import org.junit.Test

class EstacionamientoTest {

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialADiaRestringido_RestringidoTrue() {

        //Arrange
        val usuarioCarrosTest = UsuarioCarro("Asu531")
        val diaDeLaSemana = 2
        val estacionamiento = Estacionamiento()

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
        val estacionamiento = Estacionamiento()

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
        val estacionamiento = Estacionamiento()

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
        val estacionamiento = Estacionamiento()

        //Act
        val restringido = estacionamiento.restriccionDeIngreso(usuarioCarrosTest, diaDeLaSemana)

        //Assert
        assert(!restringido)
    }
    //TODO crear test
    fun consultarCapacidad_CapacidadCorrectaCarros_CapacidadTrue(){}
    fun consultarCapacidad_CapacidadCorrectaMotos_CapacidadTrue(){}
    fun consultarCapacidad_CapacidadIncorrectaCarros_CapacidadFalse(){}
    fun consultarCapacidad_CapacidadIncorrectaMotos_CapacidadFalse(){}
}