package com.estacionamiento.servicio

import com.estacionamiento.excepcion.IngresoNoPermitidoRestriccionExcepcion
import com.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.estacionamiento.excepcion.UsuarioYaExisteExcepcion
import com.estacionamiento.modelo.EstacionamientoCarro
import com.estacionamiento.modelo.EstacionamientoMoto
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoCarro
import com.usuario.modelo.UsuarioVehiculoMoto
import com.usuario.repositorio.RepositorioUsuarioVehiculo
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
import java.time.LocalDateTime

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ServicioEstacionamientoTest {

    @Mock
    private lateinit var repositorioUsuarioVehiculo: RepositorioUsuarioVehiculo

    private val horaIngreso = LocalDateTime.now()
    private val usuarioVehiculoCarro = UsuarioVehiculoCarro("HSU531")
    private val listaUsuarios: ArrayList<UsuarioVehiculo> = arrayListOf(usuarioVehiculoCarro)
    private val estacionamientoCarro = EstacionamientoCarro(usuarioVehiculoCarro, horaIngreso)

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
            ServicioEstacionamiento(estacionamientoCarro, repositorioUsuarioVehiculo).consultarListaUsuarios()

        //Assert
        Assert.assertEquals(listaUsuarios, consultaDeListaUsuarios)
    }

    @Test
    fun disponibilidadEstacionamiento_estacionamientoConCapacidadCarros_retornaCapacidadCarrosVerdadero() = runTest {

        //Arrange
        Mockito.`when`(repositorioUsuarioVehiculo.listaUsuarios()).thenReturn(listaUsuarios)

        //Act
        val capacidad =
            ServicioEstacionamiento(estacionamientoCarro, repositorioUsuarioVehiculo).consultaDisponibilidadEstacionamiento()

        //Assert
        Assert.assertTrue(capacidad)
    }

    @Test
    fun disponibilidadEstacionamiento_estacionamientoConCapacidadMotos_RetornaCapacidadMotosVerdadero() {

        //Arrange
        val usuarioVehiculoMoto = UsuarioVehiculoMoto("HSU531", true)
        val listaUsuarios: ArrayList<UsuarioVehiculo> = arrayListOf(usuarioVehiculoMoto)
        val estacionamientoMoto = EstacionamientoMoto(usuarioVehiculoMoto, horaIngreso)
        var capacidad = false

        //Act
        runTest {
            Mockito.`when`(repositorioUsuarioVehiculo.listaUsuarios()).thenReturn(listaUsuarios)
            capacidad =
                ServicioEstacionamiento(estacionamientoMoto, repositorioUsuarioVehiculo).consultaDisponibilidadEstacionamiento()
        }
        //Assert
        Assert.assertTrue(capacidad)
    }

    @Test
    fun ingresoUsuarioEstacionamiento_UsuarioNoExiste_usuarioRegistradoNoRetorna() = runTest {

        //Arrange
        val diaLunes = 1
        Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuarioVehiculoCarro)).thenReturn(false)
        Mockito.`when`(repositorioUsuarioVehiculo.listaUsuarios()).thenReturn(listaUsuarios)
        Mockito.`when`(repositorioUsuarioVehiculo.guardarUsuario(usuarioVehiculoCarro)).thenReturn(Unit)

        //Act
        ServicioEstacionamiento(estacionamientoCarro, repositorioUsuarioVehiculo).ingresoUsuarioEstacionamiento(diaLunes)

        //Assert
        verify(repositorioUsuarioVehiculo, times(1)).guardarUsuario(usuarioVehiculoCarro)
    }

    @Test
    fun ingresoUsuarioEstacionamiento_letraInicialADiaRestringidoMiercoles_lanzarUnaExcepcion() {

        //Arrange
        val diaMiercoles = 3
        val usuarioCarro = UsuarioVehiculoCarro("ASU531")
        val estacionamientoCarro = EstacionamientoCarro(usuarioCarro, LocalDateTime.now())

        runTest {
            Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuarioCarro)).thenReturn(false)
            Mockito.`when`(repositorioUsuarioVehiculo.listaUsuarios()).thenReturn(listaUsuarios)
        }

        Assert.assertThrows(IngresoNoPermitidoRestriccionExcepcion::class.java) {
            runTest {
                ServicioEstacionamiento(estacionamientoCarro, repositorioUsuarioVehiculo).ingresoUsuarioEstacionamiento(diaMiercoles)
            }
        }
    }

    @Test
    fun ingresoUsuarioEstacionamiento_elUsuarioYaExisteEnElEstacionamiento_lanzarUnaExcepcion() {

        //Arrange
        val diaMiercoles = 3
        val servicioEstacionamientoCarro = ServicioEstacionamiento(estacionamientoCarro, repositorioUsuarioVehiculo)
        runTest {
            Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuarioVehiculoCarro)).thenReturn(true)
        }

        //Act
        //Assert
        Assert.assertThrows(UsuarioYaExisteExcepcion::class.java) {
            runTest {
                servicioEstacionamientoCarro.ingresoUsuarioEstacionamiento(diaMiercoles)
            }
        }
    }

    @Test
    fun eliminarUsuario_UsuarioNoExiste_lanzarUnaExcepcion() {

        //Arrange
        val servicioEstacionamientoCarro = ServicioEstacionamiento(estacionamientoCarro, repositorioUsuarioVehiculo)

        //Act
        //Assert
        Assert.assertThrows(UsuarioNoExisteExcepcion::class.java) {
            runTest {
                Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuarioVehiculoCarro)).thenReturn(false)
                servicioEstacionamientoCarro.eliminarUsuario()
            }
        }
    }

    @Test
    fun eliminarUsuario_elUsuarioExiste_eliminaElUsuario() {

        //Arrange
        val servicioEstacionamientoCarro = ServicioEstacionamiento(estacionamientoCarro, repositorioUsuarioVehiculo)

        //Act
        runTest {
            Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuarioVehiculoCarro)).thenReturn(true)
            servicioEstacionamientoCarro.eliminarUsuario()
        }
    }

}