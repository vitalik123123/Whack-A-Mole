package com.example.whack_a_mole.ui.result

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.whack_a_mole.R
import com.example.whack_a_mole.databinding.FragmentResultBinding
import com.example.whack_a_mole.ui.game.GameFragment
import com.example.whack_a_mole.ui.main.MainActivity
import com.example.whack_a_mole.ui.start.StartFragment


class ResultFragment : Fragment(R.layout.fragment_result) {

    private val binding: FragmentResultBinding by viewBinding()
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = this.requireActivity().getPreferences(Context.MODE_PRIVATE)
        if (prefs.getInt(MainActivity.PREF_CURR, 0) > prefs.getInt(MainActivity.PREF_RECORD, 0)){
             prefs.edit().putInt(MainActivity.PREF_RECORD, prefs.getInt(MainActivity.PREF_CURR, 0)).apply()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resultLast.text = "Last result : ${prefs.getInt(MainActivity.PREF_CURR, 0)}"

        binding.resultTextRecord.text = "Your record: ${prefs.getInt(MainActivity.PREF_RECORD, 0)}"

        binding.resultButtonPlayAgain.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.slide_out_right
            )
                .replace(R.id.root, GameFragment.newInstance()).commit()
        }

        binding.resultButtonMenu.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.slide_out_right
            )
                .replace(R.id.root, StartFragment.newInstance()).commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ResultFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}