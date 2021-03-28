package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //TextInputLayout
    TextInputEditText regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, signin;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hooks

            regName = findViewById(R.id.reg_name);
            regUsername = findViewById(R.id.reg_username);
            regEmail = findViewById(R.id.reg_email);
            regPhoneNo = findViewById(R.id.reg_PhoneNo);
            regPassword = findViewById(R.id.reg_password);
            regBtn = findViewById(R.id.reg_btn);
            signin = findViewById(R.id.reg_login_btn);


            //Save a data in Firebase on Button Click
            regBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!validateName()) {
                        //Toast.makeText(SignUp.this, "NameError", Toast.LENGTH_LONG).show();
                        return;
                    }
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("Users");

                    //Get all the values
                    String name = regName.getText().toString();
                    String username = regUsername.getText().toString();
                    String email = regEmail.getText().toString();
                    String phoneNo = regPhoneNo.getText().toString();
                    String password = regPassword.getText().toString();


                    UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);

                    reference.child(username).setValue(helperClass);
                    Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                }
            });

            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                }
            });

    }

    private Boolean validateName(){
        String val = regUsername.getText().toString();

        if (val.isEmpty()){
            regName.setError("Field cannot be Empty");
            return false;
        }
        else{
            regName.setError(null);
            regName.setEnabled(false);
            return true;
        }

    }
}