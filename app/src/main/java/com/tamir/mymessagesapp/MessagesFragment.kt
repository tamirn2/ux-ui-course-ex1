package com.tamir.mymessagesapp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tamir.mymessagesapp.dummy.Message
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [MessagesFragment.OnListFragmentInteractionListener] interface.
 */
class MessagesFragment : Fragment() {
    // TODO: Customize parameters
    private var columnCount = 1

    private var userName:String = ""
    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var windowManager: WindowManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            userName = it.getString(userNameKey)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val mRootView = inflater.inflate(R.layout.fragment_messages_list, container, false)
        val recycler = mRootView.findViewById<RecyclerView>(R.id.messages_fragment_list)
        val metrics = DisplayMetrics()
        val display = windowManager.defaultDisplay
        display.getMetrics(metrics)
        val messageBox = mRootView.findViewById<EditText>(R.id.message_list_fragment_editText_message_box)
        val sendButton = mRootView.findViewById<Button>(R.id.messages_fragment_send_button)
//        messageBox.layoutParams.width = metrics.widthPixels - sendButton.width
//        recycler.layoutParams.height = metrics.heightPixels;

        // Set the adapter
        if (recycler is RecyclerView) {
            with(recycler) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MymessagesRecyclerViewAdapter(Message.ITEMS, listener)
            }
        }
        val sendMessageButton = mRootView.findViewById(R.id.messages_fragment_send_button) as Button
        sendMessageButton.setOnClickListener {

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
    interface OnListFragmentInteractionListener {}

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
