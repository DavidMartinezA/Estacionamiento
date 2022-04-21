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
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun ingresoCarroTest_placaValidaUsuarioNoExiste_usuarioGuardado() {

        onView(allOf(withId(R.id.ingreso_placa_vehiculo_calculo_cobro)))
            .perform(typeText("ccc987"), closeSoftKeyboard())

        onView(allOf(withId(R.id.radio_button_carro), withText("CARRO")))
            .perform(scrollTo(), click())

        onView(allOf(withId(R.id.boton_ingreso), withText("Ingreso")))
            .perform(scrollTo(), click())

        onView(allOf(withId(android.R.id.message), withText("Usuario Registrado")))
            .check(matches(withText("Usuario Registrado")))
    }

    @Test
    fun ingresoMotoTest_placaValidaUsuarioNoExiste_usuarioGuardado() {

        onView(allOf(withId(R.id.ingreso_placa_vehiculo_calculo_cobro)))
            .perform(typeText("ccc988"), closeSoftKeyboard())

        onView(allOf(withId(R.id.radio_button_moto), withText("MOTO")))
            .perform(scrollTo(), click())

        onView(allOf(withId(R.id.boton_ingreso), withText("Ingreso")))
            .perform(scrollTo(), click())

        onView(allOf(withId(android.R.id.message), withText("Usuario Registrado")))
            .check(matches(withText("Usuario Registrado")))
    }

    @Test
    fun ingresoCarroTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {

        onView(allOf(withId(R.id.ingreso_placa_vehiculo_calculo_cobro)))
            .perform(typeText(""), closeSoftKeyboard())

        onView(allOf(withId(R.id.radio_button_carro), withText("CARRO")))
            .perform(scrollTo(), click())

        onView(allOf(withId(R.id.boton_ingreso), withText("Ingreso")))
            .perform(scrollTo(), click())

        onView(allOf(withId(android.R.id.message), withText("Formato De Placa No Valido")))
            .check(matches(withText("Formato De Placa No Valido")))
    }

    @Test
    fun ingresoMotoTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {

        onView(allOf(withId(R.id.ingreso_placa_vehiculo_calculo_cobro)))
            .perform(typeText(""), closeSoftKeyboard())

        onView(allOf(withId(R.id.radio_button_moto), withText("MOTO")))
            .perform(scrollTo(), click())

        onView(allOf(withId(R.id.boton_ingreso), withText("Ingreso")))
            .perform(scrollTo(), click())

        onView(allOf(withId(android.R.id.message), withText("Formato De Placa No Valido")))
            .check(matches(withText("Formato De Placa No Valido")))
    }

    @Test
    fun ingresoUsuarioVehiculoTest_tipoVehiculoNoSelecionado_mensajeUsuarioRegistrado() {

        onView(allOf(withId(R.id.ingreso_placa_vehiculo_calculo_cobro)))
            .perform(typeText("kjh456"), closeSoftKeyboard())

        onView(allOf(withId(R.id.boton_ingreso), withText("Ingreso")))
            .perform(scrollTo(), click())

        onView(allOf(withId(android.R.id.message), withText("Usuario Registrado")))
            .check(matches(withText("Usuario Registrado")))
    }

    @Test
    fun ingresoUsuarioVehiculoTest_placaRestriccionMartesASabado_mensajeNoEstaAutorizadoIngresar() {

        onView(allOf(withId(R.id.ingreso_placa_vehiculo_calculo_cobro)))
            .perform(typeText("ajh456"), closeSoftKeyboard())

        onView(allOf(withId(R.id.boton_ingreso), withText("Ingreso")))
            .perform(scrollTo(), click())

        onView(allOf(withId(android.R.id.message), withText("No Esta Autorizado A Ingresar")))
            .check(matches(withText("No Esta Autorizado A Ingresar")))
    }

    @Test
    fun ingresoUsuarioVehiculoTest_placaTextoNull_mensajeFormatoPlacaNoValido() {

        onView(allOf(withId(R.id.boton_ingreso), withText("Ingreso")))
            .perform(scrollTo(), click())

        onView(allOf(withId(android.R.id.message), withText("Formato De Placa No Valido")))
            .check(matches(withText("Formato De Placa No Valido")))
    }

    @Test
    fun ingresoUsuarioVehiculoTest_usuarioYaRegistrado_mensajeUsuarioYaSeEncuentraRegistrado() {
        onView(
            allOf(withId(R.id.ingreso_placa_vehiculo_calculo_cobro),
                isDisplayed()))
            .perform(replaceText("uuu777"), closeSoftKeyboard())

        onView(
            allOf(withId(R.id.radio_button_carro), withText("CARRO"),
                isDisplayed()))
            .perform(scrollTo(), click())

        onView(
            allOf(withId(R.id.boton_ingreso), withText("Ingreso")))
            .perform(scrollTo(), click())

        onView(
            allOf(withId(android.R.id.button1), withText("Aceptar")))
            .perform(scrollTo(), click())

        onView(
            allOf(withId(R.id.boton_ingreso), withText("Ingreso")))
            .perform(scrollTo(), click())

        onView(
            allOf(withId(android.R.id.message), withText("UsuarioVehiculo Ya Existe"),
                withParent(withParent(withId(androidx.appcompat.R.id.scrollView))),
                isDisplayed()))
            .check(matches(withText("UsuarioVehiculo Ya Existe")))
    }

}
