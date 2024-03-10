package com.teamproject.wounddetection.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.teamproject.wounddetection.R
import com.teamproject.wounddetection.data.model.WoundReport
import com.teamproject.wounddetection.databinding.ReportListItemBinding
import java.lang.Exception

class ReportAdapter(
    private val context: Context,
    private val values: List<WoundReport>
) : RecyclerView.Adapter<ReportAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ReportListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.apply {
            depth.text = context.getString(R.string.depth, item.depth)
            woundClass.text = context.getString(R.string.wound_class, item.woundClass)
            type.text = context.getString(R.string.type, item.type)
            area.text = context.getString(R.string.area, item.area.toString())
            diameter.text = context.getString(R.string.diameter, item.diameter.toString())
            rot.text = context.getString(R.string.rot_percentage, item.rotPercentage.toString())
            Picasso.get().load(item.photoUrl).into(image, object : Callback {
                override fun onSuccess() {
                    progress.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    progress.visibility = View.GONE
                }
            })
        }

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ReportListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.ivWoundPhoto
        val depth = binding.tvDepth
        val woundClass = binding.tvClass
        val type = binding.tvType
        val area = binding.tvArea
        val diameter = binding.tvDiameter
        val rot = binding.tvRot
        val progress = binding.pbReport
    }

}