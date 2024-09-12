package gparap.apps.memory_matcher

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //get the device orientation
        val orientation = resources.configuration.orientation

        //get all the image views for this orientation and put them inside an array
        val images: Array<ImageView?>?
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            val grid00 = findViewById<ImageView>(R.id.grid_row_0_col_0)
            val grid01 = findViewById<ImageView>(R.id.grid_row_0_col_1)
            val grid10 = findViewById<ImageView>(R.id.grid_row_1_col_0)
            val grid11 = findViewById<ImageView>(R.id.grid_row_1_col_1)
            val grid20 = findViewById<ImageView>(R.id.grid_row_2_col_0)
            val grid21 = findViewById<ImageView>(R.id.grid_row_2_col_1)
            val grid30 = findViewById<ImageView>(R.id.grid_row_3_col_0)
            val grid31 = findViewById<ImageView>(R.id.grid_row_3_col_1)
            images = arrayOf(grid00, grid01, grid10, grid11, grid20, grid21, grid30, grid31)
        } else {
            val grid00 = findViewById<ImageView>(R.id.grid_row_0_col_0)
            val grid01 = findViewById<ImageView>(R.id.grid_row_0_col_1)
            val grid02 = findViewById<ImageView>(R.id.grid_row_0_col_2)
            val grid03 = findViewById<ImageView>(R.id.grid_row_0_col_3)
            val grid10 = findViewById<ImageView>(R.id.grid_row_1_col_0)
            val grid11 = findViewById<ImageView>(R.id.grid_row_1_col_1)
            val grid12 = findViewById<ImageView>(R.id.grid_row_1_col_2)
            val grid13 = findViewById<ImageView>(R.id.grid_row_1_col_3)
            images = arrayOf(grid00, grid01, grid02, grid03, grid10, grid11, grid12, grid13)
        }

        //get a list of decoded images inside a bitmap array
        val bitmaps = ArrayList<Bitmap>()
        assets.list("planets")!!.forEach { asset ->
            //decode images
            val inputStream = assets.open("planets/$asset")
            bitmaps.add(BitmapFactory.decodeStream(inputStream))
        }

        //display on the grid the first pair of images, shuffled
        bitmaps.shuffle()
        for (i in 0 until bitmaps.size) {
            images[i]?.setImageBitmap(bitmaps[i]).apply {
                images[i]?.setOnClickListener { println("grid position $i clicked.") }
            }
        }

        //display on the grid the second pair of images, shuffled
        bitmaps.shuffle()
        for (i in 0 until bitmaps.size) {
            images[i + bitmaps.size]?.setImageBitmap(bitmaps[i]).apply {
                images[i + bitmaps.size]?.setOnClickListener { println("grid position ${i + bitmaps.size} clicked.") }
            }
        }
    }
}