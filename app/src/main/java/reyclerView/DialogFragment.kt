package reyclerView

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.skillboxkotlin.R
import kotlinx.android.synthetic.main.dialog_dialogfragment.*
import kotlinx.android.synthetic.main.dialog_dialogfragment.view.*
import java.util.zip.Inflater

class AddInfoDialogFragment: DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = LayoutInflater.from(context).inflate(R.layout.dialog_dialogfragment, null)
        val selectedProfession = view.spinner.selectedItem.toString()

        return AlertDialog.Builder(context)
            .setTitle("Hello!")
            .setMessage("Add characteristic of a new movie figure:")
            .setView(view)
            .setPositiveButton("add"){_,_ ->
                (parentFragment as DialogButtonClick).onPositiveButtonClick(view.etName_dialogFragment.text.toString(),
                    view.etAge_dialogFragment.text.toString().toInt(), selectedProfession)
                    Log.d("dialog", "selectedProfession = $selectedProfession")
            }
            .setNegativeButton("cancel"){_,_ -> (parentFragment as DialogButtonClick).onNegativeButtonClick()}
            .create()
    }
}