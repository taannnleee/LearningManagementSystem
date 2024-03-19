package com.example.learningmanagementsystem.helper;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.learningmanagementsystem.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Random;

public class TestActivity extends ListActivity {

//    private PeopleDataSource datasource;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
//
//        datasource = new PeopleDataSource(this);
//        datasource.open();
//
//        List<Person> values = datasource.getAllPeople();
//
//        // use the SimpleCursorAdapter to show the
//        // elements in a ListView
//        ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this,
//                android.R.layout.simple_list_item_1, values);
//        setListAdapter(adapter);
//    }
//
//    // Tạo sự kiện khi click vào các nút trong activity_main.xml
//    @SuppressLint("NonConstantResourceId")
//    public void onClick(View view) {
//        @SuppressWarnings("unchecked")
//        ArrayAdapter<Person> adapter = (ArrayAdapter<Person>) getListAdapter();
//        Person person = null;
//        if(view.getId() ==R.id.add){
//            String[] people = new String[]{"Alice", "Bob", "Mallory"};
//            int nextInt = new Random().nextInt(3);
//            person = datasource.createPerson(people[nextInt]);
//            adapter.add(person);
//        } else if (view.getId() == R.id.delete) {
//            if (getListAdapter().getCount() > 0) {
//                person = (Person) getListAdapter().getItem(0);
//                datasource.deletePerson(person);
//                adapter.remove(person);
//            }
//        }
//        adapter.notifyDataSetChanged();
//    }
//
//    @Override
//    protected void onResume() {
//        datasource.open();
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        datasource.close();
//        super.onPause();
//    }
}