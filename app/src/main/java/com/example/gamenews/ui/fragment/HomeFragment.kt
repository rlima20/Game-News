package com.example.gamenews.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.gamenews.R
import com.example.gamenews.ui.components.GameNewsHomeScreen
import com.example.gamenews.viewmodel.GameNewsViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val gameNewsViewModel: GameNewsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_home, container, false)

        binding.rootView.findViewById<ComposeView>(R.id.composeView).setContent {
            GameNewsHomeScreen(gameNewsViewModel = gameNewsViewModel)
        }
        return binding.rootView
    }
}
