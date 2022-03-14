package com.example.bip.data.db.dao

import androidx.room.*
import com.example.bip.data.entity.AuthEntity
import io.reactivex.Completable

@Dao
interface AuthDao {
    @Query("SELECT token FROM auth")
    fun getToken(): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAuthData(authEntity: AuthEntity)

    @Query("DELETE FROM auth")
    fun delete(): Int

    @Transaction
    fun insertAndDeleteSynchronized(authEntity: AuthEntity) {
        delete()
        insertAuthData(authEntity)
    }

    fun insertAndDelete(authEntity: AuthEntity): Completable {
        insertAndDeleteSynchronized(authEntity)
        return Completable.complete()
    }
}
