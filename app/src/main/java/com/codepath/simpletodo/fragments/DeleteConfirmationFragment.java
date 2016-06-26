package com.codepath.simpletodo.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.activities.DetailItemActivity;

/**
 * Created by pbabu on 6/23/16.
 */
public class DeleteConfirmationFragment extends DialogFragment {

    private String todoItemTitle;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        todoItemTitle = getArguments().getString(DetailItemActivity.TODO_ITEM_TITLE_KEY);
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_warning)
                .setTitle("Confirm Delete Todo Item:" + todoItemTitle).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((DetailItemActivity)getActivity()).confirmDelete();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create();
    }
}
