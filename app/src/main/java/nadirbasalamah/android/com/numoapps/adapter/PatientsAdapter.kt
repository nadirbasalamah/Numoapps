package nadirbasalamah.android.com.numoapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.people_item.view.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.model.entity.Patient

class PatientsAdapter() : RecyclerView.Adapter<PatientsAdapter.CardViewViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    private val listPatients  = ArrayList<Patient>()

    fun setData(items: ArrayList<Patient>) {
        listPatients.clear()
        listPatients.addAll(items)
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
        holder.bind(listPatients[position])
    }

    override fun getItemCount(): Int = listPatients.size

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(patient: Patient) {
            with(itemView) {
                tv_people_name.text = patient.fullname
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(patient) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Patient)
    }
}