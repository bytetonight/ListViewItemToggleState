package android.example.com.listviewitemtogglestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * This example allows for highlighting (selecting) ListItems in a ListView
 * by means of adding a property to the actual ListItem which stores it's "selected" state
 * in form of a simple boolean value
 */
public class MainActivity extends AppCompatActivity {

    List<ListItem> listItems;
    ListItem previouslyClickedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.myListView);

        // Initialize the List of ListItems
        listItems = new ArrayList<>();
        // Add 20 ListItems to the List
        for (int i = 1; i <= 20; ++i) {
            listItems.add(new ListItem(i));
        }

        final CustomAdapter customAdapter = new CustomAdapter(this, R.layout.list_item_layout, listItems);

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Fetching the clicked ListItem from the actual customAdapter via position as index
                ListItem currentlyClickedItem = customAdapter.getItem(position);

                // or from the AdapterView which is a param of the method we're currently in
                ListItem currentlyClickedItem2 = (ListItem)adapterView.getItemAtPosition(position);

                // Which of these ways you use, is up to you

                /*
                * If there was nothing clicked before
                * save the currently clicked ListItem as previouslyClickedItem
                * and set the selected state to true
                * */
                if (previouslyClickedItem == null) {
                    previouslyClickedItem = currentlyClickedItem;
                    previouslyClickedItem.setSelected(true);
                } else {
                    /*
                    * At this point there is an item that was clicked before
                    * Check if the currently clicked item is not the one that was already clicked
                    * because there's no point in highlighting a highlighted item
                    * set previouslyClickedItem state to false
                    * set currentlyClickedItem state to true
                    * save the currently clicked ListItem as previouslyClickedItem
                    * */
                    if (previouslyClickedItem.getId() != currentlyClickedItem.getId()) {
                        previouslyClickedItem.setSelected(false);
                        currentlyClickedItem.setSelected(true);
                        previouslyClickedItem = currentlyClickedItem;
                    }
                }
                /*
                * Notify our adapter that one or more items in the list have changed
                * and that the View(s) reflecting the changed item(s) should refresh
                * See https://developer.android.com/reference/android/widget/ArrayAdapter.html#notifyDataSetChanged()
                * */
                customAdapter.notifyDataSetChanged();
            }
        });
    }


}
