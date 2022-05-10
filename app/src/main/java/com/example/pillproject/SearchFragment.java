package com.example.pillproject;

//import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchFragment extends Fragment
{
    View thisView;
    SearchView searchView;
    ListView listView;
    ArrayList<String> dataList;
    ArrayAdapter<String> dataListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        thisView = inflater.inflate(R.layout.fragment_search, container, false);
        assignElements();
        setListeners();

        return thisView;
    }

    private void assignElements()
    {
        dataList = new ArrayList<>();
        dataList.add("Vyvanse");
        dataList.add("Zoloft");
        dataList.add("Clonazepam");
        dataList.add("Meth");
        dataList.add("Cannabis");
        dataList.add("Perc 30");
        dataList.add("Lean");

        searchView = thisView.findViewById(R.id.searchView);
        listView = thisView.findViewById(R.id.listView);

        dataListAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(dataListAdapter);
    }

    private void setListeners()
    {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                if(dataList.contains(query)) { dataListAdapter.getFilter().filter(query); }
                else { Toast.makeText(thisView.getContext(), "No Match Found",Toast.LENGTH_SHORT).show(); }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                dataListAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}