package apps.weverton.com.br.validarapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import apps.weverton.com.br.validarapplication.DAO.ClienteDao;
import apps.weverton.com.br.validarapplication.Model.ClienteModel;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtSenha;
    private Button btnEntrar;
    private TextView txtCadastrar;
    SQLiteOpenHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        txtUsuario = (EditText) findViewById(R.id.main_edt_email);
        txtSenha = (EditText) findViewById(R.id.main_edt_password);
        txtCadastrar = (TextView) findViewById(R.id.main_txt_cadastrar);
        btnEntrar = (Button) findViewById(R.id.main_btn_entrar);

        dbHelper = new ClienteDao(this);
        db = dbHelper.getWritableDatabase();
        final ClienteModel cliente = new ClienteModel();


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cliente.setEmail(txtUsuario.getText().toString());
                cliente.setSenha(txtSenha.getText().toString());

                cursor = db.rawQuery("SELECT *FROM " + ClienteDao.TABELA_NOME + " WHERE " +
                                ClienteDao.COLUNA_EMAIL + "=? AND " +
                                ClienteDao.COLUNA_SENHA + "=?",
                        new String[]{cliente.getEmail(), cliente.getSenha()});
                if (cursor != null) {
                    if (cursor.getCount() > 0) {

                        cursor.moveToFirst();

                        String intentNome = cursor.getString(cursor.getColumnIndex(ClienteDao.COLUNA_NOME));
                        String intentEmail = cursor.getString(cursor.getColumnIndex(ClienteDao.COLUNA_EMAIL));
                        Toast.makeText(MainActivity.this, "Login Realizado com Sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LoginSuccessActivity.class);
                        intent.putExtra("nome", intentNome);
                        intent.putExtra("email", intentEmail);
                        startActivity(intent);

                        db.close();
                        finish();
                    } else {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Alerta");
                        builder.setMessage("O nome de usuário ou a senha estão incorreto.");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();

                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                        //-------Alert Dialog Code Snippet End Here
                    }
                }

            }
        });


        txtCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastrarActivity.class);
                startActivity(intent);
            }
        });
    }
}
