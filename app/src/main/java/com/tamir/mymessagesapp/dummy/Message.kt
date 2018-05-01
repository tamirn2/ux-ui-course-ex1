package com.tamir.mymessagesapp.dummy

import java.util.ArrayList
import java.util.HashMap

object Message {

    val ITEMS: MutableList<Message> = ArrayList()

    val ITEM_MAP: MutableMap<String, Message> = HashMap()

//    init {
        // Add some sample items.
//        for (i in 1..COUNT) {
//            addItem(createMessageItem(i))
//        }
//    }

//    private fun addItem(item: Message) {
//        ITEMS.add(item)
//        ITEM_MAP.put(item.id, item)
//    }

    private fun createMessageItem(userName: String, messageContent: String, time: Int): Message {
        return Message(userName, messageContent, time)
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class Message(val userName: String, val messageContent: String, val time: Int) {
        override fun toString(): String = "$userName - $messageContent - $time"
    }
}
