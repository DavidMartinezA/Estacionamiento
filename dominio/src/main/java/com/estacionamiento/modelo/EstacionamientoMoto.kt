package com.estacionamiento.modelo

import com.estacionamiento.servicio.ServicioEstacionamientoMoto
import com.usuario.modelo.UsuarioVehiculo
import com.usuario.modelo.UsuarioVehiculoMoto

class EstacionamientoMoto(
    usuarioVehiculo: UsuarioVehiculoMoto,
    private var servicioEstacionamientoMoto: ServicioEstacionamientoMoto,
) : Estacionamiento(usuarioVehiculo) {

    override val capacidadEstacionamiento = 10

    override fun consultarCapacidad(): Boolean {
        val listaMotos: ArrayList<UsuarioVehiculo> =
            servicioEstacionamientoMoto.consultarListaUsuarios()
        return listaMotos.filterIsInstance<UsuarioVehiculoMoto>().size < capacidadEstacionamiento
    }


}