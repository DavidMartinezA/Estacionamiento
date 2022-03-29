package com.estacionamiento.repositorio

import com.usuario.modelo.UsuarioVehiculo

interface RepositorioEstacionamiento {

    fun usuarioExiste(usuarioVehiculo: UsuarioVehiculo): Boolean

    fun guardarUsuario(usuarioVehiculo: UsuarioVehiculo)

    fun eliminarUsuario(usuarioVehiculo: UsuarioVehiculo)

    fun listaUsuarios(): ArrayList<UsuarioVehiculo>
}