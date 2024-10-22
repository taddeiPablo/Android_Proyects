package com.example.tarjeta_de_presentacion

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarjeta_de_presentacion.ui.theme.Tarjeta_de_presentacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tarjeta_de_presentacionTheme {
                //
                PersonalView(
                    name = stringResource(R.string.pablo_alejandro_taddei_text),
                    lastname = stringResource(R.string.analista_programador_text),
                    call =  stringResource(R.string._mobile_text),
                    shared = stringResource(R.string.redesSociales_text),
                    email = stringResource(R.string._email_personal_text)
                )
            }
        }
    }
}

//
@Composable
fun PersonalImage(name: String, lastname: String){
    val image = painterResource(R.drawable.android_logo)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Surface(
            color = colorResource(R.color.fondo_imagen)
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier.width(150.dp)
            )
        }
        Text(
            text= name,
            color = colorResource(R.color.font),
            fontSize = 27.sp,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center,
            lineHeight = 70.sp
        )
        Text(
            text = lastname,
            color = colorResource(R.color.coloriconsLetters),
            fontSize = 17.sp,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            lineHeight = 30.sp
        )
    }
}
//
@Composable
fun PersonalInformation(call: String, shared: String, email: String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val colorIcon = colorResource(R.color.coloriconsLetters)
        val colorLetter = R.color.coloriconsLetters

        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
        ){
            Image(
                painter = painterResource(R.drawable.call),
                contentDescription = "call",
                colorFilter = ColorFilter.tint(colorIcon),
                modifier = Modifier.width(30.dp)
            )
            Text(
                text = call,
                fontSize = 14.sp,
                lineHeight = 40.sp,
                color = colorResource(id = colorLetter),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                        .padding(
                            start = 28.dp,
                            end = 8.dp
                        )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
        ){
            Image(
                painter = painterResource(R.drawable.share),
                contentDescription = "compartir",
                colorFilter = ColorFilter.tint(colorIcon),
                modifier = Modifier.width(30.dp)
            )
            Text(
                text= shared,
                fontSize = 14.sp,
                lineHeight = 40.sp,
                color = colorResource(id = colorLetter),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(
                        start = 28.dp,
                        end = 8.dp
                    )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
        ){
            Image(
                painter = painterResource(R.drawable.mail),
                contentDescription = "call",
                colorFilter = ColorFilter.tint(colorIcon),
                modifier = Modifier.width(30.dp)
            )
            Text(
                text = email,
                fontSize = 14.sp,
                lineHeight = 25.sp,
                color = colorResource(id = colorLetter),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(
                        start = 28.dp,
                        end = 1.dp
                    )
            )
        }
    }
}
//
@Composable
fun PersonalView(name: String, lastname: String, call: String, shared: String, email: String){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(R.color.fondoTarjeta)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PersonalImage(
                name = name,
                lastname = lastname
            )
            PersonalInformation(
                call = call,
                shared = shared,
                email = email
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Tarjeta_de_presentacionTheme {
        //stringResource(R.string.pablo_alejandro_taddei_text)
        //stringResource(R.string.analista_programador_text)
        //stringResource(R.string._mobile_text)
        //stringResource(R.string.redesSociales_text)
        //stringResource(R.string._email_personal_text)
        PersonalView(
            name = stringResource(R.string.pablo_alejandro_taddei_text),
            lastname = stringResource(R.string.analista_programador_text),
            call =  stringResource(R.string._mobile_text),
            shared = stringResource(R.string.redesSociales_text),
            email = stringResource(R.string._email_personal_text)
        )
    }
}