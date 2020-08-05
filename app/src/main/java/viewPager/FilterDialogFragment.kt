package viewPager

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class FilterDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val tags = makeTagStringList(ArticleFragment.tags)
        val selectedTags = ArrayList<String>()
        val choosedTags = requireArguments().getBooleanArray(CHOOSED_TAGS_KEY)

        val dialog = AlertDialog.Builder(context)
            .setTitle("Применить фильтр")
            .setMultiChoiceItems(tags, choosedTags) { _, which, isChecked ->
                if (isChecked) {
                    tags?.get(which)?.let { selectedTags.add(it) }
                } else if (selectedTags.contains(tags?.get(which))) {
                    selectedTags.removeAt(which)
                }
            }
            .setPositiveButton("Apply", null)
            .setNegativeButton("Cancel") { _, _ -> }
            .create()


        dialog.setOnShowListener {
            (it as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                if (selectedTags.isNotEmpty()) {
                    (parentFragment as CreateArticlesByTags).createArticlesByTags(selectedTags)
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



    companion object {

        const val CHOOSED_TAGS_KEY = "choosed_tags"

        fun newInstance(tags: List<Boolean>): FilterDialogFragment {
            return FilterDialogFragment().withArguments {
                putBooleanArray(CHOOSED_TAGS_KEY, tags.toBooleanArray())
            }
        }
    }

    private fun makeTagStringList(listOfTag: List<ArticleTag>): Array<String>? {
        val mutableList: ArrayList<String> = arrayListOf("")
        listOfTag.forEach {
            mutableList.add(it.name)
        }
        mutableList.remove("")
        return mutableList.toTypedArray()
    }


}