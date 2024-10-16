package com.example.articulo_ejemplo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Articulo_ejemploTheme {
                /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }*/
                // nuestra base
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
@Composable
fun ArticleExample(title: String, paragraph1: String, paragraph2: String, modifier: Modifier = Modifier ){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        TitleImage(
            title = title//stringResource(R.string.Title)
        )
        ArticleContent(
            paragraph1 = paragraph1 ,//stringResource(R.string.Paragraph1),
            paragraph2 = paragraph2 //stringResource(R.string.Paragraph2)
        )
    }
}
@Composable
fun TitleImage(title: String, modifier: Modifier = Modifier){
    val image = painterResource(R.drawable.bg_compose_background)
    Column {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
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
@Composable
fun ArticleContent(paragraph1: String, paragraph2: String, modifier: Modifier = Modifier){
    Column(
        modifier = modifier.padding(
            top = 180.dp
        )
    ) {
        Text(
            text = paragraph1,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(
                start = 16.dp,
                end = 16.dp
            )
        )
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