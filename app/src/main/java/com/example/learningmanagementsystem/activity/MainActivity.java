package com.example.learningmanagementsystem.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.Student;
import com.example.learningmanagementsystem.models.StudentClassCrossRef;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainActivity extends Fragment  {

//    String[] newArr = new String[10];
    private String[] arr = {};

//    private String[] arr = null;

    //    private List<String> arr = new ArrayList<>();
    private ImageView imvAvatar;
    private TextView tv_absent, tvSchedule, tvScheduleDetail, tvName;
    Spinner spClassCourse;

    private TextView textViewSupport;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static MainActivity newInstance(String param1, String param2) {
        MainActivity fragment = new MainActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private class MyProcessEvent implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            tvName.setText(arr[position]);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            tvName.setText("");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view =  inflater.inflate(R.layout.activity_main, container, false);
        loadData();
        getFormWidgets(view);
        setList(view);
        addEvent();

        return view;
    }

    private void loadData() {
        List<String> classNames = new ArrayList<>();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String current_studentId = sharedPreferences.getString("current_studentId", "defaultValue");

        List<StudentClassCrossRef> listStudentClassCrossRef =  DatabaseLearningManagerSystem.getInstance(getContext()).studentClassCrossRefDAO().getLisstudentIdByStudentIdAndStatus(Integer.parseInt(current_studentId), "active");

        for (int i = 0; i < listStudentClassCrossRef.size(); i++) {
            StudentClassCrossRef studentClassCrossRef = listStudentClassCrossRef.get(i);
            int temp = studentClassCrossRef.getCourseId();

            Classes classes = new Classes();
            classes =  (Classes) DatabaseLearningManagerSystem.getInstance(getContext()).classDAO().getClassesById(temp);
            classNames.add(classes.getClassName());
        }
        arr = classNames.toArray(new String[0]);
    }

    private void setList(View view) {
        tv_absent = view.findViewById(R.id.tv_absent);
        spClassCourse = view.findViewById(R.id.spClassCourse);
        tvSchedule = view.findViewById(R.id.tvSchedule);
        tvScheduleDetail = view.findViewById(R.id.tvScheduleDetail);
        tvName = view.findViewById(R.id.tvName);
        imvAvatar = view.findViewById(R.id.imvAvatar);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spClassCourse.setAdapter(adapter);
        spClassCourse.setOnItemSelectedListener(new MyProcessEvent());

//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
//        String current_studentId = sharedPreferences.getString("current_studentId", "defaultValue");
//
//        StudentClassCrossRef studentClassCrossRef =  DatabaseLearningManagerSystem.getInstance(getContext()).studentClassCrossRefDAO().getLisstudentIdByStudentIdAndStatus1(Integer.parseInt(current_studentId), "active");
//
//        Student student = DatabaseLearningManagerSystem.getInstance(getContext()).studentDAO().getStudentById(Integer.parseInt(current_studentId));
//        tvName.setText(student.getStudentName());
        //        classCourse.setText();
//        tvSchedule.s
//        tvScheduleDetail =
//        imvAvatar

    }

    private void addEvent() {
        tv_absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "Succes", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MainAbsentActivity.class);
                startActivity(intent);
            }
        });
        textViewSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "Succes", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), SupportActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getFormWidgets(View view) {
        tv_absent = view.findViewById(R.id.tv_absent);

        spClassCourse = view.findViewById(R.id.spClassCourse);
        tvSchedule = view.findViewById(R.id.tvSchedule);
        tvScheduleDetail = view.findViewById(R.id.tvScheduleDetail);
        tvName = view.findViewById(R.id.tvName);
        imvAvatar = view.findViewById(R.id.imvAvatar);
        textViewSupport = view.findViewById(R.id.textViewSupport);
    }
}