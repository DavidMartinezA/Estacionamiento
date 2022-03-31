package com.estacionamiento.modelo

import com.usuario.modelo.UsuarioVehiculoMoto
import org.junit.Test
import java.time.LocalDateTime

class EstacionamientoMotoTest {

    private val horaIngreso = LocalDateTime.now()

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialADiaRestringidoAltoCilindrajeTrue_RestringidoTrue() {

        //Arrange
        val usuarioVehiculoMoto = UsuarioVehiculoMoto("ASU531", true)
        val estacionamientoMoto = EstacionamientoMoto(usuarioVehiculoMoto, horaIngreso)

        //Act
        val restriccionIngreso = estacionamientoMoto.restriccionDeIngreso(2)

        //Assert
        assert(restriccionIngreso)
    }

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialADiaRestringidoAltoCilindrajeFalse_RestringidoTrue() {

        //Arrange
        val usuarioVehiculoMoto = UsuarioVehiculoMoto("hSU531", false)
        val estacionamientoMoto = EstacionamientoMoto(usuarioVehiculoMoto, horaIngreso)

        //Act
        val restriccionIngreso = estacionamientoMoto.restriccionDeIngreso(1)

        //Assert
        assert(!restriccionIngreso)
    }
}