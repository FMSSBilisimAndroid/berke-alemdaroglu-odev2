package com.dag.odev2fmss

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.dag.odev2fmss.databinding.ActivityLoginScreenBinding

class LoginScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var loginUserName: String
    private lateinit var loginPassword: String
    private var sharedUsernameValue: String = ""
    private var sharedPasswordValue: String = ""
    private lateinit var userDataClass: UserDataClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        sharedUsernameValue =
            sharedPreferences.getString("USERNAME", sharedUsernameValue).toString()
        sharedPasswordValue =
            sharedPreferences.getString("PASSWORD", sharedPasswordValue).toString()

        orCreateAccountButtonIntent()
        forgottenPasswordButtonIntent()
        loginButtonIntent()
        onBackStackPressButton()
    }

    /**
     *
     * loginButtonIntent fonksiyonunda kullanıcı bilgilerinin karşılaştırılması ve karşılaştırma sonraso gerekli yere gönderilme işlemi yapılmaktadır.
     *  UserDataClass'da oluşturduğumuz değerleri initialization ederek ilk değerlerini veriyoruz.
     *  loginUserName değişkenine loginButton a basıldığında usernameEditText ve passwprdEditText lere girilen değerleri atıyoruz.
     *  if sorgumuzda statik olarak oluşturduğumuz değerler ile edittexlere girilen değerlerin aynı olup olmadığı durumları karşılaştırıyoruz.
     *  Uygulamamızda live data olmadığından uygulamaya giriş yapılabilmesi için default kullanıcı oluşturulmuştur.
     *  Eğer kullanıcıların username ve password default değerlere uymazsa ondan kayıt yapılması isteniyor.
     *  Kullanıcı kayıt açtıktan sonra tekrar loginScreen ekranına yönlendirilerek giriş yapması talep ediliyor.
     *  Shared Preference ile tutulan kayıtları yine else if yardımıyla karşılaştırıp gerekleri Activity e yönlendirmesi sağlanıyor.
     *  Yine if ile EditTextlerin boş olup olmadığıda kontrol ediliyor.
     *
     */
    private fun loginButtonIntent() {
        userDataClass = UserDataClass("demo@gmail.com", "demo", "demoPass")
        binding.loginButton.setOnClickListener {

            loginUserName = binding.usernameEditText.text.toString()
            loginPassword = binding.passwordEditText.text.toString()

            if (userDataClass.username == loginUserName && userDataClass.password == loginPassword) {
                sharedPreferences.edit {
                    putString("USERNAME", userDataClass.username)
                    putString("PASSWORD", userDataClass.password)
                    putBoolean("CHECK", true)
                }
                Util.backStack(this, HomeActivity())
            } else if (loginUserName.isEmpty() && loginPassword.isEmpty()) {
                Toast.makeText(this, "Gerekli Alanları Doldurun!", Toast.LENGTH_SHORT).show()
            } else if (sharedUsernameValue == loginPassword && sharedPasswordValue == loginPassword) {
                Toast.makeText(this, "Kayıt Sonrası Giriş Yapıldı!", Toast.LENGTH_SHORT).show()
                Util.backStack(this, HomeActivity())
            } else {
                Toast.makeText(this,
                    "Kullanıcı Bulunamadı! Giriş Yapmak İçin Kayıt Olabilirsiniz!",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * LoginScreen den CreateAccountActivity sayfasına yönlendirme yapılıyor.
     */
    private fun orCreateAccountButtonIntent() {
        binding.createNewAccountButton.setOnClickListener {
            startActivity(Intent(this@LoginScreenActivity, CreateAccountScreenActivity::class.java))
        }
    }

    /**
     * LoginScreen den ForgettenPassword sayfasına yönlendirme yapılıyor.
     */
    private fun forgottenPasswordButtonIntent() {
        binding.forgottenPassTextButton.setOnClickListener {
            startActivity(Intent(this@LoginScreenActivity, PasswordResetActivity::class.java))
        }
    }

    /**
     * backScreenButton ile bir önceki sayfaya geçişi sağlanıyor.
     */
    private fun onBackStackPressButton() {
        binding.backScreenButton.setOnClickListener {
            Util.backStack(this, GetStartedScreenActivity())
        }
    }

}