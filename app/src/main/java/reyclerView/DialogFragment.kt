package reyclerView

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.example.skillboxkotlin.R
import kotlinx.android.synthetic.main.dialog_dialogfragment.*
import kotlinx.android.synthetic.main.dialog_dialogfragment.view.*
import java.util.zip.Inflater

class AddInfoDialogFragment: DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = LayoutInflater.from(context).inflate(R.layout.dialog_dialogfragment, null)

        val dialog = AlertDialog.Builder(context)
            .setTitle("Hello!")
            .setMessage("Add characteristic of a new movie figure:")
            .setView(view)
            .setPositiveButton("add"){_,_ ->
                    (parentFragment as DialogButtonClick).onPositiveButtonClick(view.etName_dialogFragment.text.toString(),
                        view.etAge_dialogFragment.text.toString().toInt(), view.spinner.selectedItem.toString())
                    Toast.makeText(requireContext(), "fill required fields", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("cancel"){_,_ -> (parentFragment as DialogButtonClick).onNegativeButtonClick()}
            .create()

        view.etAge_dialogFragment.addTextChangedListener { dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = view.etAge_dialogFragment.text.isNotBlank() }

        dialog.setOnShowListener { (it as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false }

        return dialog
    }
}