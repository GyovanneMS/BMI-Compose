package br.senai.sp.jandira.bmicompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.bmicompose.ui.theme.BMIComposeTheme
import br.senai.sp.jandira.bmicompose.ui.theme.utils.bmiCalculate
import br.senai.sp.jandira.bmicompose.ui.theme.utils.bmiFormat
import br.senai.sp.jandira.bmicompose.ui.theme.utils.getColor
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMIComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {

                    BMICalculator()
                }
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BMICalculator() {
    //Variaveis de estados para as labels
    var weightStats by rememberSaveable() {
        mutableStateOf("")
    }
    var heightStats by rememberSaveable() {
        mutableStateOf("")
    }
    var calculate by rememberSaveable() {
        mutableStateOf(0.0)
    }
    var expandState by rememberSaveable() {
        mutableStateOf(false)
    }
    var itsWeightError by rememberSaveable() {
        mutableStateOf(false)
    }
    var itsHeightError by rememberSaveable() {
        mutableStateOf(false)
    }
    var colorCard by rememberSaveable() {
        mutableStateOf("black")
    }
    //Objeto que controla a requisição de foco

    val weightFocusRequester = FocusRequester()
    val heightFocusRequester = FocusRequester()
    //Tela
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        //Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .background(Color.LightGray)
                .padding(top = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Logo
            Image(
                painter = painterResource(id = R.drawable.hearth),
                contentDescription = "Ícone da aplicação",
                modifier = Modifier.size(80.dp)
            )
            //Título
            Text(
                text = stringResource(id = R.string.app_title),
                color = Color(79, 54, 232),
                fontSize = 32.sp,
                letterSpacing = 4.sp
            )
        }
        //Main
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //Label 1
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = stringResource(id = R.string.weight),
                    color = Color.Black,
                    fontSize = 15.sp
                )
                OutlinedTextField(
                    value = weightStats,
                    onValueChange = {
                        Log.d("YYY", it)
                        var lastChar = if (it.length == 0) {
                            itsWeightError = true
                            it
                        } else {
                            itsWeightError = false
                            it[it.length - 1]
                        }
                        var newValue =
                            if (lastChar.equals('.') || lastChar.equals(','))
                                it.dropLast(1) else it
                        //String = vetor de char
                        weightStats = newValue
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp, bottom = 10.dp)
                        .focusRequester(weightFocusRequester),

                    trailingIcon = {
                        if (itsWeightError == true) Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = ""
                        )
                    },
                    isError = itsWeightError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )
                if (itsWeightError == true) {
                    Text(
                        text = stringResource(id = R.string.error),
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .fillMaxWidth(),
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.End,
                    )
                }
            }

            // label 2
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = stringResource(id = R.string.height),
                    color = Color.Black,
                    fontSize = 15.sp
                )
                OutlinedTextField(
                    value = heightStats,
                    onValueChange = {
                        Log.d("XXX", it)
                        var lastChar = if (it.length == 0) {
                            itsHeightError = true
                            it
                        } else {
                            itsHeightError = false
                            it[it.length - 1]
                        }
                        var newValue =
                            if (lastChar.equals('.') || lastChar.equals(','))
                                it.dropLast(1) else it
                        //String = vetor de char
                        heightStats = newValue
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp, bottom = 10.dp)
                        .focusRequester(heightFocusRequester),
                    // label = { Text(text = "Dança gatinho, dança") },,
                    trailingIcon = {
                        if (itsHeightError == true) Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = ""
                        )
                    },
                    isError = itsHeightError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )
                if (itsHeightError == true) {
                    Text(
                        text = stringResource(id = R.string.error),
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .fillMaxWidth(),
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.End,
                    )
                }


                //button calculate
                Button(
                    onClick = {

                        if (weightStats.isEmpty()) {
                            itsWeightError = true
                            weightFocusRequester.requestFocus()
                        } else if (heightStats.isEmpty()) {
                            itsHeightError = true
                            heightFocusRequester.requestFocus()
                        } else {
                            expandState = true
                            calculate = bmiCalculate(weightStats.toInt(), heightStats.toDouble())
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f)
                        .padding(10.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(Color(34, 175, 65)),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.calculate),
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight(700)
                    )
                }
            }
            //Footer, porém importante
            AnimatedVisibility(
                visible = expandState,
                enter = scaleIn() + expandVertically(expandFrom = Alignment.Bottom),
                // By Default, `scaleOut` uses the center as its pivot point. When used with an
                // ExitTransition that shrinks towards the center, the content will be shrinking both
                // in terms of scale and layout size towards the center.
                exit = fadeOut()
            ) {
                Card(
                    shape = RoundedCornerShape(
                        topStart = 32.dp, topEnd = 32.dp, bottomEnd = 0.dp, bottomStart = 0.dp
                    ),
                    backgroundColor = getColor(calculate)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxHeight()
                            .background(Color(79, 54, 232))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.8f)
                                .padding(5.dp, 5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = stringResource(id = R.string.score),
                                color = Color.White,
                                fontSize = 40.sp,
                                fontWeight = FontWeight(700)
                            )
                            Text(
                                text = bmiFormat(calculate),
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight(700)
                            )
                            Text(
                                text = stringResource(id = R.string.types),
                                color = Color.White,
                                fontSize = 30.sp,
                                fontWeight = FontWeight(700),
                                textAlign = TextAlign.Center
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = {
                                    weightStats = ""
                                    heightStats = ""
                                    expandState = false
                                    heightFocusRequester.requestFocus()
                                    weightFocusRequester.requestFocus()

                                },
                                colors = ButtonDefaults.buttonColors(Color(137, 119, 248)),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.Reset),
                                    color = Color.White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight(700),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Button(
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(Color(137, 119, 248)),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.Share),
                                    color = Color.White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight(700),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

//Como fazer um composer
//@Preview(showBackground = true)
//@Composer
//fun Teste() {
//    Row(modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceEvenly) {
//        for (x in 1..3){
//            Button(onClick = { /*TODO*/ }) {
//                Text(text = "$x")
//            }
//        }
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    BMIComposeTheme {
//
//    }
//}