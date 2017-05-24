package com.example.kaios.foody;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {


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
            startActivity(i);//show trang chính
            finish();
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}

    }
}
