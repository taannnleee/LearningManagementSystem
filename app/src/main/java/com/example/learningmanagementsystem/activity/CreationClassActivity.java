package com.example.learningmanagementsystem.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.Schedule;
import com.example.learningmanagementsystem.models.Teacher;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CreationClassActivity extends AppCompatActivity {
    final int RESQUEST_TAKE_PHOTO = 123;

    final int REQUEST_CHOOSE_PHOTO = 321;

    EditText et_className, et_classSize, et_courseStart,
            et_courseEnd, et_studyingDates, et_classStart, et_classEnd,
            et_classFee;

    AutoCompleteTextView cmbBox_course, cmbBox_teacherInfo, cmbBox_classDescription;

    Button btn_createClass, btn_clearText, btn_yes, btn_no, btnSure, btnCancel;

    TextView btn_back;

    ImageView imv_classPicture, btn_courseStart, btn_courseEnd, img_classStart, img_classEnd;

    Dialog dialog;

    Dialog dialogSure;

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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_creation_class);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setAdjustScreen();
        getFormWidget();
        btn_createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialogSure = new Dialog(CreationClassActivity.this);
                    dialogSure.setContentView(R.layout.custom_dialog_sure);
                    dialogSure.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialogSure.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
                    dialogSure.setCancelable(false);
                    dialogSure.show(); // Hiển thị dialog

                    btnSure = dialogSure.findViewById(R.id.btnYes);
                    btnCancel = dialogSure.findViewById(R.id.btnCancle);
                    eventDialogSure();
                }
                catch (Exception e) {
                    Toast.makeText(CreationClassActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_clearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    clearInput();
                }
                catch (Exception e) {
                    Toast.makeText(CreationClassActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreationClassActivity.this, InteractionAdminActivity.class);
                startActivity(intent);
            }
        });

        imv_classPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(CreationClassActivity.this);
                dialog.setContentView(R.layout.custom_dialog_box);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
                dialog.setCancelable(false);
                dialog.show(); // Hiển thị dialog
                btn_yes = dialog.findViewById(R.id.btnYes);
                btn_no = dialog.findViewById(R.id.btnNo);
                eventDialog();
            }
        });

        btn_courseStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance();
                int year = calender.get(Calendar.YEAR);
                int month = calender.get(Calendar.MONTH);
                int day = calender.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(CreationClassActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast.makeText(CreationClassActivity.this, String.format("select : %d/%d/%d",dayOfMonth, (month+1), year),
                                Toast.LENGTH_SHORT).show();

                        et_courseStart.setText(String.format("%d/%d/%d",dayOfMonth, (month+1), year));
                    }
                }, year ,month,day);
                dialog.show();
            }
        });
        btn_courseEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance();
                int year = calender.get(Calendar.YEAR);
                int month = calender.get(Calendar.MONTH);
                int day = calender.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(CreationClassActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast.makeText(CreationClassActivity.this, String.format("select : %d/%d/%d",dayOfMonth, (month+1), year),
                                Toast.LENGTH_SHORT).show();

                        et_courseEnd.setText(String.format("%d/%d/%d",dayOfMonth, (month+1), year));
                    }
                }, year ,month,day);
                dialog.show();
            }
        });
        img_classStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreationClassActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        et_classStart.setText(hourOfDay + ":" + minutes);
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });

        img_classEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreationClassActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        et_classEnd.setText(hourOfDay + ":" + minutes);
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });
    }

    protected void clearInput() {
        et_className.setText("");
        et_classSize.setText("");
        cmbBox_course.setText("");
        cmbBox_teacherInfo.setText("");
        et_courseStart.setText("");
        et_courseEnd.setText("");
        et_studyingDates.setText("");
        et_classStart.setText("");
        et_classEnd.setText("");
        et_classFee.setText("");
        cmbBox_classDescription.setText("");
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
                    imv_classPicture.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == RESQUEST_TAKE_PHOTO) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imv_classPicture.setImageBitmap(bitmap);
            }

        }
    }

    private void eventDialog(){
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CHOOSE_PHOTO);
                dialog.dismiss();
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, RESQUEST_TAKE_PHOTO);
                dialog.dismiss();
            }
        });
    }

    private void insertSchedule(long id) {
        try {
            Classes classes = DatabaseLearningManagerSystem.getInstance(this).classDAO().getClassesById((int) id);
            Date courseStart = classes.getCourseStart();
            Date courseEnd = classes.getCourseEnd();

            // Khởi tạo một calendar bắt đầu từ classStart
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(courseStart);

            while (calendar.getTime().before(courseEnd)) {
                Schedule schedule = new Schedule();
                schedule.setClassId(classes.getClassId());

                //code random
                String randomCode = generateRandomCode();
                schedule.setClassScheduleCode(randomCode);

                schedule.setSpecificDate(calendar.getTime());

                // Insert schedule vào database
                DatabaseLearningManagerSystem.getInstance(this).scheduleDAO().insertSchedule(schedule);

                // Tăng ngày trong calendar lên 1
                calendar.add(Calendar.DATE, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateRandomCode() {
        int length = 6;

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder codeBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            codeBuilder.append(randomChar);
        }

        return codeBuilder.toString();
    }


    private void eventDialogSure() {
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    long id = CreateClass();
                    insertSchedule(id);
                    Toast.makeText(CreationClassActivity.this, "Created class successfully!", Toast.LENGTH_SHORT).show();
                    clearInput();
                    dialogSure.dismiss();
                } catch (ParseException e) {
                    Toast.makeText(CreationClassActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSure.dismiss();
            }
        });
    }

    protected void setAdjustScreen(){
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    public void getFormWidget() {
        et_className = findViewById(R.id.et_class_name);
        et_classSize = findViewById(R.id.et_class_size);
        et_courseStart = findViewById(R.id.et_class_starting_date);
        et_courseEnd = findViewById(R.id.et_class_ending_date);
        et_studyingDates = findViewById(R.id.et_studying_date);
        et_classStart = findViewById(R.id.et_starting_time);
        et_classEnd = findViewById(R.id.et_finishing_time);
        cmbBox_course = findViewById(R.id.auto_compl_tv_course);
        cmbBox_teacherInfo = findViewById(R.id.auto_compl_tv_teacher_name);
        btn_createClass = findViewById(R.id.btn_createClass);
        btn_clearText = findViewById(R.id.btn_clearText);
        btn_back = findViewById(R.id.btn_create_class_back);
        imv_classPicture = findViewById(R.id.imv_class_picture);
        btn_courseStart = findViewById(R.id.img_courseStart);
        btn_courseEnd = findViewById(R.id.img_courseEnd);
        et_classFee = findViewById(R.id.et_class_fee);
        cmbBox_classDescription = findViewById(R.id.auto_compl_tv_class_description);
        img_classStart = findViewById(R.id.img_class_start);
        img_classEnd = findViewById(R.id.img_class_end);

        List<Teacher> allTeachers;
        allTeachers = DatabaseLearningManagerSystem.getInstance(this).teacherDAO().getAllTeacher();
        ArrayAdapter<Teacher> teachersAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, allTeachers);
        cmbBox_teacherInfo.setAdapter(teachersAdapter);
        cmbBox_teacherInfo.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmbBox_teacherInfo.showDropDown();
            }
        });

        String[] array = {"TOEIC", "IELTS", "Giao tiếp cơ bản"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, array);
        cmbBox_course.setAdapter(adapter);
        cmbBox_course.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmbBox_course.showDropDown();
            }
        });
        String[] classDescriptions = {
                "Khoá học TOEIC Prep giúp bạn nâng cao điểm số TOEIC của mình một cách hiệu quả và nhanh chóng. Tập trung vào hai phần chính của bài kiểm tra, Listening và Reading, khoá học này cung cấp chiến lược làm bài thi, bài tập thực hành, và phản hồi cá nhân để bạn tự tin vượt qua thử thách của bài kiểm tra TOEIC.",
                "Khoá học Mastering TOEIC là chìa khóa cho sự thành công trong bài kiểm tra TOEIC. Với phương pháp học tiên tiến và tài liệu giảng dạy chất lượng, bạn sẽ được trang bị những kỹ năng cần thiết để tự tin đối mặt với cả hai phần Listening và Reading của bài thi TOEIC. Vượt qua khoá học này, bạn sẽ không chỉ nắm vững kiến thức ngôn ngữ mà còn có khả năng áp dụng chúng một cách linh hoạt và hiệu quả trong môi trường làm việc và học tập.",
                "Khoá học \"IELTS Success\" là hành trang không thể thiếu cho những ai đang chuẩn bị cho kỳ thi IELTS. Với sự hỗ trợ chuyên sâu từ giảng viên có kinh nghiệm và tài liệu ôn luyện chất lượng, bạn sẽ được trang bị những kỹ năng cần thiết để tự tin đối mặt với cả bốn phần của bài kiểm tra: Listening, Reading, Writing và Speaking. Với phương pháp học linh hoạt và tập trung vào các kỹ năng cần thiết, khoá học này giúp bạn chuẩn bị một cách hiệu quả và đạt được kết quả tốt nhất trong kỳ thi IELTS.",
                "Khoá học \"IELTS Mastery\" là hành trình đưa bạn từ nơi bạn đang đứng đến mục tiêu điểm số IELTS mơ ước của bạn. Với phương pháp học đa dạng và tập trung, bạn sẽ học cách xử lý mỗi phần của bài kiểm tra IELTS với sự tự tin và hiệu quả. Từ việc cải thiện kỹ năng nghe và đọc đến viết và giao tiếp, khoá học này cung cấp một môi trường học tập tích cực và động viên, giúp bạn vượt qua mọi thách thức và đạt được thành công đỉnh cao trong kỳ thi quan trọng này.",
                "Khoá học \"Effective English Communication\" là bước đệm quan trọng cho việc xây dựng sự tự tin trong việc giao tiếp bằng tiếng Anh. Bằng cách kết hợp các phương pháp học tập đa dạng, bài tập thực hành và các hoạt động tương tác, khoá học này giúp bạn phát triển và nâng cao kỹ năng nghe, nói, đọc và viết tiếng Anh một cách tự tin và linh hoạt.",
                "Khoá học \"Confident Conversations\" là điểm xuất phát hoàn hảo cho việc phát triển kỹ năng giao tiếp tiếng Anh của bạn. Với sự tập trung vào việc xây dựng sự tự tin trong giao tiếp hàng ngày, khoá học này cung cấp một môi trường học tập thân thiện và động viên, giúp bạn thảo luận, trao đổi ý kiến và thực hành kỹ năng giao tiếp một cách tự tin và trôi chảy.",
                "Khoá học \"TOEIC Mastery\" là hành trình đi đến sự thành công tối đa trong bài kiểm tra TOEIC của bạn. Với sự kết hợp của lộ trình học tập cấp tiến, bài tập thực hành và phản hồi cá nhân, khoá học này mang đến cho bạn một cơ hội tuyệt vời để nắm vững cả hai phần Listening và Reading của bài kiểm tra TOEIC."
        };
        ArrayAdapter<String> classDescriptionArrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, classDescriptions);
        cmbBox_classDescription.setAdapter(classDescriptionArrayAdapter);
        cmbBox_classDescription.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmbBox_classDescription.showDropDown();
            }
        });
    }

    public long CreateClass() throws ParseException {
        long a;
        Classes newClass = SetClassData();
        a = DatabaseLearningManagerSystem.getInstance(this).classDAO().insertNewClass(newClass);
        return a;
    }



    public Classes SetClassData() throws ParseException {
        byte[] picture = getByteArrayFromImageView(imv_classPicture);
        String name = et_className.getText().toString();
        String course = cmbBox_course.getText().toString();
        int size = Integer.valueOf(et_classSize.getText().toString());
        String teacherInfo = cmbBox_teacherInfo.getText().toString();
        Teacher teacher = FindTeacher(teacherInfo);

        String temp_courseStart = et_courseStart.getText().toString();
        Date courseStart = new SimpleDateFormat("dd/MM/yyyy").parse(temp_courseStart);
        String temp_courseEnd = et_courseEnd.getText().toString();
        Date courseEnd = new SimpleDateFormat("dd/MM/yyyy").parse(temp_courseEnd);

        String studyDates = et_studyingDates.getText().toString();

        String tempTime_classStart = et_classStart.getText().toString();
        String tempTime_classEnd = et_classEnd.getText().toString();
        String tempDate_classStart = "01/01/1999 " + tempTime_classStart; //gắn đại ngày tháng năm vào để cho thành kiểu Date, chủ yếu lấy time
        String tempDate_classEnd = "01/01/1999 " + tempTime_classEnd;
        Date classStart = new SimpleDateFormat("dd/MM/yyyy HH:MM").parse(tempDate_classStart);
        Date classEnd = new SimpleDateFormat("dd/MM/yyyy HH:MM").parse(tempDate_classEnd);
        long classFee = Long.valueOf(et_classFee.getText().toString());
        String classDescription = cmbBox_classDescription.getText().toString();

        Classes newClass = new Classes(picture, name, course, size, teacher.getTeacherId(), courseStart, courseEnd, studyDates, classStart, classEnd, classFee, classDescription);
        return newClass;
    }

    public Teacher FindTeacher(String teacherInfo) {
        char temp_id = teacherInfo.charAt(0);
        int id = Character.getNumericValue(temp_id);
        return DatabaseLearningManagerSystem.getInstance(this).teacherDAO().getTeacherById(id);
    }
}