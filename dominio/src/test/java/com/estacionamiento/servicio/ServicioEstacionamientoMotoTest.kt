package com.estacionamiento.servicio

import com.estacionamiento.modelo.EstacionamientoCarro
import com.excepciones.IngresoNoPermitidoRestriccionExcepcion
import com.excepciones.UsuarioNoExisteExcepcion
import com.excepciones.UsuarioYaExisteExcepcion
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro
import com.usuario.repositorio.RepositorioUsuarioVehiculoMoto
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
    private lateinit var repositorioUsuarioVehiculoMoto: RepositorioUsuarioVehiculoMoto

    private val horaIngreso = LocalDateTime.now()
    private val usuarioVehiculoCarro = UsuarioVehiculoCarro("HSU531")
    private val listaUsuarios: ArrayList<UsuarioVehiculo> = arrayListOf(usuarioVehiculoCarro)
    private val estacionamientoCarro = EstacionamientoCarro(usuarioVehiculoCarro, horaIngreso)

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun ingresoUsuarioEstacionamiento_UsuarioNoExiste_UsuarioGuardado() {

        //Arrange
        val servicioEstacionamientoCarro =
            ServicioEstacionamientoMoto(estacionamientoCarro, repositorioUsuarioVehiculoMoto)
        Mockito.`when`(repositorioUsuarioVehiculoMoto.usuarioExiste(usuarioVehiculoCarro))
            .thenReturn(false)

        //Act
        val guardarUsuario = servicioEstacionamientoCarro.ingresoUsuarioEstacionamiento(3)

        //Assert
        Assert.assertNotNull(guardarUsuario)
    }

    @Test
    fun ingresoUsuarioEstacionamiento_UsuarioRestringido_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "No Esta Autorizado A Ingresar"
        val usuario = UsuarioVehiculoCarro("ASU531")
        val estacionamientoCarro = EstacionamientoCarro(usuario, horaIngreso)
        val servicioEstacionamientoCarro =
            ServicioEstacionamientoMoto(estacionamientoCarro, repositorioUsuarioVehiculoMoto)
        Mockito.`when`(repositorioUsuarioVehiculoMoto.usuarioExiste(usuario))
            .thenReturn(false)

        //Act
        try {

            var restringido = servicioEstacionamientoCarro.ingresoUsuarioEstacionamiento(3)
            Assert.fail()
        } catch (ex: IngresoNoPermitidoRestriccionExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    @Test
    fun ingresoUsuarioEstacionamiento_UsuarioExiste_lanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "UsuarioVehiculo Ya Existe"
        val servicioEstacionamientoCarro =
            ServicioEstacionamientoMoto(estacionamientoCarro, repositorioUsuarioVehiculoMoto)
        Mockito.`when`(repositorioUsuarioVehiculoMoto.usuarioExiste(usuarioVehiculoCarro))
            .thenReturn(true)

        //Act
        try {

            var guardarUsuario = servicioEstacionamientoCarro.ingresoUsuarioEstacionamiento(3)
            Assert.fail()
        } catch (ex: UsuarioYaExisteExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }

    }

    @Test
    fun eliminarUsuario_UsuarioNoExiste_lanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "UsuarioVehiculo No Existe"
        val servicioEstacionamientoCarro =
            ServicioEstacionamientoMoto(estacionamientoCarro, repositorioUsuarioVehiculoMoto)

        //Act
        try {
            var eliminarUsuario = servicioEstacionamientoCarro.eliminarUsuario()
            Assert.fail()
        } catch (ex: UsuarioNoExisteExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }

    }

    @Test
    fun eliminarUsuario_UsuarioExiste_EliminarUsuario() {

        //Arrange
        val servicioEstacionamientoCarro =
            ServicioEstacionamientoMoto(estacionamientoCarro, repositorioUsuarioVehiculoMoto)
        Mockito.`when`(repositorioUsuarioVehiculoMoto.usuarioExiste(usuarioVehiculoCarro))
            .thenReturn(true)

        //Act

        val guardarUsuario = servicioEstacionamientoCarro.eliminarUsuario()

        //Assert
        Assert.assertNotNull(guardarUsuario)
    }

    @Test
    fun consultarListaUsuarios_ParametrosCorrectos_RetornarListaUsuarios() {

        //Arrange
        val servicioEstacionamientoCarro =
            ServicioEstacionamientoMoto(estacionamientoCarro, repositorioUsuarioVehiculoMoto)
        Mockito.`when`(repositorioUsuarioVehiculoMoto.listaUsuarios()).thenReturn(listaUsuarios)

        //Act
        val consultaDeListaUsuarios = servicioEstacionamientoCarro.consultarListaUsuarios()

        //Assert
        assert(consultaDeListaUsuarios == listaUsuarios)
    }

    @Test
    fun consultaDisponibilidadEstacionamiento_CapacidadDeCarros_RetornaCapacidad() {

        //Arrange
        val servicioEstacionamientoCarro =
            ServicioEstacionamientoMoto(estacionamientoCarro, repositorioUsuarioVehiculoMoto)
        Mockito.`when`(repositorioUsuarioVehiculoMoto.listaUsuarios()).thenReturn(listaUsuarios)

        //Act
        val capacidad = servicioEstacionamientoCarro.consultaDisponibilidadEstacionamiento()

        //Assert
        assert(capacidad)
    }

    @Test
    fun consultaDisponibilidadEstacionamiento_CapacidadDeMotos_RetornaCapacidad() {

        //Arrange
        val servicioEstacionamientoCarro =
            ServicioEstacionamientoMoto(estacionamientoCarro, repositorioUsuarioVehiculoMoto)
        Mockito.`when`(repositorioUsuarioVehiculoMoto.listaUsuarios()).thenReturn(listaUsuarios)

        //Act
        val capacidad = servicioEstacionamientoCarro.consultaDisponibilidadEstacionamiento()

        //Assert
        assert(capacidad)
    }

}