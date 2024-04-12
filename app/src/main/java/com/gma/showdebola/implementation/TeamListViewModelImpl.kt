package com.gma.showdebola.implementation

import androidx.lifecycle.MutableLiveData
import com.gma.showdebola.viewModel.TeamListViewModel

class TeamListViewModelImpl : TeamListViewModel() {
    override val loading = MutableLiveData<Boolean>()

}