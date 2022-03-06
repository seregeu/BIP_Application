package com.example.bip.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bip.data.entity.AuthEntity
import io.reactivex.Completable

@Dao
interface AuthDao {
    @Query("SELECT token FROM auth")
    fun getToken(): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAuthData(authEntity: AuthEntity): Completable

    @Query("DELETE FROM auth")
    fun delete(): Completable
}
