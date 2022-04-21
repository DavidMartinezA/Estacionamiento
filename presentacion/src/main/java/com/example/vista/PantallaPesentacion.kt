package com.example.vista

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.presentacion.R
import kotlinx.coroutines.*

class PantallaPesentacion : AppCompatActivity() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_pesentacion)

        obtenerDuracionPantalla()
    }

    private fun obtenerDuracionPantalla() {
        activityScope.launch {
            delay(1000)
            val intent = Intent(this@PantallaPesentacion, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}
