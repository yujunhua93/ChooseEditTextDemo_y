package com.junhuayu.chooseedittextdemo_y;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by junhua.yu on 2018/4/27.
 */

public class TabButton extends LinearLayout {

    private String tabContent;

    private TextView contentTv;

    private TextView deleteTv;

    private int padding = 5;

    private int sidePadding = 10;

    private OnTabCLickListener onTabCLickListener;

    private String content;

    private static long tagId;


    public TabButton(Context context) {
        this(context,null);
    }

    public TabButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TabButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setTag(System.currentTimeMillis());
        setOrientation(HORIZONTAL);
        setBackground(getResources().getDrawable(R.drawable.bg_default));
        addContentTextview(context);
        addDeleteTextView(context);
    }

    private void addContentTextview(Context context){
        contentTv = new TextView(context);
        int tabPadding = DensityUtil.dip2px(context,padding);
        int tabSidePadding = DensityUtil.dip2px(context,sidePadding);
        contentTv.setPadding(tabSidePadding,tabPadding,0,tabPadding);
        contentTv.setTextColor(getResources().getColor(R.color.c_text2));
        contentTv.setTextSize(getResources().getDimension(R.dimen.tabSize));
        contentTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTabCLickListener != null){
                    onTabCLickListener.onContentListener();
                }
            }
        });
        addView(contentTv);
    }

    private void addDeleteTextView(Context context){
        deleteTv = new TextView(context);
        deleteTv.setText("×");
        int tabPadding = DensityUtil.dip2px(context,padding);
        int tabSidePadding = DensityUtil.dip2px(context,sidePadding);
        deleteTv.setPadding(tabPadding,tabPadding,tabSidePadding,tabPadding);
        deleteTv.setTextSize(getResources().getDimension(R.dimen.tabSize));
        deleteTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTabCLickListener != null){
                    onTabCLickListener.onDeleteListener(getTag());
                }
            }
        });
        addView(deleteTv);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    public void setText(String content){
        this.content = content;
        contentTv.setText(content);
    }

    public String getText(){
        return content;
    }

    public void setOnTabCLickListener(OnTabCLickListener onTabCLickListener){
        this.onTabCLickListener = onTabCLickListener;
    }

    public interface OnTabCLickListener{
        void onContentListener();
        void onDeleteListener(Object tag);
    }


}
