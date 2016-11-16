package test.example.co.myapplication.view;

import android.content.Context;

/**
 * Created by zhenxiangyue on 2016/11/11.
 */
public class Actor {
    String name;

    String picName;

    public Actor(String name, String picName)
    {
        this.name = name;
        this.picName = picName;
    }

    public int getImageResourceId( Context context )
    {
        try
        {
            return context.getResources().getIdentifier(this.picName, "mipmap", context.getPackageName());

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

}
