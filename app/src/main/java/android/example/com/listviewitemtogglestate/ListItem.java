package android.example.com.listviewitemtogglestate;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ByteTonight on 16.04.2018.
 */

public class ListItem implements Parcelable {
    private int id;
    private boolean isSelected;

    public ListItem(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getIdString() {
        return id+"";
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    // Parcelable implementation performed by Android Parcelable Code Generator plugin
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    protected ListItem(Parcel in) {
        this.id = in.readInt();
        this.isSelected = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ListItem> CREATOR = new Parcelable.Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel source) {
            return new ListItem(source);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };
}
