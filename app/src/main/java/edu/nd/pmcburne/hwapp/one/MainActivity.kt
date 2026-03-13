package edu.nd.pmcburne.hwapp.one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.nd.pmcburne.hwapp.one.ui.theme.HWStarterRepoTheme
import androidx.room.Room
import edu.nd.pmcburne.hwapp.one.database.AppDatabase
import androidx.lifecycle.ViewModelProvider
import edu.nd.pmcburne.hwapp.one.data.ScoresViewModel
import edu.nd.pmcburne.hwapp.one.data.ScoresViewModelFactory
import edu.nd.pmcburne.hwapp.one.ui.ScoresScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "scores_db"
        ).build()
        val viewModel = ViewModelProvider(
            this,
            ScoresViewModelFactory(db)
        )[ScoresViewModel::class.java]
        setContent {
            HWStarterRepoTheme {
                ScoresScreen(viewModel)
            }
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HWStarterRepoTheme {
        Greeting("Android")
    }
}