package com.example.tarjeta_de_presentacion

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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                // funcion componible final que representa la tarjeta de presentacion
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

// funcion componible que crea el bloque en donde armo la imagen personal junto
// al text en donde coloco mi nombre y la profesicion.
@Composable
fun PersonalImage(name: String, lastname: String){
    val image = painterResource(R.drawable.android_logo)
    //
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //
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
// funcion componible que crea el bloque en donde armmo la estructura para la informacion
// perssonal
@Composable
fun PersonalInformation(call: String, shared: String, email: String){
    // aqui determmino la columna principal
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val colorIcon = colorResource(R.color.coloriconsLetters)
        val colorLetter = R.color.coloriconsLetters
        // aqui primera fila crceada para acomodar la imagen y el texto que va de bajo
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
        ){
            // aqui cargo en esta imagen un icono
            Image(
                painter = painterResource(R.drawable.call),
                contentDescription = "call",
                colorFilter = ColorFilter.tint(colorIcon),
                modifier = Modifier.width(30.dp)
            )
            // aqui cargo el texto que va ssituado por debajo de la imagen
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
        // aqui segunda fila creada para accomodar los siguientes iconos y el texto que le corresponde
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
        ) {
            // imagen usada para cargar el icono en este caso es un icono de tipo compartir
            Image(
                painter = painterResource(R.drawable.share),
                contentDescription = "compartir",
                colorFilter = ColorFilter.tint(colorIcon),
                modifier = Modifier.width(30.dp)
            )
            // aqui coloco otros texto al lado del icono
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
        // aqui tercer y ultima fila en la cual vamos a colocar tambien un icono y su respectivo texto
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
        ){
            // aqui imagen que sera nuestro icono
            Image(
                painter = painterResource(R.drawable.mail),
                contentDescription = "call",
                colorFilter = ColorFilter.tint(colorIcon),
                modifier = Modifier.width(30.dp)
            )
            // texto correspondiente
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
// funcion componible final en la cual vamos a armar una estructura para poder
// utilizar el resto de funciones componibles que contienen la imagen personal (PersonalImage)
// y ademas tambien llamaremos a la funcion que tiene la informacion personal (PersonalInformation)
@Composable
fun PersonalView(name: String, lastname: String, call: String, shared: String, email: String){
    //aqui este componente nos permite crear un fondo muy bueno en el cual podemos darle diferentes
    // configuraciones como colores, tamaños etc etc
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(R.color.fondoTarjeta)
    ) {
        //  aqui determino una columna nueva para estructurar el resto de la app
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // aqui finalmente primero invoco la funcion componible en la cual
            // vamos a dibujar en la pantalla La imagen y el nombre de la persona
            PersonalImage(
                name = name,
                lastname = lastname
            )
            // aqui finalmente llamo a la funcion que me cargara la informacion personal
            // en la tarjeta personal
            PersonalInformation(
                call = call,
                shared = shared,
                email = email
            )
        }
    }
}

// AQUI PODEMOS IR ARMANDO LA VIEW SIN LA NECESIDAD DE TENER QUE RECOMPILAR TODO EL TIEMPO LA APP
// ESTA FUNCION COMPONIBLE NOS PERMITE REALIZAR ESTA TAREA SIN MAYOR PROBLEMA Y LUEGO FINALMENTE
// UNA VEZ QUE LA TENGAMOS ARMADA PODREMOS COMPILAR EL ARMADO FINAL
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