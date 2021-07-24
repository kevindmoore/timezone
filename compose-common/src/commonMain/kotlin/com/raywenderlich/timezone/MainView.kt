package com.raywenderlich.timezone

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raywenderlich.timezone.ui.common.AppTheme

@Composable
fun MainView() {
  val selectedIndex = remember { mutableStateOf(0) }

  AppTheme {
    Scaffold(
      floatingActionButton = {
        if (selectedIndex.value == 0) {
          FloatingActionButton(
            modifier = Modifier
              .padding(16.dp),
            onClick = {
            }
          ) {
            Icon(
              imageVector = Icons.Default.Add,
              contentDescription = null
            )
          }
        }
      },
      ) {
      Text("Test")
    }
  }
}