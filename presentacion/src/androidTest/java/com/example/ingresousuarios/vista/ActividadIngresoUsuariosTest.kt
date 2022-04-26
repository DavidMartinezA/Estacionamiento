package com.example.ingresousuarios.vista

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.ingresousuarios.vista.pagina.ActividadIngresoUsuarioPageObject
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
    private val actividadIngresoUsuarioPageObject = ActividadIngresoUsuarioPageObject()

    @Before
    fun borrarBaseDatos() {
        getInstrumentation().targetContext.deleteDatabase("baseDatos")
    }

    fun encenderPantalla() {

    }

    @Test
    fun ingresoCarroTest_placaValidaUsuarioNoExiste_usuarioGuardado() {
        //Given
        mActivityTestRule.scenario
        actividadIngresoUsuarioPageObject
            .ingresarPlacaVehiculo("ccc987")
            .seleccionarRadioButtonCarro()

        //When
        actividadIngresoUsuarioPageObject
            .oprimirBotonIngreso()

        //Then
        actividadIngresoUsuarioPageObject
            .verifiacarDialogoMensaje("Usuario Registrado")

        mActivityTestRule.scenario.close()
    }

    @Test
    fun ingresoMotoTest_placaValidaUsuarioNoExiste_usuarioGuardado() {
        //Given
        mActivityTestRule.scenario
        actividadIngresoUsuarioPageObject
            .ingresarPlacaVehiculo("ccc988")
            .seleccionarRadioButtonMoto()

        //When
        actividadIngresoUsuarioPageObject
            .oprimirBotonIngreso()

        //Then
        actividadIngresoUsuarioPageObject
            .verifiacarDialogoMensaje("Usuario Registrado")

        mActivityTestRule.scenario.close()
    }

    @Test
    fun ingresoMotoAltoCilindrajeTest_placaValidaUsuarioNoExiste_usuarioGuardado() {
        //Given
        mActivityTestRule.scenario
        actividadIngresoUsuarioPageObject
            .ingresarPlacaVehiculo("ccc989")
            .seleccionarRadioButtonMotoCc()

        //When
        actividadIngresoUsuarioPageObject
            .oprimirBotonIngreso()

        //Then
        actividadIngresoUsuarioPageObject
            .verifiacarDialogoMensaje("Usuario Registrado")

        mActivityTestRule.scenario.close()

    }

    @Test
    fun ingresoCarroTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {
        //Given
        mActivityTestRule.scenario
        actividadIngresoUsuarioPageObject
            .ingresarPlacaVehiculo("")
            .seleccionarRadioButtonCarro()

        //When
        actividadIngresoUsuarioPageObject
            .oprimirBotonIngreso()

        //Then
        actividadIngresoUsuarioPageObject
            .verifiacarDialogoMensaje("Formato De Placa No Valido")

        mActivityTestRule.scenario.close()

    }

    @Test
    fun ingresoMotoTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {
        //Given
        mActivityTestRule.scenario
        actividadIngresoUsuarioPageObject
            .ingresarPlacaVehiculo("")
            .seleccionarRadioButtonMoto()

        //When
        actividadIngresoUsuarioPageObject
            .oprimirBotonIngreso()

        //Then
        actividadIngresoUsuarioPageObject
            .verifiacarDialogoMensaje("Formato De Placa No Valido")

        mActivityTestRule.scenario.close()

    }

    @Test
    fun ingresoMotoAltoCilindrajeTest_placaTextoVacio_mensajeFormatoPlacaNoValido() {
        //Given
        mActivityTestRule.scenario
        actividadIngresoUsuarioPageObject
            .ingresarPlacaVehiculo("")
            .seleccionarRadioButtonMotoCc()

        //When
        actividadIngresoUsuarioPageObject
            .oprimirBotonIngreso()

        //Then
        actividadIngresoUsuarioPageObject
            .verifiacarDialogoMensaje("Formato De Placa No Valido")

        mActivityTestRule.scenario.close()

    }

    @Test
    fun ingresoUsuarioVehiculoTest_tipoVehiculoNoSelecionado_mensajeUsuarioRegistradoComoCarro() {
        //Given
        mActivityTestRule.scenario
        actividadIngresoUsuarioPageObject
            .ingresarPlacaVehiculo("ccc990")

        //When
        actividadIngresoUsuarioPageObject
            .oprimirBotonIngreso()

        //Then
        actividadIngresoUsuarioPageObject
            .verifiacarDialogoMensaje("Usuario Registrado")

        mActivityTestRule.scenario.close()

    }

    @Test
    fun ingresoUsuarioVehiculoTest_placaRestriccionMartesASabado_mensajeNoEstaAutorizadoIngresar() {
        //Given
        mActivityTestRule.scenario
        actividadIngresoUsuarioPageObject
            .ingresarPlacaVehiculo("Acc990")
            .seleccionarRadioButtonCarro()

        //When
        actividadIngresoUsuarioPageObject
            .oprimirBotonIngreso()

        //Then
        if (LocalDateTime.now().dayOfWeek.value == 1 || LocalDateTime.now().dayOfWeek.value == 7) {
            actividadIngresoUsuarioPageObject
                .verifiacarDialogoMensaje("Usuario Registrado")
        } else {
            actividadIngresoUsuarioPageObject
                .verifiacarDialogoMensaje("No Esta Autorizado A Ingresar")
        }

        mActivityTestRule.scenario.close()

    }


    @Test
    fun ingresoUsuarioVehiculoTest_placaTextoNull_mensajeFormatoPlacaNoValido() {
        //Given
        //When
        mActivityTestRule.scenario
        actividadIngresoUsuarioPageObject
            .oprimirBotonIngreso()

        //Then
        actividadIngresoUsuarioPageObject
            .verifiacarDialogoMensaje("Formato De Placa No Valido")

        mActivityTestRule.scenario.close()

    }

    @Test
    fun ingresoUsuarioVehiculoTest_usuarioYaRegistrado_mensajeUsuarioYaSeEncuentraRegistrado() {
        //Given
        mActivityTestRule.scenario
        actividadIngresoUsuarioPageObject
            .ingresarPlacaVehiculo("uuu777")
            .seleccionarRadioButtonCarro()
            .oprimirBotonIngreso()
            .presionarAceptarDialogoMensaje()

        //When
        actividadIngresoUsuarioPageObject
            .oprimirBotonIngreso()

        //Then
        actividadIngresoUsuarioPageObject
            .verifiacarDialogoMensaje("UsuarioVehiculo Ya Existe")

        mActivityTestRule.scenario.close()

    }

}