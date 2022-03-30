package com.cobro.servicio

import com.cobro.modelo.CobroTarifaCarro
import com.estacionamiento.modelo.EstacionamientoCarro
import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.estacionamiento.servicio.ServicioEstacionamientoCarro
import com.excepciones.UsuarioNoExisteExcepcion
import com.usuario.modelo.UsuarioVehiculoCarro
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RunWith(MockitoJUnitRunner::class)
class ServicioCobroTarifaCarroTest {

    @Mock
    private lateinit var repositorioEstacionamiento: RepositorioEstacionamiento

    private val patronHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    @Test
    fun duracionServicioEstacionamiento_CincoHoras_CobroTarifa() {

        //Arrange
        val horaIngreso = "2022-01-01 00:59:01"
        val horaSalida = "2022-01-01 06:00:00"
        val horaProporcionadaDeIngreso = LocalDateTime.parse(horaIngreso, patronHora)
        val horaProporcionadaDesalida = LocalDateTime.parse(horaSalida, patronHora)
        val usuario = UsuarioVehiculoCarro("hsu531")
        val estacionamiento = EstacionamientoCarro(usuario, horaProporcionadaDeIngreso)
        val servicioEstacionamiento =
            ServicioEstacionamientoCarro(estacionamiento, repositorioEstacionamiento)
        val cobroTarifa = CobroTarifaCarro(estacionamiento)
        val servicioCobroTarifaCarro = ServicioCobroTarifaCarro(servicioEstacionamiento,
            horaProporcionadaDesalida,
            cobroTarifa)
        //Act
        val duracionServicio = servicioCobroTarifaCarro.duracionServicioEstacionamiento()

        //Assert

        assert(duracionServicio == 5)

    }

    @Test
    fun registroCobroDuracionServicio_UsuarioNoExisteDuracionCincoHoras_CincoHoras() {

        //Arrange
        val mensajeEsperado = "UsuarioVehiculo No Existe"
        val horaIngreso = "2022-01-01 00:05:01"
        val horaSalida = "2022-01-01 05:59:00"
        val horaProporcionadaDeIngreso = LocalDateTime.parse(horaIngreso, patronHora)
        val horaProporcionadaDesalida = LocalDateTime.parse(horaSalida, patronHora)
        val usuario = UsuarioVehiculoCarro("hsu531")
        val estacionamiento = EstacionamientoCarro(usuario, horaProporcionadaDeIngreso)
        val servicioEstacionamiento =
            ServicioEstacionamientoCarro(estacionamiento, repositorioEstacionamiento)
        val cobroTarifa = CobroTarifaCarro(estacionamiento)
        val servicioCobroTarifaCarro = ServicioCobroTarifaCarro(servicioEstacionamiento,
            horaProporcionadaDesalida,
            cobroTarifa)
        Mockito.`when`(repositorioEstacionamiento.usuarioExiste(usuario)).thenReturn(false)

        //Act
        try {

            val cobroTarifaServicio = servicioCobroTarifaCarro.cobroDuracionServicio()

        } catch (ex: UsuarioNoExisteExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }

    }

    @Test
    fun registroCobroDuracionServicio_UsuarioExisteDuracionCincoHoras_CincoHoras() {

        //Arrange
        val horaIngreso = "2022-01-01 00:05:01"
        val horaSalida = "2022-01-01 05:59:00"
        val horaProporcionadaDeIngreso = LocalDateTime.parse(horaIngreso, patronHora)
        val horaProporcionadaDesalida = LocalDateTime.parse(horaSalida, patronHora)
        val usuario = UsuarioVehiculoCarro("hsu531")
        val estacionamiento = EstacionamientoCarro(usuario, horaProporcionadaDeIngreso)
        val servicioEstacionamiento =
            ServicioEstacionamientoCarro(estacionamiento, repositorioEstacionamiento)
        val cobroTarifa = CobroTarifaCarro(estacionamiento)
        val servicioCobroTarifaCarro = ServicioCobroTarifaCarro(servicioEstacionamiento,
            horaProporcionadaDesalida,
            cobroTarifa)
        Mockito.`when`(repositorioEstacionamiento.usuarioExiste(usuario)).thenReturn(true)

        //Act
        val cobroTarifaServicio = servicioCobroTarifaCarro.cobroDuracionServicio()

        //Assert
        assert(cobroTarifaServicio == 5000)


    }

}
