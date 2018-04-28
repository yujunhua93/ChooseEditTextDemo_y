package com.junhuayu.chooseedittextdemo_y;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by junhua.yu on 2018/4/27.
 */

public class TabEditText extends FrameLayout implements TextWatcher ,TabButton.OnTabCLickListener,View.OnClickListener{

    private EditText editText;

    private HorizontalScrollView horizontalScrollView;

    private LinearLayout tab_ll;

    private ImageView clearIv;

    private ImageView cameraIv;

    private View tabEditLayout;

    private List<TabButton> tabButtons;

    private Context context;

    public OnTextChangeListener onTextChangeListener;

    private OnClearClickListener onClearClickListener;

    private OnCameraClickLitener onCameraClickLitener;

    public TabEditText(@NonNull Context context) {
        this(context,null);
    }

    public TabEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TabEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        tabButtons = new ArrayList<>();
        tabEditLayout = LayoutInflater.from(context).inflate(R.layout.tab_edit_layout,null);
        editText = tabEditLayout.findViewById(R.id.tab_et);
        editText.addTextChangedListener(this);
        editText.requestFocus();
        horizontalScrollView = tabEditLayout.findViewById(R.id.tab_layout);
        clearIv = tabEditLayout.findViewById(R.id.clear_iv);
        cameraIv = tabEditLayout.findViewById(R.id.camera_iv);
        clearIv.setOnClickListener(this);
        tab_ll = tabEditLayout.findViewById(R.id.tab_ll);
        horizontalScrollView.setVisibility(GONE);
        setBackground(getResources().getDrawable(R.drawable.bg_default));
        addView(tabEditLayout);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    public void addTab(String content){
        cameraIv.setVisibility(VISIBLE);
        editText.setVisibility(GONE);
        horizontalScrollView.setVisibility(VISIBLE);
        clearIv.setVisibility(GONE);
        TabButton tabButton = new TabButton(context);
        tabButton.setOnTabCLickListener(this);
        tabButton.setText(content);
        tabButtons.add(tabButton);
        tab_ll.addView(tabButton);
        horizontalScrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        },100);
//        Timer timer=new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
//            }
//        },100L);

    }


    private void removeAllTab(){
        tabButtons.clear();
        tab_ll.removeAllViews();
    }

    public void removeTab(){

    }


    @Override
    public void afterTextChanged(Editable s) {
        if (onTextChangeListener != null){
            onTextChangeListener.onTextChange(s);
        }
        cameraIv.setVisibility(GONE);
        clearIv.setVisibility(VISIBLE);
    }

    @Override
    public void onContentListener() {
        cameraIv.setVisibility(GONE);
        clearIv.setVisibility(VISIBLE);
        editText.setVisibility(VISIBLE);
        horizontalScrollView.setVisibility(GONE);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tabButtons.size(); i++) {
            stringBuilder.append(tabButtons.get(i).getText());
        }
        String content = stringBuilder.toString();
        editText.setText(content.toString());
        editText.setSelection(content.length());
        removeAllTab();
    }

    @Override
    public void onDeleteListener(Object tag) {
        for (int i = 0; i < tabButtons.size(); i++) {
            if (tabButtons.get(i).getTag() == tag){
                tab_ll.removeView(tabButtons.get(i));
                tabButtons.remove(tabButtons.get(i));
            }
        }
    }


    public void setHint(String hint){
        editText.setHint(hint);
    }

    public void setOnTextChangeListener(OnTextChangeListener onTextChangeListener){
        this.onTextChangeListener = onTextChangeListener;
    }

    public void setOnClearClickListener(OnClearClickListener onClearClickListener){
        this.onClearClickListener = onClearClickListener;
    }

    public void setOnCameraClickLitener(OnCameraClickLitener onCameraClickLitener){
        this.onCameraClickLitener = onCameraClickLitener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clear_iv:
                editText.setText("");
                if (onClearClickListener != null){
                    onClearClickListener.onClearClick();
                }
                break;
            case R.id.camera_iv:
                if (onCameraClickLitener != null){
                    onCameraClickLitener.onCameraClick();
                }
                break;

        }
    }

    public interface OnTextChangeListener{
        void onTextChange(Editable s);
    }

    public interface OnClearClickListener{
        void onClearClick();
    }

    public interface OnCameraClickLitener{
        void onCameraClick();
    }


}
