package bonch.dev.school.ui.fragments

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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_chat.*
import java.text.SimpleDateFormat
import java.util.*

class ChatFragment : Fragment() {
    private lateinit var mDatebase: FirebaseDatabase
    private lateinit var mReference: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mUser: FirebaseUser
    private var messageList = Message.MessageLab().messageList
    private lateinit var lm: LinearLayoutManager
    private lateinit var messageRecycler: RecyclerView
    var counter:Int=0
    private var recyclerViewStateBundle = Bundle()
    var adapter = message_recycler_items(messageList)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDatebase = FirebaseDatabase.getInstance()
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth.currentUser!!
        mReference = mDatebase.reference.child("Users").child(mUser.uid).child("msgs")

        lm = LinearLayoutManager(context)
        val view = inflater.inflate(R.layout.fragment_chat, container, false)
        val sendButton: ImageButton = view.findViewById(R.id.send_message_button)
        var messageet: EditText = view.findViewById(R.id.message_et)
        messageRecycler = view.findViewById(R.id.message_recycler_view)
        messageList =
            mBundleRecyclerViewState?.getParcelableArrayList<Message>("CHATS") ?: messageList
        adapter = message_recycler_items(messageList)
        Log.e("FragmentOnCView", "$messageList")

        sendButton.setOnClickListener {
            var count = 0
            for (k in messageet.text.toString()) {
                if (k.isWhitespace())
                    count++
            }
            if (count == messageet.text.length) {

                Toast.makeText(context, "Пустая строка", Toast.LENGTH_SHORT).show()

            } else {
                mReference.push().setValue(
                    Message(
                        counter,
                        messageet.text.toString().trim(),
                        SimpleDateFormat("hh:mm:ss").format(Date()),
                        true
                    )
                )
                messageet.setText("")
            }
        }
        mReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                var msgs = arrayListOf<Message>()
                for (child in p0.children) {

                    val msgId = child.child("messageId").value.toString().toInt()
                    Log.e("dfgdfg", msgId.toString())
                    val msgText = child.child("messageText").value.toString()
                    val msgDate = child.child("sentDate").value.toString()
                    val msgUser = child.child("user").value.toString().toBoolean()
                    msgs.add(
                        (Message(msgId, msgText, msgDate, msgUser))
                    )

                }
                counter=msgs.size+1
                messageRecycler.layoutManager = lm
                messageRecycler.adapter = message_recycler_items(msgs)

                lm.scrollToPosition(message_recycler_items(msgs).itemCount - 1)
            }

        })
        return view
    }

    override fun onPause() {
        super.onPause()

        mBundleRecyclerViewState = Bundle()
        mBundleRecyclerViewState?.putParcelableArrayList(
            "CHATS",
            ArrayList<Parcelable>(messageList)
        )
    }

    override fun onResume() {
        super.onResume()

        if (mBundleRecyclerViewState != null) {
            messageList = mBundleRecyclerViewState?.getParcelableArrayList<Message>("CHATS")!!
            adapter.notifyItemInserted(messageList.size - 1)
            message_recycler_view.scrollToPosition(messageList.size - 1)
        }
    }

    companion object {
        private var mBundleRecyclerViewState: Bundle? = null

        @JvmStatic
        fun newInstance() = ChatFragment()
    }
}
