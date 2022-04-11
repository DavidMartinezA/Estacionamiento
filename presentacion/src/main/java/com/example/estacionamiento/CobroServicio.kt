package com.example.estacionamiento

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.presentacion.R
import com.example.presentacion.databinding.ActivityCobroServicioBinding

class CobroServicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cobro_servicio)
        val binding = ActivityCobroServicioBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.botonTarifa.setOnClickListener {
            val intento5 = Intent(this, MainActivity::class.java)
            Toast.makeText(this, "pago realizado satisfactoriamente", Toast.LENGTH_SHORT).show()
            startActivity(intento5)
        }
    }
}