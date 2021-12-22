package br.com.alexandre.card.di

import android.app.Application
import androidx.room.Room
import br.com.alexandre.card.data.CardDao
import br.com.alexandre.card.data.CardDataBase
import br.com.alexandre.card.domain.repository.CredCardRepository
import br.com.alexandre.card.domain.usecase.*
import br.com.alexandre.card.presentation.CardViewModel
import br.com.alexandre.card.presentation.CardViewModelImp
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { CredCardRepository(get()) }
    factory { AddCard(get()) }
    factory { DeleteCard(get()) }
    factory { GetCard(get()) }
    factory { GetCardSelected(get()) }
    viewModel<CardViewModel> { CardViewModelImp(get(),get(),get(),get()) }

}

val databaseModule = module {

    fun provideDatabase(application: Application): CardDataBase {
        return Room.databaseBuilder(application, CardDataBase::class.java, "counters")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: CardDataBase): CardDao {
        return  database.counterDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}