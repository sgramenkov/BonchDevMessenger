package bonch.dev.school.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
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
import java.text.SimpleDateFormat
import java.util.*
import java.util.ResourceBundle.getBundle

class ChatFragment:Fragment() {
    private lateinit var messageList:MutableList<Message>
    private lateinit var lm:LinearLayoutManager
    private lateinit var messageRecycler:RecyclerView
    private var recyclerViewStateBundle=Bundle()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messageList= Message.MessageLab().messageList
        val view = inflater.inflate(R.layout.fragment_chat,container,false)
        val sendButton:ImageButton=view.findViewById(R.id.send_message_button)
        var messageet:EditText=view.findViewById(R.id.message_et)
        messageRecycler=view.findViewById(R.id.message_recycler_view)

        lm = LinearLayoutManager(context)
        lm.scrollToPosition(message_recycler_items(messageList).itemCount-1)

        messageRecycler.layoutManager= lm
        messageRecycler.adapter= message_recycler_items(messageList)
        sendButton.setOnClickListener(){

            var count=0
            for (k in messageet.text.toString()){
                if (k.isWhitespace())
                    count++
                }
            if (count==messageet.text.length)
            {

                Toast.makeText(context,"Пустая строка",Toast.LENGTH_SHORT).show()

            }
           else {

                messageList.add(Message(1,messageet.text.toString().trim(),SimpleDateFormat("hh:mm:ss").format(Date()),true))
                messageet.setText("")
                messageRecycler.scrollToPosition(message_recycler_items(messageList).itemCount-1)

            }
        }
        return view
    }

    override fun onPause() {
        super.onPause()
        recyclerViewStateBundle = Bundle().apply { putParcelableArrayList("saveData",ArrayList<Parcelable>(messageList))  }
        Log.e("MSG",messageList.toString())
    }

    override fun onResume() {
        super.onResume()
        if (recyclerViewStateBundle!=null && recyclerViewStateBundle.get("saveData")!=null){
            messageList.clear()
            messageList.addAll(recyclerViewStateBundle.get("saveData") as MutableList<Message>)

            Log.d("onPause","${recyclerViewStateBundle.get("saveData")}")
        }
    }
    }