package test.example.co.myapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import test.example.co.myapplication.R;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private List<Actor> actors = new ArrayList<Actor>();

    private String[] names = {"朱茵", "张柏芝", "张敏"};

    private String[] pics = {"pic1", "pic2", "pic3"};
    private ImageView icon_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        icon_img = (ImageView) findViewById(R.id.icon_img);
        // 拿到RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // 设置LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        // 设置ItemAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        // mRecyclerView.setHasFixedSize(true);
        // 初始化自定义的适配器
        getData();
        myAdapter = new MyAdapter(this);
        myAdapter.setData(actors);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int index = position % 3;
                icon_img.setImageResource(getResources().getIdentifier(pics[index], "mipmap", getPackageName()));
                icon_img.invalidate();
            }
        });
        // 为mRecyclerView设置适配器
        mRecyclerView.setAdapter(myAdapter);

        findViewById(R.id.floatingActionBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("tag", "floatingActionBtn...");
                addData();
            }
        });

    }

    private void addData(){
        int index = actors.size() % 3;
        actors.add(new Actor(names[index], pics[index]));
        myAdapter.setData(actors);
        myAdapter.notifyDataSetChanged();
    }


    private void getData() {
        for (int i = 0; i < names.length; i++) {
            actors.add(new Actor(names[i], pics[i]));
        }
    }


}

