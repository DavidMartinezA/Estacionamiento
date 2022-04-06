package com.estacionamiento.modelo

import com.usuario.modelo.UsuarioVehiculoCarro
import org.junit.Assert
import org.junit.Test
import java.time.LocalDateTime

class EstacionamientoCarroTest {

    private val horaIngreso = LocalDateTime.now()

    @Test
    fun validacionDeCreacionEstacionamiento_usuarioYHoraCorrectos_retornaElUsuario() {

        //Arrange
        val usuario = UsuarioVehiculoCarro("has531")
        val validacion = EstacionamientoCarro(usuario, LocalDateTime.now())

        //Act
        val validacionUsuario = validacion.usuarioVehiculo

        //Assert
        Assert.assertEquals(validacionUsuario, usuario)
    }

    @Test
    fun validacionDeCreacionEstacionamiento_usuarioYHoraCorrectos_retornaLaHora() {

        //Arrange
        val usuario = UsuarioVehiculoCarro("has531")
        val hora = LocalDateTime.now()

        val validacion = EstacionamientoCarro(usuario, hora)

        //Act
        val validacionhora = validacion.horaFechaIngresoUsuario

        //Assert
        Assert.assertEquals(validacionhora, hora)
    }

    @Test
    fun restriccionDeIngreso_placaLetraInicialADiaRestringidoMartes_presentaRestriccion() {

        //Arrange
        val usuarioVehiculoCarro = UsuarioVehiculoCarro("ASU531")
        val estacionamientoCarro = EstacionamientoCarro(usuarioVehiculoCarro, horaIngreso)
        val diaMartes = 2

        //Act
        val restriccionIngreso = estacionamientoCarro.restriccionDeIngreso(diaMartes)

        //Assert
        Assert.assertTrue(restriccionIngreso)
    }

    @Test
    fun restriccionDeIngreso_placaLetraInicialADiaRestringidoViernes_presentaRestriccion() {

        //Arrange
        val usuarioVehiculoCarro = UsuarioVehiculoCarro("ASU531")
        val estacionamientoCarro = EstacionamientoCarro(usuarioVehiculoCarro, horaIngreso)
        val diaViernes = 5

        //Act
        val restriccionIngreso = estacionamientoCarro.restriccionDeIngreso(diaViernes)

        //Assert
        Assert.assertTrue(restriccionIngreso)
    }

    @Test
    fun restriccionDeIngreso_placaLetraInicialDiferenteADiaRestringido_noPresentaRestriccion() {

        //Arrange
        val usuarioVehiculoCarro = UsuarioVehiculoCarro("hSU531")
        val estacionamientoCarro = EstacionamientoCarro(usuarioVehiculoCarro, horaIngreso)
        val diaMiercoles = 3

        //Act
        val restriccionIngreso = estacionamientoCarro.restriccionDeIngreso(diaMiercoles)

        //Assert
        Assert.assertFalse(restriccionIngreso)

    }

    @Test
    fun restriccionDeIngreso_placaLetraInicialDiferenteADiaNoRestringidoDomingo_noPresentaRestriccion() {

        //Arrange
        val usuarioVehiculoCarro = UsuarioVehiculoCarro("hSU531")
        val estacionamientoCarro = EstacionamientoCarro(usuarioVehiculoCarro, horaIngreso)
        val diaDomingo = 7

        //Act
        val restriccionIngreso = estacionamientoCarro.restriccionDeIngreso(diaDomingo)

        //Assert
        Assert.assertFalse(restriccionIngreso)

    }

    @Test
    fun restriccionDeIngreso_placaLetraInicialADiaNoRestringidoLunes_noPresentaRestriccion() {

        //Arrange
        val usuarioVehiculoCarro = UsuarioVehiculoCarro("ASU531")
        val estacionamientoCarro = EstacionamientoCarro(usuarioVehiculoCarro, horaIngreso)
        val diaLunes = 1

        //Act
        val restriccionIngreso = estacionamientoCarro.restriccionDeIngreso(diaLunes)

        //Assert
        Assert.assertFalse(restriccionIngreso)

    }

    @Test
    fun restriccionDeIngreso_placaLetraInicialADiaNoRestringidoDomingo_noPresentaRestriccion() {

        //Arrange
        val usuarioVehiculoCarro = UsuarioVehiculoCarro("ASU531")
        val estacionamientoCarro = EstacionamientoCarro(usuarioVehiculoCarro, horaIngreso)
        val diaDomingo = 7

        //Act
        val restriccionIngreso = estacionamientoCarro.restriccionDeIngreso(diaDomingo)

        //Assert
        Assert.assertFalse(restriccionIngreso)

    }

}