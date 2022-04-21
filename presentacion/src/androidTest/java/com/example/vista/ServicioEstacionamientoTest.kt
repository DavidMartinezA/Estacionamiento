package com.example.vista

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.example.presentacion.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ServicioEstacionamientoTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun generarInformacionCobroTest_usuarioVehiculoCarroExiste_informacionServicioCobro() {

        onView(allOf(withId(R.id.ingreso_placa_vehiculo_calculo_cobro),
            isDisplayed()))
            .perform(replaceText("ggg555"), closeSoftKeyboard())

        onView(allOf(withId(R.id.radio_button_carro), withText("CARRO"),
            isDisplayed()))
            .perform(click())

        onView(allOf(withId(R.id.boton_ingreso), withText("Ingreso")))
            .perform(scrollTo(), click())

        onView(allOf(withId(android.R.id.button1), withText("Aceptar")))
            .perform(scrollTo(), click())

        onView(allOf(withId(R.id.boton_cobro_tarifa), withText("Cobro")))
            .perform(scrollTo(), click())

        onView(allOf(withId(R.id.cobros_servicio), withText("ggg555El Costo Del Servicio Es De=1000"),
            withParent(withParent(withId(R.id.tarjeta_tarifa_cobro))), isDisplayed()))
            .check(matches(withText("ggg555El Costo Del Servicio Es De=1000")))
    }

    @Test
    fun generarInformacionCobroTest_usuarioVehiculoMotoExiste_informacionServicioCobro() {

        onView(allOf(withId(R.id.ingreso_placa_vehiculo_calculo_cobro),
            isDisplayed()))
            .perform(replaceText("yyy666"), closeSoftKeyboard())

        onView(allOf(withId(R.id.radio_button_moto), withText("MOTO"),
            isDisplayed()))
            .perform(scrollTo(), click())

        onView(allOf(withId(R.id.boton_ingreso), withText("Ingreso")))
            .perform(scrollTo(), click())

        onView(allOf(withId(android.R.id.button1), withText("Aceptar")))
            .perform(scrollTo(), click())

        onView(allOf(withId(R.id.boton_cobro_tarifa), withText("Cobro")))
            .perform(scrollTo(), click())

        onView(allOf(withId(R.id.cobros_servicio), withText("yyy666El Costo Del Servicio Es De=500"),
            withParent(withParent(withId(R.id.tarjeta_tarifa_cobro))), isDisplayed()))
            .check(matches(withText("yyy666El Costo Del Servicio Es De=500")))
    }

    @Test
    fun generarInformacionCobroTest_usuarioVehiculoMotoAltoCilindrajeExiste_informacionServicioCobro() {

        onView(allOf(withId(R.id.ingreso_placa_vehiculo_calculo_cobro),
            isDisplayed()))
            .perform(replaceText("sss222"), closeSoftKeyboard())

        onView(allOf(withId(R.id.radio_button_moto_cilindraje), withText("MOTO (Alto Cilindraje 500cc)")))
            .perform(scrollTo(), click())

        onView(
            allOf(withId(R.id.boton_ingreso), withText("Ingreso")))
            .perform(scrollTo(), click())

        onView(
            allOf(withId(android.R.id.button1), withText("Aceptar")))
            .perform(scrollTo(), click())

        onView(
            allOf(withId(R.id.boton_cobro_tarifa), withText("Cobro")))
            .perform(scrollTo(), click())

        onView(
            allOf(withId(R.id.cobros_servicio), withText("sss222El Costo Del Servicio Es De=500"),
                withParent(withParent(withId(R.id.tarjeta_tarifa_cobro))), isDisplayed()))
            .check(matches(withText("sss222El Costo Del Servicio Es De=500")))
    }

    @Test
    fun generarInformacionCobroTest_usuarioVehiculoNoExiste_mensajeUsuarioNoSeEncuentraRegistrado() {

        onView(allOf(withId(R.id.ingreso_placa_vehiculo_calculo_cobro),
            isDisplayed()))
            .perform(replaceText("fff555"), closeSoftKeyboard())

        onView(allOf(withId(R.id.radio_button_carro), withText("CARRO"),
            isDisplayed()))
            .perform(click())

        onView(allOf(withId(R.id.boton_cobro_tarifa), withText("Cobro")))
            .perform(scrollTo(), click())

        onView(allOf(withId(android.R.id.message), withText("UsuarioVehiculo No Existe"),
            withParent(withParent(withId(androidx.appcompat.R.id.scrollView))),
            isDisplayed()))
            .check(matches(withText("UsuarioVehiculo No Existe")))
    }

}
