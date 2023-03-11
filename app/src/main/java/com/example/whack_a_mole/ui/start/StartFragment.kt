package com.example.whack_a_mole.ui.start

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.whack_a_mole.R
import com.example.whack_a_mole.databinding.FragmentStartBinding
import com.example.whack_a_mole.ui.game.GameFragment
import com.example.whack_a_mole.ui.main.MainActivity


class StartFragment : Fragment(R.layout.fragment_start) {

    private val binding: FragmentStartBinding by viewBinding()
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = this.requireActivity().getPreferences(Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startTextRecord.text = "Your record: ${prefs.getInt(MainActivity.PREF_RECORD, 0)}"

        binding.startButtonPlay.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.slide_out_right
            )
                .replace(R.id.root, GameFragment.newInstance()).commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = StartFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}