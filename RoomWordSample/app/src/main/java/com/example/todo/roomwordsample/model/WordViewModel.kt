package com.example.todo.roomwordsample.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.roomwordsample.database.Word
import com.example.todo.roomwordsample.database.WordRepository
import com.example.todo.roomwordsample.database.WordRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WordRepository
    val allWords: LiveData<List<Word>>

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}