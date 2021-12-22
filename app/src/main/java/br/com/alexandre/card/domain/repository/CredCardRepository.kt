package br.com.alexandre.card.domain.repository

import br.com.alexandre.card.data.CardDataBase
import br.com.alexandre.card.data.dto.CardDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CredCardRepository(private var mDb: CardDataBase) {

    suspend fun getCard() = withContext(Dispatchers.IO) {
        return@withContext mDb?.counterDao()?.selectAll()
    }


    suspend fun getCardById(number: String) = withContext(Dispatchers.IO) {
        return@withContext mDb?.counterDao()?.getById(number)
    }

    fun create(cards: CardDto) {
        mDb?.counterDao()?.insert(cards)
    }

    fun delete(number: String) {
        mDb?.counterDao()?.deleteById(number)
    }
}