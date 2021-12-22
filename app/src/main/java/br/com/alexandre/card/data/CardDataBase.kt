package br.com.alexandre.card.data

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.alexandre.card.data.dto.CardDto


@Database(entities = [CardDto::class], version = 1)
abstract class CardDataBase : RoomDatabase() {
    abstract fun counterDao(): CardDao

}