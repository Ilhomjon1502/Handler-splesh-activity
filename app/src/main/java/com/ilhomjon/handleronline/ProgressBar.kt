package com.ilhomjon.handleronline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.ilhomjon.handleronline.databinding.ActivityProgressBarBinding

class ProgressBar : AppCompatActivity() {

    lateinit var binding:ActivityProgressBarBinding
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler(Looper.getMainLooper())

        handler.postDelayed(runnable, 1000)
    }

    private val runnable = object : Runnable{
        override fun run() {
            binding.progressbar.setProgress(binding.progressbar.progress + 2)
            handler.postDelayed(this, 1000)
        }
    }
}