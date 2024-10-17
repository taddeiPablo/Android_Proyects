package com.example.administrador_de_tareas

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.administrador_de_tareas.ui.theme.Administrador_de_tareasTheme
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

// aqui clase MainActivity la cual viene asociada a una View
class MainActivity : ComponentActivity() {
    // funcion onCreate funcionamiento similar al android anterior
    // y punto de entrada en el activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // aqui en esta seccion se comienza el diseño
        // de la view apartir de funcion composables
        setContent {
            // aqui funcion composable en la cual definimos el theme para esta view
            Administrador_de_tareasTheme {
                // aqui funcion composable la cual recibe dos argumentos.
                // esta funcion dibujara la imagen central.
                TaskComplete(
                    task = stringResource(R.string.task),
                    work = stringResource(R.string.work)
                )
            }
        }
    }
}
// aqui en esta funcion componible lo que realizo es armar
// la estructura para la view.
@Composable
fun TaskComplete(task: String, work: String, modifier: Modifier = Modifier){
    // utilizo este elemento que seria un simil a un lienzo y lo configuro
    // para que ocupe el tamaño maximo y un color de fondo en blanco.
    Surface(
        modifier = Modifier.fillMaxSize(), //que ocupe el tamaño maximo
        color = MaterialTheme.colorScheme.background // color de fondo por defecto
    ) {
        // aqui utilizo un elemento tipo Column
        // y ademas le aplico una serie de propiedades para lograr
        // centrar todo el contenido que mostrara la view
        Column(
            verticalArrangement = Arrangement.Center,// aqui alineamos de manera vertical el contenido en la columna
            horizontalAlignment = Alignment.CenterHorizontally,// aqui alineamos de manera horizontal el contenido en la columna
            modifier = Modifier.padding(18.dp) //aqui le aplico un padding general al contenido de la columna
        ) {
            // aqui creo una nueva funcion componible en la cual dibujo
            // los textos debajo de la imagen recibe dos argumentos
            // ambos textos
            CompleteInfo(
                task = task,
                work = work
            )
        }
    }
}

// aqui cree esta funcion componible en la cual armo
// la imagen y los texto debajo, ademas esto lo armo en
// formato de dos columnas
@Composable
fun CompleteInfo(task: String, work: String, modifier: Modifier = Modifier){
    // aqui obtenemos la imagen que utilizaremos en este ejemplo, la imagen
    // ya esta resourceada
    val image = painterResource(R.drawable.ic_task_completed)
    // aqui utilizo un elemento Column
    Column(
        modifier
    ) {
        // aqui utilizo un elemento Image
        Image(
            painter = image,// cargamos la imagen resourceadas
            contentDescription = null // no le agregamos descripcion
        )
    }
    // aqui tambien utilizo un elemento column
    Column(
        modifier
    ) {
        // aqui utilizo un text para el primer texto pasado por argumentos
        Text(
            text = task,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(
                top = 24.dp,
                start = 16.dp,
                end = 16.dp
            )
        )
        // aqui utilizo tambien un text para el segundo texto pasado por argumentos
        Text(
            text = work,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Administrador_de_tareasTheme {
        //Greeting("Android")
        TaskComplete(
            task = stringResource(R.string.task),
            work = stringResource(R.string.work)
        )
    }
}