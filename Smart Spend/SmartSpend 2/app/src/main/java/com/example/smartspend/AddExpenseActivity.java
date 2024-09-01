package com.example.smartspend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.smartspend.databinding.ActivityAddExpenseBinding;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.UUID;

public class AddExpenseActivity extends AppCompatActivity {
    ActivityAddExpenseBinding binding;
    private String type;
    private DatabaseReference expensesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddExpenseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        expensesRef = database.getReference("expenses");

        type = getIntent().getStringExtra("type");

        if (type.equals("Income")) {
            binding.incomeRadio.setChecked(true);
        } else {
            binding.expenseRadio.setChecked(true);
        }

        binding.incomeRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "Income";
            }
        });

        binding.expenseRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "Expense";
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.saveExpense) {
            createExpense();
            return true;
        }
        return false;
    }

    private void createExpense() {
        String expenseId = UUID.randomUUID().toString();
        String amount = binding.amount.getText().toString();
        String note = binding.note.getText().toString();
        String category = binding.category.getText().toString();
        boolean incomeChecked = binding.incomeRadio.isChecked();

        if (incomeChecked) {
            type = "Income";
        } else {
            type = "Expense";
        }

        if (amount.trim().length() == 0) {
            binding.amount.setError("Empty");
            return;
        }

        ExpenseModel expenseModel = new ExpenseModel(expenseId, note, category, Long.parseLong(amount), Calendar.getInstance().getTimeInMillis());

        expensesRef.child(expenseId).setValue(expenseModel, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@NonNull DatabaseError error, @NonNull DatabaseReference ref) {
                if (error == null) {
                    // Write was successful
                    finish();
                } else {
                    // Handle error
                    // You might want to show a toast or log the error
                }
            }
        });
    }
}
