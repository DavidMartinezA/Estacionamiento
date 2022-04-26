package com.example.pantallapresentacion.vista

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ingresousuarios.vista.ActividadIngresoUsuarios
import com.example.presentacion.R
import kotlinx.coroutines.*

class ActividadPantallaPesentacion : AppCompatActivity() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_pantalla_pesentacion)

        obtenerDuracionPantalla()
    }

    private fun obtenerDuracionPantalla() {
        activityScope.launch {
            delay(1000)
            val intent = Intent(this@ActividadPantallaPesentacion, ActividadIngresoUsuarios::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}
