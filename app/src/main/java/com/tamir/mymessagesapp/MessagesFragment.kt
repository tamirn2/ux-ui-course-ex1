package com.tamir.mymessagesapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tamir.mymessagesapp.message.Message
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [MessagesFragment.OnListFragmentInteractionListener] interface.
 */
class MessagesFragment : Fragment() {
    private var userName:String = ""
    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var windowManager: WindowManager
    private lateinit var mAdapter: MymessagesRecyclerViewAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            userName = it.getString(userNameKey)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val mRootView = inflater.inflate(R.layout.fragment_messages_list, container, false)
        val recycler = mRootView.findViewById<RecyclerView>(R.id.messages_fragment_list)
//        val metrics = DisplayMetrics()
//        val display = windowManager.defaultDisplay
//        display.getMetrics(metrics)
        val messageBox = mRootView.findViewById<EditText>(R.id.message_list_fragment_editText_message_box)
        val sendButton = mRootView.findViewById<Button>(R.id.messages_fragment_send_button)
//        messageBox.layoutParams.width = metrics.widthPixels - sendButton.width
//        recycler.layoutParams.height = metrics.heightPixels;
        val ITEMS: MutableList<Message> = ArrayList()

        // Set the adapter
        if (recycler is RecyclerView) {
            mAdapter = MymessagesRecyclerViewAdapter(ITEMS, listener)
            with(recycler) {
                adapter = mAdapter
            }
        }
        sendButton.setOnClickListener {
            val currentDateTime = LocalDateTime.now()
            var time = currentDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
            mAdapter.addMessage(Message(userName, messageBox.text.toString(), time))
//            listener?.addMessage(userName, messageBox.text.toString(), time)
        }
        return mRootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener
    {
        fun addMessage(userName: String, messageContent: String, time: String)
    }

    companion object {

        const val userNameKey = "userName"

        @JvmStatic
        fun newInstance(userName: String):MessagesFragment =
                MessagesFragment().apply {
                    arguments = Bundle().apply {
                        putString("userName", userName)
                    }
                }

    }
}
