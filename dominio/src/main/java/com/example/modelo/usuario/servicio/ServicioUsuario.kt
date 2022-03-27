package com.example.modelo.usuario.servicio

import com.example.modelo.usuario.Excepciones.UsuarioNoExisteExcepcion
import com.example.modelo.usuario.modelo.Usuario
import com.example.modelo.usuario.repositorio.RepositorioUsuario

class ServicioUsuario(var repositorioUsuario: RepositorioUsuario, var usuario: Usuario) {

    fun guardar(usuario: Usuario) {
        if (!repositorioUsuario.usuarioExiste(usuario)) {
            repositorioUsuario.guardarUsuario(usuario) // debo guardar la hora tambien
        }
    }

    fun eliminar(usuario: Usuario) {
        if (repositorioUsuario.usuarioExiste(usuario)) {
            repositorioUsuario.eliminarUsuario(usuario)
        } else {
            throw UsuarioNoExisteExcepcion()
        }
    }

    fun consultarLista(): ArrayList<Usuario> {
        return repositorioUsuario.listaUsuarios()
    }
}