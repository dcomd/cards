package br.com.alexandre.card.domain.usecase

import br.com.alexandre.card.data.dto.CardDto
import br.com.alexandre.card.domain.repository.CredCardRepository


class GetCardSelected(private val repository: CredCardRepository) :
    UseCase<String, CardDto> {

    override suspend fun invoke(params: String): CardDto  =
        repository.getCardById(params)
}