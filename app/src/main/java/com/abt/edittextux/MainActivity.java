package com.abt.edittextux;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.orhanobut.logger.Logger;

/**
 * Created by huangweiqi on 21/04/2018.
 */
public class MainActivity extends AppCompatActivity implements View.OnKeyListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.d(TAG, "onCreate"); // print only the first param.

        final EditText multiLineEditA = findViewById(R.id.et_advice_content);
        multiLineEditA.setHorizontallyScrolling(false);
        multiLineEditA.setMaxLines(Integer.MAX_VALUE);

        final EditText multiLineEdit = findViewById(R.id.edit_multi_line);
        multiLineEdit.setHorizontallyScrolling(false);
        multiLineEdit.setMaxLines(Integer.MAX_VALUE);
        multiLineEdit.setMovementMethod(ScrollingMovementMethod.getInstance());

        final EditText singleLineEdit = findViewById(R.id.edit_single_line);
        singleLineEdit.setText(getResources().getString(R.string.single_line_edit_text));
        Logger.d("setText = "+getResources().getString(R.string.single_line_edit_text));

        final EditText multiLineEditNormal = findViewById(R.id.edit_multi_line_normal);
        multiLineEditNormal.setHorizontallyScrolling(false);
        multiLineEditNormal.setMaxLines(Integer.MAX_VALUE);
        Logger.i("setMaxLines = "+Integer.MAX_VALUE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d("debug");
        Logger.e("error");
        Logger.w("warning");
        Logger.v("verbose");
        Logger.i("information");
        Logger.wtf("What a Terrible Failure");
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        Logger.d("keyEvent = "+keyEvent.getAction());
        return false;
    }
}
