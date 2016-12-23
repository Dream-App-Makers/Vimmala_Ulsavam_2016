package com.dam.vu2k16.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.dam.vu2k16.R;
import com.dam.vu2k16.utils.DownloadFileFromURL;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class UlsavamThemeSongFragment extends Fragment {

    WebView themeSongWV;
    Button offlinePlayBT;
    private static String fileURL = "http://programmerguru.com/android-tutorial/wp-content/uploads/2013/04/hosannatelugu.mp3";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ulsavam_theme_song, container, false);
        themeSongWV = (WebView) view.findViewById(R.id.themeSongWV);
        offlinePlayBT = (Button) view.findViewById(R.id.offlinePlay);
        themeSongWV.getSettings().setJavaScriptEnabled(true);
        themeSongWV.setWebViewClient(new WebViewClient());
        themeSongWV.loadUrl("file:///android_asset/theme_song.html");
        offlinePlayBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadFileFromURL().execute(fileURL);
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                File file = new File("/sdcard/Vimmala_Ulsavam_2016/ulsavamThemeSong.mp3");
                intent.setDataAndType(Uri.fromFile(file), "audio/*");
                startActivity(intent);

            }
        });
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
