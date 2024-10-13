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

//
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrimerEjemplo_en_JetpackComposeTheme {
                /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                }*/
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

//
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