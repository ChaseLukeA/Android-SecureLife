package edu.cvtc.android.securelife.Controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import edu.cvtc.android.securelife.Model.AccountField;
import edu.cvtc.android.securelife.View.AccountFieldView;


public class AccountFieldAdapter extends BaseAdapter {

    private final Context context;

    private final ArrayList<AccountField> accountFieldList;


    public AccountFieldAdapter(Context context, ArrayList<AccountField> accountFieldList) {

        this.context = context;
        this.accountFieldList = accountFieldList;
    }

    
    @Override
    public int getCount() {

        return null != accountFieldList ? accountFieldList.size() : 0;
    }


    @Override
    public Object getItem(int position) {

        return null != accountFieldList ? accountFieldList.get(position) : null;
    }


    @Override
    public long getItemId(int position) {

        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AccountField accountField = accountFieldList.get(position);
        convertView = new AccountFieldView(context, accountField);
        convertView.setFocusable(true);

        return convertView;
    }

}
