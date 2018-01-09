package app.cloudnews.com.cloudnews.cloudNews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.cloudnews.com.cloudnews.R;

import static app.cloudnews.com.cloudnews.util.ProjectPreferences.setHeader;

public class ActionBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionbar);


       setHeader(this);
    }
}

