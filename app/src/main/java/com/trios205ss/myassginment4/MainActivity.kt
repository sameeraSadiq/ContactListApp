package com.trios205ss.myassginment4


import android.os.Bundle
import android.widget.*
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val contactList = mutableListOf<Contact>()
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAddContact: Button = findViewById(R.id.btnAddContact)
        recyclerView = findViewById(R.id.rvContacts)

        adapter = ContactAdapter(contactList,
            onEditClick = { position -> showEditContactDialog(position) },
            onDeleteClick = { position ->
                contactList.removeAt(position)
                adapter.notifyItemRemoved(position)
            })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnAddContact.setOnClickListener {
            showAddContactDialog()
        }
    }

    private fun showAddContactDialog() {
        val nameInput = EditText(this).apply { hint = "Name" }
        val phoneInput = EditText(this).apply { hint = "Phone Number" }
        val emailInput = EditText(this).apply { hint = "Email" }

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 30, 40, 30)
            addView(nameInput)
            addView(phoneInput)
            addView(emailInput)
        }

        AlertDialog.Builder(this)
            .setTitle("Add New Contact")
            .setView(layout)
            .setPositiveButton("Add") { _, _ ->
                val name = nameInput.text.toString()
                val phone = phoneInput.text.toString()
                val email = emailInput.text.toString()

                if (name.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty()) {
                    contactList.add(Contact(name, phone, email))
                    adapter.notifyItemInserted(contactList.size - 1)
                } else {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showEditContactDialog(position: Int) {
        val contact = contactList[position]

        val nameInput = EditText(this).apply {
            hint = "Name"
            setText(contact.name)
        }
        val phoneInput = EditText(this).apply {
            hint = "Phone Number"
            setText(contact.phone)
        }
        val emailInput = EditText(this).apply {
            hint = "Email"
            setText(contact.email)
        }

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 30, 40, 30)
            addView(nameInput)
            addView(phoneInput)
            addView(emailInput)
        }

        AlertDialog.Builder(this)
            .setTitle("Edit Contact")
            .setView(layout)
            .setPositiveButton("Update") { _, _ ->
                contactList[position] = Contact(
                    nameInput.text.toString(),
                    phoneInput.text.toString(),
                    emailInput.text.toString()
                )
                adapter.notifyItemChanged(position)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}