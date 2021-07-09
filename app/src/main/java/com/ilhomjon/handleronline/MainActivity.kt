package com.ilhomjon.handleronline

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ilhomjon.handleronline.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

//    lateinit var handler: Handler
//    var gameOn = false
//    var startTime:Long = 0
//    private val TAG = "MainActivity"

    lateinit var binding:ActivityMainBinding
    private var counter = 0
    private var time:Long = 0L
    private lateinit var handler: Handler
    var closeApp = 0L

    lateinit var handler1:Handler
    private var isBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        startTime = System.currentTimeMillis()
//
//        handler = object : Handler(Looper.getMainLooper()){
//            override fun handleMessage(msg: Message) {
//                super.handleMessage(msg)
//
//                if (gameOn){
//                    var seconds = (System.currentTimeMillis()-startTime)/1000
//                    Log.d(TAG, "handleMessage: $seconds")
//                }
//
//                handler.sendEmptyMessageDelayed(0, 1000)
//            }
//        }
//        gameOn = true
//        handler.sendEmptyMessage(0)


        handler = Handler(Looper.getMainLooper())

        if (time == 0L){
            time = SystemClock.uptimeMillis()
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 100)
        }

        binding.btn.setOnClickListener(this)
    }

    private var runnable = object : Runnable{
        override fun run() {
            val start = time
            val millis = SystemClock.uptimeMillis() - start
            var second = millis/1000
            val minut = second/60
            second %= 60
            binding.timeTv.text = "$minut : ${String.format("%02d", second)}"
            handler.postDelayed(this, 200)
        }
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
        closeApp = SystemClock.uptimeMillis() - time
    }

    override fun onResume() {
        super.onResume()
        time = SystemClock.uptimeMillis() - closeApp
        handler.postDelayed(runnable, 100)
    }

    override fun onClick(v: View?) {
        binding.counterTv.text = (++counter).toString()
    }

    //dasturni oynadan chiqib ketishini eshitib turadigan method
    override fun onBackPressed() {
        if (isBack) {
            super.onBackPressed()
            return
        }
        isBack = true
        handler1 = Handler(Looper.getMainLooper())
        Toast.makeText(this, "Please again click", Toast.LENGTH_SHORT).show()
        handler.postDelayed({
            isBack = false
        },2000)
    }
}