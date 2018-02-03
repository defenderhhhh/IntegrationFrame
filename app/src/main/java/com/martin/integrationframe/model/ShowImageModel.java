package com.martin.integrationframe.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：Martin on 2018/2/2 13:26
 * 邮箱：martin0207mfh@163.com
 */
public class ShowImageModel implements Parcelable {

    //图片地址
    private String path;

    //图片地址，存在Uri转path失败的情况，故不去转换
    private Uri uri;

    // ===============  以下为功能预留字段（未做）  =================================================

    //图片标题
    private String title;

    //图片描述
    private String description;

    //是否展示
    private boolean show;

    //如何展示
    private int showType;


    public ShowImageModel(String path) {
        this.path = path;
    }

    public ShowImageModel(Uri uri) {
        this.uri = uri;
    }

    protected ShowImageModel(Parcel in) {
        path = in.readString();
        title = in.readString();
        description = in.readString();
        show = in.readByte() != 0;
        showType = in.readInt();
        uri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<ShowImageModel> CREATOR = new Creator<ShowImageModel>() {
        @Override
        public ShowImageModel createFromParcel(Parcel in) {
            return new ShowImageModel(in);
        }

        @Override
        public ShowImageModel[] newArray(int size) {
            return new ShowImageModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeByte((byte) (show ? 1 : 0));
        dest.writeInt(showType);
        dest.writeParcelable(uri, flags);
    }

    // ================== get and set   ==============================================

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
