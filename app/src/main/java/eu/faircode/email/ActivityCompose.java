package eu.faircode.email;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityCompose extends AppCompatActivity {
    private EditText etTo, etSubject, etBody;
    private Button btnSend, btnAttach, btnEncrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        etTo = findViewById(R.id.etTo);
        etSubject = findViewById(R.id.etSubject);
        etBody = findViewById(R.id.etBody);
        btnSend = findViewById(R.id.btnSend);
        btnAttach = findViewById(R.id.btnAttach);
        btnEncrypt = findViewById(R.id.btnEncrypt);

        btnSend.setOnClickListener(v -> sendMessage());
        btnAttach.setOnClickListener(v -> attachFile());
        btnEncrypt.setOnClickListener(v -> encryptMessage());
    }

    private void sendMessage() {
        new Thread(() -> {
            try {
                // Create message entity
                EntityMessage message = new EntityMessage();
                message.subject = etSubject.getText().toString();
                message.preview = etBody.getText().toString().substring(0, Math.min(100, etBody.getText().length()));
                message.sent = new java.util.Date();
                
                DB db = DB.getInstance(this);
                long messageId = db.message().insertMessage(message);
                
                // Create send operation
                EntityOperation operation = new EntityOperation();
                operation.message = messageId;
                operation.name = EntityOperation.SEND;
                operation.created = System.currentTimeMillis();
                db.operation().insertOperation(operation);
                
                runOnUiThread(() -> {
                    Toast.makeText(this, getString(R.string.msg_sent), Toast.LENGTH_SHORT).show();
                    finish();
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
            }
        }).start();
    }

    private void attachFile() {
        // Implement file picker
        Toast.makeText(this, "Attach file", Toast.LENGTH_SHORT).show();
    }

    private void encryptMessage() {
        // Implement encryption
        Toast.makeText(this, "Encrypt message", Toast.LENGTH_SHORT).show();
    }
}
