package com.example.ingresousuarios.vista.pagina

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.presentacion.R
import org.hamcrest.Matchers

open class ActividadIngresoUsuarioPageObject {

    fun ingresarPlacaVehiculo(placaUsuario: String): ActividadIngresoUsuarioPageObject {

        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.ingreso_placa_vehiculo_calculo_cobro)))
            .perform(ViewActions.typeText(placaUsuario), ViewActions.closeSoftKeyboard())
        return this
    }

    fun seleccionarRadioButtonCarro(): ActividadIngresoUsuarioPageObject {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.radio_button_carro),
            ViewMatchers.withText(R.string.texto_vehiculo_carro)))
            .perform(ViewActions.scrollTo(), ViewActions.click())
        return this
    }

    fun seleccionarRadioButtonMoto(): ActividadIngresoUsuarioPageObject {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.radio_button_moto),
            ViewMatchers.withText(R.string.texto_vehiculo_moto)))
            .perform(ViewActions.scrollTo(), ViewActions.click())
        return this
    }

    fun seleccionarRadioButtonMotoCc(): ActividadIngresoUsuarioPageObject {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.radio_button_moto_cilindraje),
            ViewMatchers.withText(R.string.texto_vehiculo_moto_cilindraje)))
            .perform(ViewActions.scrollTo(), ViewActions.click())
        return this
    }

    fun oprimirBotonIngreso(): ActividadIngresoUsuarioPageObject {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.boton_ingreso)))
            .perform(ViewActions.scrollTo(), ViewActions.click())
        return this
    }

    fun verifiacarDialogoMensaje(mensaje: String): ActividadIngresoUsuarioPageObject {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(android.R.id.message), ViewMatchers.withText(mensaje)))
            .check(ViewAssertions.matches(ViewMatchers.withText(mensaje)))
        return this
    }

    fun presionarAceptarDialogoMensaje(): ActividadIngresoUsuarioPageObject {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(android.R.id.button1), ViewMatchers.withText(R.string.boton_aceptar)))
            .perform(ViewActions.scrollTo(), ViewActions.click())
        return this
    }

}