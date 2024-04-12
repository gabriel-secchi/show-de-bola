package com.gma.showdebola.viewModel

import androidx.lifecycle.LiveData

interface ExtendViewModel {
    val loading: LiveData<Boolean>
}