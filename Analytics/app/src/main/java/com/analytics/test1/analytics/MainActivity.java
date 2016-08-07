package com.analytics.test1.analytics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends AppCompatActivity {

    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tracker t = ((AnalyticsApplication) getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);

        t.setScreenName("AAhhhhh");
        t.send(new HitBuilders.ScreenViewBuilder().build());

        t.send(new HitBuilders.EventBuilder()
                .setCategory("Blah")
                .setAction("BlahBlah")
                .setLabel("BlahBlahBlah")
                .build());
    }
}



