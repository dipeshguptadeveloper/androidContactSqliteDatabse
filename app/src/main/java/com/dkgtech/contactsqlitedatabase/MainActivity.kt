package com.dkgtech.contactsqlitedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dkgtech.contactsqlitedatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
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
        binding.rcViewContact.adapter = RecyclerContactAdapter(this@MainActivity, arrContacts)

        dbHelper.deleteContact(3)



    }
}