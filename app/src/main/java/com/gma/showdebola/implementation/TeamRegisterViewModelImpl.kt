package com.gma.showdebola.implementation

import androidx.lifecycle.MutableLiveData
import com.gma.showdebola.viewModel.TeamRegisterViewModel

class TeamRegisterViewModelImpl : TeamRegisterViewModel() {
    override val loading = MutableLiveData<Boolean>()

}