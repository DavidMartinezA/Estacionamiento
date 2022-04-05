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
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ServicioCobroTarifaTest {

    @Mock
    private lateinit var repositorioUsuarioVehiculo: RepositorioUsuarioVehiculo

    private val patronHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    private var usuario = UsuarioVehiculoCarro("hsu531")
    private val horaIngreso = "2022-01-01 00:00:01"
    private val horaProporcionadaDeIngreso = LocalDateTime.parse(horaIngreso, patronHora)

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun duracionServicioEstacionamiento_duracionDeCincoHorasYMedia_retornaDuracionServicioSeisHoras() {

        //Arrange
        val horaSalida = "2022-01-01 05:30:00"
        val horaProporcionadaDesalida = LocalDateTime.parse(horaSalida, patronHora)
        val estacionamiento = EstacionamientoCarro(usuario, horaProporcionadaDeIngreso)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifa = CobroTarifaCarro(estacionamiento)
        val servicioCobroTarifaCarro = ServicioCobroTarifa(servicioEstacionamiento,
            cobroTarifa, horaProporcionadaDesalida)
        //Act
        val duracionServicio = servicioCobroTarifaCarro.duracionServicioEstacionamiento()

        //Assert
        Assert.assertEquals(duracionServicio, 6)

    }

    @Test
    fun duracionServicioEstacionamiento_duracionDeOchoHorasYMedia_retornaDuracionServicioNueveHoras() {

        //Arrange
        val horaSalida = "2022-01-01 08:30:00"
        val horaProporcionadaDesalida = LocalDateTime.parse(horaSalida, patronHora)
        val estacionamiento = EstacionamientoCarro(usuario, horaProporcionadaDeIngreso)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifa = CobroTarifaCarro(estacionamiento)
        val servicioCobroTarifaCarro = ServicioCobroTarifa(servicioEstacionamiento,
            cobroTarifa, horaProporcionadaDesalida)
        //Act
        val duracionServicio = servicioCobroTarifaCarro.duracionServicioEstacionamiento()

        //Assert
        Assert.assertEquals(duracionServicio, 9)

    }


    @Test
    fun cobroDuracionServicio_elUsuarioNoExiste_lanzarUnaExcepcion() {

        //Arrange
        val horaSalida = "2022-01-01 04:59:00"
        val horaProporcionadaDesalida = LocalDateTime.parse(horaSalida, patronHora)
        val estacionamiento = EstacionamientoCarro(usuario, horaProporcionadaDeIngreso)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifa = CobroTarifaCarro(estacionamiento)


        //Act
        //Assert
        Assert.assertThrows(UsuarioNoExisteExcepcion::class.java) {
            runTest {
                Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuario)).thenReturn(false)
                ServicioCobroTarifa(servicioEstacionamiento, cobroTarifa, horaProporcionadaDesalida).cobroDuracionServicio()
            }
        }

    }

    @Test
    fun cobroDuracionServicio_elUsuarioNoExisteMoto_LanzarUnaExcepcion() {

        //Arrange
        val horaSalida = "2022-01-01 05:59:00"
        val horaProporcionadaDesalida = LocalDateTime.parse(horaSalida, patronHora)
        val usuario = UsuarioVehiculoMoto("hsu531", true)
        val estacionamiento = EstacionamientoMoto(usuario, horaProporcionadaDeIngreso)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifaMoto = CobroTarifaMoto(estacionamiento)

        //Act
        //Assert
        Assert.assertThrows(UsuarioNoExisteExcepcion::class.java) {
            runTest {
                Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuario)).thenReturn(false)
                ServicioCobroTarifa(servicioEstacionamiento, cobroTarifaMoto, horaProporcionadaDesalida).cobroDuracionServicio()
            }
        }

    }

    @Test
    fun cobroDuracionServicio_UsuarioMotoSeisHorasCilindrajeAlto_CobroDeTarifa() {

        //Arrange
        val horaSalida = "2022-01-01 05:59:00"
        val horaProporcionadaDesalida = LocalDateTime.parse(horaSalida, patronHora)
        val usuario = UsuarioVehiculoMoto("hsu531", true)
        val estacionamiento = EstacionamientoMoto(usuario, horaProporcionadaDeIngreso)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifaMoto = CobroTarifaMoto(estacionamiento)
        val servicioCobroTarifaMoto = ServicioCobroTarifa(servicioEstacionamiento, cobroTarifaMoto,
            horaProporcionadaDesalida)
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