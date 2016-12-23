package com.dam.vu2k16.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dam.vu2k16.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DasavathaaramFragment extends Fragment {

    WebView avathaaramWV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("ദശാവതാരം");
        View view = inflater.inflate(R.layout.fragment_dasavathaaram, container, false);
        avathaaramWV = (WebView) view.findViewById(R.id.avathaaramWV);
        avathaaramWV.getSettings().setJavaScriptEnabled(true);
        avathaaramWV.setWebViewClient(new WebViewClient());
        avathaaramWV.loadUrl("file:///android_asset/dasavathaaram.html");

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
