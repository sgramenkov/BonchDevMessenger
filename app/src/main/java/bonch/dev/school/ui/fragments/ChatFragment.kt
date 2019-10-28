package bonch.dev.school.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import bonch.dev.school.R

class ChatFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat,container,false)
        val sendButton:ImageButton=view.findViewById(R.id.send_message_button)
        sendButton.setOnClickListener(){
           // (activity as MainAppActivity).replace()
        }
        return view
    }
}