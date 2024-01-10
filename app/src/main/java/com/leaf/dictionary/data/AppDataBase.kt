package com.leaf.dictionary.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leaf.dictionary.App
import com.leaf.dictionary.data.dao.DictDao
import com.leaf.dictionary.data.models.Dictionary

@Database(entities = [Dictionary::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun dicDao(): DictDao

    companion object {
        private lateinit var appDataBase: AppDataBase

        fun getData(context: Context) : AppDataBase {
            if (!Companion::appDataBase.isInitialized)
                appDataBase = Room.databaseBuilder(context, AppDataBase::class.java, "dictionary")
                    //.createFromAsset("")
                    .build()
            return appDataBase
        }
    }
}