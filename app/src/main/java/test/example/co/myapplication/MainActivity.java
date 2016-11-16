package test.example.co.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tencent.bugly.crashreport.CrashReport;

import test.example.co.myapplication.view.MyService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private static final String PATH = "d://zxy";
    private static final String PATHFILE = PATH + "/" + "test1.txt";

    private TextView text_data;
    private EditText editText;
    private Intent intent;

    private MyService.MyBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_data = (TextView)findViewById(R.id.text_data);
        editText = (EditText) findViewById(R.id.edit_text);

        findViewById(R.id.startServiceBtn).setOnClickListener(this);
        findViewById(R.id.stopServiceBtn).setOnClickListener(this);
        findViewById(R.id.binderButton).setOnClickListener(this);
        findViewById(R.id.unBinderButton).setOnClickListener(this);
        findViewById(R.id.syncyData).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startServiceBtn:
                intent = new Intent(this, MyService.class);
                intent.putExtra("data", editText.getText().toString());
                startService(intent);
                break;
            case R.id.stopServiceBtn:
                stopService(new Intent(this, MyService.class));
                break;
            case R.id.binderButton:
                intent = new Intent(this, MyService.class);
                intent.putExtra("data", editText.getText().toString());
                bindService(intent, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unBinderButton:
                unbindService(this);
                break;
            case R.id.syncyData:
                if (binder != null) {
                    binder.setData(editText.getText().toString());
                }
                break;
        }
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder = (MyService.MyBinder) service;
        binder.getService().setCallback(new MyService.CallBack() {
            @Override
            public void setDataChanged(String data) {
                Message msg = new Message();
                Bundle b = new Bundle();
                b.putString("data", data);
                msg.setData(b);
                handler.sendMessage(msg);
            }
        });
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            text_data.setText(msg.getData().get("data").toString());

        }
    };

}
