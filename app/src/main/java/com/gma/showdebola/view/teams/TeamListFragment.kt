package com.gma.showdebola.view.teams

import android.os.Handler
import android.os.Looper
import com.gma.showdebola.R
import com.gma.showdebola.databinding.FragmentTeamListBinding
import com.gma.showdebola.view.PatternFragment
import com.gma.showdebola.view.components.CustomSnackBar
import com.gma.showdebola.viewModel.TeamListViewModel
import kotlin.system.exitProcess

class TeamListFragment : PatternFragment<FragmentTeamListBinding, TeamListViewModel>(
    FragmentTeamListBinding::inflate,
    TeamListViewModel::class
) {
    override fun setupViews() {
        //TODO("Not yet implemented")
        setupBackPressedButton()
    }

    override fun setupObservers() {
        //TODO("Not yet implemented")
    }

    private fun setupBackPressedButton() {
        setBackPressedCallback {
            CustomSnackBar
                .make(view, R.string.message_close_app)
                .show()
            setBackPressedCallback {
                requireActivity().finishAffinity()
                exitProcess(0)
            }
            Handler(Looper.getMainLooper()).postDelayed({
                setupBackPressedButton()
            }, BACK_PRESSED_RESET_TIMEOUT)
        }
    }

    private companion object {
        const val BACK_PRESSED_RESET_TIMEOUT = 5000L
    }
}