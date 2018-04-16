package android.example.com.listviewitemtogglestate;

/**
 * Created by ByteTonight on 16.04.2018.
 */

public class ListItem {
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
}
