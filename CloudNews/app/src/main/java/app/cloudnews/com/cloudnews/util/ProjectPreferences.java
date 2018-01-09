package app.cloudnews.com.cloudnews.util;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.cloudnews.com.cloudnews.R;

/**
 * Created by Lab08-15 on 7.01.2018.
 */

public class ProjectPreferences {

    public static void setHeader(AppCompatActivity activity) {
        activity.getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        activity.getSupportActionBar().setDisplayShowCustomEnabled(true);
        activity.getSupportActionBar().setCustomView(R.layout.activity_action_bar);
        View view = activity.getSupportActionBar().getCustomView();

        Toolbar parent = (Toolbar) view.getParent();
        parent.setPadding(0, 0, 0, 0);
        parent.setContentInsetsAbsolute(0, 0);
    }
}
