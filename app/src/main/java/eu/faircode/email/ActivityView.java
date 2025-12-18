package eu.faircode.email;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ActivityView extends AppCompatActivity {
    private RecyclerView rvAccount;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        rvAccount = findViewById(R.id.rvAccount);
        fab = findViewById(R.id.fab);

        rvAccount.setLayoutManager(new LinearLayoutManager(this));

        DB db = DB.getInstance(this);
        db.account().liveAccounts().observe(this, new Observer<List<EntityAccount>>() {
            @Override
            public void onChanged(List<EntityAccount> accounts) {
                if (accounts == null || accounts.isEmpty()) {
                    startActivity(new Intent(ActivityView.this, ActivitySetup.class));
                } else {
                    // Display accounts
                }
            }
        });

        fab.setOnClickListener(v -> {
            startActivity(new Intent(ActivityView.this, ActivityCompose.class));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, ActivitySetup.class));
            return true;
        } else if (id == R.id.action_search) {
            // Implement search
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
