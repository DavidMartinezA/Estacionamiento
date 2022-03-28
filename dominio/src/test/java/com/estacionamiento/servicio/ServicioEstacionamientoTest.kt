package com.estacionamiento.servicio

import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.excepciones.FormatoPlacaExcepcion
import com.excepciones.UsuarioNoExisteExcepcion
import com.usuario.Usuario
import com.usuario.UsuarioCarro
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
    private lateinit var repositorioEstacionamiento: RepositorioEstacionamiento

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

            repositorioEstacionamiento.guardarUsuario(usuarioCarroTest)

        } catch (ex: FormatoPlacaExcepcion) {
            //Assert
            Assert.assertEquals(usuarioCarroTest, ex.message)
        }

        //Assert
        Assert.assertNotNull(this.repositorioEstacionamiento)
    }


    @Test
    fun ingresoUsuarioEstacionamiento_ParametrosVacios_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"

        //Act
        try {
            val usuarioCarroTest = UsuarioCarro("")
            repositorioEstacionamiento.guardarUsuario(usuarioCarroTest)

        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }


    @Test
    fun salidaUsuarioEstacionamiento_UsuarioNoExiste_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "Usuario No Existe"
        val listaUsuarios = ArrayList<Usuario>()
        val usuarioCarro = UsuarioCarro("hsu531")

        val servicioEstacionamiento =
            ServicioEstacionamiento(usuarioCarro, repositorioEstacionamiento)

        //Act
        try {
            servicioEstacionamiento.salidaDeUsuariosEstacionamiento(usuarioCarro)

        } catch (ex: UsuarioNoExisteExcepcion) {
            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)

        }
    }

    @Test
    fun salidaUsuarioEstacionamiento_ParametrosCorrectos_UsuarioEliminado() {

        //Arrange
        val usuarioCarroTest = UsuarioCarro("hsu531")

        //Act
        try {

            repositorioEstacionamiento.eliminarUsuario(usuarioCarroTest)

        } catch (ex: FormatoPlacaExcepcion) {
            //Assert
            Assert.assertEquals(usuarioCarroTest, ex.message)
        }

        //Assert
        Assert.assertNotNull(repositorioEstacionamiento)
    }

}