package com.tamir.mymessagesapp.message

import android.view.View
import com.tamir.mymessagesapp.MessagesFragment

data class Message (val userName: String,
                    val messageContent: String,
                    val time: String,
                    private var listener: MessagesFragment.OnListFragmentInteractionListener?)
{
    var onClickListener : View.OnClickListener
    init {
        val itemNumBefore = mValues.size

        onClickListener = View.OnClickListener {
            val itemNum = itemNumBefore
            listener!!.messageClicked(itemNumBefore)
        }
    }

    override fun toString(): String {
        return "$userName: $messageContent - $time";
    }

    companion object {
        val mValues = mutableListOf<Message>()
    }
}
