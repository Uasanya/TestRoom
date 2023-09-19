package com.example.testroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [WordEntity::class])
abstract class MainDB : RoomDatabase(){

    abstract fun getDao(): MyDao

    companion object{
        fun getDb(context: Context): MainDB{
            return Room.databaseBuilder(
                context.applicationContext,
                MainDB::class.java,
                "db").build()
        }
    }
}