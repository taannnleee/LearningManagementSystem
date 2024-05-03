package com.example.learningmanagementsystem.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.activity.hepler.DialogHelper;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Teacher;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import android.view.ViewGroup;

public class RegisterTeacherActivity extends AppCompatActivity {
    final int RESQUEST_TAKE_PHOTO = 123;
    final int REQUEST_CHOOSE_PHOTO = 321;
    final int REQUEST_DIALOG_HELPER = 113;

    private EditText edtLastName;
    private EditText edtFirstName;
    private EditText edtPhoneNumber;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnCreateTeacher, btnBackInteraction;

    private ImageView imgBirthday;

    private EditText edtBirthday;
    private ImageView imvpictureTeacher;
    private Dialog dialog;
    private Button btnYes, btnNo, btnBack;

    private Dialog dialogSure;
    private Button btnSure, btnCancle;


    private byte[] getByteArrayFromImageView(ImageView imgv) {
        BitmapDrawable drawable =  (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress (Bitmap.CompressFormat. PNG, 100, stream);
        byte[] byteArray =  stream.toByteArray();
        return byteArray;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);

        getFormWidgets();
        addEvent();
    }

    private void addEvent() {

        btnCreateTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialogSure = new Dialog(RegisterTeacherActivity.this);
                    dialogSure.setContentView(R.layout.custom_dialog_sure);
                    dialogSure.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialogSure.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
                    dialogSure.setCancelable(false);
                    dialogSure.show(); // Hiển thị dialog

                    btnSure = dialogSure.findViewById(R.id.btnYes);
                    btnCancle = dialogSure.findViewById(R.id.btnCancle);
                    eventDialogSure();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        imgBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance();
                int year = calender.get(Calendar.YEAR);
                int month = calender.get(Calendar.MONTH);
                int day = calender.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RegisterTeacherActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast.makeText(RegisterTeacherActivity.this, String.format("select : %d/%d/%d",dayOfMonth, (month+1), year),
                                Toast.LENGTH_SHORT).show();

                        edtBirthday.setText(String.format("%d/%d/%d",dayOfMonth, (month+1), year));
                    }
                }, year ,month,day);
                dialog.show();
            }
        });
//        btnBackInteraction.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), InteractionAdminActivity.class);
//                startActivity(intent);
//            }
//        });

        imvpictureTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(RegisterTeacherActivity.this);
                dialog.setContentView(R.layout.custom_dialog_box);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
                dialog.setCancelable(false);
                dialog.show(); // Hiển thị dialog
                btnYes = dialog.findViewById(R.id.btnYes);
                btnNo = dialog.findViewById(R.id.btnNo);
                eventDialog();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterTeacherActivity.this, InteractionAdminActivity.class);
                startActivity(intent);
            }
        });


    }

    private void eventDialogSure() {
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addTeacher();
                    dialogSure.dismiss();
                    Toast.makeText(RegisterTeacherActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    Toast.makeText(RegisterTeacherActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);
                }
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSure.dismiss();
            }
        });

    }

    private void addTeacher() throws ParseException {
        if (isFieldsNotEmpty()) {
            Teacher teacher = setTeacherData();
            DatabaseLearningManagerSystem.getInstance(this).teacherDAO().insertTeacher(teacher);
            Toast.makeText(this, "Teacher registered successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isFieldsNotEmpty() {
        return !edtEmail.getText().toString().isEmpty() &&
                !edtPassword.getText().toString().isEmpty() &&
                !edtFirstName.getText().toString().isEmpty() &&
                !edtPhoneNumber.getText().toString().isEmpty() &&
                !edtLastName.getText().toString().isEmpty() &&
                !edtBirthday.getText().toString().isEmpty();
    }

    private void getFormWidgets() {
        edtEmail = findViewById(R.id.edtemail);
        edtPassword = findViewById(R.id.edtpassword);
        edtFirstName = findViewById(R.id.edtfirstName);
        edtPhoneNumber = findViewById(R.id.edtphone);
        edtLastName = findViewById(R.id.edtlastname);
        imgBirthday = findViewById(R.id.imageBirthday);
        edtBirthday = findViewById(R.id.edtBirthday11);

        btnCreateTeacher = findViewById(R.id.btnCreateTeacher);
        imvpictureTeacher = findViewById(R.id.imvpictureTeacher);
        btnBack = findViewById(R.id.btn_back);
    }
    private Teacher setTeacherData() throws ParseException {
        byte[] picture = getByteArrayFromImageView(imvpictureTeacher);

        Teacher newTeacher = new Teacher();
        newTeacher.setTeacherEmail(edtEmail.getText().toString());
        newTeacher.setTeacherPassword(edtPassword.getText().toString());
        newTeacher.setTeacherPhone(edtPhoneNumber.getText().toString());
        newTeacher.setTeacherName(edtLastName.getText().toString()+ " " + edtFirstName.getText().toString());

        String birthday_ = edtBirthday.getText().toString();
        Date birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthday_);
        newTeacher.setBirthday(birthday);
        newTeacher.setPictureTeacher(picture);
        return newTeacher;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CHOOSE_PHOTO) {
                try {
                    Uri imageUri =  data.getData();
                    InputStream is =
                            getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap =  BitmapFactory.decodeStream(is);
                    imvpictureTeacher.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == RESQUEST_TAKE_PHOTO) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imvpictureTeacher.setImageBitmap(bitmap);
            }

        }
    }
    private void eventDialog(){
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CHOOSE_PHOTO);
                dialog.dismiss();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, RESQUEST_TAKE_PHOTO);
                dialog.dismiss();
            }
        });
    }
}