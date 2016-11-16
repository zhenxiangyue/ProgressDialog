package test.example.co.myapplication.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import test.example.co.myapplication.MainActivity;
import test.example.co.myapplication.R;

public class ShowDialogActivity extends AppCompatActivity {

    private Context context = ShowDialogActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dialog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                //正在加载数据，弹出提示框ProgressBar
                CommonDialog.getInstance(context).showProgress("正在加载数据。。。");
                //开启异步处理
                new MyThread().start();
            }
        });

    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //关闭弹出提示框
                    CommonDialog.getInstance(context).closeDialog();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    class MyThread extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0);
        }
    }


}
