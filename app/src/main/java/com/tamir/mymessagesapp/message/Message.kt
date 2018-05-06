package com.tamir.mymessagesapp.message

import android.view.View
import com.tamir.mymessagesapp.MessagesFragment

data class Message (val userName: String,
                    val messageContent: String,
                    val time: String,
                    val date: String,
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

    fun getShareString(): String
    {
        return "$userName: $messageContent - $time"
    }

    override fun toString(): String {
        return "$userName: $messageContent - $time on $date";
    }

    companion object {
        val mValues = mutableListOf<Message>()

        fun upbeatIndices(){
            for (i in mValues.indices){
                mValues[i].index = i
                mValues[i].updateListener()
            }
        }
    }
}
