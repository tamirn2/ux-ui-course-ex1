package com.tamir.mymessagesapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : MainFragment.OnFragmentInteractionListener,
                     AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        supportFragmentManager
                .beginTransaction()
                .add(R.id.activity_main_frame_layout, MainFragment())
                .commit()
    }
}
