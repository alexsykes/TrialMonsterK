package com.alexsykes.trialmonsterk

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trials")
class Trial (
    @PrimaryKey val id: Int,
    @ColumnInfo(name="name") val name: String,
    @ColumnInfo(name="club") val club: String,
    @ColumnInfo(name="date") val date: String,
    @ColumnInfo(name="venue") val venue: String


    )