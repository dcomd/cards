package br.com.alexandre.card.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.alexandre.card.R
import br.com.alexandre.card.domain.model.CreditCard
import br.com.alexandre.card.presentation.CardViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_config_new_credit_card.*
import org.koin.android.viewmodel.ext.android.viewModel

class ConfigNewCreditCardFragment :
    Fragment()  {

    private val viewModel: CardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater
            .inflate(
                R.layout.fragment_config_new_credit_card,
                container,
                false
            ) as ViewGroup
    }

    override fun onActivityCreated( savedInstanceState: Bundle? ) {
        super.onActivityCreated( savedInstanceState )

        bt_add_credit_card.setOnClickListener {
            val number  = met_credit_card_number.text.toString()
            val enterprise = sp_credit_card_enterprise.selectedItem.toString()
            val ownerFullName = et_credit_card_owner_name.text.toString()
            val ownerRegNumber = met_credit_card_owner_reg.text.toString()
            val expiryMonth = sp_credit_card_expiry_month.selectedItem.toString()
            val expiryYear = et_credit_card_expiry_year.text.toString()
            val securityNumber = et_credit_card_security_code.text.toString()
            val card = CreditCard(number,enterprise,ownerFullName,ownerRegNumber,expiryMonth.toInt(),expiryYear.toInt(),securityNumber)
            viewModel.create(card)
            view?.let { it ->
                Snackbar.make(it, "card added with success", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}