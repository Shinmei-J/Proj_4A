package com.firstest.kotapp.injection

import android.content.Context
import androidx.room.Room
import com.firstest.kotapp.data.Repository.UserRepository
import com.firstest.kotapp.data.local.Database.AppDatabase
import com.firstest.kotapp.data.local.Database.DatabaseDao
import com.firstest.kotapp.domain.UseCase.CreateUserUseCase
import com.firstest.kotapp.domain.UseCase.GetUserUseCase
import com.firstest.kotapp.presentation.List.ListViewModel
import com.firstest.kotapp.presentation.main.ChangeAccountViewModel
import com.firstest.kotapp.presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val presentationModule = module {
    factory { MainViewModel(get(),get()) }
    factory { ChangeAccountViewModel(get()) }
    factory { ListViewModel() }
}
val domainModule = module {
    factory { CreateUserUseCase(get()) }
    factory { GetUserUseCase(get()) }
}

val dataModule = module {
    single { UserRepository(get()) }
    single { createDataBase(androidContext()) }
}

fun createDataBase(context: Context): DatabaseDao {
    val AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()
    return AppDatabase.databaseDao()
}


