package com.taddeipablo.myappXML

// aqui importamos las librerias necesarias para essta activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.taddeipablo.myappXML.resultados.ResultActivity

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

        // aqui estoy llamando al control button y a su vez creo el listener
        // para capturar el evento click
        val btnSaludo = findViewById<AppCompatButton>(R.id.saludo)
        // aqui utilizo el edittext y vavmos a ver como poder pasarle un texto
        val editTxt = findViewById<AppCompatEditText>(R.id.editTxt)
        //EVENT LISTENER
        btnSaludo.setOnClickListener{
            // aqui recupero lo ingresado al editText
            val txtEdit = editTxt.text.toString()
            // evaluo llamando al metodo isNotEmpty si la variable contiene algo o no
            if(txtEdit.isNotEmpty()){
                // si la variable txtEdit contiene algo procedo a crear un intent para
                // lanzar la siguiente pantalla de la siguiente manera
                val intent = Intent(this, ResultActivity::class.java)
                // aqui en el intent la agregamos nuevos datos para poder luego recuperarlos en
                // la siguiente pantalla
                intent.putExtra("extra_name", txtEdit)
                // aqui finalmente llamo al intent recientemente creado, de esta manera es como se
                //redirige de una pantalla a otra en android nativo
                startActivity(intent)
            }else{
                Log.i("Ejemplo","DEBE INGREASR UN TEXTO !!!!")
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}