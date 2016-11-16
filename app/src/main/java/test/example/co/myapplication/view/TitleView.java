package test.example.co.myapplication.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import test.example.co.myapplication.R;

/**
 * Created by zhenxiangyue on 2016/9/7.
 */
public class TitleView extends RelativeLayout {


    private TextView titleTv;

    private ImageView leftImg;
    private TextView leftTv;

    private ImageView rightImg;
    private TextView rightTv;
    private int drawablePadding = 3;


    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);

    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleAttr);

        initLeftTextView(context, typedArray);
        initTitleView(context, typedArray);


        typedArray.recycle();
    }

    private void initTitleView(Context context, TypedArray typedArray) {
        int title_tv = typedArray.getResourceId(R.styleable.TitleAttr_title_name, 0);
        CharSequence charSequence = title_tv > 0 ? typedArray.getResources().getText(title_tv) : typedArray.getString(title_tv);


        LayoutParams layoutParams = initLayoutParams();

        titleTv = createTextView(context, R.id.tv_title_name, charSequence, layoutParams);
        addView(titleTv);

    }

    private void initLeftTextView(Context context, TypedArray typedArray) {
        int leftText = typedArray.getResourceId(R.styleable.TitleAttr_left_text, 0);
        CharSequence charSequence = leftText > 0 ? typedArray.getResources().getText(leftText) : typedArray.getString(R.styleable.TitleAttr_left_text);

        LayoutParams layoutParams = initLayoutParams();

        leftTv = createTextView(context, R.id.tv_left_text, charSequence, layoutParams);
        setTextViewDrawable(typedArray, R.styleable.TitleAttr_left_text_drawable_left, R.styleable.TitleAttr_left_text_drawable_right, leftTv);


        addView(leftTv);

    }

    private void setTextViewDrawable(TypedArray typedArray, int titleAttr_left_text_drawable_left, int titleAttr_left_text_drawable_right, TextView leftTv) {
        int leftDrawable = typedArray.getResourceId(titleAttr_left_text_drawable_left, 0);
        int rightDrawable = typedArray.getResourceId(titleAttr_left_text_drawable_right, 0);
        leftTv.setCompoundDrawablePadding((int) getPixelSizeByDp(drawablePadding));
        leftTv.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, 0, rightDrawable, 0);
    }

    private float getPixelSizeByDp(int dp) {
        Resources rs = this.getResources();
        float scale = rs.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }


    private TextView createTextView(Context context, int tv_left_text, CharSequence charSequence, LayoutParams layoutParams) {
        TextView tv = new TextView(context);
        tv.setLayoutParams(layoutParams);
        tv.setText(charSequence);
        tv.setId(tv_left_text);
        tv.setGravity(Gravity.CENTER);


        return tv;
    }

    private LayoutParams initLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
    }


}
