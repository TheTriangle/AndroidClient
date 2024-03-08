package com.teamproject.wounddetection.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.teamproject.wounddetection.data.model.Case
import com.teamproject.wounddetection.databinding.CaseListItemBinding

class CaseAdapter(
    private val values: List<Case>,
    private val onClick: (Case) -> Unit
) : RecyclerView.Adapter<CaseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            CaseListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.name.text = item.doctor.name
        holder.date.text = item.date
        holder.root.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: CaseListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.tvDoctorName
        val date: TextView = binding.tvDate
        val root = binding.root
    }
}
