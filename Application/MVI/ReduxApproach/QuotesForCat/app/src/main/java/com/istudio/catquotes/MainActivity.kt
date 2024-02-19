package com.istudio.catquotes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.istudio.catquotes.states.MainIntent
import com.istudio.catquotes.states.MainState
import com.istudio.catquotes.ui.theme.CatQuotesTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val onButtonClick :() -> Unit = {
            lifecycleScope.launch {
                viewModel.userIntent.send(MainIntent.FetchQuote)
            }
        }

        setContent {
            // View model reference
            val viewModel: MainViewModel = hiltViewModel()
            // View state reference from view model
            val state = viewModel.state

            CatQuotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(onButtonClick = onButtonClick)
                }
            }
        }
    }

    @Composable
    fun MainScreen(onButtonClick: () -> Unit) {

        // View model reference
        val viewModel: MainViewModel = hiltViewModel()
        // View state reference from view model
        val state = viewModel.state.value

        when (state) {
            is MainState.Idle -> IdleScreen(onButtonClick)
            is MainState.Loading -> LoadingScreen()
            is MainState.Success -> SuccessScreen(data = state.data)
            is MainState.Error -> {
                IdleScreen(onButtonClick)
                Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun IdleScreen(onButtonClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onButtonClick) {
            Text(text = "Fetch Quote")
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun SuccessScreen(data: String?) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        data?.let {
            Text(text = it)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(
    showSystemUi = true
)
@Composable
fun ScreenPreview() {
    CatQuotesTheme {
        LoadingScreen()
    }
}