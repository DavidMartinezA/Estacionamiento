package com.cobro.servicio

import com.cobro.modelo.CobroTarifaCarro
import com.cobro.modelo.CobroTarifaMoto
import com.estacionamiento.modelo.EstacionamientoCarro
import com.estacionamiento.modelo.EstacionamientoMoto
import com.estacionamiento.servicio.ServicioEstacionamiento
import com.excepciones.UsuarioNoExisteExcepcion
import com.usuario.modelo.UsuarioVehiculoCarro
import com.usuario.modelo.UsuarioVehiculoMoto
import com.usuario.repositorio.RepositorioUsuarioVehiculo
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
    fun duracionServicioEstacionamiento_CincoHorasYMedia_CobroSeisHoras() {

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
        assert(duracionServicio == 6)

    }

    @Test
    fun duracionServicioEstacionamiento_UnDIa_CobroSeisHoras() {

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
        assert(duracionServicio == 9)

    }


    @Test
    fun cobroDuracionServicio_UsuarioNoExiste_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "UsuarioVehiculo No Existe"
        val horaSalida = "2022-01-01 04:59:00"
        val horaProporcionadaDesalida = LocalDateTime.parse(horaSalida, patronHora)
        val estacionamiento = EstacionamientoCarro(usuario, horaProporcionadaDeIngreso)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifa = CobroTarifaCarro(estacionamiento)
        val servicioCobroTarifaCarro = ServicioCobroTarifa(servicioEstacionamiento,
            cobroTarifa, horaProporcionadaDesalida)
        Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuario)).thenReturn(false)

        //Act
        try {

            val cobroTarifaServicio = servicioCobroTarifaCarro.cobroDuracionServicio()

        } catch (ex: UsuarioNoExisteExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }

    }

    @Test
    fun cobroDuracionServicio_UsuarioNoExisteMoto_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "UsuarioVehiculo No Existe"
        val horaSalida = "2022-01-01 05:59:00"
        val horaProporcionadaDesalida = LocalDateTime.parse(horaSalida, patronHora)
        val usuario = UsuarioVehiculoMoto("hsu531", true)
        val estacionamiento = EstacionamientoMoto(usuario, horaProporcionadaDeIngreso)
        val servicioEstacionamiento =
            ServicioEstacionamiento(estacionamiento, repositorioUsuarioVehiculo)
        val cobroTarifaMoto = CobroTarifaMoto(estacionamiento)
        val servicioCobroTarifaMoto = ServicioCobroTarifa(servicioEstacionamiento, cobroTarifaMoto,
            horaProporcionadaDesalida)

        Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuario)).thenReturn(true)


        //Act
        try {

            val servicio = servicioCobroTarifaMoto.cobroDuracionServicio()

        } catch (ex: UsuarioNoExisteExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
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

        Mockito.`when`(repositorioUsuarioVehiculo.usuarioExiste(usuario)).thenReturn(true)

        val servicio = servicioCobroTarifaMoto.cobroDuracionServicio()

        assert(servicio == 5000)
    }

}