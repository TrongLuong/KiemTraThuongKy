package com.example.kiemtrathuongky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Dialog dialog;
    DataSach dataSach = new DataSach(this);


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ttsach: xemChTiet();
                break;}
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    public void xemChTiet() {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.show();
        //khóa dialog, khi click ngoài k bị mất
        dialog.setCancelable(false);
        final EditText id = dialog.findViewById(R.id.edtIDA);
        final EditText title = dialog.findViewById(R.id.edtTitle);
        final EditText authorname = dialog.findViewById(R.id.edtNameA);

        final  GridView gridView = dialog.findViewById(R.id.gv);
        final  GridView gridView2 = dialog.findViewById(R.id.gv2);

        final Button btnselect = dialog.findViewById(R.id.btnSelect);
        final Button btnupdate = dialog.findViewById(R.id.btnUpdate);
        final Button btnsave = dialog.findViewById(R.id.btnSave);
        final Button btndelete = dialog.findViewById(R.id.btnDelete);
        final Button btnExit = dialog.findViewById(R.id.btnexit);


        dataSach.addTG(new Author(1, "Navathe"));
        dataSach.addTG(new Author(2, "Navathe"));
        dataSach.addTG(new Author(3, "Navathe"));
        dataSach.addTG(new Author(4, "Navathe"));


        dataSach.addSach(new Sach(1, "xxx",1));
        dataSach.addSach(new Sach(2, "xxx",2));
        dataSach.addSach(new Sach(3, "xxx",3));
        dataSach.addSach(new Sach(4, "xxx",4));




        ArrayList<String> list = new ArrayList<>();
        ArrayList<Author> authorArrayList;
        authorArrayList = dataSach.getAllAuthor();
        for(Author a : authorArrayList){
            list.add(a.getIda() + "");
            list.add(a.getNamea());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
        gridView.setAdapter(adapter);


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = new Author(Integer.parseInt(id.getText().toString()),authorname.getText().toString());
               dataSach.addTG(author);
               Toast.makeText(MainActivity.this, "Them thanh cong!", Toast.LENGTH_SHORT).show();

                ArrayList<String> list = new ArrayList<>();
                ArrayList<Author> authorArrayList;
                authorArrayList = dataSach.getAllAuthor();
                for(Author a : authorArrayList){
                    list.add(a.getIda() + "");
                    list.add(a.getNamea());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                gridView.setAdapter(adapter);


            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dataSach.deleteteAuthor(Integer.parseInt(id.getText().toString()));
                Toast.makeText(MainActivity.this, "Xoa thanh cong!", Toast.LENGTH_SHORT).show();

                ArrayList<String> list = new ArrayList<>();
                ArrayList<Author> authorArrayList;
                authorArrayList = dataSach.getAllAuthor();
                for(Author a : authorArrayList){
                    list.add(a.getIda() + "");
                    list.add(a.getNamea());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                gridView.setAdapter(adapter);
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Author at =  dataSach.getAuthorID(Integer.parseInt(id.getText().toString()));
                //at.setId(Integer.parseInt(id.getText().toString()));
                at.setNamea(authorname.getText().toString());
                dataSach.updateAuthor(at);

                Toast.makeText(MainActivity.this, "Cap nhat thanh cong!", Toast.LENGTH_SHORT).show();

                ///load lai girdview
                ArrayList<String> list = new ArrayList<>();
                ArrayList<Author> authorArrayList;
                authorArrayList = dataSach.getAllAuthor();
                for(Author a : authorArrayList){
                    list.add(a.getIda() + "");
                    list.add(a.getNamea());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                gridView.setAdapter(adapter);

            }
        });
        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





            }
        });

    }



}

