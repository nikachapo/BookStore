package com.chapo.bookstore.features.savedbooks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chapo.bookstore.features.savedbooks.domain.ISavedBooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedBooksVM @Inject constructor(
    repository: ISavedBooksRepository
) : ViewModel() {

    val savedBooks = repository.getSavedBooks()

}