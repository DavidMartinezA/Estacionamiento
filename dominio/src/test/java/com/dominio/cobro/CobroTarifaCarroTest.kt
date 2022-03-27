package com.dominio.cobro

import com.dominio.cobro.modelo.CobroTarifaCarro
import com.dominio.usuario.modelo.Usuario
import com.dominio.usuario.modelo.UsuarioCarro
import org.junit.Test

class CobroTarifaCarroTest {

    @Test
    fun cobroTarifa_ParametroCorrecto_CobroTarifa() {

        var cobro = CobroTarifaCarro()
        var usuario = UsuarioCarro("hsu531")
        var duracionServicio = 12

        val tarifa =cobro.cobroTarifa(duracionServicio, usuario )

        assert(tarifa == 8000)
    }
}