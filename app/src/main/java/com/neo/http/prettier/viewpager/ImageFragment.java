package com.neo.http.prettier.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.neo.http.prettier.R;
import com.neo.http.prettier.object.ImageEntity;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
public class ImageFragment extends Fragment {

    /**
     * ImageFragment
     * 用于图片视图的加载
     * 需要ImageEntity对象类
     */
    ImageView img;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.combination_viewpager,container,false);
        return view;
    }

    /**
     * 在onCreateView之后被调用
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        img= (ImageView) view.findViewById(R.id.combination_img);
    }

    /**
     * 在onViewCreated之后被调用
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getBundle(getArguments());
    }

    /**
     * 静态工厂方法
     * @param imageEntity
     * @return
     */
    public static Fragment getInstance(ImageEntity imageEntity){
        ImageFragment imageFragment=new ImageFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("imageFragment",imageEntity);
        imageFragment.setArguments(bundle);
        return imageFragment;
    }

    protected void getBundle(Bundle bundle){
        ImageEntity imageEntity= (ImageEntity) bundle.getSerializable("imageFragment");
        String img_url=imageEntity.getImg_url();
//        Log.d("TAG", "img_url: "+img_url);
        /**
         * 判断用于加载本地图片
         * 创建本地图片对象是，设置img_url="null"用于判断
         */

        if (img_url!=null) {
            img.setImageResource(imageEntity.getSrc());
        }else{
            Glide.with(getActivity()).load(imageEntity.getImg_url()).into(img);
        }


    }

}
