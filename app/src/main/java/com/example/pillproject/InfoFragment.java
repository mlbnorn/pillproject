package com.example.pillproject;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

public class InfoFragment extends Fragment
{
    // UI
    View thisView;
    TextView textView;
    TextView desc;
    String drug_id;
    Drug thisDrug;

    // Backend
    ArrayList<Drug> dataList;

    public InfoFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        thisView = inflater.inflate(R.layout.fragment_info, container, false);
        drug_id = getArguments().getString("drug_id");
        dataList = ((MainActivity) getActivity()).getDataList();
        setDrug();
        assignElements();

        return thisView;
    }

    private void setDrug()
    {
        for (Drug drug:dataList)
        {
            if (drug_id.equals(drug.brand_name))
            {
                thisDrug = drug;
            }
        }
    }

    private void assignElements()
    {
        textView = (TextView) thisView.findViewById(R.id.desc_header);
        textView.setText(thisDrug.brand_name);

        desc = (TextView) thisView.findViewById(R.id.desc_desc);
        desc.setText(thisDrug.scientific_name);

        dataList = ((MainActivity) getActivity()).getDataList();
    }

}
