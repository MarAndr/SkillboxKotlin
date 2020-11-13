package module22

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.skillboxkotlin.R
import kotlinx.android.synthetic.main.dialog_addscore.view.*
import module22.extensions.withArguments

class AddScoreDialog: DialogFragment() {

    companion object{
        private const val INDEX_KEY = "index_key"

        fun newInstance(index: Int): AddScoreDialog{
            return AddScoreDialog().withArguments {
                putInt(INDEX_KEY, index)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_addscore, null)
        return AlertDialog.Builder(requireContext())
            .setTitle("Put your score!")
            .setView(view)
            .setPositiveButton("ok"){_,_ -> (parentFragment as GetMovieScore).getMovieScore(requireArguments().getInt(
                INDEX_KEY), view.et_dialogAddScore_source.text.toString(), view.et_dialogAddScore_value.text.toString())}
            .setNegativeButton("cancel"){_,_ -> }
            .create()
    }
}