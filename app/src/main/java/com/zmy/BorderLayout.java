package com.zmy;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.FrameLayout;

/**
 * Created by zmy on 2016/3/3 0003.
 */
public class BorderLayout extends FrameLayout
{
    public static final String TAG = "BorderLayout";

    private int borderColor;
    private int borderBackgroundColor;
    private int borderWidth;
    private String cornerRadiusString;

    public BorderLayout(Context context)
    {
        this(context, null);
    }

    public BorderLayout(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public BorderLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BorderLayout, defStyleAttr, 0);
        for (int i = 0; i < array.getIndexCount(); i++)
        {
            switch (array.getIndex(i))
            {
                case R.styleable.BorderLayout_BorderColor:
                {
                    borderColor = array.getColor(i,Color.TRANSPARENT);
                    break;
                }
                case R.styleable.BorderLayout_BorderWidth:
                {
                    borderWidth = array.getDimensionPixelSize(i,borderWidth);
                    break;
                }
                case R.styleable.BorderLayout_BorderBackgroundColor:
                {
                    borderBackgroundColor = array.getColor(i,Color.TRANSPARENT);
                    break;
                }
                case R.styleable.BorderLayout_CornerRadius:
                {
                    cornerRadiusString = array.getString(i);
                    break;
                }
            }
        }
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(borderWidth, borderColor);
        drawable.setColor(borderBackgroundColor);
        float[] r = parseCornerRadius();
        if (r != null)
        {
            drawable.setCornerRadii(r);
        }
        setBackgroundDrawable(drawable);
    }

    private float[] parseCornerRadius()
    {
        float[] r = null;
        if (TextUtils.isEmpty(cornerRadiusString))
        {
            return r;
        }

        String[] strings = cornerRadiusString.split(",");
        if (strings.length != 1 && strings.length != 2 && strings.length != 4)
        {
            Log.e(TAG,"the corner radius string is invalid");
            return r;
        }

        float[] tmp = new float[strings.length];
        for (int i = 0; i < strings.length; i++)
        {
            try
            {
                tmp[i] = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,Float.parseFloat(strings[i]), getResources().getDisplayMetrics());
            }
            catch (NumberFormatException ex)
            {
                Log.e(TAG,"the corner radius string is invalid");
                return r;
            }
        }

        switch (strings.length)
        {
            case 1:
            {
                r = new float[]{tmp[0],tmp[0],tmp[0],tmp[0],tmp[0],tmp[0],tmp[0],tmp[0]};
                break;
            }
            case 2:
            {
                r = new float[]{tmp[0],tmp[0],tmp[1],tmp[1],tmp[0],tmp[0],tmp[1],tmp[1]};
                break;
            }
            case 4:
            {
                r = new float[]{tmp[0],tmp[0],tmp[1],tmp[1],tmp[2],tmp[2],tmp[3],tmp[3]};
                break;
            }
        }
        return r;
    }
}
