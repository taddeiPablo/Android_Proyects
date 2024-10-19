package com.example.cuadrante_de_compose

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cuadrante_de_compose.ui.theme.Cuadrante_de_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Cuadrante_de_ComposeTheme {
                FourQuadrants()
            }
        }
    }
}

@Composable
fun FourQuadrants(modifier: Modifier = Modifier){
    var title: String
    var content: String
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(color = colorResource(R.color.cuadrante1)),
                    contentAlignment = Alignment.Center
                ){
                    title = stringResource(R.string.title1)
                    content = stringResource(R.string.content1)
                    Quadrants(
                        title = title,
                        content = content,
                        modifier
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(color = colorResource(R.color.cuadrante2)),
                        contentAlignment = Alignment.Center
                ){
                    title = stringResource(R.string.title2)
                    content = stringResource(R.string.content2)
                    Quadrants(
                        title = title,
                        content = content,
                        modifier
                    )
                }
            }
            Row(
                modifier = Modifier.weight(1f)
            ){
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(color = colorResource(R.color.cuadrante3)),
                    contentAlignment = Alignment.Center
                ){
                    title = stringResource(R.string.title3)
                    content = stringResource(R.string.content3)
                    Quadrants(
                        title = title,
                        content = content,
                        modifier
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(color = colorResource(R.color.cuadrante4)),
                    contentAlignment = Alignment.Center
                ){
                    title = stringResource(R.string.title4)
                    content = stringResource(R.string.content4)
                    Quadrants(
                        title = title,
                        content = content,
                        modifier
                    )
                }
            }
        }
    }
}

@Composable
fun Quadrants(title: String, content: String, modifier: Modifier = Modifier){
    Column(
        verticalArrangement = Arrangement.Center,// aqui alineamos de manera vertical el contenido en la columna
        horizontalAlignment = Alignment.CenterHorizontally,// aqui alineamos de manera horizontal el contenido en la columna
        modifier = modifier.padding(
            top = 180.dp
        )
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(
                start = 16.dp,
                end = 16.dp
            )
        )
        Text(
            text = content,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(
                top = 10.dp,
                start = 16.dp,
                end = 16.dp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Cuadrante_de_ComposeTheme {
        FourQuadrants()
    }
}