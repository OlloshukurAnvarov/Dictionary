package com.leaf.dictionary.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("dictionaries")
class Dictionary(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    @ColumnInfo("word_en")
    val wordEn : String,
    @ColumnInfo("word_uz")
    val wordUz : String
)