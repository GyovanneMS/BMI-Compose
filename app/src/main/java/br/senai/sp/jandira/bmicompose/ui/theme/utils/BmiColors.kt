package br.senai.sp.jandira.bmicompose.ui.theme.utils

import androidx.compose.ui.graphics.Color

fun getColor (bmi: Double): Color{
    return if(bmi < 17)
        Color(0xc24e00)
    else if (bmi == 17.0 && bmi <=18.4 )
        Color.Yellow
    else if (bmi == 18.5 && bmi <=24.9 )
        Color.Green
    else if (bmi == 25.0 && bmi <=29.9 )
        Color.Green
    else if (bmi == 30.0 && bmi <=34.9 )
        Color.Yellow
    else if (bmi == 35.0 && bmi <=40.0 )
        Color(0xc24e00)
    else
        Color.Red
}