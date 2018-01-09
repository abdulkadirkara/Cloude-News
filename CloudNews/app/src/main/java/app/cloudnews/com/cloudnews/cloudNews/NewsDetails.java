package app.cloudnews.com.cloudnews.cloudNews;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import app.cloudnews.com.cloudnews.R;
import app.cloudnews.com.cloudnews.model.Articles;
import app.cloudnews.com.cloudnews.model.Sources;
import butterknife.OnClick;

import static app.cloudnews.com.cloudnews.R2.layout.activity_web;
import static app.cloudnews.com.cloudnews.util.ProjectPreferences.setHeader;

public class NewsDetails extends AppCompatActivity {

   private WebView webView;
     ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web);
        webView=(WebView)findViewById(R.id.webview);
        Articles articles=new Articles();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl(getIntent().getStringExtra("id"));



        webView.setWebViewClient(new WebViewClient() {

            // This method will be triggered when the Page Started Loading

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressDialog = ProgressDialog.show(NewsDetails.this, null,
                        "Loading...");
                progressDialog.setCancelable(true);
                super.onPageStarted(view, url, favicon);
            }
            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();
                super.onPageFinished(view, url);
            }
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                progressDialog.dismiss();
                // You can redirect to your own page instead getting the default
                // error page
                Toast.makeText(NewsDetails.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_LONG).show();
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });

        setHeader(this);

    }
}



