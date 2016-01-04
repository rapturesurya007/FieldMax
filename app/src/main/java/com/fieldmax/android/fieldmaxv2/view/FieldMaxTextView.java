
package com.fieldmax.android.fieldmaxv2.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

import com.fieldmax.android.fieldmaxv2.R;
import com.fieldmax.android.fieldmaxv2.util.TypefaceCache;


public class FieldMaxTextView extends TextView {
    private int typefaceCode;
    public FieldMaxTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            // Get Custom Attribute Name and value
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs,
                    R.styleable.FieldMaxFontView);
            typefaceCode = styledAttrs.getInt(R.styleable.FieldMaxFontView_fontType, -1);
            Typeface typeface = TypefaceCache.get(context.getAssets(), typefaceCode);
            setTypeface(typeface);

            styledAttrs.recycle();
            if (isInEditMode()) {
                return;
            }
        }
    }

    public FieldMaxTextView(Context context) {
        super(context);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (text != null) {
            text = Html.fromHtml(text.toString());
        }
        super.setText(text, type);
    }

}
