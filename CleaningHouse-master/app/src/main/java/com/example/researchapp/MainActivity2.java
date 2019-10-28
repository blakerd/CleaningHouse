package com.example.researchapp;
//tonys comment
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    String userName;
    String passWord;

    EditText userNameInput;
    EditText passWordInput;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        userNameInput = (EditText) findViewById(R.id.userName);
        passWordInput= (EditText) findViewById(R.id.passWord);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                userName = userNameInput.getText().toString();
                passWord = passWordInput.getText().toString();
                finalStage();
            }




        });

    }

    private void finalStage() {

        Intent i = new Intent(this, finalStage.class);
        startActivity(i);

    }

}
