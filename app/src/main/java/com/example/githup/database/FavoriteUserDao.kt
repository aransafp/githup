package com.example.githup.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.githup.models.User

@Dao
interface FavoriteUserDao {

    @Query("SELECT * from favorite_table")
    fun getAllFavoriteUser(): LiveData<List<User>>

    @Query("SELECT * from favorite_table where favorite_table.username = :username")
    suspend fun getUserByUsername(username: String): Boolean

    @Insert
    suspend fun insert(user: User)

    @Query("DELETE from favorite_table where favorite_table.username = :username")
    suspend fun delete(username: String): String
}