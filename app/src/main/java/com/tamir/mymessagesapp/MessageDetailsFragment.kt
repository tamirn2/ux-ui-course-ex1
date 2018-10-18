package com.tamir.mymessagesapp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import com.tamir.mymessagesapp.message.Message


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MessageDetailsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MessageDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MessageDetailsFragment : Fragment() {

    private var mListener: OnFragmentInteractionListener? = null
    private var mMsg: Message? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mRootView = inflater.inflate(R.layout.fragment_message_details,
                container, false)

        val mainText = mRootView.findViewById<TextView>(R.id.message_details_fragment_main_text_view)
        mainText.text = this.mMsg!!.toString()

        val deleteMsgButton = mRootView.findViewById<Button>(R.id.message_details_fragment_delete_button)
        val deleteListener = View.OnClickListener { mListener!!.deleteMessage(mMsg!!.index) }
        deleteMsgButton.setOnClickListener(deleteListener)

        val shareMsgButton = mRootView.findViewById<Button>(R.id.message_details_fragment_share_button)
        val shareListener = View.OnClickListener { mListener!!.shareMessage(mMsg!!.getShareString()) }
        shareMsgButton.setOnClickListener(shareListener)


        return mRootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        fun deleteMessage(index: Int)
        fun shareMessage(msg: String)
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment MessageDetailsFragment.
         */
        fun newInstance(msg: Message): MessageDetailsFragment {
            val fragment = MessageDetailsFragment()
            fragment.mMsg = msg
            return fragment
        }
    }
}// Required empty public constructor
