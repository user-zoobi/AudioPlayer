package com.example.audioplayer

import android.media.MediaPlayer
import android.media.MediaPlayer.create
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var runnable: Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


        //Let's create a new Media Player

        val mediaplayer = MediaPlayer.create(applicationContext, R.raw.bekhayali)

        seekbar.progress = 0
        seekbar.max = mediaplayer.duration

        play_button.setOnClickListener {
            //
            if (!mediaplayer.isPlaying){
                mediaplayer.start()

                play_button.setImageResource(R.drawable.ic_pause)

            }else{
                mediaplayer.pause()
                play_button.setImageResource(R.drawable.ic_play_arrow)

            }
        }

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, changed: Boolean) {
                if (changed){
                    mediaplayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        runnable  = Runnable {
            seekbar.progress = mediaplayer.currentPosition
            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable, 1000)
        mediaplayer.setOnCompletionListener {
            play_button.setImageResource(R.drawable.ic_play_arrow)
            seekbar.progress = 0
        }
    }
}