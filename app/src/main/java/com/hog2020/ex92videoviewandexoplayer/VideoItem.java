package com.hog2020.ex92videoviewandexoplayer;

import com.google.gson.annotations.SerializedName;

public class VideoItem {
    String title;
    String subtitle;
    String thumb;
    String sources;

    //만약 자동 파싱되는 json 의 키갑과 다른멤버변수명을 사용하고 싶다면
    @SerializedName("description")
    String desc;

    public VideoItem(String title, String subtitle, String thumb, String sources, String desc) {
        this.title = title;
        this.subtitle = subtitle;
        this.thumb = thumb;
        this.sources = sources;
        this.desc = desc;
    }

    public VideoItem() {
    }
}
