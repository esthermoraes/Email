package nascimento.moraes.esther.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        //Definição da ação do click do botão
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtendo dados digitados pelo usuário
                EditText etEmail = (EditText) findViewById(R.id.etEmail); // Uso do metodo findViewById para localizar o elemento atraves de seu Id
                String email = etEmail.getText().toString(); // Retorna a informação digitada e "coletada" no/do elemento em forma de String
                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();
                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();

                Intent i = new Intent(Intent.ACTION_SENDTO); // Pegando o parametro e passando ele de ação para poder enviar para alguem
                i.setData(Uri.parse("mailto:")); // Indicando que seja respondido atraves do SENDTO e da Uri | mailto -> relacionado ao e-mail
                String[] emails = new String[]{email};
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP:")); // Execução do Intent
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Não há nenhum APP que possa realizar essa operação", Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}