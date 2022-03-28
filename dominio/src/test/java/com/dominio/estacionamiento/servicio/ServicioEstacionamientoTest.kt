package com.dominio.estacionamiento.servicio

import com.dominio.excepciones.FormatoPlacaExcepcion
import com.dominio.usuario.modelo.Usuario
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
    private lateinit var mockrRepositorioUsuario: RepositorioUsuario

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun ingresoUsuarioEstacionamiento_ParametrosCorrectos_UsuarioGuardado() {

        val usuarioCarroTest = UsuarioCarro("hsu531")
        val listaUsuarios = ArrayList<Usuario>()
        listaUsuarios.add(usuarioCarroTest)
        mockrRepositorioUsuario.guardarUsuario(usuarioCarroTest)

    }

    @Test
    fun ingresoUsuarioEstacionamiento_ParametrosVacios_LanzarExcepcion() {

        val mensajeEsperado = "Formato De Placa No Valido"
        val listaUsuarios = ArrayList<Usuario>()

        try {

            val usuarioMotoTest = UsuarioMoto("", true)
            listaUsuarios.add(usuarioMotoTest)

            mockrRepositorioUsuario.guardarUsuario(usuarioMotoTest)
            Assert.fail()
        } catch (ex: FormatoPlacaExcepcion) {

            //Assert
            Assert.assertEquals(mensajeEsperado, ex.message)
        }
    }

    fun salidaUsuarioEstacionamiento_ParametrosCorrectos_UsuarioGuardado() {}
    fun salidaUsuarioEstacionamiento_Parametrosincorrectos_UsuarioGuardado() {}
}