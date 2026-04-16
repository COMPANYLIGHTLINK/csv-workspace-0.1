package com.csvapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val SR_NO: Int,
    val EPIC_Number: String,
    val Voter_Name: String,
    val Relative_Name: String,
    val Relation: String,
    val House_Number: String,
    val Age: Int,
    val Gender: String
)