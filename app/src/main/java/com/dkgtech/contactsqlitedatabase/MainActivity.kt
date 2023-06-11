package com.dkgtech.contactsqlitedatabase

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dkgtech.contactsqlitedatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var contactAdapter: RecyclerContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = MyDBHelper(this@MainActivity)

//        dbHelper.addContact("Dipesh", "9867990635")
//        dbHelper.addContact("Sachin", "8850726052")

        val arrContacts = dbHelper.getAllContacts()
        Log.d("Contact", arrContacts[0].contactName)

        binding.rcViewContact.layoutManager = LinearLayoutManager(this@MainActivity)
        contactAdapter = RecyclerContactAdapter(this@MainActivity, arrContacts)
        binding.rcViewContact.adapter = contactAdapter

        dbHelper.deleteContact(3)


/*        binding.rcViewContact.findViewById<ImageView>(R.id.btnDeleteContact).setOnClickListener {
            val deleteDialog = AlertDialog.Builder(this@MainActivity)
            deleteDialog.setTitle("Delete Contact")
            deleteDialog.setMessage("Are you sure want to delete?")
            deleteDialog.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dbHelper.deleteContact(6)
                }

            })
        }*/


        binding.btnFbAdd.setOnClickListener {
            val addDialog = Dialog(this@MainActivity).apply {
                setContentView(R.layout.contact_add)
                setCancelable(false)

                val edtContactName = findViewById<TextView>(R.id.edtContactName)
                val edtContactNumber = findViewById<TextView>(R.id.edtContactNumber)
                val btnAdd = findViewById<Button>(R.id.btnAdd)
                val btnCancel = findViewById<Button>(R.id.btnCancel)


                btnAdd.setOnClickListener {
                    val contactName = edtContactName.text.toString()
                    val contactNumber = edtContactNumber.text.toString()

                    if (contactName.isNotBlank() && contactNumber.isNotBlank()) {
                        arrContacts.add(
                            ContactModel(
                                R.drawable.ic_launcher_background,
                                contactName,
                                contactNumber
                            )
                        )
                        contactAdapter.notifyItemInserted(arrContacts.size - 1)
                        binding.rcViewContact.scrollToPosition(arrContacts.size - 1)

                    }
                    dismiss()
                }

                btnCancel.setOnClickListener {
                    dismiss()
                }
            }

            addDialog.show()
        }

    }
}