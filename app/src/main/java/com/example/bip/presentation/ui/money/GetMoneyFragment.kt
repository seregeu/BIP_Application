package com.example.bip.presentation.ui.money

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.bip.R
import com.example.bip.data.entity.MoneyEntity
import com.example.bip.presentation.interfaces.BottomNavigationController
import com.example.bip.presentation.interfaces.NavigateController
import java.lang.RuntimeException

class GetMoneyFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setGetMoneyButtonOnClickListener(view)
    }

    private fun setGetMoneyButtonOnClickListener(view: View){
        val sendButton = view.findViewById<Button>(R.id.buttonGetMoney)
        val etMoney = view.findViewById<EditText>(R.id.etGetMoney)
        sendButton.setOnClickListener{
            //Тут в общем экземпляр с денюжками
            try {
                val money = MoneyEntity(etMoney.text.toString().toInt())
            }catch (exception: RuntimeException){
                Log.d("Exception!", "Проблемы с деньгами")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_get_money, container, false)
    }

}