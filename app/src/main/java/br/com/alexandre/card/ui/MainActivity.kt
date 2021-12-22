package br.com.alexandre.card.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alexandre.card.ui.fragments.ConfigCreditCardsListFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(br.com.alexandre.card.R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(
                br.com.alexandre.card.R.id.fl_base,
                ConfigCreditCardsListFragment()
            )
            .commit()
    }
}