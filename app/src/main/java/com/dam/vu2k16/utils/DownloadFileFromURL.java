package com.dam.vu2k16.utils;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by pvr on 23/12/16.
 */
public class DownloadFileFromURL extends AsyncTask<String, String, String> {


    @Override
    protected String doInBackground(String... fileURL) {
        int count;
        try {
            URL url = new URL(fileURL[0]);
            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            int lenghtOfFile = conection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file
            File path = new File("/sdcard/Vimmala_Ulsavam_2016");
            if (!path.exists()) {
                path.mkdirs();
            }
            OutputStream output = new FileOutputStream(path + File.separator+"ulsavamThemeSong.mp3");

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
