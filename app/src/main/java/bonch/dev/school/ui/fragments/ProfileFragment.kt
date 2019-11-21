package bonch.dev.school.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import bonch.dev.school.R
import bonch.dev.school.ui.activities.MainAppActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class ProfileFragment : Fragment() {
    private lateinit var mDatebase: FirebaseDatabase
    private lateinit var mReference: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mUser: FirebaseUser
    lateinit var email:String
    lateinit var name:String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDatebase = FirebaseDatabase.getInstance()

        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth.currentUser!!
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        mReference = mDatebase.reference.child("Users")
        val emailTV:TextView=view.findViewById(R.id.email_text_view)
        val nameET:EditText=view.findViewById(R.id.name_edit_text)
        mReference.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                email=p0.child(mUser.uid).child("email").value.toString()
                emailTV.text=email
                name=p0.child(mUser.uid).child("name").value.toString()
                nameET.setText(name)
            }

        })



        val changePasswordButton: Button = view.findViewById(R.id.change_password_button)
        changePasswordButton.setOnClickListener() {
            (activity as MainAppActivity).passFrag()
        }
        return view
    }
}