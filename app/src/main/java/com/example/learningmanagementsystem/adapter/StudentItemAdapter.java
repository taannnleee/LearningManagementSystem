package com.example.learningmanagementsystem.adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.Student;
import com.example.learningmanagementsystem.models.StudentClassCrossRef;

import java.util.List;

public class StudentItemAdapter extends BaseAdapter {
    final List<StudentClassCrossRef> studentClassCrossRefs;

    public StudentItemAdapter(List<StudentClassCrossRef> studentClassCrossRefs) {
        this.studentClassCrossRefs = studentClassCrossRefs;
    }

    @Override
    public int getCount() {
        //Trả về tổng số phần tử, nó được gọi bởi ListView
        return studentClassCrossRefs.size();
    }

    @Override
    public Object getItem(int position) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        //có chỉ số position trong listProduct
        return studentClassCrossRefs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return studentClassCrossRefs.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        //Nếu null cần tạo mới

        View viewStudent;
        if (convertView == null) {
            viewStudent = View.inflate(parent.getContext(), R.layout.student_item, null);
        } else viewStudent = convertView;

        //Bind sữ liệu phần tử vào View
        StudentClassCrossRef studentClassCrossRef = (StudentClassCrossRef) getItem(position);

        Student student = findStudent(studentClassCrossRef.getStudentId(), parent);
        Classes classes = findClasses(studentClassCrossRef.getCourseId(), parent);

        ((TextView) viewStudent.findViewById(R.id.tv_Id)).setText(studentClassCrossRef.getId()+"");
        ((TextView) viewStudent.findViewById(R.id.tv_StudentName)).setText(student.getStudentName()+"");
        ((TextView) viewStudent.findViewById(R.id.tv_className)).setText(classes.getClassName()+"");


        return viewStudent;
    }

    public Student findStudent(int id, ViewGroup parent) {
        return DatabaseLearningManagerSystem.getInstance(parent.getContext()).studentDAO().getStudentById(id);
    }

    public Classes findClasses(int id, ViewGroup parent) {
        return DatabaseLearningManagerSystem.getInstance(parent.getContext()).classDAO().getClassesById(id);
    }
}
