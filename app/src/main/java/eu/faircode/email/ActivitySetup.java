package eu.faircode.email;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySetup extends AppCompatActivity {
    private EditText etEmail, etPassword, etImapHost, etImapPort, etSmtpHost, etSmtpPort;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etImapHost = findViewById(R.id.etImapHost);
        etImapPort = findViewById(R.id.etImapPort);
        etSmtpHost = findViewById(R.id.etSmtpHost);
        etSmtpPort = findViewById(R.id.etSmtpPort);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            saveAccount();
        });
    }

    private void saveAccount() {
        new Thread(() -> {
            try {
                DB db = DB.getInstance(this);
                
                EntityAccount account = new EntityAccount();
                account.name = etEmail.getText().toString();
                account.email = etEmail.getText().toString();
                account.host = etImapHost.getText().toString();
                account.port = Integer.parseInt(etImapPort.getText().toString());
                account.user = etEmail.getText().toString();
                account.password = etPassword.getText().toString();
                account.auth_type = "password";
                account.synchronize = true;
                account.primary = true;
                account.created = System.currentTimeMillis();
                
                long accountId = db.account().insertAccount(account);
                
                EntityIdentity identity = new EntityIdentity();
                identity.account = accountId;
                identity.name = etEmail.getText().toString();
                identity.email = etEmail.getText().toString();
                identity.host = etSmtpHost.getText().toString();
                identity.port = Integer.parseInt(etSmtpPort.getText().toString());
                identity.user = etEmail.getText().toString();
                identity.password = etPassword.getText().toString();
                identity.auth_type = "password";
                identity.synchronize = true;
                identity.primary = true;
                
                db.identity().insertIdentity(identity);
                
                runOnUiThread(() -> {
                    Toast.makeText(this, "Account saved", Toast.LENGTH_SHORT).show();
                    finish();
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
            }
        }).start();
    }
}
