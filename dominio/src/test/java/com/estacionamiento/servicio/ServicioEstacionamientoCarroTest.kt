package com.estacionamiento.servicio


import com.estacionamiento.modelo.EstacionamientoCarro
import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.excepciones.IngresoNoPermitidoRestriccionExcepcion
import com.excepciones.UsuarioNoExisteExcepcion
import com.excepciones.UsuarioYaExisteExcepcion
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDateTime

@RunWith(MockitoJUnitRunner::class)
class ServicioEstacionamientoCarroTest {

    @Mock
    private lateinit var repositorioEstacionamiento: RepositorioEstacionamiento

    private val horaIngreso = LocalDateTime.now()
    private val usuarioVehiculoCarro = UsuarioVehiculoCarro("HSU531")
    private val listaUsuarios: ArrayList<UsuarioVehiculo> = arrayListOf(usuarioVehiculoCarro)
    private val estacionamientoCarro = EstacionamientoCarro(usuarioVehiculoCarro, horaIngreso)

    @Test
    fun guardarUsuario_UsuarioNoExiste_UsuarioGuardado() {

        //Arrange
        val servicioEstacionamientoCarro =
            ServicioEstacionamientoCarro(estacionamientoCarro, repositorioEstacionamiento)

        //Act
        var guardarUsuario = servicioEstacionamientoCarro.guardarUsuario()

    }

    @Test
    fun guardarUsuario_UsuarioExiste_lanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "UsuarioVehiculo Ya Existe"
        val servicioEstacionamientoCarro =
            ServicioEstacionamientoCarro(estacionamientoCarro, repositorioEstacionamiento)
        Mockito.`when`(repositorioEstacionamiento.usuarioExiste(usuarioVehiculoCarro))
            .thenReturn(true)

        //Act
        try {

            var guardarUsuario = servicioEstacionamientoCarro.guardarUsuario()
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
            ServicioEstacionamientoCarro(estacionamientoCarro, repositorioEstacionamiento)
        Mockito.`when`(repositorioEstacionamiento.usuarioExiste(usuarioVehiculoCarro))
            .thenReturn(false)

        //Act
        try {
            var guardarUsuario = servicioEstacionamientoCarro.eliminarUsuario()
            Assert.fail()
        } catch (ex: UsuarioNoExisteExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }

    }

    @Test
    fun eliminarUsuario_UsuarioNoExiste_EliminarUsuario() {

        //Arrange
        val servicioEstacionamientoCarro =
            ServicioEstacionamientoCarro(estacionamientoCarro, repositorioEstacionamiento)
        Mockito.`when`(repositorioEstacionamiento.usuarioExiste(usuarioVehiculoCarro))
            .thenReturn(true)

        //Act

        val guardarUsuario = servicioEstacionamientoCarro.eliminarUsuario()

        //Assert
        Assert.assertNotNull(guardarUsuario)
    }

    @Test
    fun consultarListaUsuarios() {

        //Arrange
        val servicioEstacionamientoCarro =
            ServicioEstacionamientoCarro(estacionamientoCarro, repositorioEstacionamiento)
        Mockito.`when`(repositorioEstacionamiento.listaUsuarios()).thenReturn(listaUsuarios)

        //Act
        val consultaDeListaUsuarios = servicioEstacionamientoCarro.consultarListaUsuarios()

        //Assert
        assert(consultaDeListaUsuarios == listaUsuarios)
    }

    @Test
    fun consultaDisponibilidadEstacionamiento_CapacidadDeCarros_RetornaCapacidad() {

        //Arrange
        val servicioEstacionamientoCarro =
            ServicioEstacionamientoCarro(estacionamientoCarro, repositorioEstacionamiento)
        Mockito.`when`(repositorioEstacionamiento.listaUsuarios()).thenReturn(listaUsuarios)

        //Act
        val capacidad = servicioEstacionamientoCarro.consultaDisponibilidadEstacionamiento()

        //Assert
        assert(capacidad)
    }

    @Test
    fun ingresoUsuarioEstacionamiento_UsuarioNoExiste_IngresarUsuario() {

        //Arrange
        val servicioEstacionamientoCarro =
            ServicioEstacionamientoCarro(estacionamientoCarro, repositorioEstacionamiento)
        Mockito.`when`(repositorioEstacionamiento.usuarioExiste(usuarioVehiculoCarro))
            .thenReturn(false)

        //Act
        val ingresarUsuarios = servicioEstacionamientoCarro.ingresoUsuarioEstacionamiento(1)

    }

    @Test
    fun ingresoUsuarioEstacionamiento_UsuarioExiste_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "No Esta Autorizado A Ingresar"
        val servicioEstacionamientoCarro =
            ServicioEstacionamientoCarro(estacionamientoCarro, repositorioEstacionamiento)
        Mockito.`when`(repositorioEstacionamiento.usuarioExiste(usuarioVehiculoCarro))
            .thenReturn(true)

        //Act
        try {
            val ingresarUsuarios = servicioEstacionamientoCarro.ingresoUsuarioEstacionamiento(1)
            Assert.fail()
        } catch (ex: IngresoNoPermitidoRestriccionExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

}
