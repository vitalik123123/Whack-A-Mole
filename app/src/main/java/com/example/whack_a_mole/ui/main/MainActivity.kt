package com.example.whack_a_mole.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.whack_a_mole.R
import com.example.whack_a_mole.databinding.ActivityMainBinding
import com.example.whack_a_mole.ui.start.StartFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.root, StartFragment.newInstance())
                .commit()
        }
    }

    companion object {
        const val PREF_RECORD = "Record"
        const val PREF_CURR = "Curr"
    }
}