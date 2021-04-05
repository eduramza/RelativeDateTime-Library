package com.eduramza.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.eduramza.relativetime.LanguageType
import com.eduramza.relativetime.RelativeTimeAgo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Relative", RelativeTimeAgo.relativeUnixTimePast(1617005583.0.toLong()))
        Log.d("Relative", RelativeTimeAgo.relativeTimePast(1616929768, LanguageType.PT_BR))
        Log.d("Relative", RelativeTimeAgo.relativeTimePast(1617005583, LanguageType.ES))

    }
}