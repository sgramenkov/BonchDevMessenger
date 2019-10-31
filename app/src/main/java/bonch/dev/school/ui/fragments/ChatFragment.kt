package bonch.dev.school.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.school.R
import bonch.dev.school.ui.message_recycler_items
import bonch.dev.school.ui.models.Message
import bonch.dev.school.ui.models.MessageLab
import java.util.*

class ChatFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val messageList=MessageLab().messageList
        val view = inflater.inflate(R.layout.fragment_chat,container,false)
        val sendButton:ImageButton=view.findViewById(R.id.send_message_button)
        var messageet:EditText=view.findViewById(R.id.message_et)
        val messageRecycler:RecyclerView=view.findViewById(R.id.message_recycler_view)
        messageRecycler.adapter= message_recycler_items(messageList)
        val lm = LinearLayoutManager(context)
        lm.scrollToPosition(message_recycler_items(messageList).itemCount-1)
        messageRecycler.layoutManager= lm
        sendButton.setOnClickListener(){
            var count=0
            for (k in messageet.text.toString()){
                if (k.isWhitespace())
                    count++
                }
            if (count==messageet.text.length){
                Toast.makeText(context,"Пустая строка",Toast.LENGTH_SHORT).show()
            }
           else {
                messageList.add(Message(1,messageet.text.toString(),Date(),true))
                messageet.setText("")

            }
        }
        return view
    }
}