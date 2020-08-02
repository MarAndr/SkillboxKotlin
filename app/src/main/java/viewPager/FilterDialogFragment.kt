package viewPager

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class FilterDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val tags = makeTagStringList(ArticleFragment.tags)
        val selectedTags = ArrayList<String>()

        return AlertDialog.Builder(context)
            .setTitle("Применить фильтр")
            .setMultiChoiceItems(tags, null) { _, which, isChecked ->
                if (isChecked) {
                    tags?.get(which)?.let { selectedTags.add(it) }
                } else if (selectedTags.contains(tags?.get(which))) {
                    selectedTags.removeAt(which)
                }
            }
            .setPositiveButton("Apply") { dialog, _ ->
                if (selectedTags.isNotEmpty()) {
                    (parentFragment as OnTagsChoose).choosedTag(
                        selectedTags
                    )
                } else {
                    Toast.makeText(
                        context,
                        "Выберите интересуюущие теги или нажмите ОТМЕНА",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            .setNegativeButton("Cancel") { _, _ -> }
            .create()
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