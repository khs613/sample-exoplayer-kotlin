package com.khs.sample_exoplayer_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.ui.PlayerView
import com.khs.sample_exoplayer_kotlin.exoplayer.KHSExoPlayer

class MainActivity : AppCompatActivity() {
    private lateinit var mExoPlayer: KHSExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playerView: PlayerView = findViewById(R.id.playerView)
        mExoPlayer = KHSExoPlayer(this, playerView)
        startExoPlayer()
    }

    private fun startExoPlayer() {
        var playUrl = "https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_ts/master.m3u8"
        mExoPlayer.initPlayer(playUrl)
        mExoPlayer.startPlayer()
    }

    override fun onPause() {
        super.onPause()
        mExoPlayer.releasePlayer()
    }
}