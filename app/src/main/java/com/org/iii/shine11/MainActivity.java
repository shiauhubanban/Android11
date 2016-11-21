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
    //新增
    public void insert(View v){
        // INSERT INTO cust (cname,birthday,tel) VALUES ('Shine','1999-09-08','123');
        ContentValues data = new ContentValues();
        data.put("cname", "SF");
        data.put("birthday", "1992-09-08");
        data.put("tel", "123");
        db.insert("cust",null, data);
        query(null);
    }

    //刪除
    public void delete(View v){
        // DELETE FROM cust WHERE id = 6 AND cname = 'Shine'
        db.delete("cust","id = ? AND cname =?",new String[]{"6","Shine"});
        query(null);
    }

    //修改
    public void update(View v){
        ContentValues data = new ContentValues();
        data.put("cname", "Be");
        data.put("birthday", "1988-05-08");
        data.put("tel", "321");
        db.update("cust",data,"id = ? ",new String[]{"7"});
        query(null);

    }

    //查詢
    public void query(View v){
        textView.setText("");
        // SELECT * FROM cust ORDER BY cname DESC
        // db.execSQL("SELECT * FROM cust");
        Cursor cursor = db.query("cust",null,null,null,null,null,"cname DESC,birthday DESC");

        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String cname = cursor.getString(cursor.getColumnIndex("cname"));
            String birthday = cursor.getString(cursor.getColumnIndex("birthday"));
            String tel = cursor.getString(cursor.getColumnIndex("tel"));
            textView.append(id +":"+ cname + ":" + birthday + ":" + tel + "\n");
        }
    }

}
