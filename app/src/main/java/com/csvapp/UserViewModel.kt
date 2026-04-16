package com.csvapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: Flow<String> = _searchQuery

    val users: Flow<PagingData<User>> = _searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            repository.getUsersPaged(query)
        }
        .cachedIn(viewModelScope)

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser: Flow<User?> = _selectedUser

    fun loadUserDetails(srNo: Int) {
        viewModelScope.launch {
            _selectedUser.value = repository.getUserBySrNo(srNo)
        }
    }
}