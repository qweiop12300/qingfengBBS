package com.chenbaolu.qflt.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.chenbaolu.baselib.base.BaseActivity;
import com.chenbaolu.qflt.R;
import com.chenbaolu.qflt.ui.fragment.HomeFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchActivity extends BaseActivity {

    private Fragment fragment;

    FragmentManager fragmentManager;

    HomeFragment homeFragment;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editText = findViewById(R.id.tool_bar_edit);

        fragmentManager = getSupportFragmentManager();

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId==3){
                    search(editText.getText().toString());
                    return true;
                }
                return false;
            }
        });
        editText.setFocusable(true);
        editText.requestFocus();
        onFocusChange(editText.isFocused());


    }

    private void onFocusChange(boolean hasFocus)
    {
        final boolean isFocus = hasFocus;
        (new Handler()).postDelayed(new Runnable() {
            public void run() {
                InputMethodManager imm = (InputMethodManager)
                        editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if(isFocus)
                {
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }
                else
                {
                    imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
                }
            }
        }, 100);
    }

    void search(String s){
        if (homeFragment!=null){
            fragmentManager.beginTransaction().hide(homeFragment).remove(homeFragment).commit();
        }
        homeFragment = new HomeFragment(s);
        fragmentManager.beginTransaction().add(R.id.search_fragment,homeFragment).show(homeFragment).commit();
    }
}