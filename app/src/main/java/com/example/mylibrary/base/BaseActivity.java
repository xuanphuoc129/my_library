package com.example.mylibrary.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.mylibrary.R;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public <F extends Fragment> void replaceFragment(F fragment) {
        // Create new fragment and transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        transaction.replace(R.id.frContainer, fragment);
        // and add the transaction to the back stack if needed
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    public void showLoading() {
        dialog = ProgressDialog.show(this, "Đang tải dữ liệu",
                "Loading. Please wait...", true);
    }

    public void hideLoading(){
        if(dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
