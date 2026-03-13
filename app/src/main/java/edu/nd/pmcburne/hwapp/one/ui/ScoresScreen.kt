package edu.nd.pmcburne.hwapp.one.ui
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import edu.nd.pmcburne.hwapp.one.data.ScoresViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import java.time.Instant
import java.time.ZoneId



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoresScreen (viewModel: ScoresViewModel){
    LaunchedEffect(viewModel.selectedGender,viewModel.selectedDate) {
        viewModel.loadScores()
    }
    var showDatePicker by remember { mutableStateOf(false)}
    if (showDatePicker){
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = {showDatePicker = false},
            confirmButton = {
                TextButton(onClick = {
                    val millis = datePickerState.selectedDateMillis
                    if (millis != null){
                        viewModel.selectedDate = Instant.ofEpochMilli(millis).atZone(ZoneId.of("UTC")).toLocalDate()
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick={showDatePicker = false}){
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 40.dp, start = 16.dp, end =16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Text("Basketball Scores")
        Text("Date: ${viewModel.selectedDate.format(DateTimeFormatter.ISO_LOCAL_DATE)}")
        Button(onClick = {showDatePicker = true}){
            Text("Select Date")
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)){
            OutlinedButton(
                onClick = {viewModel.selectedGender = "men"},
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (viewModel.selectedGender == "men")
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                    else
                        MaterialTheme.colorScheme.surface
                )
            ){
                Text("Men")
            }
            OutlinedButton(
                onClick = {viewModel.selectedGender = "women"},
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (viewModel.selectedGender == "women")
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                    else
                        MaterialTheme.colorScheme.surface
                )
            ){
                Text("Women")
            }
        }
        Text("Showing: ${viewModel.selectedGender}")
        Button(onClick = {viewModel.loadScores()}){
            Text("Refresh")
        }
        if (viewModel.isLoading){
            CircularProgressIndicator()
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.games){ game ->
                Card {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ){
                        Text("Away: ${game.awayTeam}")
                        Text("Home: ${game.homeTeam}")
                        Text(
                            when (game.status.lowercase()) {
                                "final" -> "Status: Final"
                                "live" -> "Status: Live"
                                "pre" -> "Status: Upcoming"
                                else -> "Status: ${game.status}"
                            }
                        )
                        if(game.status.lowercase() == "pre"){
                            Text("Starts at: ${game.startTime ?: "-"}")
                        }else{
                            Text("Score: ${game.awayScore ?: "-"} - ${game.homeScore ?: "-"}")
                        }
                        if(game.status.lowercase() == "final" && game.winner != null){
                            Text("Winner: ${game.winner}")
                        }
                        if (game.status.lowercase() == "live"){
                            Text("Period: ${game.period ?: "-"} Time: ${game.clock ?: "-"}")
                        }
                    }
                }
            }
        }
    }
}