package test.example.co.myapplication.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.example.co.myapplication.R;

/**
 * Created by zhenxiangyue on 2016/11/11.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Actor> actors;

    private Context mContext;

    public MyAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<Actor> actors){
        this.actors = actors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 给ViewHolder设置布局文件
        View v = LayoutInflater.from(mContext).inflate(R.layout.card_view_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        // 给ViewHolder设置元素
        Actor p = actors.get(i);
        viewHolder.mTextView.setText(p.name);
        viewHolder.mImageView.setImageResource(p.getImageResourceId(mContext));

        final int position = i;
        if(this.itemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        // 返回数据总数
        return actors == null ? 0 : actors.size();
    }

    // 重写的自定义ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.title_tv);
            mImageView = (ImageView) v.findViewById(R.id.image);
        }
    }

    //点击事件，接口回调

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


}

