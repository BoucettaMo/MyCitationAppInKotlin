package com.example.citations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView

class PanoramaActivity : AppCompatActivity() {
    private lateinit var go:Button
    private lateinit var back:Button
    private lateinit var textView: TextView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panorama)
        go = findViewById(R.id.go)
        textView=findViewById(R.id.textView)
        back = findViewById(R.id.back)

        go.setOnClickListener {
            it.visibility=View.INVISIBLE

            val list = this.intent.getStringArrayListExtra("list")!!
             val myCountDownTimer=MyCountDownTimer(list,textView,it as Button)
            myCountDownTimer.countDownTimer.start()

        }

        back.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}