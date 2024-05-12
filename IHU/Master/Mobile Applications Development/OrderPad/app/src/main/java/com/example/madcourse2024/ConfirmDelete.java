package com.example.madcourse2024;


import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import android.database.sqlite.SQLiteDatabase;

public class ConfirmDelete extends DialogFragment {
    // private final int pid;

    public ConfirmDelete() {
        super();
        // pid = 0;
    }

    public ConfirmDelete(int product_id) {
        super();
        // pid = product_id;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // The dialog content
        builder.setMessage(R.string.conf_delete);

        // Positive button (caption and event listener)
        builder.setPositiveButton(R.string.yes_btn, (dialog, id) -> {
            System.out.println("Deletion Confirmed");
/*
            if (pid > 0) {
                SQLiteDatabase myDB = getActivity().openOrCreateDatabase
                        ("products_db", MODE_PRIVATE, null);

                myDB.execSQL("DELETE FROM products WHERE product_id = " + pid);
                ((DbActivity)getActivity()).PopulateRecycler();
            }
*/
        });

        // Negative button (caption and event listener)
        builder.setNegativeButton(R.string.no_btn, (dialog, id) -> {
            System.out.println("Canceled");
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}

