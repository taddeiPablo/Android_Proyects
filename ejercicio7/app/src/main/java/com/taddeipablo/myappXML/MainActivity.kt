package com.taddeipablo.myappXML

// aqui importamos las librerias necesarias para essta activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// AQUI CLASE PRINCINPAL MAINACTIVITY - LA CUAL HEREDA DE APPCOMPATACTIVITY
// desde aqui en el metodo onCreate sera nuestro punto de entrada a la aplicacion
// como el main en kotlin
class MainActivity : AppCompatActivity() {
    // metodo OnCreate, este metodo es el principal de toda activity
    // el mismo se ejecutara cada vez que arranque la pantalla en la app
    // de este modo se ejecutara el onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // aqui se cargar el layout a pintar cuando arranque la pantalla
        // atravez de la clase especial R accedemos a la definicion XML (activity_main)
        // que representara la parte visual de la pantalla.
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}