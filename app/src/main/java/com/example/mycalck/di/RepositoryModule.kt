package com.example.mycalck.di

import com.example.mycalck.data.CharacterRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        CharacterRepository(get())
    }
}
