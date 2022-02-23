package gparap.apps.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import gparap.apps.quiz.data.QuizDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //DEBUG: create database tables
        val db = QuizDatabase(this, null)
        db.readableDatabase
        db.close()
    }
}