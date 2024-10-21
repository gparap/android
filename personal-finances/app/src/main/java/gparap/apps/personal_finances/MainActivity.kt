package gparap.apps.personal_finances

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import gparap.apps.personal_finances.ui.AddTransactionActivity
import gparap.apps.personal_finances.ui.AllTransactionsActivity

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

        //redirect to add transaction screen
        findViewById<FloatingActionButton>(R.id.fab_addTransaction).setOnClickListener {
            startActivity(Intent(this, AddTransactionActivity::class.java))
        }

        //redirect to all transactions screen
        findViewById<ImageView>(R.id.imageView_all_transactions_background).setOnClickListener {
            startActivity(Intent(this, AllTransactionsActivity::class.java))
        }
    }
}
