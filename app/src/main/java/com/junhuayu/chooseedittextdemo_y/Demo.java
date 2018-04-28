package com.junhuayu.chooseedittextdemo_y;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by junhua.yu on 2018/4/28.
 */

public class Demo extends LinearLayout implements View.OnClickListener{

    private ImageView imageView;

    private int mheight;

    private LinearLayout linearLayout;

    ValueAnimator valueAnimator;
    private AnimationDrawable animationDrawable;

    public Demo(Context context) {
        this(context,null);
    }

    public Demo(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Demo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        setClipChildren(false);
        setClipToPadding(false);

        View view = LayoutInflater.from(context).inflate(R.layout.layout,null);
        imageView =  view.findViewById(R.id.iv);
        linearLayout = view.findViewById(R.id.ll);
        linearLayout.setOnClickListener(this);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        valueAnimator = ValueAnimator.ofFloat(0,50);
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 2. 获取动画对象
                animationDrawable.start();
                Toast.makeText(context, "动画结束开始笑脸动画", Toast.LENGTH_SHORT).show();
                linearLayout.setClickable(false);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                int finalvalue = DensityUtil.px2dip(context,value);
                LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams(); // 取控件mGrid当前的布局参数
                linearParams.height = mheight+finalvalue;// 当控件的高强制设成75象素
                linearLayout.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件mGrid2
//                imageView.setTranslationY(-finalvalue);
            }
        });
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int value = (int) animation.getAnimatedValue();
//                int finalvalue = DensityUtil.px2dip(context,value);
//                LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) imageView.getLayoutParams(); // 取控件mGrid当前的布局参数
//                linearParams.height = mheight+finalvalue;// 当控件的高强制设成75象素
//                imageView.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件mGrid2
////                imageView.setTranslationY(-finalvalue);
//            }
//
//
//        });
        valueAnimator.setDuration(500);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                                                                 @Override
                                                                 public void onGlobalLayout() {
                                                                     mheight = linearLayout.getHeight();
                                                                 }
                                                             });
        addView(view);






    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case  R.id.ll:
               valueAnimator.start();
            break;
        }
    }
}
