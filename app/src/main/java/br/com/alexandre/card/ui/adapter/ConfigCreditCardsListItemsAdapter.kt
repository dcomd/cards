package br.com.alexandre.card.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alexandre.card.domain.model.CreditCard
import br.com.alexandre.card.R

class ConfigCreditCardsListItemsAdapter(
    private val items: MutableList<CreditCard>
) :
    RecyclerView.Adapter<ConfigCreditCardsListItemsAdapter.ViewHolder>() {

    var removeItem: ((String, MutableList<CreditCard>)->Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        type: Int ): ViewHolder {

        val layout = LayoutInflater
            .from( parent.context )
            .inflate(
                R.layout.credit_card_item,
                parent,
                false
            )

        return ViewHolder( layout )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int ) {

        holder.setData( items[ position ] )
    }

    override fun getItemCount() = items.size

    inner class ViewHolder( itemView: View) : RecyclerView.ViewHolder( itemView ) {

        private val tvEnterprise: TextView = itemView.findViewById(R.id.tv_enterprise)
        private val tvNumber: TextView = itemView.findViewById(R.id.tv_number)
        private val tvOwnerName: TextView = itemView.findViewById(R.id.tv_owner_name)
        private val btRemove: Button = itemView.findViewById(R.id.bt_remove)

        fun setData(item: CreditCard) {
            tvEnterprise.text = item.enterprise
            tvNumber.text = item.getNumberAsHidden()
            tvOwnerName.text = item.getOwnerFullNameAsHidden()
            btRemove.setOnClickListener {
                removeItem?.invoke(item.number, items)
                items.remove(item)
                notifyDataSetChanged()
            }
        }
    }

}