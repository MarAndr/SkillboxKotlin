package viewPager

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.skillboxkotlin.R

class FilterDialogFragment : DialogFragment() {

    companion object {

        const val CHOOSED_TAGS_KEY = "choosed_tags"

        fun newInstance(tags: BooleanArray): FilterDialogFragment {
            return FilterDialogFragment().withArguments {
                putBooleanArray(CHOOSED_TAGS_KEY, tags)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val choosedTags = requireArguments().getBooleanArray(CHOOSED_TAGS_KEY)!!
        val tagsArray = ArticleTag.getArrayTags()


        val dialog = AlertDialog.Builder(context)
            .setTitle(R.string.dialog_filter_message)
            .setMultiChoiceItems(tagsArray, choosedTags) { _, which, isChecked ->
                choosedTags[which] = isChecked
            }
            .setPositiveButton(R.string.dialog_filter_positive, null)
            .setNegativeButton(R.string.dialog_filter_negative) { _, _ -> }
            .create()

        dialog.setOnShowListener {
            (it as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                if (choosedTags.isNotEmpty()) {
                    (parentFragment as DialogResultListener).applyFilter(choosedTags)
                    dismiss()
                } else {
                    Toast.makeText(
                        context,
                        "Выберите интересуюущие теги или нажмите ОТМЕНА",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        return dialog
    }
}