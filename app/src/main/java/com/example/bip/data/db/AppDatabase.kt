package com.example.bip.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bip.App
import com.example.bip.data.db.dao.AuthDao
import com.example.bip.data.db.dao.UserDao
import com.example.bip.data.entity.AuthEntity
import com.example.bip.data.entity.UserDbEntity

@Database(
    entities = [
        AuthEntity::class,
        UserDbEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun apiKeyDao(): AuthDao
    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "PhotoMoney.db"

        val instance: AppDatabase by lazy {
            Room.databaseBuilder(
                App.appComponent.context(),
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
