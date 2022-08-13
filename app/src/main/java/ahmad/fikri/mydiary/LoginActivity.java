package ahmad.fikri.mydiary;

/**
 *
 * NIM      : 10119106
 * Nama     : Ahmad Fikri Maulana
 * Kelas    : IF-3
 *
 * **/

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView btn_reg, forgotpassword;
    EditText inputEmail, inputPassword;
    String email, password;
    Button btnLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_Login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ceklogin();
            }
        });

        btn_reg = (TextView) findViewById(R.id.btn_registrasi);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent create = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(create);
            }
        });

        forgotpassword = (TextView) findViewById(R.id.forgot_Pass);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot = new Intent(LoginActivity.this, ForgotPassActivity.class);
                startActivity(forgot);
            }
        });

    }
    private void ceklogin() {
        email =  inputEmail.getText().toString();
        password =  inputPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            inputEmail.setError("Masukkan Email!");
        }else if(TextUtils.isEmpty(password)){
            inputPassword.setError("Password Harus Diisi!");
        }else if (password.length() < 6){
            inputPassword.setError("Password minimal 6 karakter!");
        }else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login Sukses", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Login Gagal!", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }
    }
}