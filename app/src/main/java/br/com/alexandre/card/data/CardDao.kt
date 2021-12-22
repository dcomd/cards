package br.com.alexandre.card.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.alexandre.card.data.dto.CardDto


@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(counter: CardDto)

    @Query("SELECT * FROM CardTb")
    fun selectAll(): MutableList<CardDto>

    @Query("DELETE FROM CardTb WHERE number = :number")
    fun deleteById(number : String)

    @Query("SELECT * FROM CardTb WHERE number = :number")
    fun getById(number : String) : CardDto
}

