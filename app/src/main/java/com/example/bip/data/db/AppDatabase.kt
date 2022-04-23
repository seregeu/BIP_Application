package com.example.bip.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bip.App
import com.example.bip.data.db.dao.AuthDao
import com.example.bip.data.db.dao.OrderDao
import com.example.bip.data.db.dao.UserDao
import com.example.bip.data.entity.AuthEntity
import com.example.bip.data.entity.OrderEntity
import com.example.bip.data.entity.UserDbEntity

@Database(
    entities = [
        AuthEntity::class,
        UserDbEntity::class,
        OrderEntity::class,
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun apiKeyDao(): AuthDao
    abstract fun userDao(): UserDao
    abstract fun orderDao(): OrderDao

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
