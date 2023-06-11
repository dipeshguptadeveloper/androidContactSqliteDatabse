package com.dkgtech.contactsqlitedatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerContactAdapter(val context: Context, val arrContact: ArrayList<ContactModel>) :
    RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtContactId = itemView.findViewById<TextView>(R.id.txtContactId)
        val txtContactName = itemView.findViewById<TextView>(R.id.txtContactName)
        val txtContactNumber = itemView.findViewById<TextView>(R.id.txtContactNumber)
        val btnDeleteContact = itemView.findViewById<ImageView>(R.id.btnDeleteContact)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false))
    }

    override fun getItemCount(): Int {
        return arrContact.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtContactId.text = arrContact[position].contactId.toString()
        holder.txtContactName.text = arrContact[position].contactName
        holder.txtContactNumber.text = arrContact[position].contactNumber

    }
}