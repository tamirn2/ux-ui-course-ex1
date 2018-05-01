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
class MymessagesRecyclerViewAdapter(
        private val mValues: MutableList<Message>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MymessagesRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Message
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
        }
    }

    fun addMessage(msg: Message){
        mValues.add(msg)
        notifyItemInserted(mValues.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_messages, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.msgTime.text = item.time
        holder.msgContent.text = item.messageContent
        holder.userName.text = item.userName
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val userName: TextView = mView.item_user_name
        val msgContent: TextView = mView.item_msg_content
        val msgTime: TextView = mView.item_msg_time

        override fun toString(): String {
            return super.toString() + " '" + msgContent.text + "'"
        }
    }
}
