package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {


    //Explicit

    private EditText nameEditText, surnameEditText, idCardEditText,
            userEditText, passwordEditText, emailEditText, phoneEditText;

    private Button registerButton;
    private String nameString, surnameString, idCardString,
            userString, passwordString, emailString, phoneString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //BindWidget
        bindWidget();


    }//Main Method

    private void bindWidget() {

        nameEditText = (EditText) findViewById(R.id.edtName);
        surnameEditText = (EditText) findViewById(R.id.edtSurname);
        idCardEditText = (EditText) findViewById(R.id.edtIDcard);
        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        emailEditText = (EditText) findViewById(R.id.edtEmail);
        phoneEditText = (EditText) findViewById(R.id.edtPhone);
        registerButton = (Button) findViewById(R.id.btnRegister);


    } //bindWidget

}//Main Class
