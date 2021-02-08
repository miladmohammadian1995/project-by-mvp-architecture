package com.example.mvp_project.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_news")
public class News implements Parcelable {
    @PrimaryKey
    private int id;
    private String title;
    private String description;
    private String date;
    private String image;
    private String video;
    private String image_writer;
    private String writer;
    private String grouping;
    @ColumnInfo(name = "is_bookmarked")
    private boolean isBookmarked;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public boolean isVideoShow() {
        return !video.isEmpty();
    }

    public String getImage_writer() {
        return image_writer;
    }

    public void setImage_writer(String image_writer) {
        this.image_writer = image_writer;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.date);
        dest.writeString(this.image);
        dest.writeString(this.video);
        dest.writeString(this.image_writer);
        dest.writeString(this.writer);
        dest.writeString(this.grouping);
        dest.writeByte(this.isBookmarked ? (byte) 1 : (byte) 0);
    }

    public News() {
    }

    protected News(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.date = in.readString();
        this.image = in.readString();
        this.video = in.readString();
        this.image_writer = in.readString();
        this.writer = in.readString();
        this.grouping = in.readString();
        this.isBookmarked = in.readByte() != 0;
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
