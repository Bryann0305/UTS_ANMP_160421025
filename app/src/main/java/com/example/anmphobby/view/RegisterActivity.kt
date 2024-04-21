package com.example.anmphobby.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.anmphobby.databinding.ActivityRegisterBinding
import com.example.anmphobby.view.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            if(binding.txtPass.text.toString() == binding.txtUlangiPass.text.toString()) {
                registerUser()
            }
            else {
                Toast.makeText(this,"Password does not match", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun registerUser() {
        val username      = binding.txtUser.text.toString()
        val password       = binding.txtPass.text.toString()
        val nama_depan   = binding.txtNamaDpn.text.toString()
        val nama_belakang    = binding.txtNamaBlkng.text.toString()
        val email        = binding.txtEmail.text.toString()

        val url = "https://ubaya.me/native/160421025/register.php"

        val request = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response -> Toast.makeText(this, response, Toast.LENGTH_SHORT).show() },
            Response.ErrorListener { error -> Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show() }) {

            override fun getParams(): HashMap<String, String>
            {
                val params = HashMap<String, String>()

                params["username"]        = username
                params["email"]         = email
                params["nama_depan"]    = nama_depan
                params["nama_belakang"] = nama_belakang
                params["password"]      = password

                return params
                }
        }
        Volley.newRequestQueue(this).add(request)
    }
}