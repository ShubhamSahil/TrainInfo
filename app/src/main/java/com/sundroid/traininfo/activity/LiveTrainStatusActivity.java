package com.sundroid.traininfo.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sundroid.traininfo.R;
import com.sundroid.traininfo.Utils.WebUrls;
import com.sundroid.traininfo.adapter.Trainlive_runningAdapter;
import com.sundroid.traininfo.webservices.WebServiceBase;
import com.sundroid.traininfo.webservices.WebServicesCallBack;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveTrainStatusActivity extends AppCompatActivity implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener,WebServicesCallBack {
    private final static String LIVE_TRAIN_API_CALL="live_train_api_call";
    private final String TAG=getClass().getSimpleName();
    public  ArrayList<InfoApps> traindataarray;
    InfoApps Userdatas;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.text1)
    TextView text1;@BindView(R.id.text2)
    TextView text2;@BindView(R.id.text3)
    TextView text3;@BindView(R.id.text4)
    TextView text4;@BindView(R.id.text5)
    TextView text5;@BindView(R.id.text6)
    TextView text6;@BindView(R.id.text7)
    TextView text7;@BindView(R.id.text8)
    TextView text8;
    @BindView(R.id.txt_act_time)
    TextView txt_act_time;@BindView(R.id.txt_arrival_name)
    TextView txt_arrival_name;@BindView(R.id.txt_depature_time)
    TextView txt_depature_time;
    @BindView(R.id.et_train_number)
    TextView et_train_number;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.txt_train_name)
    TextView tv_trainname;
    @BindView(R.id.txt_journeystation)
    TextView txt_journeystation;
    @BindView(R.id.txt_train_date)
    TextView txt_train_date;
    @BindView(R.id.txt_journeydate)
    TextView txt_journeydate;
    @BindView(R.id.txt_expectedarrival)
    TextView txt_expectedarrival;
    @BindView(R.id.txt_schedulearrival)
    TextView txt_schedulearrival;
    @BindView(R.id.txt_delayarrival)
    TextView txt_delayarrival;
    @BindView(R.id.txt_scheduledep)
    TextView txt_scheduledep;
    @BindView(R.id.txt_expdep)
    TextView txt_expdep;
    @BindView(R.id.edtxt_delay)
    TextView edtxt_delay;
    @BindView(R.id.edtxt_last_location)
    TextView edtxt_last_location;
    @BindView(R.id.edtxt_upcomoingnonstoppingstation)
    TextView edtxt_upcomoingnonstoppingstation;
    @BindView(R.id.edtxt_upcomoingstoppingstation)
    TextView edtxt_upcomoingstoppingstation;
//    @BindView(R.id.tv_date)
//    TextView tv_date;
//    @BindView(R.id.tv_date)
//    TextView tv_date;
//    @BindView(R.id.tv_date)
//    TextView tv_date;
    @BindView(R.id.btn_search)
    Button btn_search;
    @BindView(R.id.btn_search_all)
    Button btn_search_all;
    @BindView(R.id.spinnerstationloca)
    Spinner spinnerstationloca;
    @BindView(R.id.sv)
    ScrollView sv;
    Trainlive_runningAdapter mAdapterbroad;
    private RecyclerView mRecyclerView;
    private LinearLayout linearlayout;
    String string_date="";
    public ArrayList<InfoApps> arraylist;
    public ArrayList<String> arraylistlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_train_status);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        traindataarray=new ArrayList<InfoApps>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        sv = (ScrollView) findViewById(R.id.sv);
        spinnerstationloca = (Spinner) findViewById(R.id.spinnerstationloca);
        linearlayout = (LinearLayout) findViewById(R.id.linearlayout);

        Typeface tf= Typeface.createFromAsset(getAssets(),"fonts/Roboto-Regular.ttf");
        txt_train_date.setTypeface(tf);
        tv_trainname.setTypeface(tf);
        txt_journeystation.setTypeface(tf);
        txt_journeydate.setTypeface(tf);
        txt_schedulearrival.setTypeface(tf);
        txt_expectedarrival.setTypeface(tf);
        txt_delayarrival.setTypeface(tf);
        txt_scheduledep.setTypeface(tf);
        txt_expdep.setTypeface(tf);
        text1.setTypeface(tf);
        text2.setTypeface(tf);
        text3.setTypeface(tf);
        text4.setTypeface(tf);
        text5.setTypeface(tf);
        text6.setTypeface(tf);
        text7.setTypeface(tf);
        text8.setTypeface(tf);
