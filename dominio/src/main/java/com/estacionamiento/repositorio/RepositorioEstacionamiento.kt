package com.estacionamiento.repositorio

import com.usuario.Usuario

interface RepositorioEstacionamiento {

    fun usuarioExiste(usuario: Usuario): Boolean

    fun guardarUsuario(usuario: Usuario)

    fun eliminarUsuario(usuario: Usuario)

    fun listaUsuarios(): ArrayList<Usuario>
}