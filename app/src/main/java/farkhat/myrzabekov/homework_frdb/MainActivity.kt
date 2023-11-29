package farkhat.myrzabekov.homework_frdb

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.*
import farkhat.myrzabekov.homework_frdb.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")
    }

}
