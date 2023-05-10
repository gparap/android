package gparap.apps.piano

import android.annotation.SuppressLint
import android.graphics.Color
import android.media.SoundPool
import android.os.Bundle
import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.ACTION_UP
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var soundPool: SoundPool

    //natural notes
    private var c3 by Delegates.notNull<Int>()
    private var d3 by Delegates.notNull<Int>()
    private var e3 by Delegates.notNull<Int>()
    private var f3 by Delegates.notNull<Int>()
    private var g3 by Delegates.notNull<Int>()
    private var a3 by Delegates.notNull<Int>()
    private var b3 by Delegates.notNull<Int>()
    private var c4 by Delegates.notNull<Int>()

    //sharp notes
    private var cSharp by Delegates.notNull<Int>()
    private var dSharp by Delegates.notNull<Int>()
    private var fSharp by Delegates.notNull<Int>()
    private var gSharp by Delegates.notNull<Int>()
    private var aSharp by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create a SoundPool object
        soundPool = SoundPool.Builder().setMaxStreams(3).build()

        //load sound resources into memory
        c3 = soundPool.load(this, R.raw.c3, 1)
        d3 = soundPool.load(this, R.raw.d3, 1)
        e3 = soundPool.load(this, R.raw.e3, 1)
        f3 = soundPool.load(this, R.raw.f3, 1)
        g3 = soundPool.load(this, R.raw.g3, 1)
        a3 = soundPool.load(this, R.raw.a3, 1)
        b3 = soundPool.load(this, R.raw.b3, 1)
        c4 = soundPool.load(this, R.raw.c4, 1)
        cSharp = soundPool.load(this, R.raw.c3_sharp, 1)
        dSharp = soundPool.load(this, R.raw.d3_sharp, 1)
        fSharp = soundPool.load(this, R.raw.f3_sharp, 1)
        gSharp = soundPool.load(this, R.raw.g3_sharp, 1)
        aSharp = soundPool.load(this, R.raw.a3_sharp, 1)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun onWhiteButtonClick(view: View) {
        view.setOnTouchListener { v, event ->
            when (event?.action) {
                ACTION_DOWN -> v?.setBackgroundColor(Color.GRAY)
                ACTION_UP -> {
                    //handle the button color
                    v?.setBackgroundColor(Color.WHITE)

                    //play the note
                    when(view.id) {
                        R.id.button_note_c_low -> {soundPool.play(c3, 1F, 1F, 1,  0, 1F)}
                        R.id.button_note_d -> {soundPool.play(d3, 1F, 1F, 1,  0, 1F)}
                        R.id.button_note_e -> {soundPool.play(e3, 1F, 1F, 1,  0, 1F)}
                        R.id.button_note_f -> {soundPool.play(f3, 1F, 1F, 1,  0, 1F)}
                        R.id.button_note_g -> {soundPool.play(g3, 1F, 1F, 1,  0, 1F)}
                        R.id.button_note_a -> {soundPool.play(a3, 1F, 1F, 1,  0, 1F)}
                        R.id.button_note_b -> {soundPool.play(b3, 1F, 1F, 1,  0, 1F)}
                        R.id.button_note_c_high -> {soundPool.play(c4, 1F, 1F, 1,  0, 1F)}
                    }
                }
                else -> {
                    //for the gray color no to stuck
                    v?.setBackgroundColor(Color.WHITE)
                }
            }
            v?.onTouchEvent(event) ?: true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun onBlackButtonClick(view: View) {
        view.setOnTouchListener { v, event ->
            when (event?.action) {
                ACTION_DOWN -> v?.setBackgroundColor(Color.GRAY)
                ACTION_UP -> {
                    //handle the button color
                    v?.setBackgroundColor(Color.BLACK)

                    //play the note
                    when(view.id) {
                        R.id.button_note_c_sharp -> {soundPool.play(cSharp, 1F, 1F, 1,  0, 1F)}
                        R.id.button_note_d_sharp -> {soundPool.play(dSharp, 1F, 1F, 1,  0, 1F)}
                        R.id.button_note_f_sharp -> {soundPool.play(fSharp, 1F, 1F, 1,  0, 1F)}
                        R.id.button_note_g_sharp -> {soundPool.play(gSharp, 1F, 1F, 1,  0, 1F)}
                        R.id.button_note_a_sharp -> {soundPool.play(aSharp, 1F, 1F, 1,  0, 1F)}
                    }
                }
                else -> {
                    //for the gray color no to stuck
                    v?.setBackgroundColor(Color.BLACK)
                }
            }
            v?.onTouchEvent(event) ?: true
        }
    }
}