package com.raywenderlich.timezone.ui.timezone

import AnimatedSwipeDismiss
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.raywenderlich.timezone.TimeZoneHelper
import kotlinx.coroutines.delay

@Composable
fun TimeZoneScreen(
  timezoneStrings: SnapshotStateList<String>,
  timezoneHelper: TimeZoneHelper
) {
  val listState = rememberLazyListState()
  Column(
    modifier = Modifier
      .fillMaxSize()
  ) {

    var time by remember { mutableStateOf(timezoneHelper.currentTime()) }
    LaunchedEffect(0) {
      while (true) {
        time = timezoneHelper.currentTime()
        delay(1000 * 60) // Every minute
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
      items(timezoneStrings) { timezoneString ->

        AnimatedSwipeDismiss(
          item = timezoneString,
          background = { _ ->
            Box(
              modifier = Modifier
                .fillMaxSize()
                .height(50.dp)
                .background(Color.Red)
                .padding(
                  start = 20.dp,
                  end = 20.dp
                )
            ) {
              val alpha = 1f
              Icon(
                Icons.Filled.Delete,
                contentDescription = "Delete",
                modifier = Modifier
                  .align(Alignment.CenterEnd),
                tint = Color.White.copy(alpha = alpha)
              )
            }
          },
          content = {
            Box(
              modifier = Modifier
                .fillMaxSize()
                .height(80.dp)
                .background(Color.White)
                .padding(8.dp)
            ) {
              Card(
                shape = RoundedCornerShape(12.dp),
                backgroundColor = Color.White,
                border = BorderStroke(1.dp, Color.Black),
                elevation = 4.dp,
                modifier = Modifier
                  .fillMaxWidth()
                  .height(60.dp)
                  .padding(8.dp)
              )
              {
                Box(modifier = Modifier.padding(8.dp)) {
                  Text(
                    text = timezoneString
                  )
                  Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = timezoneHelper.getTime(timezoneString)
                  )
                }
              }
            }
          },
          onDismiss = { zone ->
            println("Removing $zone")
            timezoneStrings.remove(zone)
            println("Timezone strings ${timezoneStrings.size}")
          }
        )
      }
    }
  }
}