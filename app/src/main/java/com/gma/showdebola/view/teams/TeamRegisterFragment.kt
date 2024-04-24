package com.gma.showdebola.view.teams

import android.os.Handler
import android.os.Looper
import com.gma.showdebola.R
import com.gma.showdebola.databinding.FragmentTeamRegisterBinding
import com.gma.showdebola.view.PatternFragment
import com.gma.showdebola.view.components.CustomSnackBar
import com.gma.showdebola.viewModel.TeamRegisterViewModel
import kotlin.system.exitProcess

class TeamRegisterFragment(
    afterSaveCallback: (id: String) -> Unit
) : PatternFragment<FragmentTeamRegisterBinding, TeamRegisterViewModel>(
    FragmentTeamRegisterBinding::inflate,
    TeamRegisterViewModel::class
) {
    private val afterSaveCallback = afterSaveCallback

    override fun setupViews() {
        //TODO("Not yet implemented")
        binding.btnSaveTeam.setOnClickListener {
            afterSaveCallback(binding.edtTeamName.text.toString())
        }
    }

    override fun setupObservers() {
        //TODO("Not yet implemented")
    }
}