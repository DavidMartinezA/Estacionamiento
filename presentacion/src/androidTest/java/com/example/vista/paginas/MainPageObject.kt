package com.example.vista.paginas

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.presentacion.R
import org.hamcrest.Matchers

open class MainPageObject {

    companion object {
        inline fun <reified T : MainPageObject> on(): T {
            return MainPageObject().on()
        }
    }

    inline fun <reified T : MainPageObject> on(): T {
        return T::class.constructors.first().call()
    }

    fun ingresarPlacaVehiculo(placaUsuario: String): MainPageObject {

        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.ingreso_placa_vehiculo_calculo_cobro)))
            .perform(ViewActions.typeText(placaUsuario), ViewActions.closeSoftKeyboard())
        return this
    }

    fun seleccionarRadioButtonCarro(): MainPageObject {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.radio_button_carro),
            ViewMatchers.withText("CARRO")))
            .perform(ViewActions.scrollTo(), ViewActions.click())
        return this
    }

    fun seleccionarRadioButtonMoto(): MainPageObject {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.radio_button_moto),
            ViewMatchers.withText("MOTO")))
            .perform(ViewActions.scrollTo(), ViewActions.click())
        return this
    }

    fun seleccionarRadioButtonMotoCc(): MainPageObject {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.radio_button_moto_cilindraje),
            ViewMatchers.withText("MOTO (Alto Cilindraje 500cc)")))
            .perform(ViewActions.scrollTo(), ViewActions.click())
        return this
    }

    fun oprimirBotonIngreso(): MainPageObject {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.boton_ingreso)))
            .perform(ViewActions.scrollTo(), ViewActions.click())
        return this
    }

    fun verifiacarDialogoMensaje(mensaje: String): MainPageObject {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(android.R.id.message), ViewMatchers.withText(mensaje)))
            .check(ViewAssertions.matches(ViewMatchers.withText(mensaje)))
        return this
    }

    fun presionarAceptarDialogoMensaje(): MainPageObject {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(android.R.id.button1), ViewMatchers.withText("Aceptar")))
            .perform(ViewActions.scrollTo(), ViewActions.click())
        return this
    }

}