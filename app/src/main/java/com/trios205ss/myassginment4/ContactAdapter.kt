package com.trios205ss.myassginment4

import android.widget.Button
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(

    private val contacts: MutableList<Contact>,
    private val onEditClick: (position: Int) -> Unit,
    private val onDeleteClick: (position: Int) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvName)
        val phone: TextView = view.findViewById(R.id.tvPhone)
        val email: TextView = view.findViewById(R.id.tvEmail)
        val btnEdit: Button = view.findViewById(R.id.btnEdit)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.name.text = contact.name
        holder.phone.text = contact.phone
        holder.email.text = contact.email

        holder.btnEdit.setOnClickListener {
            onEditClick(position)
        }

        holder.btnDelete.setOnClickListener {
            onDeleteClick(position)
        }
    }

    override fun getItemCount(): Int = contacts.size
}