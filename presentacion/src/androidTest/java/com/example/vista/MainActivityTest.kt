package com.example.vista

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.vista.paginas.MainPageObject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)
    private val pageObject = MainPageObject()

    @Before
    fun borrarBaseDatos() {
        InstrumentationRegistry.getInstrumentation().targetContext.deleteDatabase("baseDatos")
    }

    @Test
    fun ingresoCarroTest_placaValidaUsuarioNoExiste_usuarioGuardado() {

        //Given
        pageObject
            .ingresarPlacaVehiculo("ccc987")
            .seleccionarRadioButtonCarro()

        //When
        pageObject
            .oprimirBotonIngreso()

        //Then
        pageObject
            .verifiacarDialogoMensaje("Usuario Registrado")
    }

    @Test
    fun ingresoMotoTest_placaValidaUsuarioNoExiste_usuarioGuardado() {

        //Given
        pageObject
            .ingresarPlacaVehiculo("ccc988")
            .seleccionarRadioButtonMoto()

        //When
        pageObject
            .oprimirBotonIngreso()

        //Then
        pageObject
            .verifiacarDialogoMensaje("Usuario Registrado")
    }

    @Test
    fun ingresoMotoAltoCilindrajeTest_placaValidaUsuarioNoExiste_usuarioGuardado() {

        //Given
        pageObject
            .ingresarPlacaVehiculo("ccc989")
            .seleccionarRadioButtonMotoCc()

        //When
        pageObject
            .oprimirBotonIngreso()

        //Then
        pageObject
            .verifiacarDialogoMensaje("Usuario Registrado")
    }

    @Test
    fun ingresoCarroTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {

        //Given
        pageObject
            .ingresarPlacaVehiculo("")
            .seleccionarRadioButtonCarro()

        //When
        pageObject
            .oprimirBotonIngreso()

        //Then
        pageObject
            .verifiacarDialogoMensaje("Formato De Placa No Valido")
    }

    @Test
    fun ingresoMotoTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {

        //Given
        pageObject
            .ingresarPlacaVehiculo("")
            .seleccionarRadioButtonMoto()

        //When
        pageObject
            .oprimirBotonIngreso()

        //Then
        pageObject
            .verifiacarDialogoMensaje("Formato De Placa No Valido")
    }

    @Test
    fun ingresoMotoAltoCilindrajeTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {

        //Given
        pageObject
            .ingresarPlacaVehiculo("")
            .seleccionarRadioButtonMotoCc()

        //When
        pageObject
            .oprimirBotonIngreso()

        //Then
        pageObject
            .verifiacarDialogoMensaje("Formato De Placa No Valido")
    }

    @Test
    fun ingresoUsuarioVehiculoTest_tipoVehiculoNoSelecionado_mensajeUsuarioRegistradoComoCarro() {

        //Given
        pageObject
            .ingresarPlacaVehiculo("ccc990")

        //When
        pageObject
            .oprimirBotonIngreso()

        //Then
        pageObject
            .verifiacarDialogoMensaje("Usuario Registrado")
    }

    @Test
    fun ingresoUsuarioVehiculoTest_placaRestriccionMartesASabado_mensajeNoEstaAutorizadoIngresar() {

        //Given
        pageObject
            .ingresarPlacaVehiculo("Acc990")
            .seleccionarRadioButtonCarro()

        //When
        pageObject
            .oprimirBotonIngreso()

        //Then
        if (LocalDateTime.now().dayOfWeek.value == 1 || LocalDateTime.now().dayOfWeek.value == 7) {
            pageObject
                .verifiacarDialogoMensaje("Usuario Registrado")
        } else {
            pageObject
                .verifiacarDialogoMensaje("No Esta Autorizado A Ingresar")
        }
    }


    @Test
    fun ingresoUsuarioVehiculoTest_placaTextoNull_mensajeFormatoPlacaNoValido() {

        //Given
        //When
        pageObject
            .oprimirBotonIngreso()

        //Then
        pageObject
            .verifiacarDialogoMensaje("Formato De Placa No Valido")
    }

    @Test
    fun ingresoUsuarioVehiculoTest_usuarioYaRegistrado_mensajeUsuarioYaSeEncuentraRegistrado() {

        //Given
        pageObject
            .ingresarPlacaVehiculo("uuu777")
            .seleccionarRadioButtonCarro()
            .oprimirBotonIngreso()
            .presionarAceptarDialogoMensaje()

        //When
        pageObject
            .oprimirBotonIngreso()

        //Then
        pageObject
            .verifiacarDialogoMensaje("UsuarioVehiculo Ya Existe")
    }

}