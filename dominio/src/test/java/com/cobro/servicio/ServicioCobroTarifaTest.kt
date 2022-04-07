package com.cobro.servicio

import com.cobro.modelo.CobroTarifaCarro
import com.cobro.modelo.CobroTarifaMoto
import com.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.estacionamiento.modelo.EstacionamientoCarro
import com.estacionamiento.modelo.EstacionamientoMoto
import com.estacionamiento.servicio.ServicioEstacionamiento
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
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDateTime

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ServicioCobroTarifaTest {

    @Mock
    private lateinit var repositorioUsuarioVehiculo: RepositorioUsuarioVehiculo

    private var usuario = UsuarioVehiculoCarro("hsu531")


    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun duracionServicioEstacionamiento_duracionDeCincoHorasYMedia_retornaDuracionServicioSeisHoras() {

        //Arrange
        usuario.horaFechaIngresoUsuario = LocalDateTime.now().minusHours(5)
        val estacionamiento = EstacionamientoCarro(usuario)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifa = CobroTarifaCarro(estacionamiento)
        val servicioCobroTarifaCarro = ServicioCobroTarifa(servicioEstacionamiento,
            cobroTarifa, LocalDateTime.now().plusMinutes(30))
        //Act
        val duracionServicio = servicioCobroTarifaCarro.duracionServicioEstacionamiento()

        //Assert
        Assert.assertEquals(duracionServicio, 6)

    }

    @Test
    fun duracionServicioEstacionamiento_duracionDeOchoHorasYMedia_retornaDuracionServicioNueveHoras() {

        //Arrange
        usuario.horaFechaIngresoUsuario = LocalDateTime.now().minusHours(8)
        val estacionamiento = EstacionamientoCarro(usuario)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifa = CobroTarifaCarro(estacionamiento)
        val servicioCobroTarifaCarro = ServicioCobroTarifa(servicioEstacionamiento,
            cobroTarifa, LocalDateTime.now().plusMinutes(30))
        //Act
        val duracionServicio = servicioCobroTarifaCarro.duracionServicioEstacionamiento()

        //Assert
        Assert.assertEquals(duracionServicio, 9)

    }

    @Test
    fun cobroDuracionServicio_elUsuarioNoExiste_lanzarUnaExcepcion() {

        //Arrange
        usuario.horaFechaIngresoUsuario = LocalDateTime.now().minusHours(8)
        val estacionamiento = EstacionamientoCarro(usuario)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifa = CobroTarifaCarro(estacionamiento)

        //Act
        //Assert
        Assert.assertThrows(UsuarioNoExisteExcepcion::class.java) {
            runTest {
                Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuario)).thenReturn(false)
                ServicioCobroTarifa(servicioEstacionamiento, cobroTarifa, LocalDateTime.now()).cobroDuracionServicio()
            }
        }

    }

    @Test
    fun cobroDuracionServicio_elUsuarioNoExisteMoto_lanzarUnaExcepcion() {

        //Arrange

        val usuario = UsuarioVehiculoMoto("hsu531", true)
        usuario.horaFechaIngresoUsuario = LocalDateTime.now().minusHours(5)
        val estacionamiento = EstacionamientoMoto(usuario)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifaMoto = CobroTarifaMoto(estacionamiento)

        //Act
        //Assert
        Assert.assertThrows(UsuarioNoExisteExcepcion::class.java) {
            runTest {
                Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuario)).thenReturn(false)
                ServicioCobroTarifa(servicioEstacionamiento, cobroTarifaMoto, LocalDateTime.now()).cobroDuracionServicio()
            }
        }

    }

    @Test
    fun cobroDuracionServicio_usuarioMotoSeisHorasCilindrajeAlto_cobroDeTarifa() {

        //Arrange

        val usuario = UsuarioVehiculoMoto("hsu531", true)
        usuario.horaFechaIngresoUsuario = LocalDateTime.now().minusHours(5)
        val estacionamiento = EstacionamientoMoto(usuario)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifaMoto = CobroTarifaMoto(estacionamiento)
        val servicioCobroTarifaMoto = ServicioCobroTarifa(servicioEstacionamiento, cobroTarifaMoto,
            LocalDateTime.now().plusMinutes(30))
        var servicio = 0

        //Act
        runTest {
            Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuario)).thenReturn(true)
            servicio = servicioCobroTarifaMoto.cobroDuracionServicio()
        }

        //Assert
        Assert.assertEquals(servicio, 5000)
    }

}