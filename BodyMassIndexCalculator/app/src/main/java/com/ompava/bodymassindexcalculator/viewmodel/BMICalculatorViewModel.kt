package com.ompava.bodymassindexcalculator.viewmodel

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.ompava.bodymassindexcalculator.model.BMICalculator
import com.ompava.bodymassindexcalculator.model.BMICalculator.BMIResponse

class BMICalculatorViewModel {

    private val bmiCalculator: BMICalculator = BMICalculator()


    val bmi: MutableLiveData<Double> = MutableLiveData()


    private fun calculate(weight: Double, height: Double) {
        //Using coroutine
        CoroutineScope(Dispatchers.IO).launch { //This run in background coroutine
            val bmiResult = bmiCalculator.calculate(BMICalculator.Request(weight, height))

            bmi.postValue(bmiResult)

        }
    }
}