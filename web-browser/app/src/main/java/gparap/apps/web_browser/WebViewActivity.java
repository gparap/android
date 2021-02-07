package gparap.apps.web_browser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by gparap on 2020-10-06.
 */
public class WebViewActivity extends AppCompatActivity {
    EditText editTextSearchUrl;
    ImageView imageButtonHome;
    WebView webViewUrl;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        //get widgets
        editTextSearchUrl = findViewById(R.id.editTextSearchUrl);
        imageButtonHome = findViewById(R.id.imageButtonHome);
        webViewUrl = findViewById(R.id.webViewUrl);

        //get intent extra and fill url
        String url = getIntent().getStringExtra("url");
        assert url != null;
        if (!url.isEmpty()) {
            editTextSearchUrl.setText(url);
        }

        //enable javascript
        WebSettings webSettings = webViewUrl.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //load the given URL
        webViewUrl.loadUrl(url);
        webViewUrl.setWebViewClient(new WebViewClient());   //replace current handler
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //check if WebView has a back history item
        if (webViewUrl.canGoBack()) {
            webViewUrl.goBack();
            finish();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            //go to home page
            case R.id.imageButtonHome:
                Intent intent = new Intent(WebViewActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            //custom url search
            case R.id.imageButtonSearchUrl:
                //check if search is empty
                if (editTextSearchUrl.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Please search web page.", Toast.LENGTH_SHORT).show();
                } else {
                    String url = editTextSearchUrl.getText().toString();
                    url = Utils.searchUrl(url);

                    //perform web search
                    webViewUrl.loadUrl("about:blank");  //empty webView first
                    webViewUrl.loadUrl(url);            //load url
                    final String finalUrl = url;        //helper
                    webViewUrl.setWebViewClient(new WebViewClient(){
                        @Override
                        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                            super.onReceivedError(view, request, error);
                            Toast.makeText(WebViewActivity.this, "Web site not found...", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onPageFinished(WebView view, String url) {
                            super.onPageFinished(view, url);

                            if (!url.isEmpty()) {
                                editTextSearchUrl.setText(finalUrl);
                            }
                        }
                    });
                }
                break;
        }
    }
}
