package apps.weverton.com.br.validarapplication;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import apps.weverton.com.br.validarapplication.DAO.ClienteDao;

public class CadastrarActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        openHelper = new ClienteDao(this);

        final EditText txtNome = (EditText) findViewById(R.id.cadastrar_edt_nome);
        final EditText txtEmail = (EditText) findViewById(R.id.cadastrar_edt_email);
        final EditText txtSenha = (EditText) findViewById(R.id.cadastrar_edt_password);
        Button btnCadastrar = (Button) findViewById(R.id.cadastrar_btn_cadastrar);
        Button btnCancelar = (Button) findViewById(R.id.cadastrar_btn_cancelar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = openHelper.getWritableDatabase();

                String nome = txtNome.getText().toString();
                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();

                inserirCliente(nome, email, senha);

                final AlertDialog.Builder builder = new AlertDialog.Builder(CadastrarActivity.this);
                builder.setTitle("Informação");
                builder.setMessage("Sua conta foi criada com sucesso");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastrarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    public void inserirCliente(String nome, String email, String senha) {
        ContentValues values = new ContentValues();
        values.put(ClienteDao.COLUNA_NOME, nome);
        values.put(ClienteDao.COLUNA_EMAIL, email);
        values.put(ClienteDao.COLUNA_SENHA, senha);
        long id = db.insert(ClienteDao.TABELA_NOME, null, values);

    }
}