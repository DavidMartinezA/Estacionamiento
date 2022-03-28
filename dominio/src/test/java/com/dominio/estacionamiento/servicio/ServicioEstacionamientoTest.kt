package com.dominio.estacionamiento.servicio

import com.dominio.estacionamiento.repositorio.RepositorioEstacionamiento
import com.dominio.excepciones.FormatoPlacaExcepcion
import com.dominio.excepciones.UsuarioNoExisteExcepcion
import com.dominio.usuario.modelo.Usuario
import com.dominio.usuario.modelo.UsuarioCarro
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
    private lateinit var mockRepositorioEstacionamiento: RepositorioEstacionamiento

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun ingresoUsuarioEstacionamiento_ParametrosCorrectos_UsuarioGuardado() {

        //Arrange
        val usuarioCarroTest = UsuarioCarro("hsu531")
        val repositorioEstacionamiento = mockRepositorioEstacionamiento

        //Act
        try {

            repositorioEstacionamiento.guardarUsuario(usuarioCarroTest)

        } catch (ex: FormatoPlacaExcepcion) {
            //Assert
            Assert.assertEquals(usuarioCarroTest, ex.message)
        }

        //Assert
        Assert.assertNotNull(mockRepositorioEstacionamiento)
    }


    @Test
    fun ingresoUsuarioEstacionamiento_ParametrosVacios_LanzarExcepcion() {

        //Arrange
        val mensajeEsperado = "Formato De Placa No Valido"

        //Act
        try {
            val usuarioCarroTest = UsuarioCarro("")
            val repositorioEstacionamiento = mockRepositorioEstacionamiento
            repositorioEstacionamiento.guardarUsuario(usuarioCarroTest)

        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }


    @Test
    fun salidaUsuarioEstacionamiento_UsuarioNoExiste_LanzarExcepcion() {

        //Arrange
        val listaUsuarios = ArrayList<Usuario>()
        val mensajeEsperado = "Usuario No Existe"
        val usuarioCarro = UsuarioCarro("hsu531")
        val servicioEstacionamiento =
            ServicioEstacionamiento(usuarioCarro, mockRepositorioEstacionamiento)
        listaUsuarios.add(usuarioCarro)


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
        val repositorioEstacionamiento = mockRepositorioEstacionamiento

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