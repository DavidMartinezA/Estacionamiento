package com.estacionamiento.servicio

import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.excepciones.FormatoPlacaExcepcion
import com.excepciones.UsuarioNoExisteExcepcion
import com.usuario.modelo.UsuarioVehiculoMoto
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ServicioEstacionamientoMotoTest {

    @Mock
    private lateinit var repositorioEstacionamiento: RepositorioEstacionamiento

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Test()
    fun ingresoUsuarioEstacionamiento_ParametrosVacios_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"

        //Act
        try {
            val usuarioMoto = UsuarioVehiculoMoto("")
            val servicioEstacionamiento =
                ServicioEstacionamientoMoto(usuarioMoto, repositorioEstacionamiento)
            servicioEstacionamiento.salidaDeUsuariosEstacionamiento(usuarioMoto)

        } catch (ex: FormatoPlacaExcepcion) {
            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)

        }
    }

    @Test
    fun salidaUsuarioEstacionamiento_UsuarioNoExiste_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "UsuarioVehiculo No Existe"
        val usuarioMoto = UsuarioVehiculoMoto("hsu531")

        val servicioEstacionamiento =
            ServicioEstacionamientoMoto(usuarioMoto, repositorioEstacionamiento)

        //Act
        try {
            servicioEstacionamiento.salidaDeUsuariosEstacionamiento(usuarioMoto)

        } catch (ex: UsuarioNoExisteExcepcion) {
            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)

        }
    }

}