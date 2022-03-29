package com.estacionamiento.modelo

import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro
import com.usuario.modelo.UsuarioVehiculoMoto
import org.junit.Test

class EstacionamientoTest {

    private val estacionamiento = Estacionamiento()

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialADiaRestringido_RestringidoTrue() {

        //Arrange
        val usuarioCarrosTest = UsuarioVehiculoCarro("Asu531")
        val diaDeLaSemana = 2

        //Act
        val restringido = estacionamiento.restriccionDeIngreso(usuarioCarrosTest, diaDeLaSemana)

        //Assert
        assert(restringido)
    }

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialDiferenteADiaRestringido_RestringidoFalse() {

        //Arrange
        val usuarioCarrosTest = UsuarioVehiculoCarro("Hsu531")
        val diaDeLaSemana = 5

        //Act
        val restringido = estacionamiento.restriccionDeIngreso(usuarioCarrosTest, diaDeLaSemana)

        //Assert
        assert(!restringido)
    }

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialDiferenteADiaNoRestringido_RestringidoFalse() {

        //Arrange
        val usuarioCarrosTest = UsuarioVehiculoCarro("Hsu531")
        val diaDeLaSemana = 7

        //Act
        val restringido = estacionamiento.restriccionDeIngreso(usuarioCarrosTest, diaDeLaSemana)

        //Assert
        assert(!restringido)
    }

    @Test
    fun restriccionDeIngreso_PlacaLetraInicialADiaNoRestringido_RestringidoFalse() {

        //Arrange
        val usuarioCarrosTest = UsuarioVehiculoCarro("Asu531")
        val diaDeLaSemana = 1

        //Act
        val restringido = estacionamiento.restriccionDeIngreso(usuarioCarrosTest, diaDeLaSemana)

        //Assert
        assert(!restringido)
    }

    @Test
    fun consultarCapacidad_CapacidadCorrectaCarros_CapacidadTrue() {

        //Arrange
        val listaUsuarios = ArrayList<UsuarioVehiculo>()
        val usuarioCarrosTest = UsuarioVehiculoCarro("Asu531")

        //Act
        val capacidad = Estacionamiento().consultarCapacidad(usuarioCarrosTest,
            listaUsuarios)

        //Assert
        assert(capacidad)
    }

    @Test
    fun consultarCapacidad_CapacidadCorrectaMotos_CapacidadTrue() {

        //Arrange
        val listaUsuarios = ArrayList<UsuarioVehiculo>()
        val usuarioMotoTest = UsuarioVehiculoMoto("Asu531", true)

        //Act
        val capacidad = Estacionamiento().consultarCapacidad(usuarioMotoTest,
            listaUsuarios)

        //Assert
        assert(capacidad)
    }
}