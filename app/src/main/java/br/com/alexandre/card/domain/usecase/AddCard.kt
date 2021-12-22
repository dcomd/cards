package br.com.alexandre.card.domain.usecase

import br.com.alexandre.card.data.dto.CardDto
import br.com.alexandre.card.domain.repository.CredCardRepository


class AddCard(private val repository: CredCardRepository) :
    UseCase<CardDto, Unit> {

    override suspend fun invoke(params: CardDto) =
        repository.create(params)
}