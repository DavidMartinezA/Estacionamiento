package com.example.serviciosestacionamiento.vista

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.ingresousuarios.vista.ActividadIngresoUsuarios
import com.example.presentacion.R
import com.example.serviciosestacionamiento.vista.pagina.ServiciosEstacionamientoUsuarioPageObject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class ActividadServicioEstacionamientoTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(ActividadIngresoUsuarios::class.java)
    private val servicioEstacionamientoPageObject = ServiciosEstacionamientoUsuarioPageObject()

    @Before
    fun borrarBaseDatos() {
        InstrumentationRegistry.getInstrumentation().targetContext.deleteDatabase(R.string.nombre_base_datos.toString())
    }

    @Test
    fun generarInformacionCobroTest_usuarioVehiculoCarroExiste_informacionServicioCobro() {
        //Given
        mActivityTestRule.scenario
        servicioEstacionamientoPageObject
            .ingresarPlacaVehiculo("ggg555")
            .seleccionarRadioButtonCarro()
            .oprimirBotonIngreso()
            .presionarAceptarDialogoMensaje()

        //When
        servicioEstacionamientoPageObject
            .presionarBotonCobro()

        //Then
        servicioEstacionamientoPageObject.verificarTextoCobro("El Costo Del Servicio Es De =1000")
        mActivityTestRule.scenario.close()
    }

    @Test
    fun generarInformacionCobroTest_usuarioVehiculoMotoExiste_informacionServicioCobro() {
        //Given
        mActivityTestRule.scenario
        servicioEstacionamientoPageObject
            .ingresarPlacaVehiculo("yyy666")
            .seleccionarRadioButtonMoto()
            .oprimirBotonIngreso()
            .presionarAceptarDialogoMensaje()

        //When
        servicioEstacionamientoPageObject
            .presionarBotonCobro()

        //Then
        servicioEstacionamientoPageObject.verificarTextoCobro("El Costo Del Servicio Es De =500")
        mActivityTestRule.scenario.close()
    }

    @Test
    fun generarInformacionCobroTest_usuarioVehiculoMotoAltoCilindrajeExiste_informacionServicioCobro() {
        //Given
        mActivityTestRule.scenario
        servicioEstacionamientoPageObject
            .ingresarPlacaVehiculo("sYs222")
            .seleccionarRadioButtonMotoCc()
            .oprimirBotonIngreso()
            .presionarAceptarDialogoMensaje()

        //When
        servicioEstacionamientoPageObject
            .presionarBotonCobro()

        //Then
        servicioEstacionamientoPageObject.verificarTextoCobro("El Costo Del Servicio Es De =2500")
        mActivityTestRule.scenario.close()
    }

    @Test
    fun generarInformacionCobroTest_usuarioVehiculoNoExiste_mensajeUsuarioNoSeEncuentraRegistrado() {
        //Given
        mActivityTestRule.scenario
        servicioEstacionamientoPageObject
            .ingresarPlacaVehiculo("fff555")
            .seleccionarRadioButtonCarro()

        //When
        servicioEstacionamientoPageObject
            .presionarBotonCobro()

        //Then
        servicioEstacionamientoPageObject
            .verifiacarDialogoMensaje("UsuarioVehiculo No Existe")
        mActivityTestRule.scenario.close()
    }

}