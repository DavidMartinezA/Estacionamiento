package com.dominio.usuario.servico

import com.dominio.usuario.modelo.Usuario
import com.dominio.usuario.modelo.UsuarioCarro
import com.dominio.usuario.modelo.UsuarioMoto
import com.dominio.usuario.repositorio.RepositorioUsuario
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ServicioUsuarioTest {

    @Test
    fun guardar() {

        //Arrange
        val carroUno = UsuarioCarro("hsu531")
        val carrodos = UsuarioCarro("hsu532")
        val moto = UsuarioMoto("hsu533",true)
        var listaUsuarios: Usuario
        //Act
         RepositorioUsuario.guardarUsuario(carroUno)



        //Act
        RepositorioUsuario.guardarUsuario(carroUno)
    }

    @Test
    fun eliminar() {
    }

    @Test
    fun consultarLista() {
    }
}