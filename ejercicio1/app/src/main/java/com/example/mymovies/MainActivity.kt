package com.example.mymovies

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovies.databinding.ActivityMainBinding

// aqui un ejemplo de una Activity, siempre que se crea un layout
// se asocia un activity en este caso para nuestra activity_main.xml tenemos
// un MainActivity.kt
class MainActivity : AppCompatActivity() {
    // aqui utilizamos el onCreate, en este metodo se dibuja o pinta
    // la view en nuestra app metodo fundamental en todas las activity.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // SEGUNDO EJEMPLO
        // aqui representa todos los controles en el layout, es la nueva forma
        // de realizar la consulta de los controles en la view
        val banding = ActivityMainBinding.inflate(layoutInflater);
        // aqui finalmente inflamos la view para que sea renderizada
        setContentView(banding.root);
        try {
            banding.recycleTest.layoutManager = LinearLayoutManager(this)
            banding.recycleTest.adapter = MoviesAdapter(
                listOf(
                    Movie("Pelicula1","url:1"),
                    Movie("Pelicula2","url:2"),
                    Movie("Pelicula3","url:3"),
                    Movie("Pelicula4","url:4")
                )
            )
        }catch (e: Exception) {
            Log.e("ERROR MAINACTIVITY", e.message.toString());
        }
    }
}