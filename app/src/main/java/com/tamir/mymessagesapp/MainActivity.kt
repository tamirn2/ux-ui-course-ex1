package com.tamir.mymessagesapp

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.tamir.mymessagesapp.message.Message
import android.content.Intent



class MainActivity : AppCompatActivity(),
        MainFragment.OnFragmentInteractionListener,
        MessagesFragment.OnListFragmentInteractionListener,
        MessageDetailsFragment.OnFragmentInteractionListener
{
    override fun shareMessage(msg: String)
    {
        val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
        sharingIntent.type = "text/plain";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Shared msg");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, msg);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    override fun deleteMessage(index: Int) {
        Message.mValues.removeAt(index)
        Message.upbeatIndices()
        supportFragmentManager.popBackStack()
    }

    override fun messageClicked(index: Int) {
        var messageDetailsFragment = MessageDetailsFragment.newInstance(Message.mValues[index])
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_main_frame_layout, messageDetailsFragment)
                .addToBackStack(null)
                .commit()

    }

    override fun addMessage(userName: String, messageContent: String, time: String) {

    }

    override fun onListFragmentInteraction(username: String)
    {
        var messagesFragment = MessagesFragment.newInstance(username)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_main_frame_layout, messagesFragment)
                .addToBackStack(null)
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
        supportFragmentManager
                .beginTransaction()
                .add(R.id.activity_main_frame_layout, MainFragment())
                .commit()
    }
}
