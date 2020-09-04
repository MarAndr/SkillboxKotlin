package reyclerView

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.example.skillboxkotlin.R
import kotlinx.android.synthetic.main.add_director.view.*
import java.util.zip.Inflater

class InfoDialogFragment : DialogFragment() {

    companion object {
        private const val FIGURE_NUMBER = "figure number"

        fun newInstance(figureNumber: Int): InfoDialogFragment {
            return InfoDialogFragment().withArguments {
                putInt(FIGURE_NUMBER, figureNumber)
            }
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val number = requireArguments().getInt(FIGURE_NUMBER)
        return when (MovieFigureEnum.values()[number]) {
            MovieFigureEnum.ACTOR -> onCreateActorDialog()
            MovieFigureEnum.FILM_DIRECTOR -> onCreateFilmDirectorDialog()
        }


    }

    private fun onCreateActorDialog(): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.add_actor, null)

        val dialog = AlertDialog.Builder(context)
            .setTitle("Add characteristic of a new movie figure:")
            .setView(view)
            .setPositiveButton("add") { _, _ ->
                val age = view.etAge_dialogFragment.text.toString().toIntOrNull() ?: 0
                var name = view.etName_dialogFragment.text.toString()
                if (name == "") name = "Anonym"
                val isAward = view.switchAcademyAward_dialogFragment.isChecked
                val actor = MovieFigure.Actor(name, age, "", isAward)
                (parentFragment as DialogButtonClick).onPositiveButtonClick(actor)
            }
            .setNegativeButton("cancel") { _, _ -> }
            .create()

        view.etAge_dialogFragment.addTextChangedListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                view.etAge_dialogFragment.text.isNotBlank()
        }
        dialog.setOnShowListener {
            (it as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false
        }

        return dialog
    }

    private fun onCreateFilmDirectorDialog(): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.add_director, null)

        val dialog = AlertDialog.Builder(context)
            .setTitle("Add characteristic of a new movie figure:")
            .setView(view)
            .setPositiveButton("add") { _, _ ->
                val age = view.etAge_dialogFragment.text.toString().toIntOrNull() ?: 0
                var name = view.etName_dialogFragment.text.toString()
                if (name == "") name = "Anonym"
                val isAward = view.switchAcademyAward_dialogFragment.isChecked
                val genres = view.spinnerGenre.selectedItem.toString()
                val director = MovieFigure.FilmDirector(name, age, "", genres, isAward)
                (parentFragment as DialogButtonClick).onPositiveButtonClick(director)
            }
            .setNegativeButton("cancel") { _, _ -> }
            .create()

        view.etAge_dialogFragment.addTextChangedListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                view.etAge_dialogFragment.text.isNotBlank()
        }
        dialog.setOnShowListener {
            (it as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false
        }

        return dialog
    }


}