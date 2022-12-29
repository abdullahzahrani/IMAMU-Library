package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;





public class MainActivity extends AppCompatActivity {

    sqLite db = new sqLite(this);
    TextView author_name;
    TextView  book_name;
    TextView  id;
    TextView  cat;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        author_name=findViewById(R.id.editTextAuthName_view);
        book_name=findViewById(R.id.editTextBookName_view);
        cat=findViewById(R.id.editTextCat_view);
        id=findViewById(R.id.editTextNumberSigned2Hint_view);
        lst=findViewById(R.id.listView_view);
        showData();
    }

    public void showData(){
        ArrayList<String> listData = db.getAllRecord();
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listData);
        lst.setAdapter(arrayAdapter);

    }
    public void btn_add_data(View view) {
        String Name=author_name.getText().toString();
        String Book=book_name.getText().toString();
        String Cat=cat.getText().toString();

        boolean request= db.insertData(Name,Book,Cat);
        if (request == true){
            Toast.makeText(getBaseContext(), "Ok", Toast.LENGTH_SHORT).show();
            author_name.setText("");
            book_name.setText("");
            cat.setText("");
            showData();
        }else{
            Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();

        }
    }
    public void btn_edit_data(View view) {
        String Name=author_name.getText().toString();
        String Book=book_name.getText().toString();
        String Cat=cat.getText().toString();

        String Id=id.getText().toString();

        boolean request= db.updateData(Id,Name,Book,Cat);
        if (request == true){
            Toast.makeText(getBaseContext(), "Ok", Toast.LENGTH_SHORT).show();
            author_name.setText("");
            book_name.setText("");
            cat.setText("");
            id.setText("");
            showData();
        }else{
            Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();

        }
    }
    public void btn_delete_data(View view) {
        String Id=id.getText().toString();
        Integer result = db.Delete(Id);
        if (result>0){
            Toast.makeText(getBaseContext(), "Ok", Toast.LENGTH_SHORT).show();
            id.setText("");
            showData();
        }else{
            Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
        }

    }
}