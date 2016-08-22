package com.neo.http.utillibrary.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class LableView extends View{
    private Paint paint, paintText;
    private String text;
    private int r=50;
    public LableView(Context context) {
        this(context,null);
    }



    public LableView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public LableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inti();
    }
    /**
     * 设置需要显示的文本内容
     * @param text
     */
    public void setText(String text) {
        this.text = text;
        //将控件显示
        this.setVisibility(VISIBLE);

        invalidate();
    }


    private void inti() {
        //默认是隐藏状态
        this.setVisibility(GONE);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#32f9e1"));

        paintText = new Paint();//新的画笔
        paintText.setAntiAlias(true);
        paintText.setTextSize(50);
//        paintText.setTextSize(getResources().getDimension(R.dimen.slide_label));
        paintText.setColor(Color.parseColor("#ffffff"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth()/2,getHeight()/2,r,paint);
        if(text!=null){
            canvas.drawText(text,
                    getWidth()/2 - paintText.measureText(text)/2,
                    getHeight()/2 + (paintText.descent() - paintText.ascent())/2 - paintText.descent(),
                    paintText);
        }

    }
}
