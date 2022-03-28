package com.dominio.estacionamiento.servicio

import com.dominio.excepciones.FormatoPlacaExcepcion
import com.dominio.usuario.modelo.UsuarioCarro
import com.dominio.usuario.modelo.UsuarioMoto
import com.dominio.usuario.repositorio.RepositorioUsuario
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ServicioEstacionamientoTest {

    @Mock
    private lateinit var mockRepositorioUsuario: RepositorioUsuario

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun ingresoUsuarioEstacionamiento_ParametrosCorrectos_UsuarioGuardado() {

        //Arrange
        val usuarioCarroTest = UsuarioCarro("hsu531")

        //Act
        try {

            mockRepositorioUsuario.guardarUsuario(usuarioCarroTest)

        } catch (ex: FormatoPlacaExcepcion) {
            //Assert
            Assert.assertEquals(usuarioCarroTest, ex.message)
        }

        //Assert
        Assert.assertNotNull(mockRepositorioUsuario)
    }

    @Test
    fun ingresoUsuarioEstacionamiento_ParametrosVacios_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"


        //Act
        try {

            val usuarioMotoTest = UsuarioMoto("", true)
            mockRepositorioUsuario.guardarUsuario(usuarioMotoTest)

        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    fun salidaUsuarioEstacionamiento_ParametrosCorrectos_UsuarioGuardado() {}
    fun salidaUsuarioEstacionamiento_Parametrosincorrectos_UsuarioGuardado() {}
}