package com.alexsykes.trialmonsterk

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trials")
class Trial(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "club") val club: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "venue") val venue: String,
    @ColumnInfo(name = "classlist") val classlist: String,
    @ColumnInfo(name = "courselist") val courselist: String,
    @ColumnInfo(name = "formatted_date") val formatted_date: String,
    @ColumnInfo(name="updated") val updated: String
    )