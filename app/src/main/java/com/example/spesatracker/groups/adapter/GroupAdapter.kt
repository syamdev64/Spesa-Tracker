package com.example.spesatracker.groups.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.spesatracker.R
import com.example.spesatracker.groups.model.Group
import com.squareup.picasso.Picasso

class GroupAdapter(private val groups: List<Group>) :
    RecyclerView.Adapter<GroupAdapter.GroupViewHolder>() {

    class GroupViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val groupimage: ImageView = view.findViewById(R.id.group_image)
        val groupName: TextView = view.findViewById(R.id.group_name)
        val balanceStatus: TextView = view.findViewById(R.id.balance_status)
        val balanceAmount: TextView = view.findViewById(R.id.balance_amount)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroupViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_group, parent, false)
        return GroupViewHolder(view)
    }


    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val group = groups[position]
        holder.groupName.text = group.name
        if (group.imageurl.isNotEmpty()) {
            Picasso.get().load(group.imageurl).into(holder.groupimage)
        } else {
            holder.groupimage.setImageResource(R.drawable.forgot_password_icon)
        }
        val context = holder.itemView.context
        when {
            group.balance > 0 -> {
                holder.balanceStatus.text = "you are owed"
                holder.balanceAmount.text = String.format("$%.2f", group.balance)
                val green = ContextCompat.getColor(context, R.color.google_blue)
                holder.balanceStatus.setTextColor(green)
                holder.balanceAmount.setTextColor(green)
            }

            group.balance < 0 -> {
                holder.balanceStatus.text = "you owe"
                holder.balanceAmount.text = String.format("$%.2f", -group.balance)
                val red = ContextCompat.getColor(context, R.color.google_red)
                holder.balanceStatus.setTextColor(red)
                holder.balanceAmount.setTextColor(red)
            }

            else -> {
                holder.balanceStatus.text = "settled up"
                holder.balanceAmount.text = ""
                val gray = ContextCompat.getColor(context, android.R.color.darker_gray)
                holder.balanceStatus.setTextColor(gray)
                holder.balanceAmount.setTextColor(gray)
            }
        }
    }

    override fun getItemCount(): Int {
        return groups.size

    }

}