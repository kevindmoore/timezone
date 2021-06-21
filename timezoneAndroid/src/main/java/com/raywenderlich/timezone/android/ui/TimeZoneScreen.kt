package com.raywenderlich.timezone.android.ui

import android.text.Layout
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.raywenderlich.timezone.TimeZoneHelper
import kotlinx.coroutines.delay
import org.koin.java.KoinJavaComponent

@Composable
fun TimeZoneScreen(
    timezoneStrings: List<String>
) {
    val timezoneHelper: TimeZoneHelper = KoinJavaComponent.get(TimeZoneHelper::class.java)
    val listState = rememberLazyListState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var time by remember { mutableStateOf(timezoneHelper.currentTime()) } // 2
        LaunchedEffect(0) {
            while (true) {
                time = timezoneHelper.currentTime()
                delay(1000*60) // Every minute
            }
        }
        Text(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            text = time,
            style = MaterialTheme.typography.h2.copy(textAlign = TextAlign.Center)
        )
        Spacer(modifier = Modifier.size(16.dp))
        LazyColumn(
            state = listState,
        ) {
            itemsIndexed(timezoneStrings) { i, timezoneString ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),

                    ) {
                    Text(
                        text = timezoneString
                    )
                    Text(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        text = timezoneHelper.getTime(timezoneString)
                    )
                }
            }
        }
    }
}