package com.example.learningmanagementsystem.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatebaseSQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ghinho_congviec";
    private static final String TEN_TABLE = "ds_congviec";
    private static final String KEY_ID = "id";
    private static final String KEY_TEN_CONGVIEC = "ten_congviec";

    public DatebaseSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TEN_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TEN_CONGVIEC + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TEN_TABLE);
        onCreate(db);
    }

    public Person layCongViecDauTien() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + KEY_TEN_CONGVIEC + " FROM " + TEN_TABLE + " LIMIT 1";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Person person = new Person();
            person.setName(cursor.getString(1));
            person.setId(cursor.getInt(0));

            cursor.close();
            db.close();
            return person;

        }
        return null;
    }
}
