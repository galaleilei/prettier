package com.neo.http.prettier.viewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.neo.http.prettier.R;
import com.neo.http.prettier.json_download.JSON_DownLoad;
import com.neo.http.prettier.object.ImageEntity;
import com.neo.http.prettier.utils.NavView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的Viewpager用于图片的轮播或滑动
 * 与NavView连用
 */
public class CombinationViewPage extends FrameLayout implements JSON_DownLoad.DownLoadJsonSucc, ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private List<ImageEntity> list;
    private ImageAdapter imageAdapter;
    private FragmentManager fragmentManager;
    private int cityId;
    private NavView navView;
    private FrameLayout frameLayout;
    private JoinViewPagerboolean joinViewPagerboolean;
    private List<ImageEntity> copylist;
    private int count=0;
    private Runnable runnable;
    private boolean tagImgchange=false;//用于图片是否自动轮播的标志位，默认不轮播

    private String img_url;//用于保存ViewPager的链接

    /**
     * 联网加载，设置链接
     * @param img_url
     */
    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public boolean isTagImgchange() {
        return tagImgchange;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    /**
     * 设置是否自动轮播
     * @param tagImgchange
     */
    public void setTagImgchange(boolean tagImgchange) {
        this.tagImgchange = tagImgchange;
    }

    /**
     * 用于子线程调用更新UI
     */
    private Handler handler=new Handler() {
        public void handleMessage(Message msg) {
                viewPager.setCurrentItem(Integer.valueOf(msg.obj.toString()),false);
        }

    };
    public void setJoinViewPagerboolean(JoinViewPagerboolean joinViewPagerboolean){
        this.joinViewPagerboolean=joinViewPagerboolean;
    }
    public CombinationViewPage(Context context) {
        this(context,null);
    }

    public CombinationViewPage(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CombinationViewPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        frameLayout= (FrameLayout) findViewById(R.id.framelayout);
        //获取层次视图
        LayoutInflater.from(getContext()).inflate(R.layout.homepage_header_viewpage, this, true);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(this);
        navView= (NavView) findViewById(R.id.navview);
        //设置viewpager联动
        navView.setViewPagerListener(viewPager);

    }


    /**
     * 加载ImageEntity列表
     * 加载适配器
     * 注：加载本地图片时，调用
     * @param list
     */
        public void setViewPagerAdapter(List<ImageEntity> list) {
            this.list=list;
            copylist=new ArrayList<>();

            copylist.add(list.get(list.size() - 1));
            copylist.addAll(list);
            copylist.add(list.get(0));
            if (list != null) {
//                joinViewPagerboolean.joinViewPagerboolean(true);
                //加载小圆点个数
                navView.setCount(copylist.size());
                imageAdapter = new ImageAdapter(fragmentManager, copylist);
                viewPager.setAdapter(imageAdapter);

                if (handler != null) {
                    handler.removeCallbacks(runnable);
                }
                pictureChange();//初始化Runnable
                handler.postDelayed(runnable, 0);//设置默认为显示第一页,通过handle可以在主界面刷新UI
            }
             else {
//                joinViewPagerboolean.joinViewPagerboolean(false);
            }
    }


    /**
     * 注：联网加载图片时调用
     * 根据URL加载数据
     */
    public void loadData() {
//        String url = String.format(Url_Interface.FIRST_PAGE_WEBVIEW,cityId);
        JSON_DownLoad.json_DownLoad(img_url,this);//下载json
    }


    /**
     * 根据城市ID变换不同的URL，刷新数据时调用，
     * @param fragmentManger
     * @param cityId
     */
    public void setCityId(FragmentManager fragmentManger, int cityId){
        this.fragmentManager=fragmentManger;
        this.cityId=cityId;
        loadData();
    }
    /**
     * 请求的数据的回调，JSON下载成功，解析
     * @param url
     * @param strJson
     */
    @Override
    public void downLoadSucc(String url, String strJson) {
        if (strJson!=null){
            try {
                JSONObject jsonObject=new JSONObject(strJson);
                JSONArray jsonArray=jsonObject.getJSONArray("data");
                //依赖GSON,获取图片列表
                TypeToken<List<ImageEntity>> typeToken=new TypeToken<List<ImageEntity>>(){};
                List<ImageEntity> data= new Gson().fromJson(jsonArray.toString(),typeToken.getType());
                setViewPagerAdapter(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * ViewPager的适配器
     * PagerAdapter
     * FragmentPagerAdapter -- 当ViewPager的内容固定不变并且item的数量不多的时候使用该adatper效率会更好
     * FragmentStatePagerAdapter -- 当Viewpager的内容会随时更新或者item数量比较多的时候使用该adapter的效率会更好
     *
     */
    class ImageAdapter extends FragmentStatePagerAdapter {

        private List<ImageEntity> datas;
        public ImageAdapter(FragmentManager fm, List<ImageEntity> list) {
            super(fm);
            this.datas=list;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Fragment getItem(int position) {

            return ImageFragment.getInstance(datas.get(position));
        }
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        /**
         * 默认不自动轮播，故不用监控，触摸

         */
        if (tagImgchange) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    handler.removeCallbacks(runnable);
                    Log.d("TAG", "dispatchTouchEvent:ACTION_DOWN ");
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    handler.postDelayed(runnable,2000);
                    Log.d("TAG", "dispatchTouchEvent:ACTION_UP ");
                default:
                    break;
            }
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        count=position;
        Log.d("TAG", "position: "+position);
        int pageIndex = position;

        if (position ==0) {
            // 当视图在第一个时，将页面号设置为图片的最后一张。
            pageIndex = list.size();
        } else if (position==list.size()+1) {
            // 当视图在最后一个是,将页面号设置为图片的第一张。
            pageIndex = 1;
        }
        if (position!= pageIndex) {
            viewPager.setCurrentItem(pageIndex,true);
            return;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private void pictureChange(){
            runnable=new Runnable() {
                @Override
                public void run() {
                    if (count<list.size()+1){
                        count++;
                    }else{
                        count=0;
                    }
                    Log.d("TAG","count:"+count);
                    Message message=new Message();
                    message.obj=count;
                    handler.sendMessage(message);

                    if (tagImgchange) {
                        handler.postDelayed(runnable,2000);
                    }

                }
            };
        //延时

    }


    public interface JoinViewPagerboolean{
        void joinViewPagerboolean(boolean bool);
    }


}
