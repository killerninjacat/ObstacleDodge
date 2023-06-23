package com.example.obstacledodge;

public class Characterreceive {
    String imgurl,desc,chatrname;

    public Characterreceive(String imgurl, String desc, String chatrname) {
        this.imgurl = imgurl;
        this.desc = desc;
        this.chatrname = chatrname;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getChatrname() {
        return chatrname;
    }

    public void setChatrname(String chatrname) {
        this.chatrname = chatrname;
    }
}
