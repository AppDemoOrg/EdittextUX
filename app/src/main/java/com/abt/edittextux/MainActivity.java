package com.abt.edittextux;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SecurityEditText editText = findViewById(R.id.live_title_detail);

        editText.setHorizontallyScrolling(false);
        editText.setMaxLines(Integer.MAX_VALUE);

        editText.setDelKeyEventListener(new SecurityEditText.OnDelKeyEventListener() {
            @Override
            public void onDeleteClick() {
                LogHelper.d(TAG, "on delete click...");
            }
        });

        final String[] beforeStr = new String[2];
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                LogHelper.d(TAG, "onkeyCode : "+keyEvent.getKeyCode());
                return false;
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //LogHelper.d(TAG, "onTextChanged sss--> "+s.toString());
                /*LogHelper.d(TAG, "onTextChanged text--> "+editText.getText());
                LogHelper.d(TAG, "onTextChanged sss--> "+s.toString());
                LogHelper.d(TAG, "onTextChanged start--> "+start);
                LogHelper.d(TAG, "onTextChanged before--> "+before);
                LogHelper.d(TAG, "onTextChanged count--> "+count);*/
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //LogHelper.d(TAG, "beforeTextChanged sss--> "+s.toString());
//                LogHelper.d(TAG, "beforeTextChanged start--> "+start);
//                LogHelper.d(TAG, "beforeTextChanged count--> "+count);
//                LogHelper.d(TAG, "beforeTextChanged after--> "+after);
                beforeStr[0] = s.toString().trim();
            }
            @Override
            public void afterTextChanged(Editable s) {
                //LogHelper.d(TAG, "afterTextChanged sss--> "+s.toString());
                beforeStr[1] = s.toString().trim();
//
//                if (beforeStr[0].equalsIgnoreCase(beforeStr[1])) {
//                    LogHelper.d(TAG, "hide key board....");
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//                }
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                LogHelper.d(TAG, "onEditorAction");
                switch (actionId) {
                    //点击GO键
                    case EditorInfo.IME_ACTION_GO:
                        LogHelper.d(TAG, "IME_ACTION_GO");
                        return true;
                    //点击Done
                    case EditorInfo.IME_ACTION_DONE:
                        LogHelper.d(TAG, "IME_ACTION_DONE");
                        return true;
                    //点击Next
                    case EditorInfo.IME_ACTION_NEXT:
                        LogHelper.d(TAG, "IME_ACTION_NEXT");
                        return true;
                    //点击Previous
                    case EditorInfo.IME_ACTION_PREVIOUS:
                        LogHelper.d(TAG, "IME_ACTION_PREVIOUS");
                        return true;
                    //点击None
                    case EditorInfo.IME_ACTION_NONE:
                        LogHelper.d(TAG, "IME_ACTION_NONE");
                        return true;
                    //点击Send
                    case EditorInfo.IME_ACTION_SEND:
                        LogHelper.d(TAG, "IME_ACTION_SEND");
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        LogHelper.d(TAG, "dispatchKeyEvent event = "+event.getAction());
        if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
            /*隐藏软键盘*/
            LogHelper.d(TAG, "getKeyCode  = "+event.getKeyCode());
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if(inputMethodManager.isActive()){
                inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
            }
            LogHelper.d(TAG, "success ");
            //edittext.setText("success");
            //webview.loadUrl(URL);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
