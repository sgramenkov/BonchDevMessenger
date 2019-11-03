package bonch.dev.school.ui.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat
import java.util.*

data class Message( val messageId: Int,val messageText: String,val sentDate: Date,val isUser: Boolean) {


    class MessageLab() {
        val messageList: MutableList<Message>

        init {
            messageList = mutableListOf()

            for (i in 1..20) {
                val usermessage = Message(i, "Текст от другого пользователя #$i", Date(), false)

                messageList.add(usermessage)

            }
        }
    }

}

