package com.example.kiemtrathuongky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import android.widget.SpinnerAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Dialog dialog;
    DataSach dataSach = new DataSach(this);
    EditText id, authorname;
    GridView gridView;
    ArrayList<Author> authorArrayList;
    Button btnselect, btnupdate, btnsave, btnExit, btndelete;
//sach
    EditText ids, title;
    Button btnselectS, btnupdateS, btnsaveS, btnExitS, btndeleteS;
    Spinner spinnerAthor;
    GridView gridViewS;

    ArrayList<Sach> sachArrayList;
    ArrayList<String> sach_authors;
    ArrayList<String> array_BookToString;
    ArrayAdapter<String> adater_Spiner;
    ArrayAdapter<String> adapter_books;




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ttsach: xemChTietSach();
                break;
            case R.id.tttg:
                xemChTiet();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void initAuthor(Dialog dialog) {

        id = dialog.findViewById(R.id.edtIDA);
        authorname = dialog.findViewById(R.id.edtNameA);
        gridView = dialog.findViewById(R.id.gv);

        btnselect = dialog.findViewById(R.id.btnSelect);
        btnupdate = dialog.findViewById(R.id.btnUpdate);
        btnsave = dialog.findViewById(R.id.btnSave);
        btndelete = dialog.findViewById(R.id.btnDelete);
        btnExit = dialog.findViewById(R.id.btnexit);

    }
    //SACH
    public void initSach(Dialog dialog) {

        ids = dialog.findViewById(R.id.edtIDS);
        title = dialog.findViewById(R.id.edtTitle);
        gridViewS = dialog.findViewById(R.id.gvS);

        btnselectS = dialog.findViewById(R.id.btnSelectS);
        btnupdateS = dialog.findViewById(R.id.btnUpdateS);
        btnsaveS = dialog.findViewById(R.id.btnSaveS);
        btndeleteS = dialog.findViewById(R.id.btnDeleteS);
        btnExitS = dialog.findViewById(R.id.btnexitS);
        spinnerAthor = dialog.findViewById(R.id.spi_bAuthorId);

    }

    //hien thi author len gird Author
    public void displayGird(){
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Author> authorArrayList;
        authorArrayList = dataSach.getAllAuthor();

        for (Author a : authorArrayList) {
            list.add(a.getIda() + "");
            list.add(a.getNamea());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
        gridView.setAdapter(adapter);

    }
    //hien thi sach
    public void displayGirdSach(){
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Sach> sachArrayList;
        sachArrayList = dataSach.getAllSach();

        for (Sach a : sachArrayList) {
            list.add(a.getId() + "");
            list.add(a.getTitle());
            list.add(a.getIda()+"");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
        gridViewS.setAdapter(adapter);
    }

    //Author
    public void xemChTiet() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.show();
        //khóa dialog, khi click ngoài k bị mất
        dialog.setCancelable(false);
        initAuthor(dialog);
        authorArrayList = new ArrayList<>();

        dataSach.addTG(new Author(1, "Trong"));
        dataSach.addTG(new Author(2, "Truc"));
        dataSach.addTG(new Author(3, "Trinh"));
        dataSach.addTG(new Author(4, "Trang"));

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = new Author(Integer.parseInt(id.getText().toString()), authorname.getText().toString());
                if (authorArrayList.contains(author))
                    Toast.makeText(MainActivity.this, "Trùng mã", Toast.LENGTH_SHORT).show();
                else if (dataSach.addTG(author)) {
                    authorArrayList.add(author);
                    Toast.makeText(MainActivity.this, "Thêm tác giả thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dataSach.deleteteAuthor(Integer.parseInt(id.getText().toString()));
                Toast.makeText(MainActivity.this, "Xoa thanh cong!", Toast.LENGTH_SHORT).show();
               displayGird();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author at = dataSach.getAuthorID(Integer.parseInt(id.getText().toString()));

                at.setNamea(authorname.getText().toString());
                dataSach.updateAuthor(at);
                Toast.makeText(MainActivity.this, "Cap nhat thanh cong!", Toast.LENGTH_SHORT).show();
                displayGird();

            }
        });
        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayGird();
            }
        });

    }
    //sach
    public void setAdapter_books() {
        if (adapter_books == null) {
            adapter_books = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, array_BookToString);
            gridViewS.setAdapter(adapter_books);
        } else
            adapter_books.notifyDataSetChanged();
    }
    public void xemChTietSach() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sach);
        dialog.show();
        //khóa dialog, khi click ngoài k bị mất
        dialog.setCancelable(false);
        initSach(dialog);


        // truyền spiner
//        authorArrayList = dataSach.getAllAuthor();
//        sach_authors = new ArrayList<>();
//        for (Author a : authorArrayList)
//            sach_authors.add(a.getIda() + " - " + a.getNamea());
//
//
//        adater_Spiner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sach_authors);
//        adater_Spiner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerAthor.setAdapter(adater_Spiner);
//        spinnerAthor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, spinnerAthor.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("3");
        list.add("5");

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerAthor.setAdapter(adapter);
        spinnerAthor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent();
//                Bundle b = new Bundle();
//                b.putString("s", spinnerAthor.getSelectedItem().toString());
//                intent.putExtra("data",b);
//                startActivity(intent);
               Toast.makeText(MainActivity.this, spinnerAthor.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // truyền gridview
        array_BookToString = new ArrayList<>();
        sachArrayList = dataSach.getAllSach();

        // chuyển thành string để đưa vào array_BookToString
        for (Sach b : sachArrayList) {
            array_BookToString.add(b.getId() + "");
            array_BookToString.add(b.getTitle());
            array_BookToString.add(b.getIda() + "");
        }



        btnExitS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //chua sua
        btnsaveS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = getIntent();
//                Bundle bundle = intent.getBundleExtra("data");
//                String x =bundle.getString("s");

                Sach sach = new Sach(Integer.parseInt(ids.getText().toString()), title.getText().toString(),1);
                if (sachArrayList.contains(sach))
                    Toast.makeText(MainActivity.this, "Trùng mã", Toast.LENGTH_SHORT).show();
                else if (dataSach.insertBook(sach)) {
                    sachArrayList.add(sach);
                    Toast.makeText(MainActivity.this, "Thêm sach thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btndeleteS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dataSach.deleteteAuthor(Integer.parseInt(id.getText().toString()));
                Toast.makeText(MainActivity.this, "Xoa thanh cong!", Toast.LENGTH_SHORT).show();
                displayGird();
            }
        });
        btnupdateS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author at = dataSach.getAuthorID(Integer.parseInt(id.getText().toString()));

                at.setNamea(authorname.getText().toString());
                dataSach.updateAuthor(at);
                Toast.makeText(MainActivity.this, "Cap nhat thanh cong!", Toast.LENGTH_SHORT).show();
                displayGird();

            }
        });
        btnselectS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayGirdSach();


            }
        });

    }


}

