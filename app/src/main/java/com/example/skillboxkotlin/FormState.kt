package com.example.skillboxkotlin

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

data class FormState(
    val valid: Boolean, val message: String)
: Parcelable {

    constructor(parcel: Parcel) : this(
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) parcel.readBoolean()
        else parcel.readString()!!.toBoolean(),
        parcel.readString().orEmpty()
    )

    fun withError(): FormState{
        return copy(false, "Fill out all the necessary fields, please!")
    }

    fun noError(): FormState{
        return copy(false, "")
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(valid)
        } else {
            parcel.writeString(valid.toString())
        }
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FormState> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): FormState {
            return FormState(parcel)
        }

        override fun newArray(size: Int): Array<FormState?> {
            return arrayOfNulls(size)
        }
    }
}


