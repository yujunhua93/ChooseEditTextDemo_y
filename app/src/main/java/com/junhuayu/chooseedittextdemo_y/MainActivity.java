package com.junhuayu.chooseedittextdemo_y;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,TabButton.OnTabCLickListener,
        TabEditText.OnTextChangeListener,
        TabEditText.OnClearClickListener{

    ChooseEditText chooseEditText;
    TabEditText tabEditText;
    TextView tvResult;
    TextView content;

    SmileView smileView;

    SeekBar seekBar;
    LinearLayout backGround;
    ImageView smileFace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chooseEditText = (ChooseEditText) findViewById(R.id.chooseedittext);
        tabEditText = findViewById(R.id.tab_et_layout);
        tvResult = (TextView) findViewById(R.id.textView);
        content = findViewById(R.id.content);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.search).setOnClickListener(this);
        content.setOnClickListener(this);
        tabEditText.setOnTextChangeListener(this);
        tabEditText.setHint("啦啦啦啦");
        final TabButton button = findViewById(R.id.tabbutton);
        button.setText("12312412541");
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                button.setText("12312412541");
//            }
//        });
        chooseEditText.setOnChooseEditTextListener(new OnChooseEditTextListener() {
            @Override
            public void onTextChangeed(String text) {
                tvResult.setText(text);
            }
        });


        seekBar = (SeekBar) findViewById(R.id.seekBar);
        backGround = (LinearLayout) findViewById(R.id.backGround);
        smileFace = (ImageView) findViewById(R.id.smileFace);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) smileFace.getLayoutParams();
                layoutParams.bottomMargin = i*3;
                smileFace.setLayoutParams(layoutParams);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        smileView = (SmileView) findViewById(R.id.smileView);
        smileView.setNum(60,40);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_2:
                tabEditText.addTab("NIKE");
                chooseEditText.addItem("NIKE");
                break;
            case R.id.btn_3:
                tabEditText.addTab("ADIDAS");
                chooseEditText.addItem("ADIDAS");
                break;
            case R.id.btn_4:
                tabEditText.addTab("MIZUNO");
                chooseEditText.addItem("MIZUNO");
                break;
            case R.id.btn_5:
                tabEditText.addTab("安踏");
                chooseEditText.addItem("安踏");
                break;
            case R.id.search:
                //获取输入值
                String text = chooseEditText.getText();
                if (text == null)
                    Toast.makeText(MainActivity.this, "输入为空", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                break;
            case R.id.content:
                tabEditText.addTab(content.getText().toString());
                content.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onContentListener() {

    }

    @Override
    public void onDeleteListener(Object tag) {

    }

    @Override
    public void onTextChange(Editable s) {
        content.setText(s.toString());
        content.setVisibility(View.VISIBLE);
        Toast.makeText(this,s.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClearClick() {

    }
}
