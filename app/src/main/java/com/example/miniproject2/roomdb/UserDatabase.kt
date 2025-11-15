package com.example.miniproject2.roomdb

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(
    entities = [UserEntity::class],
    version = 1
)

abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

fun createUserDatabase(context: Context): UserDatabase {
    return Room.databaseBuilder(
        context,
        UserDatabase::class.java,
        "user_database"
    ).build()
}