package com.example.estacionamiento.servicio

import com.example.estacionamiento.excepcion.IngresoNoPermitidoRestriccionExcepcion
import com.example.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.example.estacionamiento.excepcion.UsuarioYaExisteExcepcion
import com.example.estacionamiento.modelo.EstacionamientoCarro
import com.example.estacionamiento.modelo.EstacionamientoMoto
import com.example.usuario.modelo.UsuarioVehiculo
import com.example.usuario.modelo.UsuarioVehiculoCarro
import com.example.usuario.modelo.UsuarioVehiculoMoto
import com.example.usuario.repositorio.RepositorioUsuarioVehiculo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ServicioEstacionamientoTest {

    @Mock
    private lateinit var repositorioUsuarioVehiculo: RepositorioUsuarioVehiculo

    private val usuarioVehiculoCarro = UsuarioVehiculoCarro("HSU531")
    private val listaUsuarios: ArrayList<UsuarioVehiculo> = arrayListOf(usuarioVehiculoCarro)
    private val estacionamientoCarro = EstacionamientoCarro(usuarioVehiculoCarro)

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun consultarListaUsuarios_parametrosCorrectos_retornaListaUsuarios() = runTest {

        //Arrange
        Mockito.`when`(repositorioUsuarioVehiculo.listaUsuarios()).thenReturn(listaUsuarios)

        //Act
        val consultaDeListaUsuarios =
            ServicioEstacionamiento(repositorioUsuarioVehiculo).consultarListaUsuarios()

        //Assert
        Assert.assertEquals(listaUsuarios, consultaDeListaUsuarios)
    }

    @Test
    fun disponibilidadEstacionamiento_estacionamientoConCapacidadCarros_retornaCapacidadCarrosVerdadero() = runTest {

        //Arrange
        Mockito.`when`(repositorioUsuarioVehiculo.listaUsuarios()).thenReturn(listaUsuarios)

        //Act
        val capacidad =
            ServicioEstacionamiento(repositorioUsuarioVehiculo).consultaDisponibilidadEstacionamiento(estacionamientoCarro)

        //Assert
        Assert.assertTrue(capacidad)
    }

    @Test
    fun disponibilidadEstacionamiento_estacionamientoConCapacidadMotos_retornaCapacidadMotosVerdadero() {

        //Arrange
        val usuarioVehiculoMoto = UsuarioVehiculoMoto("HSU531", true)
        val listaUsuarios: ArrayList<UsuarioVehiculo> = arrayListOf(usuarioVehiculoMoto)
        val estacionamientoMoto = EstacionamientoMoto(usuarioVehiculoMoto)
        var capacidad = false
        runTest {
            Mockito.`when`(repositorioUsuarioVehiculo.listaUsuarios()).thenReturn(listaUsuarios)
        }

        //Act
        runTest {
            capacidad =
                ServicioEstacionamiento(repositorioUsuarioVehiculo).consultaDisponibilidadEstacionamiento(estacionamientoMoto)
        }
        //Assert
        Assert.assertTrue(capacidad)
    }

    @Test
    fun ingresoUsuarioEstacionamiento_elUsuarioNoExiste_usuarioRegistradoNoRetorna() = runTest {

        //Arrange
        val diaLunes = 1
        Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuarioVehiculoCarro.placaVehiculo)).thenReturn(false)
        Mockito.`when`(repositorioUsuarioVehiculo.listaUsuarios()).thenReturn(listaUsuarios)
        Mockito.`when`(repositorioUsuarioVehiculo.guardarUsuario(usuarioVehiculoCarro)).thenReturn(Unit)

        //Act
        ServicioEstacionamiento(repositorioUsuarioVehiculo).ingresoUsuarioEstacionamiento(estacionamientoCarro, diaLunes)

        //Assert
        verify(repositorioUsuarioVehiculo, times(1)).guardarUsuario(usuarioVehiculoCarro)
    }

    @Test
    fun ingresoUsuarioEstacionamiento_letraInicialADiaRestringidoMiercoles_lanzarUnaExcepcion() {

        //Arrange
        val diaMiercoles = 3
        val usuarioCarro = UsuarioVehiculoCarro("ASU531")
        val estacionamientoCarro = EstacionamientoCarro(usuarioCarro)

        runTest {
            Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuarioCarro.placaVehiculo)).thenReturn(false)
            Mockito.`when`(repositorioUsuarioVehiculo.listaUsuarios()).thenReturn(listaUsuarios)
        }

        Assert.assertThrows(IngresoNoPermitidoRestriccionExcepcion::class.java) {
            runTest {
                ServicioEstacionamiento(repositorioUsuarioVehiculo).ingresoUsuarioEstacionamiento(estacionamientoCarro, diaMiercoles)
            }
        }
    }

    @Test
    fun ingresoUsuarioEstacionamiento_elUsuarioYaExisteEnElEstacionamiento_lanzarUnaExcepcion() {

        //Arrange
        val diaMiercoles = 3
        val servicioEstacionamientoCarro = ServicioEstacionamiento(repositorioUsuarioVehiculo)
        runTest {
            Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuarioVehiculoCarro.placaVehiculo)).thenReturn(true)
        }

        //Act
        //Assert
        Assert.assertThrows(UsuarioYaExisteExcepcion::class.java) {
            runTest {
                servicioEstacionamientoCarro.ingresoUsuarioEstacionamiento(estacionamientoCarro, diaMiercoles)
            }
        }
    }

    @Test
    fun eliminarUsuario_elUsuarioNoExiste_lanzarUnaExcepcion() {

        //Arrange
        val servicioEstacionamientoCarro = ServicioEstacionamiento(repositorioUsuarioVehiculo)
        runTest {
            Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuarioVehiculoCarro.placaVehiculo)).thenReturn(false)
        }
        //Act
        //Assert
        Assert.assertThrows(UsuarioNoExisteExcepcion::class.java) {
            runTest {

                servicioEstacionamientoCarro.eliminarUsuario(usuarioVehiculoCarro.placaVehiculo)
            }
        }
    }


    @Test
    fun eliminarUsuario_elUsuarioExiste_eliminaElUsuario() {

        //Arrange
        val servicioEstacionamientoCarro = ServicioEstacionamiento(repositorioUsuarioVehiculo)
        runTest {
            Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuarioVehiculoCarro.placaVehiculo)).thenReturn(true)
        }

        //Act
        //Assert
        runTest {
            servicioEstacionamientoCarro.eliminarUsuario(usuarioVehiculoCarro.placaVehiculo)
        }
    }

}