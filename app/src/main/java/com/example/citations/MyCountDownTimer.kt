package com.example.citations

import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.Timer
import java.util.TimerTask

class MyCountDownTimer(private val list:ArrayList<String>,private val textView: TextView,private val button:Button) {


    private var secondRemaining = list.size-1
     val countDownTimer= object: CountDownTimer(((secondRemaining+1)*5000).toLong(),5000) {
        override fun onTick(millisUntilFinished: Long) {
            textView.text=list[list.size-1-secondRemaining]
            secondRemaining--

        }

        override fun onFinish() {
            /*val timer= Timer()
             timer.schedule(object : TimerTask() {
                 override fun run() {
                     button.visibility= View.VISIBLE
                 }
             },3000)
*/           button.visibility= View.VISIBLE

        }

    }


}
