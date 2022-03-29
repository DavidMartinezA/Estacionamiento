package com.estacionamiento.servicio

import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.excepciones.FormatoPlacaExcepcion
import com.excepciones.UsuarioNoExisteExcepcion

import com.usuario.modelo.UsuarioVehiculoCarro
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ServicioEstacionamientoTest {

    @Mock
    private lateinit var repositorioEstacionamiento: RepositorioEstacionamiento

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Test()
    fun ingresoUsuarioEstacionamiento_ParametrosVacios_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"

        //Act
        try {
            val usuarioCarro = UsuarioVehiculoCarro("")
            val servicioEstacionamiento =
                ServicioEstacionamiento(usuarioCarro, repositorioEstacionamiento)
            servicioEstacionamiento.salidaDeUsuariosEstacionamiento(usuarioCarro)

        } catch (ex: FormatoPlacaExcepcion) {
            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)

        }
    }

    @Test
    fun salidaUsuarioEstacionamiento_UsuarioNoExiste_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "UsuarioVehiculo No Existe"
        val usuarioCarro = UsuarioVehiculoCarro("hsu531")

        val servicioEstacionamiento =
            ServicioEstacionamiento(usuarioCarro, repositorioEstacionamiento)

        //Act
        try {
            servicioEstacionamiento.salidaDeUsuariosEstacionamiento(usuarioCarro)

        } catch (ex: UsuarioNoExisteExcepcion) {
            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)

        }
    }

}