//        text9.setTypeface(tf);
//        text10.setTypeface(tf);
        txt_act_time.setTypeface(tf);
        txt_arrival_name.setTypeface(tf);
        txt_depature_time.setTypeface(tf);
        edtxt_last_location.setTypeface(tf);
        edtxt_upcomoingstoppingstation.setTypeface(tf);
        edtxt_upcomoingnonstoppingstation.setTypeface(tf);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(LiveTrainStatusActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        tv_date.setOnClickListener(this);
        btn_search.setOnClickListener(this);
        btn_search_all.setOnClickListener(this);
        arraylist =new ArrayList<InfoApps>();
        arraylistlocation =new ArrayList<String>();
        arraylistlocation.add("Select station");
        spinnerstationloca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position <1){
//                    arraylist.clear();
                }
                else {
                    int positionforstation =spinnerstationloca.getSelectedItemPosition();
                    txt_train_date.setText(arraylist.get(positionforstation-1).getTraindate());
                    tv_trainname.setText(arraylist.get(positionforstation-1).getTrain_number());
                    txt_journeystation.setText(arraylist.get(positionforstation-1).getStation_name());
                    txt_journeydate.setText(arraylist.get(positionforstation-1).getDay());
                    txt_schedulearrival.setText(arraylist.get(positionforstation-1).getScharr_expectedarrivaltime());
                    txt_expectedarrival.setText(arraylist.get(positionforstation-1).getAct_dep());
                    txt_delayarrival.setText(arraylist.get(positionforstation-1).getTrain_status());
                    txt_scheduledep.setText(arraylist.get(positionforstation-1).getSchdeptime());
                    txt_expdep.setText(arraylist.get(positionforstation-1).getExp_dep());
                    edtxt_last_location.setText(arraylist.get(positionforstation-1).getPlatform_no());
                    edtxt_upcomoingnonstoppingstation.setText(arraylist.get(positionforstation-1).getTrain_status_position());
                    edtxt_upcomoingstoppingstation.setText(arraylist.get(positionforstation-1).getDay());
                    Log.d("positionforstation",positionforstation+"");



                    try {
//                        initProgressDialog("Please wait...");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.tv_date:
                selectDate();
                break;
            case R.id.btn_search:
                callAPI();
                break;
            case R.id.btn_search_all:
                mRecyclerView.setVisibility(View.VISIBLE);
                sv.setVisibility(View.GONE);
//                callAPI();
                break;
        }
    }
    public void callAPI(){
        if(et_train_number.getText().toString().length()>0&&string_date.length()>0) {
            String url = WebUrls.getLiveTrainURL(et_train_number.getText().toString(), string_date);
            string_date = "";
            Log.d(TAG,"url:-"+url);
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            new WebServiceBase(nameValuePairs, this, LIVE_TRAIN_API_CALL).execute(url);
        }
        else{
            Toast.makeText(getApplicationContext(),"Please Enter Information Correctly",Toast.LENGTH_LONG).show();
        }
    }
    public void selectDate(){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                LiveTrainStatusActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        int month=monthOfYear+1;
        if(String.valueOf(month).length()==1 ) {
            if (dayOfMonth < 10) {
                string_date = String.valueOf(year) + "0" + String.valueOf(month) + "0" + String.valueOf(dayOfMonth);
            } else {
                string_date = String.valueOf(year) + "0" + String.valueOf(month) + String.valueOf(dayOfMonth);
            }
        }
//        else if (dayOfMonth<10){
//            string_date=String.valueOf(year)+String.valueOf(month)+"0"+String.valueOf(dayOfMonth);
//        }
        else{
            if (dayOfMonth < 10) {
                string_date = String.valueOf(year) + String.valueOf(month) + "0" + String.valueOf(dayOfMonth);
            } else {
                string_date = String.valueOf(year) + String.valueOf(month) + String.valueOf(dayOfMonth);
            }
        }

        tv_date.setText(string_date);
    }

    @Override
    public void onGetMsg(String[] msg) {
        String apicall=msg[0];
        String response=msg[1];
        switch (apicall){
            case LIVE_TRAIN_API_CALL:
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("arraylist",jsonObject.toString());
                    if (arraylistlocation.size()>0) {
                        arraylistlocation.clear();
                    }
                    else{

                    }
//                    arraylistlocation.add("Shubham sahil");
                    arraylistlocation.add("Select Station");
//                    arraylistlocation.add("Amar Sharma");
                    ArrayAdapter<String> spinnerArrayAdapte = new ArrayAdapter<String>(
                            getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arraylistlocation);
                    spinnerstationloca.setAdapter(spinnerArrayAdapte);
if (response.startsWith("<!")){
    Toast.makeText(getApplicationContext(),"Server issue occured",Toast.LENGTH_LONG).show();
}
                    else{
                    JSONArray jsonArray=jsonObject.getJSONArray("route");
                    for (int i =0; i<jsonArray.length();i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONObject jsonObject2 = jsonObject1.getJSONObject("station_");
                        String station_name = jsonObject2.getString("name");
                        String station_code = jsonObject2.getString("code");
                        String train_status = jsonObject1.getString("status");
                        String day = jsonObject1.getString("day");
                        String scharr_expectedarrivaltime = jsonObject1.getString("scharr");
                        String actarr_expectedarrivaltime = jsonObject1.getString("actarr");
                        String actdep_expectedarrivaltime = jsonObject1.getString("actdep");
                        String schdeptime = jsonObject1.getString("schdep");
                        String platform_no = jsonObject1.getString("no");
                        String station_distance = jsonObject1.getString("distance");
                        JSONObject jsonObject4 = jsonObject.getJSONObject("current_station");
                        String current_station = jsonObject.getString("position");
                        String train_date = jsonObject.getString("start_date");
                        String train_number = jsonObject.getString("train_number");
                        Userdatas = new InfoApps();
                        Userdatas.setStation_name(station_name + " " + "(" + station_code + ")");
                        Userdatas.setStation_code(station_code);
                        Userdatas.setTrain_status(train_status);
                        Userdatas.setTrain_status_position(current_station);
                        Userdatas.setDay(day);
                        Userdatas.setTraindate(train_date);
                        Userdatas.setScharr_expectedarrivaltime(scharr_expectedarrivaltime);
                        Userdatas.setSchdeptime(schdeptime);
                        Userdatas.setActarr_expectedarrivaltime(actarr_expectedarrivaltime + " " + "/" + actdep_expectedarrivaltime);
                        Userdatas.setExp_dep(actdep_expectedarrivaltime);
                        Userdatas.setAct_dep(actarr_expectedarrivaltime);
                        Userdatas.setPlatform_no(platform_no);
                        Userdatas.setStation_distance(station_distance);
                        Userdatas.setTrain_number(train_number);
                        traindataarray.add(Userdatas);
//                        mRecyclerView.setVisibility(View.VISIBLE);
                        sv.setVisibility(View.VISIBLE);
                        btn_search.setVisibility(View.GONE);
                        btn_search_all.setVisibility(View.VISIBLE);
//                        linearlayout.setVisibility(View.GONE);
                        arraylist.add(Userdatas);
                        arraylistlocation.add(Userdatas.getStation_name());
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                                getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arraylistlocation);
                        spinnerstationloca.setAdapter(spinnerArrayAdapter);
                        Log.e("arraylist", arraylist.toString());
                        mAdapterbroad = new Trainlive_runningAdapter(traindataarray, LiveTrainStatusActivity.this);
                        mRecyclerView.setAdapter(mAdapterbroad);
                    }
                    }
                }
                catch (Exception e){
                    Log.d("error",e.toString());
                }
                Log.d(TAG,"response:-"+response);
                break;
        }
    }

}
