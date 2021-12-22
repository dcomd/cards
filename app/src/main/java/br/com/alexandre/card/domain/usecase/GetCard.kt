package br.com.alexandre.card.domain.usecase

import br.com.alexandre.card.data.dto.CardDto
import br.com.alexandre.card.domain.repository.CredCardRepository


class GetCard(private val repository: CredCardRepository) :
    UseCase<String, List<CardDto>> {

    override suspend fun invoke(params: String): List<CardDto>  =
        repository.getCard()
}