package org.example

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MusicActivity : AppCompatActivity() {
    var mp: MediaPlayer? = null
    val songsList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        val lv = findViewById<ListView>(R.id.lvTracks)
        val sbVol = findViewById<SeekBar>(R.id.sbVolume)
        val sbProg = findViewById<SeekBar>(R.id.sbProgress)
        val am = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        val fields = R.raw::class.java.fields
        val names = ArrayList<String>()
        for (field in fields) {
            try {
                songsList.add(field.getInt(field))
                names.add(field.name)
            } catch (e: Exception) { }
        }
        lv.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)

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
            mp = MediaPlayer.create(this, songsList[position])
            mp?.start()
            sbProg.max = mp!!.duration
        }

        findViewById<Button>(R.id.btnPlay).setOnClickListener { mp?.start() }
        findViewById<Button>(R.id.btnPause).setOnClickListener { mp?.pause() }

        Thread {
            while (true) {
                Thread.sleep(500)
                try {
                    if (mp != null && mp!!.isPlaying) {
                        runOnUiThread { sbProg.progress = mp!!.currentPosition }
                    }
                } catch (e: Exception) {}
            }
        }.start()
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