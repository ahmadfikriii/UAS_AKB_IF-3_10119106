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

public class RegisterActivity extends AppCompatActivity {

    Button registrasi_btn;
    TextView login_btn;
    EditText inputEmail, inputPassword, confirmpassword;
    String email, password1, password2;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        login_btn  = (TextView) findViewById(R.id.login_button);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });

        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password1);
        confirmpassword = findViewById(R.id.password2);
        registrasi_btn = findViewById(R.id.registrasi_button);
        registrasi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrasi();
            }
        });

    }
    private void registrasi() {
        email =  inputEmail.getText().toString();
        password1 =  inputPassword.getText().toString();
        password2 =  confirmpassword.getText().toString();
        if (TextUtils.isEmpty(email)){
            inputEmail.setError("Email Harus Diisi!");
        }else if(TextUtils.isEmpty(password1)){
            inputPassword.setError("Password Harus Diisi!");
        }else if (inputPassword.length() < 6){
            confirmpassword.setError("Password minimal 6 karakter!");
        }else {
            mAuth.createUserWithEmailAndPassword(email, password2)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Registrasi Sukses", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registrasi Gagal, masukkan password minimal 6 character!", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }
    }
}