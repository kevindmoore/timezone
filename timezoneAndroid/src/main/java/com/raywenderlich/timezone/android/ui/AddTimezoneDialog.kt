package com.raywenderlich.timezone.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.raywenderlich.timezone.TimeZoneHelper
import com.raywenderlich.timezone.TimeZoneHelperImpl
import com.raywenderlich.timezone.android.R
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import org.koin.java.KoinJavaComponent.get

fun isSelected(selectedStates: Map<Int, Boolean>, index: Int): Boolean {
    return (selectedStates.containsKey(index) && (true == selectedStates[index]))
}

const val SEARCH_DELAY_MILLIS = 30L

@Composable
fun AddTimeZoneDialog(
    timezoneHelper: TimeZoneHelper = get(TimeZoneHelper::class.java),
    onAdd: (List<String>) -> Unit,
    onDismiss: () -> Unit
) = Dialog(
    onDismissRequest = onDismiss
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        val timeZoneStrings by remember {
            mutableStateOf(
                timezoneHelper.getTimeZoneStrings().toList()
            )
        }
        val selectedStates = remember { SnapshotStateMap<Int, Boolean>() }
        val listState = rememberLazyListState()
        val searchValue = remember { mutableStateOf("") }
        val coroutineScope = rememberCoroutineScope()
        val focusRequester = remember { FocusRequester() }
//        val scope = rememberCoroutineScope()
//        var currentJob by remember { mutableStateOf<Job?>(null) }

        OutlinedTextField(
            singleLine = true,
            value = searchValue.value,
            modifier = Modifier.focusRequester(focusRequester),
            onValueChange = {
                searchValue.value = it
                if (searchValue.value.isEmpty()) {
                    return@OutlinedTextField
                }
//                currentJob?.cancel()
//                currentJob = scope.async {
//                    delay(SEARCH_DELAY_MILLIS)
                val index = searchZones(searchValue.value, timeZoneStrings = timeZoneStrings)
                if (index != -1) {
                    coroutineScope.launch {
                        listState.animateScrollToItem(index)
                    }
                }
//                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    searchValue.value = ""
                }) {
                    Icon(
                        Icons.Filled.Cancel,
                        contentDescription = "Cancel",
                    )
                }
            }
        )
        DisposableEffect(Unit) {
            focusRequester.requestFocus()
            onDispose { }
        }

        Spacer(modifier = Modifier.size(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentPadding = PaddingValues(16.dp),
            state = listState,

            ) {
            itemsIndexed(timeZoneStrings) { i, timezone ->
                Surface(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    color = if (isSelected(selectedStates, i)) Color.Gray else Color.White

                ) {
                    Row(
                        modifier = Modifier
                            .toggleable(
                                value = isSelected(selectedStates, i),
                                onValueChange = {
                                    selectedStates[i] = it
                                })
                            .fillMaxWidth(),
                    ) {
                        Text(timezone)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier.align(Alignment.End),
        ) {

            Button(
                onClick = {
                    onAdd(
                        getTimezones(
                            selectedStates = selectedStates,
                            timeZoneStrings = timeZoneStrings
                        )
                    )
                }
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                onClick = {
                    onAdd(
                        getTimezones(
                            selectedStates = selectedStates,
                            timeZoneStrings = timeZoneStrings
                        )
                    )
                }
            ) {
                Text(text = stringResource(id = R.string.add))
            }
        }
    }
}

fun searchZones(searchString: String, timeZoneStrings: List<String>): Int {
    var timezone = timeZoneStrings.firstOrNull { it.startsWith(searchString, ignoreCase = true) }
    if (timezone == null) {
        timezone = timeZoneStrings.firstOrNull { it.contains(searchString, ignoreCase = true) }
    }
    if (timezone != null) {
        return timeZoneStrings.indexOf(timezone)
    }
    return -1
}

fun getTimezones(selectedStates: Map<Int, Boolean>, timeZoneStrings: List<String>): List<String> {
    val timezoneIndexes = selectedStates.map { if (it.value) it.key else -1 }
    val timezones = mutableListOf<String>()
    timezoneIndexes.forEach { index ->
        if (index != -1) {
            timezones.add(timeZoneStrings[index])
        }
    }
    return timezones
}

@Preview
@Composable
fun PreviewDialog() {
    MaterialTheme {
        AddTimeZoneDialog(
            timezoneHelper = TimeZoneHelperImpl(),
            onAdd = {
            },
            onDismiss = {
            }
        )
    }
}