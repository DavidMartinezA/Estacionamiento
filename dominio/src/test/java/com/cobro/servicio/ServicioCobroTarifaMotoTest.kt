package com.cobro.servicio

import com.cobro.modelo.CobroTarifaMoto
import com.estacionamiento.modelo.EstacionamientoMoto
import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.estacionamiento.servicio.ServicioEstacionamientoMoto
import com.excepciones.UsuarioNoExisteExcepcion
import com.usuario.modelo.UsuarioVehiculoMoto
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RunWith(MockitoJUnitRunner::class)
class ServicioCobroTarifaMotoTest {

    @Mock
    private lateinit var repositorioEstacionamiento: RepositorioEstacionamiento

    private val patronHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    @Test
    fun cobroDuracionServicio_UsuarioNoExiste_CobroDeTarifa() {

        //Arrange
        val mensajeEsperado = "UsuarioVehiculo No Existe"
        val horaIngreso = "2022-01-01 00:05:01"
        val horaSalida = "2022-01-01 05:59:00"
        val horaProporcionadaDeIngreso = LocalDateTime.parse(horaIngreso, patronHora)
        val horaProporcionadaDesalida = LocalDateTime.parse(horaSalida, patronHora)
        val usuario = UsuarioVehiculoMoto("hsu531", true)
        val estacionamiento = EstacionamientoMoto(usuario, horaProporcionadaDeIngreso)
        val servicioEstacionamiento =
            ServicioEstacionamientoMoto(estacionamiento, repositorioEstacionamiento)
        val cobroTarifaMoto = CobroTarifaMoto(estacionamiento)
        val servicioCobroTarifaMoto = ServicioCobroTarifaMoto(servicioEstacionamiento,
            horaProporcionadaDesalida,
            cobroTarifaMoto)
        Mockito.`when`(repositorioEstacionamiento.usuarioExiste(usuario)).thenReturn(false)

        //Act
        try {

            val cobroDuracionServicio = servicioCobroTarifaMoto.cobroDuracionServicio()

        } catch (ex: UsuarioNoExisteExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }

    }


}