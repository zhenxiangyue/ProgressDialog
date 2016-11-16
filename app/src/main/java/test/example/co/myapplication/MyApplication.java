package test.example.co.myapplication;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by zhenxiangyue on 2016/10/31.
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //CrashReport.initCrashReport(getApplicationContext(), "900057597", true);
    }
}
