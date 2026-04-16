package com.csvapp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    fun getUsersPaged(query: String): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { userDao.getUsersPaged(query) }
        ).flow
    }

    suspend fun getUserBySrNo(srNo: Int): User? {
        return userDao.getUserBySrNo(srNo)
    }
}