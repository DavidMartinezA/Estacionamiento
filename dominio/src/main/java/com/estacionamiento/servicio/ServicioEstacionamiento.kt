package com.estacionamiento.servicio


import com.estacionamiento.modelo.Estacionamiento
import com.estacionamiento.repositorio.RepositorioEstacionamiento
import com.excepciones.IngresoNoPermitidoRestriccionExcepcion
import com.usuario.modelo.UsuarioVehiculo


abstract class ServicioEstacionamiento(
    protected var estacionamiento: Estacionamiento,
    protected val repositorioEstacionamiento: RepositorioEstacionamiento,
) {

    abstract fun consultaDisponibilidadEstacionamiento(): Boolean

    protected fun guardarUsuario() {

        if (!repositorioEstacionamiento.usuarioExiste(estacionamiento.usuarioVehiculo)) {
            repositorioEstacionamiento.guardarUsuario(estacionamiento.usuarioVehiculo)
        }
    }

    fun eliminarUsuario() {

        if (repositorioEstacionamiento.usuarioExiste(estacionamiento.usuarioVehiculo)) {
            repositorioEstacionamiento.eliminarUsuario(estacionamiento.usuarioVehiculo)
        }
    }

    fun consultarListaUsuarios(): ArrayList<UsuarioVehiculo> {
        return repositorioEstacionamiento.listaUsuarios()
    }

    fun ingresoUsuarioEstacionamiento(diaDeLaSemana: Int) {

        var espacioDisponibleEnEstacionamiento = false

        val usuarioNoExiste =
            !repositorioEstacionamiento.usuarioExiste(estacionamiento.usuarioVehiculo)

        if (usuarioNoExiste) {
            espacioDisponibleEnEstacionamiento = consultaDisponibilidadEstacionamiento()
        }
        if (espacioDisponibleEnEstacionamiento && !estacionamiento.restriccionDeIngreso(
                diaDeLaSemana)
        ) {
            repositorioEstacionamiento.guardarUsuario(estacionamiento.usuarioVehiculo)
        } else {
            throw IngresoNoPermitidoRestriccionExcepcion()
        }
    }

}