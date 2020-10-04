package module17

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class Person(
  val id: Long,
  val name: String,
  val avatarLink: String,
  val age: Int,
  val isDeveloper: Boolean
)