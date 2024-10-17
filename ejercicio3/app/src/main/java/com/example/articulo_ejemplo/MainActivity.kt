package com.example.articulo_ejemplo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.articulo_ejemplo.ui.theme.Articulo_ejemploTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// aqui estoy armando el primer desafio realizado en android con jetpack
// en este caso arme un articulo muy simple para poner en practica todo
// lo visto hasta el momento con esta tecnologia
class MainActivity : ComponentActivity() {
    // funcion Oncreate punto de entrada esta activity que vamos a crear la parte visual
    // utilizando jetpack compose
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // aqui en esta seccion comenzamos a crear el diseño de la view.
        setContent {
            // aqui tenemos declarada una funcion componible la cual sera la principal
            // aqui dentro definimos el theme e iremos llamando el resto de funciones componibles
            // que necesitemos para trabajar.
            Articulo_ejemploTheme {
                // aqui declaro la funcion principal que utilizo para crear la view
                // del articulo en este caso esta funcion componible ademas recibe
                // tres argumentos que son textos que previamente fueron resourceados.
                // y se encuentran en el archivo string.xml
                ArticleExample(
                    title = stringResource(R.string.Title),
                    paragraph1 = stringResource(R.string.Paragraph1),
                    paragraph2 = stringResource(R.string.Paragraph2)
                )
            }
        }
    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}*/
/*** AQUI VOY A COMENZAR A ARMAR LOS BLOQUES DE LA UI QUE NECESITO ***/
// AQUI creo la funcion ArticleExample tambien componible y aqui armo la estructura
// del articulo creando dentro un surface y luego llamando al resto de funciones componibles.
@Composable
fun ArticleExample(title: String, paragraph1: String, paragraph2: String, modifier: Modifier = Modifier ){
    // aqui utiizo como base un elemento llamado surface que sera el fondo completo de la app
    // en este caso lo configure para que tome todo el ancho posible y que tenga un fondo por defecto
    // en blanco.
    Surface(
        modifier = Modifier.fillMaxSize(), // toma Todo el ancho
        color = MaterialTheme.colorScheme.background // aqui fondo en blanco.
    ) {
        // aqui creo una funcion componible la cual utilizo para dibujar una imagen
        // que sera tipo banner y un titulo debajo de esta imagen como argumento
        // recibe un titulo
        TitleImage(
            title = title // titulo del articulo
        )
        // aqui creo una nueva funcion componible en la cual aqui creo
        // el cuerpo del articulo pasandole como argumentos el primer
        // parrafo y un segundo parrafo para completar el cuerpo
        ArticleContent(
            paragraph1 = paragraph1 , // primer parrafo
            paragraph2 = paragraph2  // segundo parrafo
        )
    }
}
// aqui en esta funcion componible la creo para poder dibujar en pantalla
// la imagen que utilizaremos como banner mas el titulo del articulo
// y recibe como argumento un titulo y un modifier que se utiliza para setear los atributos
// necesarios de cada elemento que se van a dibujar en pantalla atributos como padding y demas.
@Composable
fun TitleImage(title: String, modifier: Modifier = Modifier){
    val image = painterResource(R.drawable.bg_compose_background) // aqui tomo el resource image para el banner
    // aqui utilizo un elemento tipo Column ya que para el banner y el titulo utilice este elemento
    // para contener de manera vertical una imagen y un text
    Column {
        // elemento tipo Image es similar a un img en html
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        // elemento tipo Text es similar a un label en html
        Text(
            text = title,
            color = Color.Black,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
        )
    }
}
// aqui en esta funcion componible la creo para armar el cuerpo del articulo
// lo formo de la siguiente forma utilizo una columna y dentro posiciono
// dos textos para el primer parrafo y el segundo parrafo
@Composable
fun ArticleContent(paragraph1: String, paragraph2: String, modifier: Modifier = Modifier){
    // aqui creo un elemento Column y le aplico un padding atravez del modifier que le paso
    // por argumento articleContent
    Column(
        modifier = modifier.padding(
            top = 180.dp
        )
    ) {
        // aqui creo un elemento text para el primer parrafo
        // le aplico un padding para contener el texto y le aplico una justificacion en la
        // alineacion
        Text(
            text = paragraph1,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(
                start = 16.dp,
                end = 16.dp
            )
        )
        // aqui creo un elemento text para el segundo parrafo
        // le aplico un padding para contener el texto y le aplico una justificacion en la
        // alineacion y un top para separar el segundo parrafo del primero
        Text(
            text = paragraph2,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(
                top = 10.dp,
                start = 16.dp,
                end = 16.dp
            )
        )
    }
}
/** FIN */

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Articulo_ejemploTheme {
        //Greeting("Android")
        ArticleExample(
            title = stringResource(R.string.Title),
            paragraph1 = stringResource(R.string.Paragraph1),
            paragraph2 = stringResource(R.string.Paragraph2)
        )
    }
}