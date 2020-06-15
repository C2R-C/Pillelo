package com.example.pillelo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.FieldClassification
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferencias = getSharedPreferences("puntaje", Context.MODE_PRIVATE)
        val v = preferencias.getInt("puntos", 0).toString()
        tv2.setText(v)
        val preferencias1 = getSharedPreferences("intentos", Context.MODE_PRIVATE)
        val c = preferencias1.getInt("intento", 0).toString()
        tv5.setText(c)
        val preferencias2 = getSharedPreferences("juegos", Context.MODE_PRIVATE)
        val j = preferencias2.getInt("juego", 1).toString()
        tv7.setText(j)
        var numero = 1 + (Math.random() * 50).toInt()


        boton.setOnClickListener {

            if (et1.text.toString().isEmpty()) {
                Toast.makeText(this, "Ups! No ingresaste el número", Toast.LENGTH_LONG).show()
            }

            else {

                val jugador = et1.text.toString().toInt()
                var intentosactual = tv5.text.toString().toInt()
                var puntosactual = tv2.text.toString().toInt()
                var juegoactual = tv7.text.toString().toInt()

                if (numero == jugador) {

                    juegoactual++
                    tv7.text = juegoactual.toString()

                    if (intentosactual == 0) puntosactual += 100 else if (intentosactual > 0 && intentosactual < 4) puntosactual += 50 else puntosactual += 10

                    tv2.text = puntosactual.toString()
                    tv3.setText("Justo en el blanco. Bien hecho. Hagámoslo otra vez")
                    et1.setText("")
                    tv5.setText("0")
                    val preferencias2 = getSharedPreferences("juegosactuales", Context.MODE_PRIVATE)
                    val editor = preferencias2.edit()
                    editor.putInt("juego", juegoactual)
                    editor.commit()
                    val preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE)
                    val editor1 = preferencias.edit()
                    editor1.putInt("puntos", puntosactual)
                    editor1.commit()
                    numero = 1 + (Math.random() * 50).toInt()

                }

                else if (jugador < numero) {

                    intentosactual++
                    tv5.text = intentosactual.toString()
                    tv3.setText("Súbele que te quedaste corto")
                    et1.setText("")
                }

                else {
                    (jugador > numero)
                    intentosactual++
                    tv5.text = intentosactual.toString()
                    tv3.setText("Bájale que te pasaste")
                    et1.setText("")
                }
            }
        }
    }
}
