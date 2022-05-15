package com.example.imagecropper

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.imagecropper.databinding.ActivityImageCropperBinding
import com.example.imagecropper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivPickImage.setOnClickListener{
            resultLauncher.launch("image/*")
        }
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
        if(it!=null)
        {
            val intent = Intent(this,ImageCropper::class.java)
            intent.putExtra("key",it.toString())
            startActivityForResult(intent,101)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode==-1 && requestCode==101){
            val result = data!!.getStringExtra("Result").toString()
            val resultUri =Uri.parse(result)
            binding.ivPickImage.setImageURI(resultUri)
        }
    }
}