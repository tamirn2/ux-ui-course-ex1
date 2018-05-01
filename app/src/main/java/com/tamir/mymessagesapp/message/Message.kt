package com.tamir.mymessagesapp.message

import org.json.JSONObject

data class Message (val userName: String,val messageContent: String,val time: String){

//    val ITEMS: MutableList<Message> = ArrayList()

    private fun createMessageItem(userName: String, messageContent: String, time: String): Message {
        return Message(userName, messageContent, time)
    }


    fun toJsonObject() : JSONObject {
        val output= JSONObject()
        output.put(KEY_NAME, userName)
        output.put(KEY_CONTENT, messageContent)
        output.put(KEY_TIME, time)
        return output
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class Message(val userName: String, val messageContent: String, val time: String) {
        override fun toString(): String = "$userName - $messageContent - $time"
    }

    companion object {
        val KEY_NAME = "userName"
        val KEY_CONTENT = "messageContent"
        val KEY_TIME = "time"

        fun fromJsonObject(input: JSONObject): Message {
            return Message(
                    input.getString(KEY_NAME),
                    input.getString(KEY_CONTENT),
                    input.getString(KEY_TIME)
            )
        }
    }

}
