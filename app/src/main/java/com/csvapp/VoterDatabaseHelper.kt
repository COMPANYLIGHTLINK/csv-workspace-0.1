package com.csvapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Environment
import java.io.File
import java.io.File

class VoterDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "voter_database.db"
        private const val DATABASE_VERSION = 1
        private const val ASSET_SQL_PATH = "data.sql"

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
        // If manual data.sql exists, execute it
        val manualFile = File(Environment.getExternalStorageDirectory(), "Android/data/com.csvapp/files/data.sql")
        if (manualFile.exists()) {
            val db = writableDatabase
            val sqlScript = manualFile.readText()
            executeSqlScript(db, sqlScript)
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.let {
            // Execute SQL from asset
            val assetSql = context.assets.open(ASSET_SQL_PATH).bufferedReader().use { it.readText() }
            executeSqlScript(it, assetSql)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Handle upgrades
    }

    private fun executeSqlScript(db: SQLiteDatabase, sqlScript: String) {
        val statements = sqlScript.split(";").map { it.trim() }.filter { it.isNotEmpty() }
        for (statement in statements) {
            db.execSQL(statement)
        }
    }
}