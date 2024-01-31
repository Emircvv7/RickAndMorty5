package com.example.mycalck.di

import com.example.mycalck.characterdetail.CharacterDetailViewModel
import com.example.mycalck.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel{
        CharacterDetailViewModel(get())
    }
}
