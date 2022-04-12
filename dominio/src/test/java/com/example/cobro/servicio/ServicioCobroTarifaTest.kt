package com.example.cobro.servicio

import com.example.cobro.modelo.CobroTarifaCarro
import com.example.cobro.modelo.CobroTarifaMoto
import com.example.estacionamiento.excepcion.UsuarioNoExisteExcepcion
import com.example.estacionamiento.modelo.EstacionamientoCarro
import com.example.estacionamiento.modelo.EstacionamientoMoto
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
        val horaSalida = LocalDateTime.now().plusHours(6)
        usuario.horaFechaIngresoUsuario = LocalDateTime.now()
        val estacionamiento = EstacionamientoCarro(usuario)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifa = CobroTarifaCarro(estacionamiento)
        val servicioCobroTarifaCarro = ServicioCobroTarifa(servicioEstacionamiento,
            cobroTarifa, horaSalida)
        //Act
        val duracionServicio = servicioCobroTarifaCarro.duracionServicioEstacionamiento()

        //Assert
        Assert.assertEquals(duracionServicio, 6)

    }

    @Test
    fun duracionServicioEstacionamiento_duracionDeOchoHorasYMedia_retornaDuracionServicioNueveHoras() {

        //Arrange
        val usuario = UsuarioVehiculoCarro("hsu531")
        val horaSalida = LocalDateTime.now().plusHours(9)
        usuario.horaFechaIngresoUsuario = LocalDateTime.now()
        val estacionamiento = EstacionamientoCarro(usuario)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifa = CobroTarifaCarro(estacionamiento)
        val servicioCobroTarifaCarro = ServicioCobroTarifa(servicioEstacionamiento,
            cobroTarifa, horaSalida)
        //Act
        val duracionServicio = servicioCobroTarifaCarro.duracionServicioEstacionamiento()

        //Assert
        Assert.assertEquals(duracionServicio, 9)

    }

    @Test
    fun cobroDuracionServicio_elUsuarioNoExiste_lanzarUnaExcepcion() {

        //Arrange
        val usuario = UsuarioVehiculoCarro("hsu531")
        val horaSalida = LocalDateTime.now().plusHours(9)
        usuario.horaFechaIngresoUsuario = LocalDateTime.now()
        val estacionamiento = EstacionamientoCarro(usuario)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifa = CobroTarifaCarro(estacionamiento)

        //Act
        //Assert
        Assert.assertThrows(UsuarioNoExisteExcepcion::class.java) {
            runTest {
                Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuario)).thenReturn(false)
                ServicioCobroTarifa(servicioEstacionamiento, cobroTarifa, horaSalida).cobroDuracionServicio()
            }
        }

    }

    @Test
    fun cobroDuracionServicio_elUsuarioNoExisteMoto_lanzarUnaExcepcion() {

        //Arrange
        val horaSalida = LocalDateTime.now().plusHours(9)
        val usuario = UsuarioVehiculoMoto("hsu531", true)
        usuario.horaFechaIngresoUsuario = LocalDateTime.now()
        val estacionamiento = EstacionamientoMoto(usuario)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifaMoto = CobroTarifaMoto(estacionamiento)

        //Act
        //Assert
        Assert.assertThrows(UsuarioNoExisteExcepcion::class.java) {
            runTest {
                Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuario)).thenReturn(false)
                ServicioCobroTarifa(servicioEstacionamiento, cobroTarifaMoto, horaSalida).cobroDuracionServicio()
            }
        }

    }

    @Test
    fun cobroDuracionServicio_usuarioMotoSeisHorasCilindrajeAlto_cobroDeTarifa() {

        //Arrange
        val horaSalida = LocalDateTime.now().plusHours(6)
        val usuario = UsuarioVehiculoMoto("hsu531", true)
        usuario.horaFechaIngresoUsuario = LocalDateTime.now()
        val estacionamiento = EstacionamientoMoto(usuario)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifaMoto = CobroTarifaMoto(estacionamiento)
        val servicioCobroTarifaMoto = ServicioCobroTarifa(servicioEstacionamiento, cobroTarifaMoto,
            horaSalida)
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