package com.example.sqlite2_tablayout.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite2_tablayout.R;
import com.example.sqlite2_tablayout.adapter.RecycleViewAdapter;
import com.example.sqlite2_tablayout.model.CongViec;
import com.example.sqlite2_tablayout.sqlite.SQLiteHelper;

import java.util.Calendar;
import java.util.List;

public class FragmentSearch extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private SearchView searchView;
    private Spinner spCategory;
    private RecycleViewAdapter adapter;
    private SQLiteHelper db;
    private EditText efrom, eto;
    private Button btSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timkiem,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //gan thong tin
        recyclerView=view.findViewById(R.id.recycleView);
        searchView=view.findViewById(R.id.search);
        spCategory=view.findViewById(R.id.spCategory);
        efrom = view.findViewById(R.id.eFrom);
        eto = view.findViewById(R.id.eTo);
        btSearch = view.findViewById(R.id.btSearch);
        //chen them tim kiem tat ca
        String[] arr=getResources().getStringArray(R.array.tinhtrang);
        String[] arr1=new String[arr.length+1];
        arr1[0]="All";
        for(int i=0;i<arr.length;i++){
            arr1[i+1]=arr[i];
        }
        spCategory.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.item_spinner,arr1));


        //khoi tao du lieu
        adapter=new RecycleViewAdapter();
        db=new SQLiteHelper(getContext());
        List<CongViec> list=db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<CongViec> list =db.searchByTen(s);
                adapter.setList(list);
                return true;
            }
        });
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String cate= spCategory.getItemAtPosition(position).toString();
                List<CongViec> list1;
                if(!cate.equalsIgnoreCase("All")){
                    list1=db.searchByCategory(cate);
                }else
                    list1=db.getAll();
                adapter.setList(list1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        efrom.setOnClickListener(this);
        eto.setOnClickListener(this);
        btSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == efrom){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if (m>8){
                        date = d+"/"+(m+1)+"/"+y;
                    }else {
                        date = d+"/0"+(m+1)+"/"+y;
                    }
                    efrom.setText(date);
                }
            },year, month,day);
            dialog.show();
        }
        if (view == eto){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if (m>8){
                        date = d+"/"+(m+1)+"/"+y;
                    }else {
                        date = d+"/0"+(m+1)+"/"+y;
                    }
                    eto.setText(date);
                }
            },year, month,day);
            dialog.show();
        }
        if (view == btSearch){
            String from = efrom.getText().toString();
            String to = eto.getText().toString();
            if(!from.isEmpty()&&!to.isEmpty()){
                List<CongViec> list = db.searchByDate(from,to);
                adapter.setList(list);
            }
        }
    }
}
