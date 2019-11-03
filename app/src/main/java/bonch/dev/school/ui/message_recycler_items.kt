package bonch.dev.school.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.school.R
import bonch.dev.school.ui.models.Message
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.hours
import kotlin.time.minutes

class message_recycler_items(val messageList:MutableList<Message>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mymsg=1
    val othermsg=2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {
      return when (viewType){
            mymsg-> myViewHolder((LayoutInflater.from(parent.context).inflate(R.layout.my_message_item,parent,false)))
            othermsg-> userViewHolder((LayoutInflater.from(parent.context).inflate(R.layout.other_message_item,parent,false)))
          else -> myViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.my_message_item,parent,false))
      }

    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(getItemViewType(position)){
            mymsg-> (holder as myViewHolder).bind(position)
            othermsg->(holder as userViewHolder).bind(position)
        }
    }


    override fun getItemCount(): Int {
        return messageList.size
    }

   inner class myViewHolder(view:View):RecyclerView.ViewHolder(view){
       val showText:TextView=view.findViewById(R.id.show_message_text)
       val showDate:TextView=view.findViewById(R.id.sent_message_date)
        fun bind(position: Int){
            showText.text="${messageList[position].messageText}"
            showDate.text="${Date()}"
        }

    }
    inner class userViewHolder(view: View):RecyclerView.ViewHolder(view){
        val showText:TextView=view.findViewById(R.id.other_message_text)
        val showDate:TextView=view.findViewById(R.id.other_message_date)
        val senderName:TextView=view.findViewById(R.id.other_message_sender_name)
        fun bind(position: Int){
            showText.text="${messageList[position].messageText}"
            showDate.text="${Date()}"
            senderName.text="Имя другого пользователя"
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (messageList.get(position).isUser){
            return mymsg
        }
        else return othermsg
    }

}