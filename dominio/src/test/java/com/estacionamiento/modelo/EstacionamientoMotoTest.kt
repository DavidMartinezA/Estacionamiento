package com.estacionamiento.modelo

import com.usuario.modelo.UsuarioVehiculoMoto
import org.junit.Assert
import org.junit.Test

class EstacionamientoMotoTest {

    @Test
    fun restriccionDeIngreso_placaLetraInicialADiaRestringidoMartesAltoCilindrajeTrue_ingresoRestringido() {

        //Arrange
        val usuarioVehiculoMoto = UsuarioVehiculoMoto("ASU531", true)
        val estacionamientoMoto = EstacionamientoMoto(usuarioVehiculoMoto)
        val diaMartes = 2

        //Act
        val restriccionIngreso = estacionamientoMoto.restriccionDeIngreso(diaMartes)

        //Assert
        Assert.assertTrue(restriccionIngreso)

    }

    @Test
    fun restriccionDeIngreso_placaLetraInicialADiaNoRestringidoLunesAltoCilindrajeFalse_ingresoNoRestringido() {

        //Arrange
        val usuarioVehiculoMoto = UsuarioVehiculoMoto("ASU531", false)
        val estacionamientoMoto = EstacionamientoMoto(usuarioVehiculoMoto)

        //Act
        val restriccionIngreso = estacionamientoMoto.restriccionDeIngreso(1)

        //Assert
        Assert.assertFalse(restriccionIngreso)

    }
}