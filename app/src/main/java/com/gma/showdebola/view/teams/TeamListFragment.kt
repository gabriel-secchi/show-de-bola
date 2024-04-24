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
        setupBackPressedButton()
        setupTeamRegister()
    }

    override fun setupObservers() {
        //TODO("Not yet implemented")
    }

    private fun setupTeamRegister() {
        binding.btnCreateTeam.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(binding.teamRegisterCard.id, TeamRegisterFragment(::onCreateTeam))
                .commit()
        }
    }

    private fun onCreateTeam(id: String) {
        childFragmentManager.let { fragmentManager ->
            fragmentManager.findFragmentById(binding.teamRegisterCard.id)?.let { fragment ->
                fragmentManager.beginTransaction()
                    .remove(fragment)
                    .commitNow()
            }
        }

        CustomSnackBar
            .make(view, "Time Criado -> $id")
            .show()
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