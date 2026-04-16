package com.csvapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VoterDatabaseHelper private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "voter_database.db"
        private const val DATABASE_VERSION = 1
        private const val ASSET_SQL_PATH = "data.sql"

        @Volatile
        private var INSTANCE: VoterDatabaseHelper? = null

        fun getInstance(context: Context): VoterDatabaseHelper {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: VoterDatabaseHelper(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

    private val appContext = context.applicationContext

    override fun onCreate(db: SQLiteDatabase) {
        val sql = appContext.assets.open(ASSET_SQL_PATH).bufferedReader().use { it.readText() }
        executeSqlScript(db, sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    private fun executeSqlScript(db: SQLiteDatabase, script: String) {
        script.lines()
            .filter { !it.trimStart().startsWith("--") }
            .joinToString("\n")
            .split(";")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .forEach { db.execSQL(it) }
    }
}
