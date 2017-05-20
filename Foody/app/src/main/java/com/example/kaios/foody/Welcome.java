package com.example.kaios.foody;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class Welcome extends AppCompatActivity {

    private static final int PROGRESS = 0x1;
    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        startHeavyProcessing();
    }
    //
    private void startHeavyProcessing(){
        new LongOperation().execute();
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                Thread.sleep(4000);//wait 4s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            mProgress = (ProgressBar) findViewById(R.id.processbar);
//            // Start lengthy operation in a background thread
//            new Thread(new Runnable() {
//                public void run() {
//                    while (mProgressStatus < 100) {
//                        mProgressStatus =getODau1();
//
//                        // Update the progress bar
//                        mHandler.post(new Runnable() {
//                            public void run() {
//                                mProgress.setProgress(mProgressStatus);
//                            }
//                        });
//                    }
//                }
//            }).start();
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Intent i = new Intent(Welcome.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}

    }
}
