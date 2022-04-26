package com.example.ingresousuarios.vista

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.ingresousuarios.vista.pagina.ActividadIngresoPageObject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime


@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class ActividadIngresoUsuariosTest {

    @Rule
    @JvmField

    var mActivityTestRule = ActivityScenarioRule(ActividadIngresoUsuarios::class.java)
    private val mainActivityPageObject = ActividadIngresoPageObject()

    @Before
    fun borrarBaseDatos() {
        getInstrumentation().targetContext.deleteDatabase("baseDatos")
    }

    fun encenderPantalla() {

    }

    @Test
    fun ingresoCarroTest_placaValidaUsuarioNoExiste_usuarioGuardado() {
        mActivityTestRule.scenario
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

        mActivityTestRule.scenario.close()
    }

    @Test
    fun ingresoMotoTest_placaValidaUsuarioNoExiste_usuarioGuardado() {
        mActivityTestRule.scenario
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

        mActivityTestRule.scenario.close()
    }

    @Test
    fun ingresoMotoAltoCilindrajeTest_placaValidaUsuarioNoExiste_usuarioGuardado() {
        mActivityTestRule.scenario
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

        mActivityTestRule.scenario.close()

    }

    @Test
    fun ingresoCarroTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {
        mActivityTestRule.scenario
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

        mActivityTestRule.scenario.close()

    }

    @Test
    fun ingresoMotoTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {
        mActivityTestRule.scenario
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

        mActivityTestRule.scenario.close()

    }

    @Test
    fun ingresoMotoAltoCilindrajeTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {
        mActivityTestRule.scenario
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

        mActivityTestRule.scenario.close()

    }

    @Test
    fun ingresoUsuarioVehiculoTest_tipoVehiculoNoSelecionado_mensajeUsuarioRegistradoComoCarro() {
        mActivityTestRule.scenario
        //Given
        mainActivityPageObject
            .ingresarPlacaVehiculo("ccc990")

        //When
        mainActivityPageObject
            .oprimirBotonIngreso()

        //Then
        mainActivityPageObject
            .verifiacarDialogoMensaje("Usuario Registrado")

        mActivityTestRule.scenario.close()

    }

    @Test
    fun ingresoUsuarioVehiculoTest_placaRestriccionMartesASabado_mensajeNoEstaAutorizadoIngresar() {
        mActivityTestRule.scenario
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

        mActivityTestRule.scenario.close()

    }


    @Test
    fun ingresoUsuarioVehiculoTest_placaTextoNull_mensajeFormatoPlacaNoValido() {
        mActivityTestRule.scenario
        //Given
        //When
        mainActivityPageObject
            .oprimirBotonIngreso()

        //Then
        mainActivityPageObject
            .verifiacarDialogoMensaje("Formato De Placa No Valido")

        mActivityTestRule.scenario.close()

    }

    @Test
    fun ingresoUsuarioVehiculoTest_usuarioYaRegistrado_mensajeUsuarioYaSeEncuentraRegistrado() {
        mActivityTestRule.scenario
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

        mActivityTestRule.scenario.close()

    }

}