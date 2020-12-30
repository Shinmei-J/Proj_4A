package com.firstest.kotapp.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.firstest.kotapp.domain.entity.User

@Entity
data class Userlocal(
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String
){
    @PrimaryKey(autoGenerate = true) var uid: Int? = null
}

fun User.toData() : Userlocal {
    return Userlocal(
       email = email,
       password = password

    )
}
fun Userlocal.toEntity() : User {
    return User(
        email = email,
            password = password

    )
}