package com.example.githup.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class UserResponse(
    val items: ArrayList<User>
)

@Entity(tableName = "favorite_table")
data class User(

    @PrimaryKey
    @ColumnInfo(name = "username")
    @SerializedName("login")
    val username: String,

    @SerializedName("avatar_url")
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String
)

