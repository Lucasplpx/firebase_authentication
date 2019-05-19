package br.com.faculdadedelta.materialdesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmailLogin;
    private EditText etSenhaLogin;

    private String mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");

        etEmailLogin = (EditText) findViewById(R.id.etEmailLogin);
        etSenhaLogin = (EditText) findViewById(R.id.etSenhaLogin);


        Intent intent = getIntent();

        if(intent != null){

            mensagem = intent.getStringExtra("mensagemValidacao");
            Toast.makeText(getBaseContext(), mensagem , Toast.LENGTH_LONG).show();

        }
    }

    public void btn_login(View view){
        limparCampos();
        Toast.makeText(getBaseContext(), "*** Acesso negado *** \n*** Consulte o ADM ***", Toast.LENGTH_LONG).show();
    }

    public void btn_registrar(View view){

        Intent intent = new Intent(getBaseContext(), SignupActivity.class);

        startActivity(intent);

        finish();
    }

    public void limparCampos(){
        etEmailLogin.setText("");
        etSenhaLogin.setText("");
    }
}
