package com.example.anmphobby.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.anmphobby.databinding.ActivityLoginBinding
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignIn.setOnClickListener {
            val q   = Volley.newRequestQueue(this)
            val url = "https://ubaya.me/native/160421025/login.php"

            val stringRequest = object : StringRequest(Request.Method.POST, url,
                Response.Listener
                {
                    Log.d("apilogin", it.toString())
                    val obj = JSONObject(it)
                    if (obj.getString("result") == "success")
                    {
                        val data = obj.getString("nama")
                        Log.d("apiuser", data.toString())

                        var loginInfo = "com.example.anmphobby"
                        var shared:SharedPreferences = getSharedPreferences(loginInfo, Context.MODE_PRIVATE )
                        var editor:SharedPreferences.Editor = shared.edit()

                        editor.putString("username", binding.textUsername.text.toString())
                        editor.putString("name",data.toString())
                        editor.apply()

                        //Login success
                        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                },

                Response.ErrorListener
                {
                    Log.d("apilogin", it.message.toString())
                    Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
                })
            {
                override fun getParams(): MutableMap<String, String>
                {
                    val params = HashMap<String, String>()

                    params["username"]    = binding.textUsername.text.toString()
                    params["password"]  = binding.textPassword.text.toString()

                    return params
                }
            }

            q.add(stringRequest)
        }

        binding.btnCreate.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed()
    {
        var loginInfo = "com.example.anmphobby"
        var shared:SharedPreferences = getSharedPreferences(loginInfo, Context.MODE_PRIVATE )
        var userid = shared.getString("username","").toString()

        if(userid == "")
        {
            Toast.makeText(this, "Please Login First", Toast.LENGTH_SHORT).show()
        }
        else
        {
            super.onBackPressed()
        }
    }
}