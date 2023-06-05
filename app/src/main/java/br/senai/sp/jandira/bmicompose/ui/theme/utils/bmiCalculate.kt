package br.senai.sp.jandira.bmicompose.ui.theme.utils

import java.text.DecimalFormat
import kotlin.math.pow

fun bmiCalculate(weight: Int, height:Double): Double {
    var conta = weight/(height/100).pow(2)
    return conta.toDouble()
}

fun bmiFormat(bmi: Double): String{
    var decimal = DecimalFormat("#.##")

    return decimal.format(bmi)
}