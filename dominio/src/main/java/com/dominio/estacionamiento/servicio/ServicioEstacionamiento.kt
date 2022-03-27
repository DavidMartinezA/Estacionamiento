package com.dominio.estacionamiento.servicio

import com.dominio.estacionamiento.modelo.Estacionamiento
import com.dominio.usuario.modelo.Usuario
import com.dominio.usuario.repositorio.RepositorioUsuario
import java.time.LocalDateTime

class ServicioEstacionamiento(var usuario: Usuario, var estacionamiento: Estacionamiento,var repositorioUsuario: RepositorioUsuario) {

    fun ingresoUsuarioEstacionamiento(){
        var listaUsuarios = repositorioUsuario.listaUsuarios()
        var horaIngreso = LocalDateTime.now()

    }

}