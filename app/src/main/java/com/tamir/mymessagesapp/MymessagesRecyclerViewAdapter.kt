package com.tamir.mymessagesapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.tamir.mymessagesapp.MessagesFragment.OnListFragmentInteractionListener
import com.tamir.mymessagesapp.message.Message

import kotlinx.android.synthetic.main.fragment_messages.view.*

/**
 * [RecyclerView.Adapter] that can display a [Message] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyMessagesRecyclerViewAdapter
    : RecyclerView.Adapter<MyMessagesRecyclerViewAdapter.ViewHolder>() {

    init { }

    fun addMessage(msg: Message){
        Message.mValues.add(msg)
        notifyItemInserted(Message.mValues.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_messages, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Message.mValues[position]
        holder.msgTime.text = item.time
        holder.msgContent.text = item.messageContent
        holder.userName.text = item.userName
        holder.itemView.setOnClickListener(item.onClickListener)

    }

    override fun getItemCount(): Int = Message.mValues.size

    inner class ViewHolder(mView: View) :
            RecyclerView.ViewHolder(mView) {
        val userName: TextView = mView.item_user_name
        val msgContent: TextView = mView.item_msg_content
        val msgTime: TextView = mView.item_msg_time

        override fun toString(): String {
            return super.toString() + " " + msgContent.text + " "
        }
    }
}
