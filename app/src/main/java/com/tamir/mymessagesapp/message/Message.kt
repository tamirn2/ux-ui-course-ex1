package com.tamir.mymessagesapp.message

import android.view.View
import com.tamir.mymessagesapp.MessagesFragment

data class Message (val userName: String,
                    val messageContent: String,
                    val time: String,
                    private var listener: MessagesFragment.OnListFragmentInteractionListener?)
{
    var onClickListener : View.OnClickListener
    var index: Int

    init {
        index = mValues.size
        onClickListener = View.OnClickListener {
            listener!!.messageClicked(index)
        }
    }

    fun updateListener(){
        onClickListener = View.OnClickListener {
            listener!!.messageClicked(index)
        }
    }

    override fun toString(): String {
        return "$userName: $messageContent - $time";
    }

    companion object {
        val mValues = mutableListOf<Message>()

        fun updeateIndecies(){
            for (i in mValues.indices){
                mValues[i].index = i
                mValues[i].updateListener()
            }
        }
    }
}
