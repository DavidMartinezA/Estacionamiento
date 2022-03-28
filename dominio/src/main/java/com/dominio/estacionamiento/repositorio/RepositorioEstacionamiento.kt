package com.dominio.estacionamiento.repositorio

import com.dominio.usuario.modelo.Usuario

interface RepositorioEstacionamiento {

    fun usuarioExiste(usuario: Usuario): Boolean

    fun guardarUsuario(usuario: Usuario)

    fun eliminarUsuario(usuario: Usuario)

    fun listaUsuarios(): ArrayList<Usuario>
}