package com.example.smartspend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartspend.R;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    // List to store transaction data
    private List<String> transactionList;

    // Context to be used for layout inflation
    private Context context;

    // Constructor to initialize the adapter with context and data
    public TransactionAdapter(Context context, List<String> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the transaction item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the data at the specified position
        String transaction = transactionList.get(position);

        // Set the data to the view holder
        holder.transactionTextView.setText(transaction);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    // ViewHolder class to represent each item in the RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        // TextView to display transaction information
        public TextView transactionTextView;

        // Constructor to initialize the ViewHolder
        public ViewHolder(View itemView) {
            super(itemView);

            // Find and initialize the TextView from the layout
            transactionTextView = itemView.findViewById(R.id.transactionTextView);
        }
    }
}