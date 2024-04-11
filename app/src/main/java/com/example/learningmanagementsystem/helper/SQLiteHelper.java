//package com.example.learningmanagementsystem.helper;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class SQLiteHelper extends SQLiteOpenHelper {
//
//    private static final String DATABASE_NAME = "learning_management_system.db";
//    private static final int DATABASE_VERSION = 24;
//
//    //student
//    public static final String TABLE_STUDENTS = "students";
//    public static final String COLUMN_STUDENT_ID = "student_id";
//    public static final String COLUMN_STUDENT_NAME = "student_name";
//    public static final String COLUMN_STUDENT_ADDRESS = "student_address";
//    public static final String COLUMN_STUDENT_PHONE = "student_phone";
//    public static final String COLUMN_STUDENT_EMAIL = "student_email";
//    public static final String COLUMN_STUDENT_PASSWORD = "student_passWord";
//    public static final String COLUMN_STUDENT_ROLE = "student_role";
//    public static final String COLUMN_STUDENT_STATUS = "student_status";
//
//    //teacher
//    public static final String TABLE_TEACHERS = "teachers";
//
//
//    //courses
//    public static final String TABLE_COURSES = "courses";
//    public static final String COLUMN_COURSE_ID = "course_id";
//    public static final String COLUMN_COURSE_NAME = "course_name";
//
//    public static final String COLUMN_COURSE_DESCRIPTION = "course_description";
//    public static final String COLUMN_COURSE_INSTRUCTOR = "course_instructor";
//    public static final String COLUMN_COURSE_DURATION = "course_duration";
//    public static final String COLUMN_COURSE_LEVEL = "course_course_level";
//
////    // Tên bảng và cột cho bảng Lớp Học
////    public static final String TABLE_CLASSES = "classes";
////    public static final String COLUMN_CLASS_ID = "class_id";
////    public static final String COLUMN_CLASS_NAME = "class_name";
////
////    // Tên bảng và cột cho bảng Điểm Danh
////    public static final String TABLE_ATTENDANCE = "attendance";
////    public static final String COLUMN_ATTENDANCE_ID = "attendance_id";
////    public static final String COLUMN_ATTENDANCE_STUDENT_ID = "student_id";
////    public static final String COLUMN_ATTENDANCE_CLASS_ID = "class_id";
////    public static final String COLUMN_ATTENDANCE_DATE = "attendance_date";
////    public static final String COLUMN_ATTENDANCE_STATUS = "attendance_status";
////
////    // Tên bảng và cột cho bảng trung gian Sinh Viên - Lớp Học
////    public static final String TABLE_STUDENT_CLASS = "student_class";
////    public static final String COLUMN_SC_ID = "sc_id";
////    public static final String COLUMN_SC_STUDENT_ID = "student_id";
//
////    public static final String COLUMN_SC_CLASS_ID = "class_id";
//
//    public SQLiteHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//
//    // Tạo bảng Học Sinh
//    private static final String SQL_CREATE_STUDENTS_TABLE =
//            "CREATE TABLE " + TABLE_STUDENTS + " (" +
//                    COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                    COLUMN_STUDENT_NAME + " TEXT," +
//                    COLUMN_STUDENT_EMAIL + " TEXT," +
//                    COLUMN_STUDENT_PASSWORD + " TEXT," +
//                    COLUMN_STUDENT_ADDRESS + " TEXT," +
//                    COLUMN_STUDENT_ROLE + " TEXT," +
//                    COLUMN_STUDENT_STATUS + " TEXT," +
//                    COLUMN_STUDENT_PHONE + " TEXT)";
//
//    private static final String SQL_CREATE_TEACHERS_TABLE =
//            "CREATE TABLE " + TABLE_TEACHERS + " (" +
//                    COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                    COLUMN_STUDENT_NAME + " TEXT," +
//                    COLUMN_STUDENT_EMAIL + " TEXT," +
//                    COLUMN_STUDENT_PASSWORD + " TEXT," +
//                    COLUMN_STUDENT_ADDRESS + " TEXT," +
//                    COLUMN_STUDENT_ROLE + " TEXT," +
//                    COLUMN_STUDENT_STATUS + " TEXT," +
//                    COLUMN_STUDENT_PHONE + " TEXT)";
//
//    private static final String SQL_CREATE_COURSES_TABLE =
//            "CREATE TABLE " + TABLE_COURSES + " (" +
//                    COLUMN_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                    COLUMN_COURSE_NAME + " TEXT," +
//                    COLUMN_COURSE_DESCRIPTION + " TEXT," +
//                    COLUMN_COURSE_INSTRUCTOR + " TEXT," +
//                    COLUMN_COURSE_DURATION + " INTEGER," +
//                    COLUMN_COURSE_LEVEL + " TEXT)";
//
//    // Tạo bảng Lớp Học
////    private static final String SQL_CREATE_CLASSES_TABLE =
////            "CREATE TABLE " + TABLE_CLASSES + " (" +
////                    COLUMN_CLASS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
////                    COLUMN_CLASS_NAME + " TEXT)";
////
////    // Tạo bảng Điểm Danh
////    private static final String SQL_CREATE_ATTENDANCE_TABLE =
////            "CREATE TABLE " + TABLE_ATTENDANCE + " (" +
////                    COLUMN_ATTENDANCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
////                    COLUMN_ATTENDANCE_STUDENT_ID + " INTEGER," +
////                    COLUMN_ATTENDANCE_CLASS_ID + " INTEGER," +
////                    COLUMN_ATTENDANCE_DATE + " TEXT," +
////                    COLUMN_ATTENDANCE_STATUS + " TEXT," +
////                    "FOREIGN KEY (" + COLUMN_ATTENDANCE_STUDENT_ID + ") REFERENCES " + TABLE_STUDENTS + "(" + COLUMN_STUDENT_ID + ")," +
////                    "FOREIGN KEY (" + COLUMN_ATTENDANCE_CLASS_ID + ") REFERENCES " + TABLE_CLASSES + "(" + COLUMN_CLASS_ID + "))";
////
////    // Tạo bảng trung gian Sinh Viên - Lớp Học
////    private static final String SQL_CREATE_STUDENT_CLASS_TABLE =
////            "CREATE TABLE " + TABLE_STUDENT_CLASS + " (" +
////                    COLUMN_SC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
////                    COLUMN_SC_STUDENT_ID + " INTEGER," +
////                    COLUMN_SC_CLASS_ID + " INTEGER," +
////                    "FOREIGN KEY (" + COLUMN_SC_STUDENT_ID + ") REFERENCES " + TABLE_STUDENTS + "(" + COLUMN_STUDENT_ID + ")," +
////                    "FOREIGN KEY (" + COLUMN_SC_CLASS_ID + ") REFERENCES " + TABLE_CLASSES + "(" + COLUMN_CLASS_ID + "))";
//
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(SQL_CREATE_STUDENTS_TABLE);
//        db.execSQL(SQL_CREATE_TEACHERS_TABLE);
//        db.execSQL(SQL_CREATE_COURSES_TABLE);
//
////        db.execSQL(SQL_CREATE_CLASSES_TABLE);
////        db.execSQL(SQL_CREATE_ATTENDANCE_TABLE);
////        db.execSQL(SQL_CREATE_STUDENT_CLASS_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // Drop bảng cũ nếu tồn tại và tạo lại
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHERS);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
//
////        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASSES);
////        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTENDANCE);
////        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT_CLASS);
//        onCreate(db);
//    }
//}
