package fr.code.project_coffe.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import fr.code.project_coffe.Helper.ChangeNumberItemsListener
import fr.code.project_coffe.Helper.ManagementCart
import fr.code.project_coffe.R
import fr.code.project_coffe.adapters.CartAdapter
import fr.code.project_coffe.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    lateinit var managementCart: ManagementCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagementCart(this)

        calculateCart()
        setVariable()
        initCartList()
    }

    private fun initCartList() {
        binding.apply {
            listView.layoutManager =
                LinearLayoutManager(
                    this@CartActivity,
                    LinearLayoutManager.VERTICAL,
                    false)
            listView.adapter =
                CartAdapter(
                    managementCart.getListCart(),
                    this@CartActivity,
                    object : ChangeNumberItemsListener {
                        override fun onChanged() {
                            calculateCart()
                        }

                    }
                )
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun calculateCart() {
        val percentTax: Double = 0.02
        val delivery = 10.0
        val tax = (managementCart.getTotalFee() * percentTax)
        val total = managementCart.getTotalFee() + tax + delivery
        val itemTotal = managementCart.getTotalFee()
        binding.apply {
            // %.1f ensures exactly one decimal digit is shown
            totalFeeTxt.text = String.format("$%.1f", itemTotal)
            deliveryTxt.text = String.format("$%.1f", delivery)
            totalTaxTxt.text = String.format("$%.2f", tax)
            totalTxt.text = String.format("$%.1f", total)
        }
    }
}