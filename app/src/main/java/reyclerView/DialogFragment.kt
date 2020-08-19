package reyclerView

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.skillboxkotlin.R

class AddInfoDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context)
            .setTitle("Hello!")
            .setMessage("Add characteristic of new movie figure")
            .setView(R.layout.dialog_dialogfragment)
            .setPositiveButton("add"){_,_ -> (parentFragment as DialogButtonClick).onPositiveButtonClick()}
            .setNegativeButton("cancel"){_,_ -> (parentFragment as DialogButtonClick).onNegativeButtonClick()}
            .create()
    }
}