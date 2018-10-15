package com.example.arshu.lab07;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
public class GradeArrayAdapter extends ArrayAdapter<Grades> {
    private Context mContext;
    private List<Grades> mData;

    public GradeArrayAdapter(Context context, List<Grades> data) {
        super(context, R.layout.grade_list_item, data);
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public View getView(int position, View reusableView, ViewGroup parent) {
        Grades grade = mData.get(position);

        if (reusableView == null) {
            // Create a new view (this is the first item)
            LayoutInflater inflater = LayoutInflater.from(mContext);
            reusableView = inflater.inflate(R.layout.grade_list_item, parent, false);
        }

        TextView lblGradeStudentID = reusableView.findViewById(R.id.lblGradeStudentID);
        TextView lblGradeCourseComponent = reusableView.findViewById(R.id.lblGradeCourseComponent);
        TextView lblGradeMark = reusableView.findViewById(R.id.lblGradeMark);

        lblGradeStudentID.setText(Integer.toString(grade.getStudentID()));
        lblGradeCourseComponent.setText(grade.getCourseComponent());
        lblGradeMark.setText(Float.toString(grade.getMark()));

        return reusableView;
    }
}
