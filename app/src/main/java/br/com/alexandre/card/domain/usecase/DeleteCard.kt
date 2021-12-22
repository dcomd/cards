package br.com.alexandre.card.domain.usecase

import br.com.alexandre.card.domain.repository.CredCardRepository


class DeleteCard(private val repository: CredCardRepository) :
    UseCase<String, Unit> {

    override suspend fun invoke(params: String) =
        repository.delete(params)
}