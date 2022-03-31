package com.estacionamiento.servicio

import com.estacionamiento.modelo.EstacionamientoCarro
import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.excepciones.UsuarioYaExisteExcepcion
import com.usuario.modelo.UsuarioVehiculoMoto
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDateTime


@RunWith(MockitoJUnitRunner::class)
class ServicioEstacionamientoMotoTest {


    @Mock
    private lateinit var repositorioEstacionamiento: RepositorioEstacionamiento

    private val horaIngreso = LocalDateTime.now()
    private val usuarioVehiculoMoto = UsuarioVehiculoMoto("HSU531", true)
    private val estacionamientoMoto = EstacionamientoCarro(usuarioVehiculoMoto, horaIngreso)

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Test()
    fun ingresoUsuarioEstacionamiento_UsuarioNoExiste_LanzarExcepcion() {

        //Arrange
        val servicioEstacionamientoMoto =
            ServicioEstacionamientoCarro(estacionamientoMoto, repositorioEstacionamiento)
        Mockito.`when`(repositorioEstacionamiento.usuarioExiste(usuarioVehiculoMoto))
            .thenReturn(false)

        //Act
        val ingresarUsuarios = servicioEstacionamientoMoto.ingresoUsuarioEstacionamiento(1)


    }

    @Test()
    fun ingresoUsuarioEstacionamiento_UsuarioExiste_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "UsuarioVehiculo Ya Existe"
        val servicioEstacionamientoMoto =
            ServicioEstacionamientoCarro(estacionamientoMoto, repositorioEstacionamiento)
        Mockito.`when`(repositorioEstacionamiento.usuarioExiste(usuarioVehiculoMoto))
            .thenReturn(true)

        //Act
        try {

            var guardarUsuario = servicioEstacionamientoMoto.guardarUsuario()
            Assert.fail()
        } catch (ex: UsuarioYaExisteExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }


}