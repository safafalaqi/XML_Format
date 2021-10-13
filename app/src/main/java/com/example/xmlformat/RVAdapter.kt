package com.example.xmlformat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlformat.databinding.ItemRowBinding


class RVAdapter(private val students: List<Student>): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val name = students[position].name
        val marks = students[position].marks

        holder.binding.apply {
            tvName.text=name
            tvMarks.text=marks.toString()
        }
    }

    override fun getItemCount() = students.size
}