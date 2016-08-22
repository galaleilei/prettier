package com.neo.http.prettier.object;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
public class ImageEntity implements Serializable{

    /**
     * 轮播图片实体类
     *
     */

    private String type;//图片的类型
    private String img_url;//图片的URL
    private String img_id;//图片ID
    private int src;//本地图片源

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }
}
