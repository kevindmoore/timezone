package com.raywenderlich.timezone.android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raywenderlich.timezone.TimeZoneHelper
import org.koin.java.KoinJavaComponent

@Composable
fun TimeZoneCalculator(
    timezoneStrings: List<String>
) {
    val listState = rememberLazyListState()
    // 8am
    val startTime = remember {
        mutableStateOf(8)
    }
    // 5pm
    val endTime = remember {
        mutableStateOf(17)
    }
    val selectedTimeZones = remember {
        val selected = SnapshotStateMap<Int, Boolean>()
        for (i in 0..timezoneStrings.size-1) selected[i] = true
        selected
    }
    val timezoneHelper: TimeZoneHelper = KoinJavaComponent.get(TimeZoneHelper::class.java)
    val showAddDialog = remember { mutableStateOf(false) }
    val meetingHours = remember { SnapshotStateList<Int>() }

    if (showAddDialog.value) {
        MeetingDialog(
            hours = meetingHours,
            onDismiss = {
                showAddDialog.value = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            text = "Find Mtg",

            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            text = "Time Range",
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp)

        ) {
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = "Start",
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.size(16.dp))
            TimePicker(startTime)
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = "End",
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.size(16.dp))
            TimePicker(endTime)
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp)

        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                text = "TimeZones",
                style = MaterialTheme.typography.h6
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp)

        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentPadding = PaddingValues(16.dp),
                state = listState,

                ) {
                itemsIndexed(timezoneStrings) { i, timezone ->
                    Surface(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),

                        ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                        ) {
                            Checkbox(checked = isSelected(selectedTimeZones, i),
                                onCheckedChange = {
                                    selectedTimeZones[i] = it
                                })
                            Text(timezone)
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(start = 4.dp, end = 4.dp)

        ) {
            OutlinedButton(onClick = {
                meetingHours.clear()
                meetingHours.addAll(timezoneHelper.search(startTime.value, endTime.value, getSelectedTimeZones(timezoneStrings, selectedTimeZones)))
                showAddDialog.value = true
            }) {
                Text("Search")
            }
        }
    }
}

fun getSelectedTimeZones(timezoneStrings: List<String>, selectedStates: Map<Int, Boolean>): List<String> {
    val selectedTimezones = mutableListOf<String>()
    selectedStates.keys.map {
        if (isSelected(selectedStates, it) ) {
            selectedTimezones.add(timezoneStrings[it])
        }
    }
    return selectedTimezones
}


@Composable
fun TimePicker(hours: MutableState<Int>) {
    timeBoxPicker(hours)
}



@Composable
fun timeBoxPicker(hours: MutableState<Int>) {
    NumberPicker(state = hours, range = IntRange(0, 24),
        onStateChanged = {
            hours.value = it
        })
}