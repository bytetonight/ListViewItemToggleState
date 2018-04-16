package android.example.com.listviewitemtogglestate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ChildActivity extends AppCompatActivity {

    private ListItem listItem;
    private TextView simpleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        setTitle("ChildActivity");
        simpleText = findViewById(R.id.simpleText);

        Intent senderIntent = getIntent();
        if (senderIntent.hasExtra(Global.KEY_EXTRA_DATA)) {
            listItem = senderIntent.getParcelableExtra(Global.KEY_EXTRA_DATA);

            simpleText.setText(
                    getString(R.string.placeholder_text, listItem.getIdString())
            );
        }
    }
}
