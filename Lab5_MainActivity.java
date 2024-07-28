package fpoly.trungtdph27790.lab1_firebase.lab5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fpoly.trungtdph27790.lab1_firebase.R;
import fpoly.trungtdph27790.lab1_firebase.lab5.delete.InterfaceDelete;
import fpoly.trungtdph27790.lab1_firebase.lab5.select.SvrResponseSelect;
import fpoly.trungtdph27790.lab1_firebase.lab5.select.interfaceSelect;
import fpoly.trungtdph27790.lab1_firebase.lab5.update.InterfaceUpdate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Lab5_MainActivity extends AppCompatActivity {
    EditText txt1, txt2,txt3 ;
    TextView tvkq ;
    Button btn1,btn2,btn3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab5_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        tvkq = findViewById(R.id.tvkq);
        btn1 = findViewById(R.id.lab5);
        btn2 = findViewById(R.id.delete);
        btn3 = findViewById(R.id.update);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                insertData(txt1,txt2,txt3,tvkq);
                selectData();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteData(txt1);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData(txt1,txt2,txt3,tvkq);
            }
        });
    }
    private void insertData(EditText txt1, EditText txt2, EditText txt3, TextView tvkq){
        //
        sanpham s = new sanpham(txt1.getText().toString(),
                txt2.getText().toString(),
                txt3.getText().toString());
        //
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.31.42/lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //
        interfaceInsertSanPham insertSanPham = retrofit.create(interfaceInsertSanPham.class);
        //
        Call<SvrResponseSanPham> call = insertSanPham.insertSanPham(s.getMaSp(),s.getTenSp(),s.getMota());
        //
        call.enqueue(new Callback<SvrResponseSanPham>() {
            @Override
            public void onResponse(Call<SvrResponseSanPham> call, Response<SvrResponseSanPham> response) {
                SvrResponseSanPham res =  response.body();
                tvkq.setText(res.getMessage());
            }

            @Override
            public void onFailure(Call<SvrResponseSanPham> call, Throwable t) {
                tvkq.setText(t.getMessage());
            }
        });
    }
    private void DeleteData(EditText txt1){
        //b2. tao doi tuong retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.31.42/lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b3. goi ham insert
        InterfaceDelete deleteSanPham
                =retrofit.create(InterfaceDelete.class);
        //chuan bi ham
        Call<SvrResponseSanPham> call
                =deleteSanPham.deleteSanPham(txt1.getText().toString());
        //thuc thi ham
        call.enqueue(new Callback<SvrResponseSanPham>() {
            //thanh cong
            @Override
            public void onResponse(Call<SvrResponseSanPham> call, Response<SvrResponseSanPham> response) {
                SvrResponseSanPham res=response.body();
                tvkq.setText(res.getMessage());
            }
            //that bai
            @Override
            public void onFailure(Call<SvrResponseSanPham> call, Throwable t) {
                tvkq.setText(t.getMessage());
            }
        });

    }
    private void updateData(EditText txt1, EditText txt2, EditText txt3, TextView tvKQ){
        //B1. tao doi tuong chua du lieu
        sanpham s=new sanpham(txt1.getText().toString(),
                txt2.getText().toString(),txt3.getText().toString());
        //b2. tao doi tuong retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.31.42/lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b3. goi ham insert
        InterfaceUpdate updateSanPham
                =retrofit.create(InterfaceUpdate.class);
        //chuan bi ham
        Call<SvrResponseSanPham> call
                =updateSanPham.updateSanPham(s.getMaSp(),s.getTenSp(),s.getMota());
        //thuc thi ham
        call.enqueue(new Callback<SvrResponseSanPham>() {
            //thanh cong
            @Override
            public void onResponse(Call<SvrResponseSanPham> call, Response<SvrResponseSanPham> response) {
                SvrResponseSanPham res=response.body();
                tvKQ.setText(res.getMessage());
            }
            //that bai
            @Override
            public void onFailure(Call<SvrResponseSanPham> call, Throwable t) {
                tvKQ.setText(t.getMessage());
            }
        });

    }
    String strKQ = "" ;
    List<sanpham> ls ;
    private void selectData(){
        strKQ="";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.31.42/lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //
        //
        interfaceSelect interfaceSelect = retrofit.create(interfaceSelect.class);
        //
        Call<SvrResponseSelect> call = interfaceSelect.selectSanPham();

        call.enqueue(new Callback<SvrResponseSelect>() {
            @Override
            public void onResponse(Call<SvrResponseSelect> call, Response<SvrResponseSelect> response) {
                SvrResponseSelect res = response.body(); // lấy kết quả từ server tar về
                // chuyển đổi sang dạng list
                ls = new ArrayList<>(Arrays.asList(res.getProducts()));
                // đọc list
                for (sanpham p: ls){
                    strKQ += "MaSP: "+p.getMaSp()+"  TenSP: "+p.getTenSp()+"  Mota: "+p.getMota()+"\n";
                }
                tvkq.setText(strKQ);
            }

            @Override
            public void onFailure(Call<SvrResponseSelect> call, Throwable t) {
                tvkq.setText(t.getMessage());
            }
        });
    }
}