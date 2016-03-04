package demo.tigerwit.com.nativcommunicateh5demo;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import org.json.JSONObject;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button showBtn = (Button)findViewById(R.id.btn_show);
        showBtn.setOnClickListener(this);
        Button hiddenBtn = (Button)findViewById(R.id.btn_hidden);
        hiddenBtn.setOnClickListener(this);
        webView =(WebView)findViewById(R.id.my_webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.requestFocus();
        webView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void jsCall(String str) {
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    String url = jsonObject.getString("url");
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                }catch (Exception e){

                }


            }
        }

                , "native");
            webView.loadUrl("file:///android_asset/www/index.html");
        }
    public void onClick(View view){
        if (view.getId()==R.id.btn_hidden){
            webView.loadUrl("javaScript:hidden()");
        }else if (view.getId()==R.id.btn_show){
            webView.loadUrl("javaScript:show()");
        }
    }
    }
