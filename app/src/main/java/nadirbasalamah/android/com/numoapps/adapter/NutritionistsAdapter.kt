package nadirbasalamah.android.com.numoapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.people_item.view.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.model.entity.Nutritionist

class NutritionistsAdapter() : RecyclerView.Adapter<NutritionistsAdapter.CardViewViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    private val listNutritionists  = ArrayList<Nutritionist>()

    fun setData(items: ArrayList<Nutritionist>) {
        listNutritionists.clear()
        listNutritionists.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.people_item, viewGroup, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listNutritionists[position])
    }

    override fun getItemCount(): Int = listNutritionists.size

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(nutritionist: Nutritionist) {
            with(itemView) {
                tv_people_name.text = nutritionist.fullname
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(nutritionist) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Nutritionist)
    }
}