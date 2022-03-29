package com.cobro.modelo

import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.estacionamiento.servicio.ServicioEstacionamientoMoto
import com.excepciones.UsuarioNoExisteExcepcion
import com.usuario.modelo.UsuarioVehiculoMoto
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDateTime

@RunWith(MockitoJUnitRunner::class)
class CobroTarifaMotoTest {

    @Mock
    private lateinit var repositorioEstacionamiento: RepositorioEstacionamiento


    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun cobroTarifa_ParametroCorrectoCilindrajeAltoTrue_CobroTarifa() {

        //Arrange
        val mensajeEsperado = "UsuarioVehiculo No Existe"
        val usuario = UsuarioVehiculoMoto("hsu531", true)
        usuario.fechaIngreso = LocalDateTime.now().toLocalDate()
        val servicioEstacionamiento =
            ServicioEstacionamientoMoto(usuario, repositorioEstacionamiento)

        Mockito.`when`(repositorioEstacionamiento.usuarioExiste(usuario)).thenReturn(true)
        Mockito.`when`(repositorioEstacionamiento.eliminarUsuario(usuario))
        //Act
        val serviciocobroTarifa = CobroTarifaMoto(usuario).cobroTarifa()
        try {
            servicioEstacionamiento.salidaDeUsuariosEstacionamiento()

        } catch (ex: UsuarioNoExisteExcepcion) {
            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)

        }
    }
/*

    @Test
    fun cobroTarifa_ParametroCorrectoCilindrajeAltoFalse_CobroTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu531", false)
        val duracionServicio = 10

        //Act
        val tarifa = CobroTarifa.cobroTarifa(duracionServicio, usuario)
        //Assert

        //Assert
        Assert.assertNotNull(usuarioCarroTest)
    }

    @Test
    fun cobroTarifa_ParametroNegativoCilindrajeAltoPorDefectoFalse_CobroTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu531")
        val duracionServicio = -10

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio, usuario)

        //Assert
        assert(tarifa == 0)
    }

    @Test
    fun cobroTarifa_ParametroNegativoCilindrajeAltoTrue_CobroTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu531", true)
        val duracionServicio = -10

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio, usuario)

        //Assert
        assert(tarifa == 0)
    }


    @Test
    fun cobroTarifa_CobroPorHorasCilindrajeAltoTrue_CobroTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu531", true)
        val duracionServicio = 6

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio, usuario)

        //Assert
        assert(tarifa == 5000)
    }

    @Test
    fun cobroTarifa_CobroPorHorasCilindrajeAltoFalse_CobroTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu531", false)
        val duracionServicio = 6

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio, usuario)

        //Assert
        assert(tarifa == 3000)
    }

    @Test
    fun cobroTarifa_DiasCilindrajeAltoTrue_CobroTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu531", true)
        val duracionServicio = 48

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio, usuario)

        //Assert
        assert(tarifa == 10000)
    }

    @Test
    fun cobroTarifa_DiasCilindrajeAltoFalse_CobroTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu531", false)
        val duracionServicio = 48

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio, usuario)

        //Assert
        assert(tarifa == 8000)
    }

    @Test
    fun cobroTarifa_UnDiaCilindrajeAltoFalse_CobroTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu531", false)
        val duracionServicio = 9

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio, usuario)

        //Assert
        assert(tarifa == 4000)
    }

    @Test
    fun cobroTarifa_CasoExtremoCilindrajeAltoFalse_CobroTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu531", false)
        val duracionServicio = 2376

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio, usuario)

        //Assert
        assert(tarifa == 396000)
    }

    @Test
    fun cobroTarifa_CasoExtremoCilindrajeAltoTrue_CobroTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu531", true)
        val duracionServicio = 2376

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio, usuario)

        //Assert
        assert(tarifa == 398000)
    }
*/

}