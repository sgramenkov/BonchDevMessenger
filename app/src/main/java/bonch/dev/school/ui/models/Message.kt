package bonch.dev.school.ui.models

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
@Parcelize
data class Message( val messageId: Int,val messageText: String,val sentDate: String,val isUser: Boolean):Parcelable {


    class MessageLab() {
        val messageList: MutableList<Message>

        init {
            messageList = mutableListOf()


        }
    }

}

