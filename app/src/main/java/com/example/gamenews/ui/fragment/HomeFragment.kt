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
import com.example.gamenews.R
import com.example.gamenews.ui.components.HomeScreen
import com.example.gamenews.viewmodel.GameNewsViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val gameNewsViewModel: GameNewsViewModel by viewModel()
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_home, container, false)

        binding.rootView.findViewById<ComposeView>(R.id.composeView).setContent {
            // Obtain the FirebaseAnalytics instance.
            firebaseAnalytics = Firebase.analytics

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background,
            ) {
                HomeScreen(gameNewsViewModel)

                firebaseAnalytics.logEvent(
                    FirebaseAnalytics.Event.SELECT_ITEM,
                    Bundle().apply {
                        putString(FirebaseAnalytics.Param.ITEM_ID, "HomeFragment")
                        putString(FirebaseAnalytics.Param.ITEM_NAME, "HomeFragment")
                        putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fragment")
                    }
                )

                firebaseAnalytics.logEvent(
                    FirebaseAnalytics.Event.SCREEN_VIEW,
                    Bundle().apply {
                        putString(
                            FirebaseAnalytics.Param.SCREEN_NAME,
                            "HomeFragment"
                        )
                        putString(
                            FirebaseAnalytics.Param.SCREEN_CLASS,
                            "HomeFragment"
                        )
                    }
                )
            }
        }
        // throw RuntimeException("Test Crash") // Force a crash
        return binding.rootView
    }
}
