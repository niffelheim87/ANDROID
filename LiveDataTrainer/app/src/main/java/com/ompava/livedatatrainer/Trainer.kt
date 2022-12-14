package com.ompava.livedatatrainer

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

import java.util.*
import androidx.lifecycle.MutableLiveData




typealias  OnOrder = (order:String) -> Unit

class Trainer {
    val random:Random = Random()

    var training:Job? = null

    var exercise = 0
    var repetitions = -1


    fun startTraining(onOrder: OnOrder) {
        if (training == null || training!!.isCancelled || training!!.isCompleted) {
            training = CoroutineScope(Dispatchers.IO).launch {

                while (true) {
                    if (repetitions < 0) {
                        repetitions = random.nextInt(3) + 3
                        exercise = random.nextInt(5) + 1
                    }
                    onOrder("EXERCISE" + exercise + ":" + if (repetitions == 0) "CHANGE" else repetitions)
                    repetitions--

                    delay(1000)
                }

            }
        }
    }

    fun stopTraining() {
        training?.let {
            if(it.isActive)
                it.cancel()
        }
        exercise = 0
        repetitions = -1

    }
}