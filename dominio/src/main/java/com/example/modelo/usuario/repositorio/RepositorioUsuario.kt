package com.example.modelo.usuario.repositorio

import com.example.modelo.usuario.modelo.Usuario

interface RepositorioUsuario {

    fun usuarioExiste(usuario: Usuario): Boolean

    fun guardarUsuario(usuario: Usuario)

    fun eliminarUsuario(usuario: Usuario)

    fun listaUsuarios(): ArrayList<Usuario>
}