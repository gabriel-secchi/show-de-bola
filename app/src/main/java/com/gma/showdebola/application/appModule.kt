package com.gma.showdebola.application

import com.gma.showdebola.implementation.TeamListViewModelImpl
import com.gma.showdebola.implementation.TeamRegisterViewModelImpl
import com.gma.showdebola.viewModel.TeamListViewModel
import com.gma.showdebola.viewModel.TeamRegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<TeamListViewModel> {
        TeamListViewModelImpl()
    }

    viewModel<TeamRegisterViewModel> {
        TeamRegisterViewModelImpl()
    }

}