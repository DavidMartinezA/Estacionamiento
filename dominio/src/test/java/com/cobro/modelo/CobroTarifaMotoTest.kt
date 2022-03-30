package com.cobro.modelo

import com.estacionamiento.modelo.EstacionamientoMoto
import com.usuario.modelo.UsuarioVehiculoMoto
import org.junit.Test
import java.time.LocalDate

class CobroTarifaMotoTest {

    @Test
    fun cobroTarifaMoto_DuracionCorrectaCilindrajeAltoTrue_CobroDeTarifa() {

        //Arrange
        val usuario = UsuarioVehiculoMoto("hsu53c", true)
        val estacionamiento = EstacionamientoMoto(usuario, LocalDate.now())
        val cobroTarifa = CobroTarifaMoto(estacionamiento)
        val duracionServicio = 10

        //Act
        val tarifa = cobroTarifa.cobroTarifa(duracionServicio)

        //Assert
        assert(tarifa == 6000)
    }

}


