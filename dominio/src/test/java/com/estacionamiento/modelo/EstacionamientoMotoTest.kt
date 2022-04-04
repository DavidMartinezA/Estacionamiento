package com.estacionamiento.modelo

import com.usuario.modelo.UsuarioVehiculoMoto
import org.junit.Assert
import org.junit.Test
import java.time.LocalDateTime

class EstacionamientoMotoTest {

    private val horaIngreso = LocalDateTime.now()

    @Test
    fun restriccionDeIngreso_placaLetraInicialADiaRestringidoMartesAltoCilindrajeTrue_ingresoRestringido() {

        //Arrange
        val usuarioVehiculoMoto = UsuarioVehiculoMoto("ASU531", true)
        val estacionamientoMoto = EstacionamientoMoto(usuarioVehiculoMoto, horaIngreso)
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
        val estacionamientoMoto = EstacionamientoMoto(usuarioVehiculoMoto, horaIngreso)

        //Act
        val restriccionIngreso = estacionamientoMoto.restriccionDeIngreso(1)

        //Assert
        Assert.assertTrue(!restriccionIngreso)

    }
}