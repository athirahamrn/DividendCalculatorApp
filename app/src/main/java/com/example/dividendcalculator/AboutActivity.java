package com.example.dividendcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // Import the Toolbar

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // --- NEW: Set up Toolbar as ActionBar ---
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("About"); // Set the title for this page
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable the back arrow
        }
        // --- END NEW ---

        TextView tvGitHubLink = findViewById(R.id.tvGitHubLink);
        if (tvGitHubLink != null) {
            tvGitHubLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String githubUrl = "https://github.com/athirahamrn/DividendCalculatorApp"; // Replace with your GitHub URL
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(githubUrl));
                    startActivity(intent);
                }
            });
        }
    }

    // --- Options Menu (for Navigation) ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_home) {
            Intent intent = new Intent(AboutActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.menu_about) {
            // Already on About page
            return true;
        }
        // Handle the Up/Back button in the Toolbar
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Or navigate back to MainActivity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
