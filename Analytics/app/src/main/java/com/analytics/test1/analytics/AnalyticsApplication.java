package com.analytics.test1.analytics;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

/**
 * Created by FG000030 on 2016/7/12.
 */
public class AnalyticsApplication extends Application {
//    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();
//
//    public synchronized Tracker getTracker(TrackerName trackerId) {
//
//        if (!mTrackers.containsKey(trackerId)) {
//            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
//            Tracker t = analytics.newTracker(R.xml.app_tracker);
//            mTrackers.put(trackerId, t);
//        }
//        return mTrackers.get(trackerId);
//    }
//
//    public enum TrackerName {
//        APP_TRACKER,
//        GLOBAL_TRACKER,
//        E_COMMERCE_TRACKER,
//    }
HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    public synchronized Tracker getTracker(TrackerName trackerId) {

        String PROPERTY_ID = "";

        if (!mTrackers.containsKey(trackerId)) {

            ApplicationInfo appInfo;
            try {
                appInfo = this.getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
                PROPERTY_ID = appInfo.metaData.getString("GoogleAnalyticsId");
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            analytics.getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
            Tracker t = analytics.newTracker(PROPERTY_ID);

            t.enableAutoActivityTracking(true);
            t.enableExceptionReporting(true);
            t.setSessionTimeout(300);
            t.enableAdvertisingIdCollection(true);

            mTrackers.put(trackerId, t);
        }
        return mTrackers.get(trackerId);
    }

    public enum TrackerName {
        APP_TRACKER
    }
}