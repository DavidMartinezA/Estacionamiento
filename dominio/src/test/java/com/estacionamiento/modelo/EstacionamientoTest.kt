package com.estacionamiento.modelo

import com.usuario.Usuario
import com.usuario.UsuarioCarro
import com.usuario.UsuarioMoto
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
    @Test
    fun consultarCapacidad_CapacidadCorrectaCarros_CapacidadTrue() {

        //Arrange
        val listaUsuarios = ArrayList<Usuario>()
        val usuarioCarrosTest = UsuarioCarro("Asu531")

        //Act
        val capacidad = Estacionamiento().consultarCapacidad(usuarioCarrosTest,
            listaUsuarios)

        //Assert
        assert(capacidad)
    }

    @Test
    fun consultarCapacidad_CapacidadCorrectaMotos_CapacidadTrue() {

        //Arrange
        val listaUsuarios = ArrayList<Usuario>()
        val usuarioMotoTest = UsuarioMoto("Asu531", true)

        //Act
        val capacidad = Estacionamiento().consultarCapacidad(usuarioMotoTest,
            listaUsuarios)

        //Assert
        assert(capacidad)
    }
}