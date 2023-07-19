package com.example.gamenews.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.gamenews.Analytics
import com.example.gamenews.R
import com.example.gamenews.ui.components.HomeScreen
import com.example.gamenews.viewmodel.GameNewsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val gameNewsViewModel: GameNewsViewModel by viewModel()
    private val analytics: Analytics by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_home, container, false)

        binding.rootView.findViewById<ComposeView>(R.id.composeView).setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background,
            ) {
                HomeScreen(gameNewsViewModel)
                analytics.trackScreenView("HomeFragment")
            }
        }
        // throw RuntimeException("Test Crash") // Force a crash
        return binding.rootView
    }
}
