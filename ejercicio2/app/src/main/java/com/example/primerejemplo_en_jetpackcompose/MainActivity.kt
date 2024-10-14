package com.example.primerejemplo_en_jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.primerejemplo_en_jetpackcompose.ui.theme.PrimerEjemplo_en_JetpackComposeTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration

// CLASE MAIN ACTIVITY - SIMILAR A LAS VERSION ANTERIORES DE ANDROID TIENE LA MISMA
// UTILIDAD FUNDAMENTAL EN ANDROID.
class MainActivity : ComponentActivity() {
    // aqui metodo onCreate tiene una igual funcionalidad que en versiones anteriores de android.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // aqui comenzanmos a ver las diferencia que existen en esta nueva version de android
        // este bloque se utiliza para definir el diseño de la IU utilizando jetpack compose
        setContent {
            // aqui declaramos una funcion composable en la cual colocaremos
            // el resto de elementos que colocaremos para formar la iu
            PrimerEjemplo_en_JetpackComposeTheme {
                /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                }*/
                // aqui creamos un elemento de tipo surface, este es un tipo de elemento
                // que representa una parte de la ui y que podemos aplicarle estilos y demas
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    /*GreetingText(
                        message = "Happy Birthday Sam!",
                        from = "From Emma",
                        modifier = Modifier.padding(8.dp)
                    )*/
                    // aqui creamos una funcion composable, en la cual creamos
                    // una card muy simple pero que muestra como trabajar en android.
                    //nota : aqui podemos ver que utilizamos un stringResources ya que los
                    // los string utilizados estan en el string.xml
                    GreetingImage(
                        message = stringResource(R.string.happy_birthday_sam_text),
                        from = stringResource(R.string.from_emma_text)
                    )
                }
            }
        }
    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}*/
//
@Composable
fun GreetingText(message: String, from: String,  modifier: Modifier = Modifier){
    // aqui creamos un elemento de tipo column que alinea a sus childs
    // de forma vertical.
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        // asi se puede aplicar color de fondo en  un text o otro elemento
        // modifier = modifier.background(color = Color.green)
        Text(
            text = message,
            color = Color.Blue,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(
                top = 150.dp
            )
        )
        Text(
            text = from,
            textDecoration = TextDecoration.Underline,
            color = Color.Blue,
            fontSize = 36.sp,
            modifier = modifier
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

// Aqui creamos una funcion composable, por la cual vamos a colocar
// una imagen de fondo + poder agregar de frente el texto necesario
// para poder ver nuestra tarjeta
@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier){
    val image = painterResource(R.drawable.androidparty)
    /*Image(
        painter = image,
        contentDescription = null
    )*/
    /*
    * GreetingText(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
    * )
    * */
    //
    Box(
        modifier
    ){
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )
        GreetingText(
            message = message,
            from = from
        )
    }
}

/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrimerEjemplo_en_JetpackComposeTheme {
        Greeting("Android")
    }
}*/

//
@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview(){
    PrimerEjemplo_en_JetpackComposeTheme{
        //GreetingText(message = "Happy Birthday Sam!", from = "From Emma")
        GreetingImage(
            message = "Happy Birthday Sam!",
            from = "From Emma"
        )
    }
}