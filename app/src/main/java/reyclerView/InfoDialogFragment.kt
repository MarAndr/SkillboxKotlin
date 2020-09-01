package reyclerView

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.example.skillboxkotlin.R
import kotlinx.android.synthetic.main.dialog_dialogfragment.*
import kotlinx.android.synthetic.main.dialog_dialogfragment.view.*
import java.util.zip.Inflater

class InfoDialogFragment: DialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = LayoutInflater.from(context).inflate(R.layout.dialog_dialogfragment, null)

        val dialog = AlertDialog.Builder(context)
            .setTitle("Add characteristic of a new movie figure:")
            .setView(view)
            .setPositiveButton("add"){_,_ ->

                val age = view.etAge_dialogFragment.text.toString().toIntOrNull()?:0
                var name = view.etName_dialogFragment.text.toString()
                if (name == "") name = "Anonym"
                val profession = view.spinner.selectedItem.toString()
                val isAward = view.switchAcademyAward_dialogFragment.isChecked
                    (parentFragment as DialogButtonClick).onPositiveButtonClick(name,
                        age, profession, isAward)
            }
            .setNegativeButton("cancel"){_,_ -> }
            .create()

        view.etAge_dialogFragment.addTextChangedListener { dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = view.etAge_dialogFragment.text.isNotBlank() }
        dialog.setOnShowListener { (it as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false }

        return dialog


    }


}