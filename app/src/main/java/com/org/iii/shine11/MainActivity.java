package com.org.iii.shine11;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private MyDBHelper dbHelper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.tv);

        dbHelper = new MyDBHelper(this,"iii",null,1);
        db = dbHelper.getReadableDatabase();
    }
    public void delete(View v){
        // DELETE FROM cust WHERE id = 6 AND cname = 'Shine'
        db.delete("cust","id = ? AND cname =?",new String[]{"6","Shine"});
        query(null);
    }

    //新增
    public void insert(View v){
        // INSERT INTO cust (cname,birthday,tel) VALUES ('Shine','1999-09-08','123');
        ContentValues data = new ContentValues();
        data.put("cname", "Shine");
        data.put("birthday", "1999-09-08");
        data.put("tel", "123");
        db.insert("cust",null, data);
        query(null);
    }

    //查詢
    public void query(View v){
        textView.setText("");
        // SELECT * FROM cust
        // db.execSQL("SELECT * FROM cust");
        Cursor cursor = db.query("cust",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String cname = cursor.getString(1);
            String birthday = cursor.getString(2);
            String tel = cursor.getString(3);
            textView.append(id +":"+ cname + ":" + birthday + ":" + tel + "\n");
        }
    }
}
