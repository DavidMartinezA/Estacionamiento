package com.example.serviciosestacionamiento.vista.pagina

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.ingresousuarios.vista.pagina.ActividadIngresoUsuarioPageObject
import com.example.presentacion.R
import org.hamcrest.Matchers


open class ServiciosEstacionamientoUsuarioPageObject : ActividadIngresoUsuarioPageObject() {

    fun presionarBotonCobro(): ServiciosEstacionamientoUsuarioPageObject {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.boton_cobro_tarifa)))
            .perform(ViewActions.scrollTo(), ViewActions.click())
        return this
    }

    fun verificarTextoCobro(mensaje: String): ServiciosEstacionamientoUsuarioPageObject {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.cobros_servicio),
            ViewMatchers.withText(mensaje),
            ViewMatchers.withParent(ViewMatchers.withParent(ViewMatchers.withId(R.id.tarjeta_tarifa_cobro))),
            ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.withText(mensaje)))
        return this
    }


}

