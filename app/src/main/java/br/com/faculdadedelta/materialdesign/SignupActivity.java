package br.com.faculdadedelta.materialdesign;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignupActivity extends AppCompatActivity {


    private EditText etEmail;
    private EditText etSenha;

    private Button btnRegistrar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("Cadastre-se");


        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);
        btnRegistrar = (Button) findViewById(R.id.btn_registrar);

        auth = FirebaseAuth.getInstance();


    }



    private void criarUsuario(String email, String senha){

        String mensagemValidacao = validarCampos();


        if(mensagemValidacao.equals("")){
            auth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getBaseContext(), "Cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                                limparCampos();
                                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                                intent.putExtra("mensagemValidacao", "Cadastrado com sucesso!");
                                startActivity(intent);
                                finish();
                            } else {
                                String resposta = task.getException().toString();
                                opcoesErro(resposta);
                            }

                            // ...
                        }
                    });
                } else {
                Toast.makeText(getBaseContext(), mensagemValidacao, Toast.LENGTH_LONG).show();
            }



    }

    public String validarCampos(){
        String mensagem = "";

        if(etEmail.getText().toString().equals("")){
            mensagem = "Preecha o E-mail!";
        }

        if(etSenha.getText().toString().equals("")){
            mensagem = "Preecha a Senha!";
        }

        return mensagem;
    }

    private void opcoesErro(String resposta) {

        if(resposta.contains("least 6 characters")){
            Toast.makeText(getBaseContext(), "Digite uma senha maior que 5 characters!", Toast.LENGTH_LONG).show();
        }
        else if(resposta.contains("address is badly")){
            Toast.makeText(getBaseContext(), "E-mail inválido!", Toast.LENGTH_LONG).show();
        }
        else if(resposta.contains("address is already")){
            Toast.makeText(getBaseContext(), "E-mail já cadastrado!", Toast.LENGTH_LONG).show();
        }
        else if(resposta.contains("interrupted connection")){
            Toast.makeText(getBaseContext(), "Sem conexão com a internet!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), resposta, Toast.LENGTH_LONG).show();
        }

    }

    private void limparCampos() {

        etEmail.setText("");
        etSenha.setText("");

    }

    public void btn_reg_signup(View view) {

        criarUsuario(etEmail.getText().toString(), etSenha.getText().toString());

    }
}
