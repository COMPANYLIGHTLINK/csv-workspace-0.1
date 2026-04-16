package com.csvapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.File
import java.io.FileOutputStream

class VoterDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "data.db"
        private const val DATABASE_VERSION = 1
        private const val ASSET_DB_PATH = "data.db" // or data.sql if it's script

        @Volatile
        private var INSTANCE: VoterDatabaseHelper? = null

        fun getInstance(context: Context): VoterDatabaseHelper {
            return INSTANCE ?: synchronized(this) {
                val instance = VoterDatabaseHelper(context.applicationContext)
                INSTANCE = instance
                instance
            }
        }
    }

    init {
        // Check if db exists in internal storage, else copy from assets
        val dbFile = context.getDatabasePath(DATABASE_NAME)
        if (!dbFile.exists()) {
            copyDatabaseFromAssets(context)
        }
    }

    private fun copyDatabaseFromAssets(context: Context) {
        val assetManager = context.assets
        val outFile = context.getDatabasePath(DATABASE_NAME)

        try {
            val inputStream = assetManager.open(ASSET_DB_PATH)
            val outputStream = FileOutputStream(outFile)

            inputStream.copyTo(outputStream)
            inputStream.close()
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // If it's SQL script, execute here, but assuming pre-built db
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Handle upgrades if needed
    }
}