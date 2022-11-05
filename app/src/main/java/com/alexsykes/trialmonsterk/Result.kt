package com.alexsykes.trialmonsterk

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results")
class Result (
    @PrimaryKey val id: Int,
    @ColumnInfo(name="trialid") val trialid: Int,
    @ColumnInfo(name="rider") val rider: String,
    @ColumnInfo(name="course") val course: String,
    @ColumnInfo(name="name") val name: String,
    @ColumnInfo(name="class") val classs: String,
    @ColumnInfo(name="machine") val machine: String,
    @ColumnInfo(name="total") val total: Int,
    @ColumnInfo(name="cleans") val cleans: Int,
    @ColumnInfo(name="ones") val ones: Int,
    @ColumnInfo(name="twos") val twos: Int,
    @ColumnInfo(name="threes") val threes: Int,
    @ColumnInfo(name="fives") val fives: Int,
    @ColumnInfo(name="missed") val missed: Int,
    @ColumnInfo(name="dnf") val dnf: Int,
    @ColumnInfo(name="sectionscores") val sectionscores: String,
    @ColumnInfo(name="scores") val scores: String
)