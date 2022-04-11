package com.example.estacionamiento

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.presentacion.R

class CobroServicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cobro_servicio)

        val botonCalculoCobro = findViewById<Button>(R.id.boton_tarifa)

        botonCalculoCobro.setOnClickListener {
            val intento5 = Intent(this, MainActivity::class.java)
            startActivity(intento5)
            Toast.makeText(this, "pago realizado satisfactoriamente", Toast.LENGTH_SHORT).show()

        }
    }
}