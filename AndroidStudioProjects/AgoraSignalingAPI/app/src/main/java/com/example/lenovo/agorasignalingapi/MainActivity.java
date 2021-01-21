package com.example.lenovo.agorasignalingapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.agora.AgoraAPI;
import io.agora.AgoraAPIOnlySignal;

public class MainActivity extends AppCompatActivity {
AgoraAPIOnlySignal m_agoraAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_agoraAPI = AgoraAPIOnlySignal.getInstance(this, "fe1aa9e8ab9b47acae0255862676efea");
        m_agoraAPI.login2("fe1aa9e8ab9b47acae0255862676efea", account, token, uid, deviceID, retry_time_in_s, retry_count);
        /* Set the onLoginSuccess callback. */
        m_agoraAPI.onLoginSuccess(uid,fd) {
            /* Your code. */
        }
        /* Set the onLoginFailed callback. */
        m_agoraAPI.onLoginFailed(ecode){
            /* Your code. */
        }

    }
}
