package com.example.bip.data.db.dao

import androidx.room.*
import com.example.bip.data.entity.AuthEntity
import io.reactivex.Single

@Dao
interface AuthDao {
    @Query("SELECT * FROM auth")
    fun getAuth(): Single<AuthEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAuthData(authEntity: AuthEntity)

    @Query("DELETE FROM auth")
    fun delete(): Int

    @Query("SELECT token FROM auth")
    fun getToken(): String?

    @Transaction
    fun insertAndDeleteSynchronized(authEntity: AuthEntity) {
        delete()
        insertAuthData(authEntity)
    }

    fun insertAndDelete(authEntity: AuthEntity): Single<Boolean> {
        insertAndDeleteSynchronized(authEntity)
        return Single.just(true)
    }
}
