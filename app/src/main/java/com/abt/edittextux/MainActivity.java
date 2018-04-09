package com.abt.edittextux;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.live_title_detail);
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
}
