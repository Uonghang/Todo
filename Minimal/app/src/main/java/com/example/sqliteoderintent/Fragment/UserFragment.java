package com.example.sqliteoderintent.Fragment;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sqliteoderintent.Login;
import com.example.sqliteoderintent.R;
import com.example.sqliteoderintent.SQLiteHelper.SQLiteUserHelper;
import com.example.sqliteoderintent.model.User;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserFragment extends Fragment implements View.OnClickListener{
    private View v;
    private CircleImageView imageView;
    private TextView nameUser;
    private SQLiteUserHelper sqLiteUserHelper;
    private Button btnDangXuat, btnCodeVip, btnMuaVip;
    private User userlogin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_user_fragemt, container, false);
        init();
        setData();
        btnDangXuat.setOnClickListener(this);
        return v;
    }

    public void init() {
        imageView = v.findViewById(R.id.imageUser);
        nameUser = v.findViewById(R.id.nameUser);
        btnDangXuat = v.findViewById(R.id.btnDangXuat);

    }

    public void setData() {

        Intent intent= getActivity().getIntent();
         String name= (String) intent.getSerializableExtra("userlogin");
        nameUser.setText(name);
    }

    @Override
    public void onClick(View v) {
        if (v == btnDangXuat) {
            Intent intent = new Intent(v.getContext(), Login.class);
            sqLiteUserHelper.updateUserLoginOut(userlogin.getId());
            v.getContext().startActivity(intent);
        }
    }
}