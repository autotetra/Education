package com.example.orderpad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    private List<Product> productList; // Correct naming consistent with other methods

    public ProductListAdapter(Context context, List<Product> productList) {
        this.productList = productList;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.format("$%.2f", product.getPrice())); // Ensure formatting is correct
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPrice; // Corrected from productStyle to productPrice

        ProductViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice); // Corrected ID reference
        }
    }

    public void updateData(List<Product> newProducts) {
        productList.clear(); // Changed from mProductList to productList
        productList.addAll(newProducts);
        notifyDataSetChanged(); // Notify adapter about data set changes
    }
}
