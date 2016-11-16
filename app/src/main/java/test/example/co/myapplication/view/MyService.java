package test.example.co.myapplication.view;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.telecom.Call;
import android.util.Log;

public class MyService extends Service {

    public String text = "myService";

    public MyBinder myBinder = new MyBinder();
    private boolean flag = false;
    int i = 0;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public class MyBinder extends Binder {
        public void setData(String data) {
            MyService.this.text = data;
        }

        public MyService getService() {
            return MyService.this;
        }

    }


    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("onCreate", "onCreate--->");
        flag = true;
        new Thread() {
            @Override
            public void run() {
                while (flag) {
                    super.run();
                    i++;
                    String str = i + ":" + text;
                    Log.i("text", str);
                    if (callback != null) {
                        callback.setDataChanged(str);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        text = intent.getStringExtra("data");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag = false;
        Log.i("onDestroy", "onDestroy--->");
    }


    public CallBack callback;

    public CallBack getCallback() {
        return callback;
    }

    public void setCallback(CallBack callback) {
        this.callback = callback;
    }

    public interface CallBack {
        void setDataChanged(String data);
    }


}
