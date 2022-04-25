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
class ActividadPrincipalTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(ActividadPrincipal::class.java)
    private val mainActivityPageObject = MainPageObject()

    @Before
    fun borrarBaseDatos() {
        InstrumentationRegistry.getInstrumentation().targetContext.deleteDatabase("baseDatos")
    }

    @Test
    fun ingresoCarroTest_placaValidaUsuarioNoExiste_usuarioGuardado() {

        //Given
        mainActivityPageObject
            .ingresarPlacaVehiculo("ccc987")
            .seleccionarRadioButtonCarro()

        //When
        mainActivityPageObject
            .oprimirBotonIngreso()

        //Then
        mainActivityPageObject
            .verifiacarDialogoMensaje("Usuario Registrado")
    }

    @Test
    fun ingresoMotoTest_placaValidaUsuarioNoExiste_usuarioGuardado() {

        //Given
        mainActivityPageObject
            .ingresarPlacaVehiculo("ccc988")
            .seleccionarRadioButtonMoto()

        //When
        mainActivityPageObject
            .oprimirBotonIngreso()

        //Then
        mainActivityPageObject
            .verifiacarDialogoMensaje("Usuario Registrado")
    }

    @Test
    fun ingresoMotoAltoCilindrajeTest_placaValidaUsuarioNoExiste_usuarioGuardado() {

        //Given
        mainActivityPageObject
            .ingresarPlacaVehiculo("ccc989")
            .seleccionarRadioButtonMotoCc()

        //When
        mainActivityPageObject
            .oprimirBotonIngreso()

        //Then
        mainActivityPageObject
            .verifiacarDialogoMensaje("Usuario Registrado")
    }

    @Test
    fun ingresoCarroTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {

        //Given
        mainActivityPageObject
            .ingresarPlacaVehiculo("")
            .seleccionarRadioButtonCarro()

        //When
        mainActivityPageObject
            .oprimirBotonIngreso()

        //Then
        mainActivityPageObject
            .verifiacarDialogoMensaje("Formato De Placa No Valido")
    }

    @Test
    fun ingresoMotoTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {

        //Given
        mainActivityPageObject
            .ingresarPlacaVehiculo("")
            .seleccionarRadioButtonMoto()

        //When
        mainActivityPageObject
            .oprimirBotonIngreso()

        //Then
        mainActivityPageObject
            .verifiacarDialogoMensaje("Formato De Placa No Valido")
    }

    @Test
    fun ingresoMotoAltoCilindrajeTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {

        //Given
        mainActivityPageObject
            .ingresarPlacaVehiculo("")
            .seleccionarRadioButtonMotoCc()

        //When
        mainActivityPageObject
            .oprimirBotonIngreso()

        //Then
        mainActivityPageObject
            .verifiacarDialogoMensaje("Formato De Placa No Valido")
    }

    @Test
    fun ingresoUsuarioVehiculoTest_tipoVehiculoNoSelecionado_mensajeUsuarioRegistradoComoCarro() {

        //Given
        mainActivityPageObject
            .ingresarPlacaVehiculo("ccc990")

        //When
        mainActivityPageObject
            .oprimirBotonIngreso()

        //Then
        mainActivityPageObject
            .verifiacarDialogoMensaje("Usuario Registrado")
    }

    @Test
    fun ingresoUsuarioVehiculoTest_placaRestriccionMartesASabado_mensajeNoEstaAutorizadoIngresar() {

        //Given
        mainActivityPageObject
            .ingresarPlacaVehiculo("Acc990")
            .seleccionarRadioButtonCarro()

        //When
        mainActivityPageObject
            .oprimirBotonIngreso()

        //Then
        if (LocalDateTime.now().dayOfWeek.value == 1 || LocalDateTime.now().dayOfWeek.value == 7) {
            mainActivityPageObject
                .verifiacarDialogoMensaje("Usuario Registrado")
        } else {
            mainActivityPageObject
                .verifiacarDialogoMensaje("No Esta Autorizado A Ingresar")
        }
    }


    @Test
    fun ingresoUsuarioVehiculoTest_placaTextoNull_mensajeFormatoPlacaNoValido() {

        //Given
        //When
        mainActivityPageObject
            .oprimirBotonIngreso()

        //Then
        mainActivityPageObject
            .verifiacarDialogoMensaje("Formato De Placa No Valido")
    }

    @Test
    fun ingresoUsuarioVehiculoTest_usuarioYaRegistrado_mensajeUsuarioYaSeEncuentraRegistrado() {

        //Given
        mainActivityPageObject
            .ingresarPlacaVehiculo("uuu777")
            .seleccionarRadioButtonCarro()
            .oprimirBotonIngreso()
            .presionarAceptarDialogoMensaje()

        //When
        mainActivityPageObject
            .oprimirBotonIngreso()

        //Then
        mainActivityPageObject
            .verifiacarDialogoMensaje("UsuarioVehiculo Ya Existe")
    }

}