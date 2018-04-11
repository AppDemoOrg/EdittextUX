package com.abt.edittextux;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by huangweiqi on 12/04/2018.
 */


public class ResponseActivity extends Activity {

    private EditText editText;
    private TextView textView;
    public static final String TAG="ResponseActivity";
    int count=0;
    int num=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        editText=(EditText)findViewById(R.id.response_et);
        textView=(TextView)findViewById(R.id.content_tv);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d(TAG, "onTouch: "+count);
                count++;
                return false;
                /*return true不会响应键盘打开事件，但是有响应触摸事件
                也就是说在响应触摸按下和松开后，事件就被消费而不往下传递了
                return false时，在触摸被松开的时候响应打开键盘事件，
                如果触摸长时间被按下，触摸松开时也不会对其他时间进行相应
                例如，不会打开系统默认键盘。
                */

            }
        });
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.d(TAG, "onKey: "+num);
                num++;
                int metaState = keyEvent.getMetaState();
                int unicodeChar = keyEvent.getUnicodeChar();
                String msg = "";
                msg +="按键动作:" + String.valueOf(keyEvent.getAction())+"\n";
                msg +="按键代码:" + String.valueOf(i)+"\n";
                msg +="按键字符:" + (char)unicodeChar+"\n";
                msg +="UNICODE:" + String.valueOf(unicodeChar)+"\n";
                msg +="重复次数:"+ String.valueOf(keyEvent.getRepeatCount())+"\n";
                msg +="功能键状态:" + String.valueOf(metaState)+"\n";
                msg +="硬件编码:" + String.valueOf(keyEvent.getScanCode())+"\n";
                msg +="按键标志:" + String.valueOf(keyEvent.getFlags())+"\n";
                textView.setText(msg);
                return false;
            }
        });
    }
}