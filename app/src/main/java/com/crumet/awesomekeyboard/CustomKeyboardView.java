package com.crumet.awesomekeyboard;

import android.graphics.drawable.Drawable;
import android.inputmethodservice.KeyboardView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.inputmethod.InputMethodSubtype;

import java.util.List;

public class CustomKeyboardView extends KeyboardView {

    static final int KEYCODE_OPTIONS = -100;
    static final int KEYCODE_LANGUAGE_SWITCH = -102;
    static final int KEYCODE_EMOJI_SWITCH = -101;

    public CustomKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean onLongPress(Key key) {
        if (key.codes[0] == Keyboard.KEYCODE_CANCEL) {
            getOnKeyboardActionListener().onKey(KEYCODE_OPTIONS, null);
            return true;
        /*} else if (key.codes[0] == 113) {

            return true; */
        } else {
            //Log.d("LatinKeyboardView", "KEY: " + key.codes[0]);
            return super.onLongPress(key);
        }
    }

    void setSubtypeOnSpaceKey(final InputMethodSubtype subtype) {
        final Customkeyboard keyboard = (Customkeyboard) getKeyboard();
        keyboard.setSpaceIcon(getResources().getDrawable(subtype.getIconResId()));
        invalidateAllKeys();
    }

    @Override
    public boolean setShifted(boolean shifted) {
        return super.setShifted(shifted);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<Key> keys = getKeyboard().getKeys();
        for (Key key : keys) {
            Log.e("KEY", "Drawing key with code " + key.codes[0]);
            if (key.codes[0] == -101) {

            }
        }
    }
}
