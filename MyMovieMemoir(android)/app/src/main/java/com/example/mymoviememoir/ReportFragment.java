package com.example.mymoviememoir;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mymoviememoir.utils.BarChartManager;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.zyyoona7.wheel.WheelView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ReportFragment extends Fragment implements CalendarView.OnDateSelectedListener {

    CalendarView calendarView;
    Button btn_check;
    PieChart pc_chat;
    WheelView<Integer> wv_view;
    BarChart bc_view;

    private HashMap<Integer,List<Integer>> map;
    private List<Integer> da;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_report, container, false);

        initView(root);

        return root;
    }

    private void initView(View root) {
        calendarView = root.findViewById(R.id.calendarView);
        btn_check = root.findViewById(R.id.btn_check);
        pc_chat = root.findViewById(R.id.pc_chat);
        wv_view = root.findViewById(R.id.wv_view);
        bc_view = root.findViewById(R.id.bc_view);


        calendarView.setOnDateSelectedListener(this);
        btn_check.setOnClickListener(view -> changePie());


        List<Integer> month = new ArrayList<>();
        month.add(2015);
        month.add(2016);
        month.add(2017);
        month.add(2018);
        month.add(2019);
        month.add(2020);

        map = new HashMap<>();
        for (int i =0; i< month.size();i++) {
            List<Integer> floats = new ArrayList<>();
            for (int j = 0 ; j < 12; j++) {
                floats.add(new Random().nextInt(100));
            }
            map.put(month.get(i),floats);
        }

        da = new ArrayList<>();
        for (int i = 0 ;i < 12; i++) {
            da.add(i + 1);
        }

        wv_view.setData(month);
        wv_view.setOnItemSelectedListener((wheelView, data, position) -> {

            List<Integer> floats = map.get(data);

            BarChartManager chartManager = new BarChartManager(bc_view);
            chartManager.showBarChart(da,floats,"movie", Color.parseColor("#0077ff"));

        });

    }

    @Override
    public void onDateSelected(Calendar calendar, boolean isClick) {

        changePie();
    }

    protected void changePie() {
        List<PieEntry> strings = new ArrayList<>();

        Random random = new Random();

        int i = random.nextInt(100);
        strings.add(new PieEntry(i,"VIC"));

        if (i < 100) {
            int i1 = random.nextInt(100 - i);
            if (i1 + i > 100) {
                i1 = 100 - i;
            }
            strings.add(new PieEntry(i1,"NT"));
            if (i1+i < 100) {
                int i2 = random.nextInt(100 - i - i1);
                if (i2 + i1 + i > 100) {
                    i2 = 100 - i - i1;
                }
                strings.add(new PieEntry(i2,"SA"));

                if (i+i1+i2 < 100) {
                    int i3 = random.nextInt(100 - i - i1 - i2);
                    if (i3+i2 + i1 + i > 100) {
                        i3 = 100 - i - i1 - i2;
                    }
                    strings.add(new PieEntry(i3,"ACT"));
                    if (i+i1+i2+i3 < 100) {
                        int i4 = 100 - i - i1-i2-i3;
                        strings.add(new PieEntry(i4,"WA"));
                    }
                }
            }
        }


        PieDataSet dataSet = new PieDataSet(strings,"Label");

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int j = 0; j < strings.size(); j++) {
            if (j == 0) {
                colors.add(getResources().getColor(R.color.colorAccent));
            }else if (j == 1) {
                colors.add(getResources().getColor(R.color.colorPrimary));
            }else if (j == 2) {
                colors.add(getResources().getColor(R.color.colorPrimaryDark));
            }else if (j == 3) {
                colors.add(getResources().getColor(R.color.design_default_color_error));
            }else if (j == 4) {
                colors.add(getResources().getColor(R.color.browser_actions_divider_color));
            }
        }
        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);

        pc_chat.setData(pieData);
        pc_chat.invalidate();
    }
}
