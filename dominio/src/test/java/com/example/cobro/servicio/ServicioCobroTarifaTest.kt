package com.example.cobro.servicio

import com.example.cobro.modelo.CobroTarifaCarro
import com.example.cobro.modelo.CobroTarifaMoto
import com.example.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.example.estacionamiento.servicio.ServicioEstacionamiento
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
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDateTime

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ServicioCobroTarifaTest {

    @Mock
    private lateinit var repositorioUsuarioVehiculo: RepositorioUsuarioVehiculo

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun duracionServicioEstacionamiento_duracionDeCincoHorasYMedia_retornaDuracionServicioSeisHoras() {

        //Arrange
        val usuario = UsuarioVehiculoCarro("hsu531")
        usuario.horaFechaIngresoUsuario = LocalDateTime.now().minusHours(6)
        ServicioEstacionamiento(repositorioUsuarioVehiculo)
        val servicioCobroTarifaCarro = ServicioCobroTarifa(repositorioUsuarioVehiculo)
        //Act
        val duracionServicio = servicioCobroTarifaCarro.duracionServicioEstacionamiento(usuario)

        //Assert
        Assert.assertEquals(duracionServicio, 7)

    }

    @Test
    fun duracionServicioEstacionamiento_duracionDeOchoHorasYMedia_retornaDuracionServicioNueveHoras() {

        //Arrange
        val usuario = UsuarioVehiculoCarro("hsu531")
        usuario.horaFechaIngresoUsuario = LocalDateTime.now().minusHours(9)
        val servicioCobroTarifaCarro = ServicioCobroTarifa(repositorioUsuarioVehiculo)
        //Act
        val duracionServicio = servicioCobroTarifaCarro.duracionServicioEstacionamiento(usuario)

        //Assert
        Assert.assertEquals(duracionServicio, 10)

    }

    @Test
    fun cobroDuracionServicio_elUsuarioNoExiste_lanzarUnaExcepcion() {

        //Arrange
        val usuario = UsuarioVehiculoCarro("hsu531")
        usuario.horaFechaIngresoUsuario = LocalDateTime.now().minusHours(9)
        val cobroTarifa = CobroTarifaCarro()

        //Act
        //Assert
        Assert.assertThrows(UsuarioNoExisteExcepcion::class.java) {
            runTest {
                Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuario.placaVehiculo)).thenReturn(false)
                ServicioCobroTarifa(repositorioUsuarioVehiculo).cobroDuracionServicio(usuario.placaVehiculo, cobroTarifa)
            }
        }

    }

    @Test
    fun cobroDuracionServicio_elUsuarioNoExisteMoto_lanzarUnaExcepcion() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu531", true)
        usuario.horaFechaIngresoUsuario = LocalDateTime.now().minusHours(9)
        val cobroTarifaMoto = CobroTarifaMoto()

        //Act
        //Assert
        Assert.assertThrows(UsuarioNoExisteExcepcion::class.java) {
            runTest {
                Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuario.placaVehiculo)).thenReturn(false)
                ServicioCobroTarifa(repositorioUsuarioVehiculo).cobroDuracionServicio(usuario.placaVehiculo, cobroTarifaMoto)
            }
        }

    }

}