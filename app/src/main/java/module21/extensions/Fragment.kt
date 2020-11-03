package module21.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import module21.AutoClearedValue


fun<T:Fragment>T.withArguments(action: Bundle.() -> Unit): T{
    return apply {
        val args = Bundle().apply(action)
        arguments = args
    }
}

fun <T : Any> Fragment.autoCleared() =
    AutoClearedValue<T>(
        this
    )