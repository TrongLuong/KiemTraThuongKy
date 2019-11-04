package com.example.kiemtrathuongky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;


import java.util.ArrayList;


public class DataSach extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sachlist";

    private static final String TABLE_Sach= "sach";
    private static final String TABLE_TG = "tacgia";

    private static final String IDSACH = "idsach";
    private static final String TITLESACH = "titlesach";


    private static final String AUTHORNAME = "authorname";
    private static final String IDAUTHOR = "idauthor";

    private Context context;


    //khoi tao database
    public DataSach(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("DataSach", TABLE_Sach + TABLE_TG);
        this.context = context;

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            String sqlQuery1 = "CREATE TABLE "+TABLE_TG +" (" +
                    IDAUTHOR + " integer primary key autoincrement, "+
                    AUTHORNAME +" TEXT)";
            sqLiteDatabase.execSQL(sqlQuery1);

            String sqlQuery2 = "CREATE TABLE "+TABLE_Sach +" (" +
                    IDSACH + " integer primary key, "+
                    TITLESACH + " TEXT ,"+
                    IDAUTHOR + " integer not null " +
                    "constraint IDAUTHOR  references TABLE_TG(IDAUTHOR)"+
                    "on delete cascade on update cascade)";
            sqLiteDatabase.execSQL(sqlQuery2);
            Toast.makeText(context, "Create successfylly", Toast.LENGTH_SHORT).show();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TG);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Sach);

        onCreate(sqLiteDatabase);
        Toast.makeText(context, "Drop successfylly", Toast.LENGTH_SHORT).show();
    }
    //them author
    public boolean addTG(Author author){
        if (author == null)
            return false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(IDAUTHOR, author.getIda());
        cv.put(AUTHORNAME, author.getNamea());


        db.insert(TABLE_TG, null, cv);
        return true;
    }
    //update author
    public int updateAuthor(Author author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AUTHORNAME, author.getNamea());
        return db.update(TABLE_TG, values, IDAUTHOR + "=?", new String[]{String.valueOf(author.getIda())});

    }
    //delete
    public void deleteteAuthor(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TG, "IDAUTHOR =" + id, null);
        db.close();
    }
    //search author by id
    public Author getAuthorID(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        Author author ;

        cursor = db.query(TABLE_TG, new String[]{IDAUTHOR,AUTHORNAME},IDAUTHOR + " =? ",
                new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        author  = new Author( cursor.getInt(0),cursor.getString(1));
        cursor.close();
        db.close();

        return author;
    }
    //LAY TATA CA
    public ArrayList<Author> getAllAuthor(){
        ArrayList<Author> authorArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String qr = "SELECT  * FROM " + TABLE_TG;
        Cursor cursor = db.rawQuery(qr,null);
        if(cursor!=null)
            cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            authorArrayList.add(new Author(cursor.getInt(0),cursor.getString(1)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return authorArrayList;
    }


    // ------------------------------- Quản lý BOOKS -------------------------------//




    //lay toan bo sach
    public boolean insertBook (Sach sach) {
        if (sach == null)
            return false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(IDSACH, sach.getId());
        cv.put(TITLESACH, sach.getTitle());
        cv.put(IDAUTHOR, sach.getIda());

        db.insert(TABLE_Sach, null, cv);
        return true;
    }
    public int updateSach(Sach sach) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLESACH, sach.getTitle());
        return db.update(TABLE_Sach, values, IDSACH + "=?", new String[]{String.valueOf(sach.getId())});

    }
    //lay sach theo id
    public Sach getsachID(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TG, new String[]{IDSACH,TITLESACH,IDAUTHOR},IDSACH + " =? ",
                new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null)
            cursor.moveToFirst();
      //  Sach sach = new Sach( cursor.getInt(0),   cursor.getString(1), cursor.getInt(2));
        Sach sach = new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
        cursor.close();
        db.close();
        return sach;
    }
    //them sach
    public int addSach(Sach sach){
        int rs = 0;
        try {
            SQLiteDatabase  db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(IDSACH, sach.getId());
            values.put(TITLESACH, sach.getTitle());
            values.put(IDAUTHOR, sach.getIda());
            rs = (int) db.insert(TABLE_TG,null, values);
            db.close();

        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return  rs;
    }
    //xoa sacsh
    public void deleteSach(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TG, "IDSACH =" + id, null);
        db.close();
    }
    public ArrayList<Sach> getAllSach(){
        ArrayList<Sach> sachArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String qr = "SELECT  * FROM " + TABLE_Sach;
        Cursor cursor = db.rawQuery(qr,null);
        if(cursor!=null)
            cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            sachArrayList.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return sachArrayList;
    }




}
