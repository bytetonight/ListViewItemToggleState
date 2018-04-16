package android.example.com.listviewitemtogglestate;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ByteTonight on 16.04.2018.
 */

public class CustomAdapter extends ArrayAdapter<ListItem> {

    private Context context;
    private int layoutResource;


    public CustomAdapter(@NonNull Context context, int resource, List<ListItem> listItems) {
        super(context, resource, listItems);

        this.context = context;
        layoutResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutResource, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ListItem listItem = getItem(position);

        int color;
        if (listItem.isSelected()) {
            color = ContextCompat.getColor(context, android.R.color.holo_blue_dark);
        } else {
            color = ContextCompat.getColor(context, android.R.color.white);
        }
        holder.listItemRoot.setBackgroundColor(color);
        holder.itemId.setText(listItem.getIdString());

        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "My Id is: " + listItem.getIdString(), Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * Adding an OnClickListener on the Intent Button
         * using {@link CustomAdapter#context} in the Intent
         * to request running {@link ChildActivity}
         */
        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoChildActivityIntent = new Intent(context, ChildActivity.class);
                gotoChildActivityIntent.putExtra(Global.KEY_EXTRA_DATA, listItem);
                context.startActivity(gotoChildActivityIntent);
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        TextView itemId;
        View listItemRoot;
        Button button1;
        Button button2;

        ViewHolder(View v) {
            itemId = v.findViewById(R.id.itemId);
            listItemRoot = v.findViewById(R.id.listItemRoot);
            button1 = v.findViewById(R.id.button1);
            button2 = v.findViewById(R.id.button2);
        }
    }
}
