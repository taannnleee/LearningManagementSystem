package com.example.learningmanagementsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Support;
import com.example.learningmanagementsystem.models.Teacher;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SupportActivity extends AppCompatActivity {
    final int REQUEST_CHOOSE_PHOTO = 321;
    final int RESQUEST_TAKE_PHOTO = 123;
    private ImageButton btn_back, btn_add_image;
    private Button btn_send;
    private Button btnYes, btnNo;
    private EditText edt_message;
    private Button btnSure, btnCancle;
    private Dialog dialog;
    private Dialog dialogSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        getFormWidgets();
        addEvent();

    }

    private void addEvent() {
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialogSure = new Dialog(SupportActivity.this);
                    dialogSure.setContentView(R.layout.custom_dialog_sure);
                    dialogSure.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialogSure.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
                    dialogSure.setCancelable(false);
                    dialogSure.show();
                    btnSure = dialogSure.findViewById(R.id.btnYes);
                    btnCancle = dialogSure.findViewById(R.id.btnCancle);
                    eventDialogSure();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SupportActivity.this, InteractionAdminActivity.class);
                startActivity(intent);
            }
        });
        btn_add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(SupportActivity.this);
                dialog.setContentView(R.layout.custom_dialog_box);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
                dialog.setCancelable(false);
                dialog.show();
                btnYes = dialog.findViewById(R.id.btnYes);
                btnNo = dialog.findViewById(R.id.btnNo);
                eventDialog();
            }
        });

    }

    private void getFormWidgets() {
        btn_back = findViewById(R.id.btn_back);
        btn_send = findViewById(R.id.btn_send);
        edt_message = findViewById(R.id.edt_message);
        btn_add_image = findViewById(R.id.btn_add_image);

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
    private void eventDialogSure() {
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addSupport();
                    dialogSure.dismiss();
                    Toast.makeText(SupportActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    Toast.makeText(SupportActivity.this, "Fail", Toast.LENGTH_SHORT).show();
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
    private void addSupport() throws ParseException {
        if (isFieldsNotEmpty()) {
            Support support = setSupportData();
            DatabaseLearningManagerSystem.getInstance(this).supportDAO().insertSupport(support);
            Toast.makeText(this, "Support added successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isFieldsNotEmpty() {
        return !edt_message.getText().toString().isEmpty();
    }
    private Support setSupportData() throws ParseException {
        byte[] picture = getByteArrayFromImageView(btn_add_image);

        Support newSupport = new Support();
        newSupport.setSupportContent(edt_message.getText().toString());

        newSupport.setPictureSupport(picture);
        return newSupport;
    }
    private byte[] getByteArrayFromImageView(ImageView imgv) {
        BitmapDrawable drawable =  (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress (Bitmap.CompressFormat. PNG, 100, stream);
        byte[] byteArray =  stream.toByteArray();
        return byteArray;

    }

}