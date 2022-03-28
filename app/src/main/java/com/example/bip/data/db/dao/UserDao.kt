package com.example.bip.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bip.data.entity.UserDbEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUser(): Single<UserDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserDbEntity): Completable
}
