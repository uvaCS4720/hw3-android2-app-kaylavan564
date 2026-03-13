package edu.nd.pmcburne.hwapp.one.data
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.nd.pmcburne.hwapp.one.database.AppDatabase

class ScoresViewModelFactory (
    private val db: AppDatabase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return ScoresViewModel(db) as T
    }
}