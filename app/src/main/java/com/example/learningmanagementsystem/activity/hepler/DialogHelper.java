package com.example.learningmanagementsystem.activity.hepler;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.activity.RegisterTeacherActivity;

public class DialogHelper extends AppCompatActivity {
    private Dialog dialog;
    private Button btnYes, btnCancle;
    private Activity activity;

    public DialogHelper(Activity activity) {
        this.activity = activity;
    }


    public void showDialog() {
        dialog = new Dialog(activity);
        dialog.setContentView(R.layout.custom_dialog_sure);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Drawable background = activity.getResources().getDrawable(R.drawable.custom_dialog_bg);
        dialog.getWindow().setBackgroundDrawable(background);
        dialog.setCancelable(false);
        dialog.show();

        btnYes = dialog.findViewById(R.id.btnYes);
        btnCancle = dialog.findViewById(R.id.btnCancle);
        eventDialog();
    }


    private void eventDialog() {
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DialogHelper.this, RegisterTeacherActivity.class);
                intent.putExtra("isYesClicked", true);
                startActivity(intent);
                finish();
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
                dialog.dismiss();
            }
        });
    }
}