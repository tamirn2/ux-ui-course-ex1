package com.tamir.mymessagesapp

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : MainFragment.OnFragmentInteractionListener,
                     AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR)
        supportFragmentManager
                .beginTransaction()
                .add(R.id.activity_main_frame_layout, MainFragment())
                .commit()
    }
}
