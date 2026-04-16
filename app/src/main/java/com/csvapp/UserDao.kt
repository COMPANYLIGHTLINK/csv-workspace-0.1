package com.csvapp

import android.database.Cursor
import androidx.paging.PagingSource
import androidx.paging.PagingState

class UserDao(private val dbHelper: VoterDatabaseHelper) {

    fun getUsersPaged(query: String): PagingSource<Int, User> {
        return UserPagingSource(dbHelper, query)
    }

    suspend fun getUserBySrNo(srNo: Int): User? {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM users WHERE SR_NO = ?", arrayOf(srNo.toString()))
        return cursor.use {
            if (it.moveToFirst()) {
                cursorToUser(it)
            } else null
        }
    }

    private fun cursorToUser(cursor: Cursor): User {
        return User(
            SR_NO = cursor.getInt(cursor.getColumnIndexOrThrow("SR_NO")),
            EPIC_Number = cursor.getString(cursor.getColumnIndexOrThrow("EPIC_Number")),
            Voter_Name = cursor.getString(cursor.getColumnIndexOrThrow("Voter_Name")),
            Relative_Name = cursor.getString(cursor.getColumnIndexOrThrow("Relative_Name")),
            Relation = cursor.getString(cursor.getColumnIndexOrThrow("Relation")),
            House_Number = cursor.getString(cursor.getColumnIndexOrThrow("House_Number")),
            Age = cursor.getInt(cursor.getColumnIndexOrThrow("Age")),
            Gender = cursor.getString(cursor.getColumnIndexOrThrow("Gender"))
        )
    }
}

class UserPagingSource(private val dbHelper: VoterDatabaseHelper, private val query: String) : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: 0
        val pageSize = params.loadSize
        val offset = page * pageSize

        val db = dbHelper.readableDatabase
        val sql = "SELECT * FROM users WHERE EPIC_Number LIKE '%' || ? || '%' OR Voter_Name LIKE '%' || ? || '%' ORDER BY SR_NO LIMIT ? OFFSET ?"
        val cursor = db.rawQuery(sql, arrayOf(query, query, pageSize.toString(), offset.toString()))

        val users = mutableListOf<User>()
        cursor.use {
            while (it.moveToNext()) {
                users.add(cursorToUser(it))
            }
        }

        val nextKey = if (users.size == pageSize) page + 1 else null
        val prevKey = if (page > 0) page - 1 else null

        return LoadResult.Page(
            data = users,
            prevKey = prevKey,
            nextKey = nextKey
        )
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private fun cursorToUser(cursor: Cursor): User {
        return User(
            SR_NO = cursor.getInt(cursor.getColumnIndexOrThrow("SR_NO")),
            EPIC_Number = cursor.getString(cursor.getColumnIndexOrThrow("EPIC_Number")),
            Voter_Name = cursor.getString(cursor.getColumnIndexOrThrow("Voter_Name")),
            Relative_Name = cursor.getString(cursor.getColumnIndexOrThrow("Relative_Name")),
            Relation = cursor.getString(cursor.getColumnIndexOrThrow("Relation")),
            House_Number = cursor.getString(cursor.getColumnIndexOrThrow("House_Number")),
            Age = cursor.getInt(cursor.getColumnIndexOrThrow("Age")),
            Gender = cursor.getString(cursor.getColumnIndexOrThrow("Gender"))
        )
    }
}