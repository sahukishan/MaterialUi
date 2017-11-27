package com.promodigit.kishansahu.materialui.view;

/**
 * Created by kishansahu on 4/7/17.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.promodigit.kishansahu.materialui.R;


public class CustomEditText extends EditText {
    Typeface tf = null;
    private static final String TAG = "Edittext";

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.CustomEdittext);
        String customFont = a.getString(R.styleable.CustomEdittext_customEdittextFont);
        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(), asset);
        } catch (Exception e) {
            Log.e(TAG, "Could not get typeface: "+e.getMessage());
            return false;
        }

        setTypeface(tf);
        return true;
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        tf = this.tf;
        super.setTypeface(tf, style);
    }

}

