package com.neo.http.utillibrary.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class SlideView extends View {

    OnSelectListener onSelectListener;


//    private String[] str;
    private String[] str={"热门","A","B","C","D","E","F","G","H","J","K",
            "L","M","N","P","Q","R","S","T","W","X","Y","Z"};
    private  Paint paint;
    private int mHeight;
    private int mindex=-1;//当前选择的下标，默认-1为没有选择

    //setOnSelectListener方法
    public void setOnSelectListener(OnSelectListener onSelectListener){
        this.onSelectListener=onSelectListener;
    }

    public SlideView(Context context) {
        this(context,null);
    }

    public SlideView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inti();
    }

    private void inti() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#88888888"));
        paint.setTextSize(30);
        mHeight= (int) (paint.descent()-paint.ascent());
//        str = onSelectListener.SetListDatas();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i=0;i<str.length;i++){
//            Log.d("TAG","a"+mindex);
            if(mindex!=-1&&mindex==i){
//                Log.d("TAG","b");
                paint.setColor(0xff0000ff);
                canvas.drawText(str[i], getWidth() / 2 - paint.measureText(str[i]) / 2, getPaddingTop() + mHeight * (i + 1), paint);
                paint.setColor(Color.parseColor("#88888888"));
            }else {
                canvas.drawText(str[i], getWidth() / 2 - paint.measureText(str[i]) / 2, getPaddingTop() + mHeight * (i + 1), paint);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureView(widthMeasureSpec,0),measureView(heightMeasureSpec,1));
    }

    private int measureView(int spec,int type){
        int mode=MeasureSpec.getMode(spec);
        int size=MeasureSpec.getSize(spec);
        switch(mode){
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.AT_MOST:
                if(type==0){//测量宽度
                    size= (int) (getPaddingLeft()+getPaddingRight()+paint.measureText(str[0]));
                }else{//测量高度
                    size =getPaddingTop()+getPaddingBottom()+mHeight*str.length;
                }
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
            default:break;
        }
        return size;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y= (int) event.getY();
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                selectY(y);break;
            case MotionEvent.ACTION_UP:
                mindex = -1;
                onSelectListener.uptouch();
                invalidate();
                break;
        }
        return true;//拦截事件，表示当前控件对事件感兴趣；对move和up进行调用
    }

    private void selectY(int y){
        int index=y/mHeight;
        //判断下标位置
        if (index<0){
            index=0;
        }else if(index>=str.length){
            index=str.length-1;
        }

        if(mindex==index){//判断不允许重绘
            return ;
        }
        //判断接口回调是否为空
        if(onSelectListener!=null) {
//            Log.d("TAG","b"+index);
            onSelectListener.OnSelectListener(str[index]);
        }
        mindex=index;
        invalidate();//刷新重绘
    }

    /**
     * 接口回调
     * 定义接口
     */
    public interface OnSelectListener{

        void OnSelectListener(String keys);
        void uptouch();
//        String[] SetListDatas();
    }

}
