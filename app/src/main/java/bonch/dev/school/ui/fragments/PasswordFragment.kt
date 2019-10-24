package bonch.dev.school.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import bonch.dev.school.R

class PasswordFragment:DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder=AlertDialog.Builder(activity)

        val inflater= activity?.layoutInflater?.inflate(R.layout.fragment_password,null)
       /* val oldPassword:EditText=view.findViewById(R.id.old_password_edit_text)
        val newPassword:EditText=view.findViewById(R.id.new_password_edit_text)
        val confirmPass:EditText=view.findViewById(R.id.confirm_password_edit_text)
        val confirmButton:Button=view.findViewById(R.id.change_password_button)*/
        builder.setView(inflater)
        return builder.create()
    }
}