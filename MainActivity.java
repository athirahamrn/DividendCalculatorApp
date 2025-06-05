package com.example.dividendcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText etInvestedFund, etAnnualRate, etMonthsInvested;
    private TextView tvMonthlyDividend, tvTotalDividend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- NEW: Set up Toolbar as ActionBar ---
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Dividend Calculator"); // Set the title for this page
        }
        // --- END NEW ---

        etInvestedFund = findViewById(R.id.etInvestedFund);
        etAnnualRate = findViewById(R.id.etAnnualRate);
        etMonthsInvested = findViewById(R.id.etMonthsInvested);
        tvMonthlyDividend = findViewById(R.id.tvMonthlyDividend);
        tvTotalDividend = findViewById(R.id.tvTotalDividend);
        Button btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(v -> calculateDividend());
    }

    @SuppressLint("SetTextI18n")
    private void calculateDividend() {
        String investedFundStr = etInvestedFund.getText().toString();
        String annualRateStr = etAnnualRate.getText().toString();
        String monthsInvestedStr = etMonthsInvested.getText().toString();

        if (investedFundStr.isEmpty() || annualRateStr.isEmpty() || monthsInvestedStr.isEmpty()) {
            Toast.makeText(this, "Please enter all values", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double investedFund = Double.parseDouble(investedFundStr);
            double annualRate = Double.parseDouble(annualRateStr);
            int monthsInvested = Integer.parseInt(monthsInvestedStr);

            if (monthsInvested <= 0 || monthsInvested > 12) {
                Toast.makeText(this, "Months invested must be between 1 and 12", Toast.LENGTH_SHORT).show();
                return;
            }

            // Calculate Monthly Dividend: (Rate / 12) x Invested Fund
            // Convert percentage to decimal (e.g., 5.0% -> 0.05)
            double monthlyDividend = (annualRate / 100.0 / 12.0) * investedFund;

            // Calculate Total Dividend: Monthly Dividend * Number of Months
            double totalDividend = monthlyDividend * monthsInvested;

            // Format results to 2 decimal places
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            tvMonthlyDividend.setText("Monthly Dividend: RM " + decimalFormat.format(monthlyDividend));
            tvTotalDividend.setText("Total Dividend: RM " + decimalFormat.format(totalDividend));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
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
            // Already on Home (MainActivity), or navigate to MainActivity if elsewhere
            return true;
        } else if (id == R.id.menu_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}