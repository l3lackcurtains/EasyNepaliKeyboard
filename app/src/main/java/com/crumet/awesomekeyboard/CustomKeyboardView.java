package com.crumet.awesomekeyboard;

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
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(18);
        paint.setColor(Color.LTGRAY);
        int x = 30;
        int y = 30;
        List<Key> keys = getKeyboard().getKeys();
        for (Key key : keys) {

            if (key.label != null) {
                if (key.label.equals("q")) {
                    canvas.drawText("1", key.x + (key.width - x), key.y + y, paint);
                } else if (key.label.equals("w")) {
                    canvas.drawText("2", key.x + (key.width - x), key.y + y, paint);
                } else if (key.label.equals("e")) {
                    canvas.drawText("3", key.x + (key.width - x), key.y + y, paint);
                } else if (key.label.equals("r")) {
                    canvas.drawText("4", key.x + (key.width - x), key.y + y, paint);
                } else if (key.label.equals("t")) {
                    canvas.drawText("5", key.x + (key.width - x), key.y + y, paint);
                } else if (key.label.equals("y")) {
                    canvas.drawText("5", key.x + (key.width - x), key.y + y, paint);
                } else if (key.label.equals("u")) {
                    canvas.drawText("6", key.x + (key.width - x), key.y + y, paint);
                } else if (key.label.equals("i")) {
                    canvas.drawText("7", key.x + (key.width - x), key.y + y, paint);
                } else if (key.label.equals("o")) {
                    canvas.drawText("8", key.x + (key.width - x), key.y + y, paint);
                } else if (key.label.equals("p")) {
                    canvas.drawText("9", key.x + (key.width - x), key.y + y, paint);
                }
            }

        }
    }
}
