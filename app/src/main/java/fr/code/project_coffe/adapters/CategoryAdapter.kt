package fr.code.project_coffe.adapters

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import fr.code.project_coffe.R
import fr.code.project_coffe.activities.ItemsListActivity
import fr.code.project_coffe.databinding.ViewholderCategoryBinding
import fr.code.project_coffe.domain.CategoryModel

class CategoryAdapter(val items: MutableList<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private lateinit var context : Context
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    class ViewHolder(val binding: ViewholderCategoryBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        context=parent.context
        val binding = ViewholderCategoryBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.binding.titleCat.text = item.title

        holder.binding.root.setOnClickListener {
            // ON RÉCUPÈRE LA POSITION DYNAMIQUE ICI
            val adapterPos = holder.bindingAdapterPosition
            if (adapterPos != RecyclerView.NO_POSITION) {
                lastSelectedPosition = selectedPosition
                selectedPosition = adapterPos
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)

                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(context, ItemsListActivity::class.java).apply {
                        putExtra("title", item.title)
                        putExtra("id", item.id.toString())
                    }
                    ContextCompat.startActivity(context, intent, null)

                }, 500)
            }
        }

        if(selectedPosition == position){
            holder.binding.titleCat.setBackgroundResource(R.drawable.brown_full_corner_bg)
        } else {
            holder.binding.titleCat.setBackgroundResource(R.drawable.brown_2_full_corner)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}