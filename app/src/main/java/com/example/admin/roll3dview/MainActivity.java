package com.example.admin.roll3dview;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.library.Roll3DView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements myAdapter.CallBack{

    Roll3DView roll3DView; //百叶窗、轮转、开合效果必须设置partnum
    private MyPopwindow mListPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roll3DView = findViewById(R.id.rollview);
        roll3DView.setRollMode(Roll3DView.RollMode.Whole3D);//设置翻转模式 Roll2D
        BitmapDrawable bitmap1 = (BitmapDrawable)getResources().getDrawable(R.mipmap.img1);
        BitmapDrawable bitmap2 = (BitmapDrawable)getResources().getDrawable(R.mipmap.img2);
        roll3DView.addImageBitmap(bitmap1.getBitmap());
        roll3DView.addImageBitmap(bitmap2.getBitmap());

        findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll3DView.setRollDirection(Roll3DView.RollDirection.Landscape);
                roll3DView.toPre();
            }
        });

        findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll3DView.setRollDirection(Roll3DView.RollDirection.Landscape);
                roll3DView.toNext();
            }
        });

        findViewById(R.id.up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll3DView.setRollDirection(Roll3DView.RollDirection.Portrait);
                roll3DView.toNext();
            }
        });

        findViewById(R.id.down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll3DView.setRollDirection(Roll3DView.RollDirection.Portrait);
                roll3DView.toNext();
            }
        });

        findViewById(R.id.type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.pop_list,null);
                //处理popWindow 显示内容
                handleListView(contentView);
                //创建并显示popWindow
                mListPopWindow= new MyPopwindow.PopupWindowBuilder(MainActivity.this)
                        .setView(contentView)
                        .size(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)//显示大小
                        .create()
                        .showAsDropDown(roll3DView,0,0);
            }
        });

    }

    private void handleListView(View contentView){
        RecyclerView recyclerView = contentView.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        myAdapter adapter = new myAdapter();
        adapter.setData(mockData(), this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private List<String> mockData(){
        List<String> data = new ArrayList<>();
        data.add("3D翻转");
        data.add("2D平移");
        data.add("3D开合翻转");
        data.add("百叶窗");
        data.add("轮转效果");
        return data;
    }

    @Override
    public void btnClick(int position) {
        switch (position){
            case 0:
                roll3DView.setRollMode(Roll3DView.RollMode.Whole3D);//设置翻转模式 Roll2D
                mListPopWindow.dissmiss();
                break;
            case 1:
                roll3DView.setRollMode(Roll3DView.RollMode.Roll2D);//设置翻转模式 Roll2D
                mListPopWindow.dissmiss();
                break;
            case 2:
                roll3DView.setRollMode(Roll3DView.RollMode.SepartConbine);//设置翻转模式 开合效果
                roll3DView.setPartNumber(8);
                mListPopWindow.dissmiss();
                break;
            case 3:
                roll3DView.setRollMode(Roll3DView.RollMode.Jalousie);//设置翻转模式 百叶窗
                roll3DView.setPartNumber(8);
                mListPopWindow.dissmiss();
                break;
            case 4:
                roll3DView.setRollMode(Roll3DView.RollMode.RollInTurn);//设置翻转模式 轮转效果
                roll3DView.setPartNumber(8);
                mListPopWindow.dissmiss();
                break;
        }
    }
}
