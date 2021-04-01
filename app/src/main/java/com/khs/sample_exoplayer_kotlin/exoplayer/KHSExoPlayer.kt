package com.khs.sample_exoplayer_kotlin.exoplayer

import android.content.Context
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.Util
import com.khs.sample_exoplayer_kotlin.R

/**************************************************************************************************
 * Title : ExoPlayer
 * Description :
 *
 * @author   kimhyesoo123@naver.com
 * @since    2021-03-31 오후 6:34
**************************************************************************************************/

class KHSExoPlayer (private val context: Context, private val playerView: PlayerView) {
    private lateinit var exoPlayer: SimpleExoPlayer
    private lateinit var mediaItem: MediaItem
    private lateinit var dataSourceFactory: DataSource.Factory

    private var currentWindow = 0
    private var playbackPosition: Long = 0
    private var isPlayerPlaying = false

    fun initPlayer(url:String?) {
        mediaItem = MediaItem.Builder()
            .setUri(url)
            .setMimeType(MimeTypes.APPLICATION_M3U8)
            .build()

        dataSourceFactory = DefaultDataSourceFactory(context, Util.getUserAgent(context, context.getString(
            R.string.app_name)))

        exoPlayer = SimpleExoPlayer.Builder(context).build().apply {
            playWhenReady = isPlayerPlaying
            seekTo(currentWindow, playbackPosition)
            setMediaItem(mediaItem, false)
            prepare()
        }
        playerView.player = exoPlayer
        playerView.onResume()
    }

    fun startPlayer() {
        exoPlayer.playWhenReady = true
    }

    fun pausePlayer() {
        if (::exoPlayer.isInitialized) {
            exoPlayer.playWhenReady = false
            playerView.hideController()
            playerView.onPause()
        }
    }

    fun releasePlayer() {
        if (::exoPlayer.isInitialized) {
            playbackPosition = exoPlayer.currentPosition
            currentWindow = exoPlayer.currentWindowIndex
            isPlayerPlaying = exoPlayer.playWhenReady
            exoPlayer.release()
        }
    }
}