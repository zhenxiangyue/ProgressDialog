package test.example.co.myapplication.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ProgressBar;

import test.example.co.myapplication.R;

/**
 * Created by zhenxiangyue on 2016/11/16.
 */
public class CommonDialog {


    private final Context context;
    private ProgressDialog progressDialog;

    public static  CommonDialog instance;

    private CommonDialog(Context context){
        this.context = context;
    }
    
    public static CommonDialog getInstance(Context context) {
        if (instance == null) {
            instance = new CommonDialog(context);
        }
        return instance;
    }


    /**
     * 开启弹框
     *
     * @param message
     */
    public void showProgress(String message) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(true);
        progressDialog.setIcon(R.drawable.common_ic_googleplayservices);
        progressDialog.setTitle("提示");
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    //关闭弹框
    public void closeDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


}
