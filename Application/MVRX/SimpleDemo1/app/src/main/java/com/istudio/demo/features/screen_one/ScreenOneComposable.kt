package com.istudio.demo.features.screen_one

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.mvrx.compose.collectAsState

@Composable
fun ScreenOneComposable(
    viewModel: ScreenOneViewModel
) {

    val view by viewModel.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = view.counterOneInitialValue.toString())
            Button(onClick = { view.updateCounterOne() }) {
                Text(text = "INCREMENT")
            }
        }

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = view.counterTwoInitialValue.toString())
            Button(onClick = { view.updateCounterTwo() }) {
                Text(text = "INCREMENT")
            }
        }

    }


}