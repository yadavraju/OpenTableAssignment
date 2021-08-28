package com.example.openmoviereviews.base

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.openmoviereviews.util.ApiExceptionUtils.getExceptionMessage

open class BaseViewModel : ViewModel() {

  protected fun handleException(
      TAG: String,
      e: Throwable,
      loadingObservableField: ObservableField<Boolean>
  ) {
    Log.e(TAG, """${e.message}""")
    loadingObservableField.set(false)
    showExceptionMessage(getExceptionMessage(e))
  }

  open fun showExceptionMessage(message: String?) {}
}
