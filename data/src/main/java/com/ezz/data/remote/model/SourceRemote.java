
package com.ezz.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SourceRemote implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Creator<SourceRemote> CREATOR = new Creator<SourceRemote>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SourceRemote createFromParcel(Parcel in) {
            return new SourceRemote(in);
        }

        public SourceRemote[] newArray(int size) {
            return (new SourceRemote[size]);
        }

    }
    ;

    protected SourceRemote(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SourceRemote() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
