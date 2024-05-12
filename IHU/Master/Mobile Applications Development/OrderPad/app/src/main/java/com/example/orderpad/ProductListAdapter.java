package com.example.orderpad;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;
    private Runnable onUpdateTotal;

    public ProductListAdapter(Context context, List<Product> productList, Runnable onUpdateTotal) {
        this.context = context;
        this.productList = productList;
        this.onUpdateTotal = onUpdateTotal;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.productQuantity.setText(String.valueOf(product.getQuantity()));

        holder.btnIncrease.setOnClickListener(v -> {
            product.incrementQuantity();
            notifyItemChanged(position);
            ((OrderActivity) context).updateTotalOrderValue();  // Update total value on activity
        });

        holder.btnDecrease.setOnClickListener(v -> {
            product.decrementQuantity();
            notifyItemChanged(position);
            ((OrderActivity) context).updateTotalOrderValue();  // Update total value on activity
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public List<Product> getProductList() {
        return productList;
    }
    public void updateData(List<Product> newProducts) {
        productList.clear();
        productList.addAll(newProducts);
        notifyDataSetChanged();
        Log.d("Adapter Update", "Product list updated. Size: " + productList.size());
    }


    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, productQuantity;
        Button btnIncrease, btnDecrease;

        ProductViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productQuantity = itemView.findViewById(R.id.productQuantity);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
        }
    }
}
