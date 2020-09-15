package reyclerView

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OffsetItemDecoration: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val offset = 10.fromDpToPixels(parent.context)
        with(outRect){
            left = offset
            right = offset
            bottom = offset
            top = offset
        }
    }
}