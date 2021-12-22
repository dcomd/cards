package br.com.alexandre.card.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alexandre.card.data.CreditCardsDataBase
import br.com.alexandre.card.data.dto.CardDto
import br.com.alexandre.card.domain.model.CreditCard
import br.com.alexandre.card.domain.usecase.AddCard
import br.com.alexandre.card.domain.usecase.DeleteCard
import br.com.alexandre.card.domain.usecase.GetCard
import br.com.alexandre.card.domain.usecase.GetCardSelected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


abstract class CardViewModel : ViewModel() {
    abstract val fetchData: LiveData<List<CreditCard>>
    abstract val fetchById: LiveData<CreditCard>
    abstract fun getAllCard()
    abstract fun delete(number: String)
    abstract fun create(card: CreditCard)
    abstract fun getCardById(number: String)
}

class CardViewModelImp(
    private val addCard: AddCard,
    private val deleteCard: DeleteCard,
    private val getCard: GetCard,
    private val getCardSelected: GetCardSelected
) : CardViewModel() {

    override val fetchData = MutableLiveData<List<CreditCard>>()
    override val fetchById = MutableLiveData<CreditCard>()

    override fun getAllCard() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val repo = getCard.invoke("")
                var nameMap: List<CreditCard> = repo.map { CreditCard(
                    it.number,
                    it.enterprise,
                    it.ownerFullName,
                    it.ownerRegNumber,
                    it.expiryMonth,
                    it.expiryYear,
                    it.securityNumber
                ) }
                fetchData.postValue(nameMap)

            } catch (e: Throwable) {
                Log.d("Wrong at get card", e.message.toString())
            }
        }
    }

    override fun getCardById(number: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val repo = getCardSelected.invoke(number)

                val creditCard = CreditCard(
                    repo.number,
                    repo.enterprise,
                    repo.ownerFullName,
                    repo.ownerRegNumber,
                    repo.expiryMonth,
                    repo.expiryYear,
                    repo.securityNumber
                )
                fetchById.postValue(creditCard)

            } catch (e: Throwable) {
                Log.d("Wrong at get card", e.message.toString())
            }
        }
    }


    override fun delete(number: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                deleteCard.invoke(number)
            } catch (e: Throwable) {
                Log.d("Wrong at delete", e.message.toString())
            }
        }
    }

    override fun create(card: CreditCard) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cardDto = CardDto(
                    card.number,
                    card.enterprise,
                    card.ownerFullName,
                    card.ownerRegNumber,
                    card.expiryMonth,
                    card.expiryYear,
                    card.securityNumber
                )

                addCard.invoke(cardDto)

            } catch (e: Throwable) {
                Log.d("Wrong at insert", e.message.toString())
            }
        }
    }
}