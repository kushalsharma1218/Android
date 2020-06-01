package com.example.hijeck;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class UserSecretAdapter extends ArrayAdapter{
    private Context context;
    List list=new ArrayList();
    int res;

    public UserSecretAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object)
    {
        super.add(object);
        list.add(object);
    }

    public int getCount()
    {
        return list.size();
    }

    public Object getItem(int position)
    {
        return list.get(position);
    }

    static class LayoutHandler
    {
        TextView Application_name,USerNAme,Pass;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row=convertView;
        LayoutHandler layoutHandler;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.adapterview,parent,false);
            layoutHandler= new LayoutHandler();
            layoutHandler.Application_name=(TextView)row.findViewById(R.id.application_name_text);
            layoutHandler.USerNAme=(TextView)row.findViewById(R.id.user_name_text);
            layoutHandler.Pass=(TextView)row.findViewById(R.id.pass_text);
            row.setTag(layoutHandler);

        }
        else
            {
                layoutHandler =(LayoutHandler)row.getTag();
            }

        UserSecrets us=(UserSecrets)this.getItem(position);
        layoutHandler.Application_name.setText(us.getApplicationame());
        layoutHandler.USerNAme.setText(us.getUsername());
        layoutHandler.Pass.setText(us.getPassword());

        return  row;

    }

    

}
