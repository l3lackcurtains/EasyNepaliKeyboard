package com.crumet.awesomekeyboard;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.util.Log;
import android.view.inputmethod.EditorInfo;

public class Customkeyboard extends Keyboard {

    Context mContext;

    private Key mEnterKey;
    private Key mSpaceKey;
    private Key mShiftKey;

    private Drawable mShiftLockIcon;
    private Drawable mShiftLockPreviewIcon;
    private Drawable mOldShiftIcon;

    public Customkeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
    }

    public Customkeyboard(Context context, int xmlLayoutResId, int modeId, int width, int height) {
        super(context, xmlLayoutResId, modeId, width, height);
        mContext = context;
        final Resources res = context.getResources();
        mShiftLockIcon = res.getDrawable(R.drawable.sym_keyboard_shift_locked);
        //mShiftLockPreviewIcon = res.getDrawable(R.drawable.sym_keyboard_feedback_shift_locked);

    }

    public Customkeyboard(Context context, int xmlLayoutResId, int modeId) {
        super(context, xmlLayoutResId, modeId);
    }

    public Customkeyboard(Context context, int layoutTemplateResId, CharSequence characters, int columns, int horizontalPadding) {
        super(context, layoutTemplateResId, characters, columns, horizontalPadding);
    }

    @Override
    protected Key createKeyFromXml(Resources res, Row parent, int x, int y,
                                   XmlResourceParser parser) {
        Key key = new CustomKey(res, parent, x, y, parser);

        switch (key.codes[0]){
            case 10:
                mEnterKey = key;
                break;
            case ' ':
                mSpaceKey = key;
                break;
            case -1:
                mShiftKey = key;

        }



        /*else if (key.codes[0] == Keyboard.KEYCODE_MODE_CHANGE) {
            mModeChangeKey = key;
            mSavedModeChangeKey = new LatinKey(res, parent, x, y, parser);
        } else if (key.codes[0] == LatinKeyboardView.KEYCODE_LANGUAGE_SWITCH) {
            mLanguageSwitchKey = key;
            mSavedLanguageSwitchKey = new LatinKey(res, parent, x, y, parser);
        }*/
        return key;
    }

    void setSpaceIcon(final Drawable icon) {
        if (mSpaceKey != null) {
            mSpaceKey.icon = icon;
        }
    }
    /**
     * This looks at the ime options given by the current editor, to set the
     * appropriate label on the keyboard's enter key (if it has one).
     */
    void setImeOptions(Resources res, int options) {
        if (mEnterKey == null) {
            return;
        }

        switch (options&(EditorInfo.IME_MASK_ACTION|EditorInfo.IME_FLAG_NO_ENTER_ACTION)) {
            case EditorInfo.IME_ACTION_GO:
                mEnterKey.iconPreview = null;
                mEnterKey.icon = null;
                mEnterKey.label = res.getText(R.string.label_go_key);
                break;
            case EditorInfo.IME_ACTION_NEXT:
                mEnterKey.iconPreview = null;
                mEnterKey.icon = null;
                mEnterKey.label = res.getText(R.string.label_next_key);
                break;
            case EditorInfo.IME_ACTION_SEARCH:
                Log.d("IME","search");
                mEnterKey.icon = res.getDrawable(R.drawable.sym_keyboard_search);
                mEnterKey.label = null;
                break;
            case EditorInfo.IME_ACTION_SEND:
                mEnterKey.iconPreview = null;
                mEnterKey.icon = null;
                mEnterKey.label = res.getText(R.string.label_send_key);
                break;

            default:
                mEnterKey.icon = res.getDrawable(R.drawable.sym_keyboard_return);
                mEnterKey.label = null;
                break;
        }
    }
    static class CustomKey extends Keyboard.Key {

        public CustomKey(Resources res, Keyboard.Row parent, int x, int y,
                        XmlResourceParser parser) {
            super(res, parent, x, y, parser);
        }

        /**
         * Overriding this method so that we can reduce the target area for the key that
         * closes the keyboard.
         */
        @Override
        public boolean isInside(int x, int y) {
            return super.isInside(x, codes[0] == KEYCODE_CANCEL ? y - 10 : y);
        }
    }
}
