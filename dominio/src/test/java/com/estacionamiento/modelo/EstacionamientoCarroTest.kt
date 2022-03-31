package com.estacionamiento.modelo

import com.usuario.modelo.UsuarioVehiculoCarro
import org.junit.Test
import java.time.LocalDateTime

class EstacionamientoCarroTest {

    private val horaIngreso = LocalDateTime.now()

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialADiaRestringido_RestringidoTrue() {

        //Arrange
        val usuarioVehiculoCarro = UsuarioVehiculoCarro("ASU531")
        val estacionamientoCarro = EstacionamientoCarro(usuarioVehiculoCarro, horaIngreso)

        //Act
        val restriccionIngreso = estacionamientoCarro.restriccionDeIngreso(2)

        //Assert
        assert(restriccionIngreso)
    }

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialADiaRestringidoDos_RestringidoTrue() {

        //Arrange
        val usuarioVehiculoCarro = UsuarioVehiculoCarro("ASU531")
        val estacionamientoCarro = EstacionamientoCarro(usuarioVehiculoCarro, horaIngreso)

        //Act
        val restriccionIngreso = estacionamientoCarro.restriccionDeIngreso(5)

        //Assert
        assert(restriccionIngreso)
    }

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialDiferenteADiaRestringido_RestringidoFalse() {

        //Arrange
        val usuarioVehiculoCarro = UsuarioVehiculoCarro("hSU531")
        val estacionamientoCarro = EstacionamientoCarro(usuarioVehiculoCarro, horaIngreso)

        //Act
        val restriccionIngreso = estacionamientoCarro.restriccionDeIngreso(3)

        //Assert
        assert(!restriccionIngreso)
    }

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialDiferenteADiaNoRestringido_RestringidoFalse() {

        //Arrange
        val usuarioVehiculoCarro = UsuarioVehiculoCarro("hSU531")
        val estacionamientoCarro = EstacionamientoCarro(usuarioVehiculoCarro, horaIngreso)

        //Act
        val restriccionIngreso = estacionamientoCarro.restriccionDeIngreso(3)

        //Assert
        assert(!restriccionIngreso)
    }

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialADiaNoRestringido_RestringidoFalse() {

        //Arrange
        val usuarioVehiculoCarro = UsuarioVehiculoCarro("ASU531")
        val estacionamientoCarro = EstacionamientoCarro(usuarioVehiculoCarro, horaIngreso)

        //Act
        val restriccionIngreso = estacionamientoCarro.restriccionDeIngreso(1)

        //Assert
        assert(!restriccionIngreso)
    }

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialADiaNoRestringidoDos_RestringidoFalse() {

        //Arrange
        val usuarioVehiculoCarro = UsuarioVehiculoCarro("ASU531")
        val estacionamientoCarro = EstacionamientoCarro(usuarioVehiculoCarro, horaIngreso)

        //Act
        val restriccionIngreso = estacionamientoCarro.restriccionDeIngreso(7)

        //Assert
        assert(!restriccionIngreso)
    }


}