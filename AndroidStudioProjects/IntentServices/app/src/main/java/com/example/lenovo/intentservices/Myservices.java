package com.example.lenovo.intentservices;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class Myservices extends IntentService {
//    /**
//     * Creates an IntentService.  Invoked by your subclass's constructor.
//     *
//     * @param name Used to name the worker thread, important only for debugging.//
//     */
    public Myservices() {
        super("My_worker_thread");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Toast.makeText(this,"Services started...",Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Services Stopped",Toast.LENGTH_LONG).show();
        super.onDestroy();

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
      synchronized (this)
      {
          int count=0;
          while(count<=5)
          {
              try {
                  wait(1000);          //100 seconds
                 count++;
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }

          }
      }
    }
}
