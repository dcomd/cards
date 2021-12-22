package br.com.alexandre.card.domain.usecase

interface UseCase<ParamsType,ReturnType> {
    suspend operator fun invoke(params: ParamsType): ReturnType
}