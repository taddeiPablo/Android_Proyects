package com.taddeipablo.myappXML.resultados

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.taddeipablo.myappXML.R

// NUEVA ACTIVITY CREADA - ESTA SERA LA VENTANA NUEVA DONDE MUESTRO EL RESULTADO
// DE LO INGRESADO EN LA PANTALLA ANTERIOR
class ResultActivity : AppCompatActivity() {
    // metodo OnCreate, este metodo es el principal de toda activity
    // el mismo se ejecutara cada vez que arranque la pantalla en la app
    // de este modo se ejecutara el onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // cargamos o asociamos un layout o XML
        setContentView(R.layout.activity_result)

        // aqui tomo el textview creado en el layout
        val tvresult = findViewById<TextView>(R.id.tvresult)
        // aqui finalmente recupero el extra que pase en la pantalla anterior
        // este extra contiene el valor ingresado en el edittext
        val nameIntent: String = intent.extras?.getString("extra_name").orEmpty()
        // aqui finalmente cargo el string que extraje del intent y lo muestro
        // en el textxview llamado tvvresult.
        tvresult.text = "Hola ${nameIntent}"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}