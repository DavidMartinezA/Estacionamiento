package com.usuario.repositorio

import com.usuario.modelo.UsuarioVehiculo

interface RepositorioUsuarioVehiculo {

    fun usuarioExiste(usuarioVehiculo: UsuarioVehiculo): Boolean

    fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo)

    fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculo)

    fun listaUsuarios(): ArrayList<UsuarioVehiculo>
}