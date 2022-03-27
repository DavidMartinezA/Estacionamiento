package com.dominio.usuario.repositorio

import com.dominio.usuario.modelo.Usuario

interface RepositorioUsuario {

    fun usuarioExiste(usuario: Usuario): Boolean

    fun guardarUsuario(usuario: Usuario)

    fun eliminarUsuario(usuario: Usuario)

    fun listaUsuarios(): ArrayList<Usuario>
}