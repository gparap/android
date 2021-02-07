package gparap.apps.web_browser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by gparap on 2020-10-06.
 */
public class MainActivity extends AppCompatActivity {
    EditText editTextSearchUrl;
    ImageButton imageButtonSearchUrl;
    Button buttonGoogle, buttonFacebook, buttonInstagram,
            buttonYoutube, buttonWikipedia, buttonSnapchat,
            buttonGMail, buttonReddit, buttonViber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //get widgets
        editTextSearchUrl = findViewById(R.id.editTextSearchUrl);
        imageButtonSearchUrl = findViewById(R.id.imageButtonSearchUrl);
        buttonGoogle = findViewById(R.id.buttonGoogle);
        buttonFacebook = findViewById(R.id.buttonFacebook);
        buttonInstagram = findViewById(R.id.buttonInstagram);
        buttonYoutube = findViewById(R.id.buttonYoutube);
        buttonWikipedia = findViewById(R.id.buttonWikipedia);
        buttonSnapchat = findViewById(R.id.buttonSnapchat);
        buttonGMail = findViewById(R.id.buttonGMail);
        buttonReddit = findViewById(R.id.buttonReddit);
        buttonViber = findViewById(R.id.buttonViber);
    }

    public void onClick(View view) {
        Intent intent;
        String url;

        switch (view.getId()){
            //custom url search
            case R.id.imageButtonSearchUrl:
                //check if search is empty
                if (editTextSearchUrl.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please search web page.", Toast.LENGTH_SHORT).show();
                }else{
                    url = editTextSearchUrl.getText().toString();
                    url = Utils.searchUrl(url);

                    //perform a web search
                    intent = new Intent(MainActivity.this, WebViewActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                }
                break;
            //bookmark search
            case R.id.buttonGoogle:
                intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", "https://www.google.com");
                startActivity(intent);
                break;
            case R.id.buttonFacebook:
                intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", "https://www.facebook.com");
                startActivity(intent);
                break;
            case R.id.buttonInstagram:
                intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", "https://www.instagram.com");
                startActivity(intent);
                break;
            case R.id.buttonYoutube:
                intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", "https://www.youtube.com");
                startActivity(intent);
                break;
            case R.id.buttonWikipedia:
                intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", "https://www.wikipedia.org");
                startActivity(intent);
                break;
            case R.id.buttonSnapchat:
                intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", "https://www.snapchat.com");
                startActivity(intent);
                break;
            case R.id.buttonGMail:
                intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", "https://www.gmail.com");
                startActivity(intent);
                break;
            case R.id.buttonReddit:
                intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", "https://www.reddit.com");
                startActivity(intent);
                break;
            case R.id.buttonViber:
                intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", "https://www.viber.com");
                startActivity(intent);
                break;
        }
    }
}