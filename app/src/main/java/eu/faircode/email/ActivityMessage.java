package eu.faircode.email;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityMessage extends AppCompatActivity {
    private TextView tvSubject, tvFrom, tvBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        tvSubject = findViewById(R.id.tvSubject);
        tvFrom = findViewById(R.id.tvFrom);
        tvBody = findViewById(R.id.tvBody);

        long messageId = getIntent().getLongExtra("message_id", -1);
        if (messageId != -1) {
            loadMessage(messageId);
        }
    }

    private void loadMessage(long messageId) {
        new Thread(() -> {
            DB db = DB.getInstance(this);
            EntityMessage message = db.message().getMessage(messageId);
            if (message != null) {
                runOnUiThread(() -> {
                    tvSubject.setText(message.subject);
                    tvBody.setText(message.preview);
                });
            }
        }).start();
    }
}
