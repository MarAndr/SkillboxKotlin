package module22

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.skillboxkotlin.R

class AddScoreDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_addscore, null)
        return AlertDialog.Builder(requireContext())
            .setTitle("Put your score!")
            .setView(view)
            .setPositiveButton("ok"){_,_ -> }
            .setNegativeButton("cancel"){_,_ -> }
            .create()
    }
}