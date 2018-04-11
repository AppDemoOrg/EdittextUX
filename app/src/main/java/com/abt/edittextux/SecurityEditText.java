package com.abt.edittextux;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
import android.widget.EditText;

/**
 * Created by huangweiqi on 12/04/2018.
 */
@SuppressLint("AppCompatCustomView")
public class SecurityEditText extends EditText {

    private static final String TAG = SecurityEditText.class.getSimpleName();

    private OnDelKeyEventListener delKeyEventListener;

    public SecurityEditText(Context context) {
        super(context);
    }

    public SecurityEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SecurityEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        LogHelper.d(TAG, "onCreateInputConnection");
        return new ZanyInputConnection(super.onCreateInputConnection(outAttrs),
                true);
    }

    @Override
    public void onEditorAction(int actionCode) {
        LogHelper.d(TAG, "onEditorAction actionCode = "+ actionCode);
        super.onEditorAction(actionCode);
    }

    private class ZanyInputConnection extends InputConnectionWrapper {

        public ZanyInputConnection(InputConnection target, boolean mutable) {

            super(target, mutable);
            LogHelper.d(TAG, "ZanyInputConnection");
        }

        @Override
        public boolean sendKeyEvent(KeyEvent event) {
            LogHelper.d(TAG, "sendKeyEvent");
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                if (delKeyEventListener != null) {
                    delKeyEventListener.onDeleteClick();
                    return true;
                }
            }
            return super.sendKeyEvent(event);
        }

        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            LogHelper.d(TAG, "deleteSurroundingText");
            if (beforeLength == 1 && afterLength == 0) {
                return sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_ENTER))
                        && sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_ENTER));
            }

            return super.deleteSurroundingText(beforeLength, afterLength);
        }

        @Override
        public boolean commitCompletion(CompletionInfo text) {
            LogHelper.d(TAG, "commitCompletion text = "+text.toString());

            return super.commitCompletion(text);
        }

        @Override
        public boolean finishComposingText() {
            LogHelper.d(TAG, "finishComposingText text = ");
            return super.finishComposingText();
        }

        @Override
        public boolean commitContent(InputContentInfo inputContentInfo, int flags, Bundle opts) {
            LogHelper.d(TAG, "commitContent text = ");
            return super.commitContent(inputContentInfo, flags, opts);
        }

        @Override
        public boolean commitText(CharSequence text, int newCursorPosition) {
            LogHelper.d(TAG, "commitText text = "+text);
            LogHelper.d(TAG, "commitText newCursorPosition = "+newCursorPosition);
            return super.commitText(text, newCursorPosition);
        }

        @Override
        public void closeConnection() {
            super.closeConnection();
            LogHelper.d(TAG, "close connection");
        }
    }

    /**
     *
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param delKeyEventListener EditText删除回调
     */
    public void setDelKeyEventListener(OnDelKeyEventListener delKeyEventListener) {
        this.delKeyEventListener = delKeyEventListener;
    }

    public interface OnDelKeyEventListener {
        void onDeleteClick();
    }

}
