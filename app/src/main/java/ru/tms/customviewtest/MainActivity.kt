package ru.tms.customviewtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

//        findViewById<RevertTextView>(R.id.revertText).setOnClickListener { revertText ->
//            (revertText as RevertTextView).isRevert
//        }
    }
}