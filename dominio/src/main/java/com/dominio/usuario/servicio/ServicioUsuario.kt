package com.dominio.usuario.servicio

import com.dominio.excepciones.UsuarioNoExisteExcepcion
import com.dominio.usuario.modelo.Usuario
import com.dominio.usuario.repositorio.RepositorioUsuario

class ServicioUsuario(var repositorioUsuario: RepositorioUsuario, var usuario: Usuario) {

    fun guardar(usuario: Usuario) {
        if (!repositorioUsuario.usuarioExiste(usuario)) {
            repositorioUsuario.guardarUsuario(usuario) // debo guardar la hora tambien
        } else {
            throw UsuarioNoExisteExcepcion()
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