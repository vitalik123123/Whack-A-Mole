package com.example.whack_a_mole.ui.game

import android.content.Context
import android.content.SharedPreferences
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.whack_a_mole.R
import com.example.whack_a_mole.databinding.FragmentGameBinding
import com.example.whack_a_mole.ui.main.MainActivity
import com.example.whack_a_mole.ui.result.ResultFragment
import kotlinx.coroutines.*

class GameFragment : Fragment(R.layout.fragment_game) {

    private val binding: FragmentGameBinding by viewBinding()
    private var timer: CountDownTimer? = null
    private lateinit var prefs: SharedPreferences
    private var countMoles: Int = 0
    private lateinit var soundPool: SoundPool
    private var soundId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = this.requireActivity().getPreferences(Context.MODE_PRIVATE)
        soundPool = SoundPool.Builder().setMaxStreams(10).build()
        soundId = soundPool.load(context, R.raw.kick_sound, 1)
        prefs.edit().putInt(MainActivity.PREF_CURR, countMoles).apply()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startTimer()
        replaceMole()
    }

    private fun replaceMole() {
        val randomBox = (1..9).shuffled().first()
        when (randomBox.toString()) {
            "1" -> {
                binding.box1.viewFlipperBox.showNext()
                lifecycleScope.launch {
                    delay(500)
                    binding.box1.viewFlipperBox.showNext()
                    replaceMole()
                }
                binding.box1.viewFlipperMoleElement.setOnClickListener {
                    countWhackMole()
                }
            }
            "2" -> {
                binding.box2.viewFlipperBox.showNext()
                lifecycleScope.launch {
                    delay(500)
                    binding.box2.viewFlipperBox.showNext()
                    replaceMole()
                }
                binding.box2.viewFlipperMoleElement.setOnClickListener {
                    countWhackMole()
                }
            }
            "3" -> {
                binding.box3.viewFlipperBox.showNext()
                lifecycleScope.launch {
                    delay(500)
                    binding.box3.viewFlipperBox.showNext()
                    replaceMole()
                }
                binding.box3.viewFlipperMoleElement.setOnClickListener {
                    countWhackMole()
                }
            }
            "4" -> {
                binding.box4.viewFlipperBox.showNext()
                lifecycleScope.launch {
                    delay(500)
                    binding.box4.viewFlipperBox.showNext()
                    replaceMole()
                }
                binding.box4.viewFlipperMoleElement.setOnClickListener {
                    countWhackMole()
                }
            }
            "5" -> {
                binding.box5.viewFlipperBox.showNext()
                lifecycleScope.launch {
                    delay(500)
                    binding.box5.viewFlipperBox.showNext()
                    replaceMole()
                }
                binding.box5.viewFlipperMoleElement.setOnClickListener {
                    countWhackMole()
                }
            }
            "6" -> {
                binding.box6.viewFlipperBox.showNext()
                lifecycleScope.launch {
                    delay(500)
                    binding.box6.viewFlipperBox.showNext()
                    replaceMole()
                }
                binding.box6.viewFlipperMoleElement.setOnClickListener {
                    countWhackMole()
                }
            }
            "7" -> {
                binding.box7.viewFlipperBox.showNext()
                lifecycleScope.launch {
                    delay(500)
                    binding.box7.viewFlipperBox.showNext()
                    replaceMole()
                }
                binding.box7.viewFlipperMoleElement.setOnClickListener {
                    countWhackMole()
                }
            }
            "8" -> {
                binding.box8.viewFlipperBox.showNext()
                lifecycleScope.launch {
                    delay(500)
                    binding.box8.viewFlipperBox.showNext()
                    replaceMole()
                }
                binding.box8.viewFlipperMoleElement.setOnClickListener {
                    countWhackMole()
                }
            }
            "9" -> {
                binding.box9.viewFlipperBox.showNext()
                lifecycleScope.launch {
                    delay(500)
                    binding.box9.viewFlipperBox.showNext()
                    replaceMole()
                }
                binding.box9.viewFlipperMoleElement.setOnClickListener {
                    countWhackMole()
                }
            }
        }
    }

    private fun countWhackMole() {
        soundId?.let { it1 -> soundPool.play(it1, 1F, 1F, 1, 0, 1F) }
        countMoles++
        prefs.edit().putInt(MainActivity.PREF_CURR, countMoles).apply()
        binding.startResult.text = " : $countMoles"
    }

    private fun startTimer() {
        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(p0: Long) {
                binding.startTimer.text = "00:${p0 / 1000}"
            }

            override fun onFinish() {
                requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(
                    android.R.anim.slide_in_left,
                    android.R.anim.fade_out,
                    android.R.anim.fade_in,
                    android.R.anim.slide_out_right
                )
                    .replace(R.id.root, ResultFragment.newInstance()).commit()
            }
        }.start()
    }

    companion object {
        @JvmStatic
        fun newInstance() = GameFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}