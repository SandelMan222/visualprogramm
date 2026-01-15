package org.example

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File

class MusicActivity : AppCompatActivity() {
    var mp: MediaPlayer? = null
    val songsPaths = ArrayList<String>()
    val songsNames = ArrayList<String>()
    val rawIds = ArrayList<Int>()
    var isRaw = false

    lateinit var sbProg: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        val lv = findViewById<ListView>(R.id.lvTracks)
        val sbVol = findViewById<SeekBar>(R.id.sbVolume)
        sbProg = findViewById<SeekBar>(R.id.sbProgress)
        val am = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }

        loadSongs()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, songsNames)
        lv.adapter = adapter

        sbVol.max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        sbVol.progress = am.getStreamVolume(AudioManager.STREAM_MUSIC)
        sbVol.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(s: SeekBar?, p: Int, b: Boolean) {
                am.setStreamVolume(AudioManager.STREAM_MUSIC, p, 0)
            }
            override fun onStartTrackingTouch(s: SeekBar?) {}
            override fun onStopTrackingTouch(s: SeekBar?) {}
        })

        sbProg.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(s: SeekBar?, p: Int, fromUser: Boolean) {
                if (fromUser) mp?.seekTo(p)
            }
            override fun onStartTrackingTouch(s: SeekBar?) {}
            override fun onStopTrackingTouch(s: SeekBar?) {}
        })

        lv.setOnItemClickListener { _, _, position, _ ->
            mp?.stop()
            mp?.release()

            if (isRaw) {
                mp = MediaPlayer.create(this, rawIds[position])
            } else {
                mp = MediaPlayer.create(this, Uri.fromFile(File(songsPaths[position])))
            }

            mp?.start()
            sbProg.max = mp?.duration ?: 0
        }

        findViewById<Button>(R.id.btnPlay).setOnClickListener { mp?.start() }
        findViewById<Button>(R.id.btnPause).setOnClickListener { mp?.pause() }

        Thread {
            while (true) {
                try {
                    Thread.sleep(500)
                    if (mp != null && mp!!.isPlaying) {
                        runOnUiThread { sbProg.progress = mp!!.currentPosition }
                    }
                } catch (e: Exception) {}
            }
        }.start()
    }

    private fun loadSongs() {
        val musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
        if (musicDir.exists() && musicDir.isDirectory) {
            val files = musicDir.listFiles()
            if (files != null) {
                for (file in files) {
                    if (!file.isDirectory && file.name.endsWith(".mp3")) {
                        songsPaths.add(file.absolutePath)
                        songsNames.add(file.name)
                    }
                }
            }
        }

        if (songsNames.isEmpty()) {
            isRaw = true
            val fields = R.raw::class.java.fields
            for (field in fields) {
                try {
                    rawIds.add(field.getInt(field))
                    songsNames.add(field.name)
                } catch (e: Exception) {}
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mp?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mp?.release()
    }
}