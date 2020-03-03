package fr.geraud.mycplusplusmovies.core

import androidx.annotation.UiThread
import androidx.lifecycle.MutableLiveData

class NonNullMutableLiveData<T> @UiThread constructor(initialValue: T) : MutableLiveData<T>() {
    init {
        value = initialValue
    }

    override fun getValue(): T {
        return super.getValue()!!
    }

}