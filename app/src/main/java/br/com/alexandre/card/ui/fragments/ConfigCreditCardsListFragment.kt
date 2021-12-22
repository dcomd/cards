package br.com.alexandre.card.ui.fragments

import br.com.alexandre.card.ui.adapter.ConfigCreditCardsListItemsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alexandre.card.data.CreditCardsDataBase
import br.com.alexandre.card.R
import br.com.alexandre.card.presentation.CardViewModel
import kotlinx.android.synthetic.main.fragment_config_credit_cards_list2.*
import org.koin.android.viewmodel.ext.android.viewModel

class ConfigCreditCardsListFragment : Fragment(){

    private val viewModel: CardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater
            .inflate(
                R.layout.fragment_config_credit_cards_list2,
                container,
                false
            ) as ViewGroup
    }

    override fun onActivityCreated( savedInstanceState: Bundle? ) {
        super.onActivityCreated( savedInstanceState )
        viewModel.getAllCard()
        initItems()

        bt_add_credit.setOnClickListener {
            val fragmentManager = fragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.fl_base, ConfigNewCreditCardFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

    }

    private fun initItems(){
        tv_empty_list.visibility = View.VISIBLE
        viewModel.fetchData.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()){
                tv_empty_list.visibility = View.GONE
                rvCreditCards.setHasFixedSize( false )

                val layoutManager = LinearLayoutManager( activity )
                rvCreditCards.layoutManager = layoutManager

                val adapter = ConfigCreditCardsListItemsAdapter(
                    it.toMutableList()
                )
                adapter.removeItem= { number ,list ->
                    viewModel.delete(number)
                    if (list.isEmpty()){
                        tv_empty_list.visibility = View.VISIBLE
                    }
                }

                rvCreditCards.adapter = adapter
            }

        })

    }
}