package kmutnb.ratchaphol.natthawut.natdanai.blacksheeptoy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        //Botton Controller
        buttonController();


    }//Main Method

    private void buttonController() {

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get Value From Edit Text
                nameString = nameEditText.getText().toString().trim();
                surnameString = surnameEditText.getText().toString().trim();
                idCardString = idCardEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();
                emailString = emailEditText.getText().toString().trim();
                phoneString = phoneEditText.getText().toString().trim();

                //Check Space ห้ามว่างนะ
                if (checkSpace()) {
                    //Have Space

                } else {
                    //No Space

                } //if


            }//event
        });

    }

    private boolean checkSpace() {

        boolean spaceStatus = true;

        spaceStatus = nameString.equals("") ||
                surnameString.equals("") ||
                idCardString.equals("") ||
                userString.equals("") ||
                passwordString.equals("") ||
                emailString.equals("") ||
                phoneString.equals("");

        return spaceStatus;
    }

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
