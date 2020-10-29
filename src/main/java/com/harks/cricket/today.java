package com.harks.cricket;

import android.widget.Button;

public class today {

   public String name;
    public String imageurl;
    public Button TodayReport;

    public today() {
    }

    public Button getTodayReport() {
        return TodayReport;
    }

    public void setTodayReport(Button todayReport) {
        TodayReport = todayReport;
    }

    public today(String name, String imageurl) {
        this.name = name;
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
