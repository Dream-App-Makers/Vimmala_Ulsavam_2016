package com.dam.vu2k16.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.dam.vu2k16.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {
    WebView contactWV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        contactWV = (WebView) view.findViewById(R.id.contactWV);
        contactWV.loadUrl("file:///android_asset/contact_us.html");
        return view;
    }

    //Handle Back Button Press
    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container, new HomeFragment()).commit();
                    return true;
                }
                return false;
            }
        });

    }
}
