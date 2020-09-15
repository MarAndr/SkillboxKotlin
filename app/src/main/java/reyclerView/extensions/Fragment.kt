package reyclerView.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import reyclerView.AutoClearedValue

fun <T: Fragment> T.withArguments(action: Bundle.() -> Unit): T {
    return apply {
        val args = Bundle().apply(action)
        arguments = args
    }
}

/**
 * Creates an [AutoClearedValue] associated with this fragment.
 */
fun <T : Any> Fragment.autoCleared() = AutoClearedValue<T>(this)