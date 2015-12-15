package edu.cvtc.android.securelife.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import edu.cvtc.android.securelife.Helper.AccountFieldViewHelper;
import edu.cvtc.android.securelife.Model.AccountField;
import edu.cvtc.android.securelife.Model.AccountType;
import edu.cvtc.android.securelife.R;


public class AccountFieldView extends LinearLayout implements OnItemSelectedListener {

    private final TextView fieldTextView;
    private final EditText fieldEditText;
    private final Spinner fieldSpinner;

    private Context context;

    private AccountField accountField;


    public AccountFieldView(Context context, final AccountField accountField) {

        super(context);

        this.context = context;

        final LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_account_field_view, this, true);

        fieldTextView = (TextView) findViewById(R.id.account_field_textview);
        fieldEditText = (EditText) findViewById(R.id.account_field_edittext);
        fieldSpinner = (Spinner) findViewById(R.id.account_field_spinner);

        setField(accountField);
    }


    public AccountField getField() {

        return accountField;
    }


    public void setField(AccountField accountField) {

        this.accountField = accountField;

        fieldTextView.setText(accountField.getLabel());

        if (accountField.isSpinner()) {

            fieldEditText.setVisibility(View.GONE);
            fieldSpinner.setVisibility(View.VISIBLE);

            fieldSpinner.setOnItemSelectedListener(this);


            final Field[] typeOfAccounts = AccountType.class.getDeclaredFields();
            for (Field type : typeOfAccounts) {
                Log.d("typeOfAccount ", type.getName());

            }


            List<String> types = new ArrayList<>();

            for (int type = 0; type < AccountType.class.getDeclaredFields().length; type++) {
                types.add(String.valueOf(type));
//                types.add(value);
//                fieldEditText.setHint(value_descr);
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_item, types);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            fieldSpinner.setAdapter(dataAdapter);

        }
        else { // accountField is a normal TextEdit

            fieldEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        String value = ((EditText) v).getText().toString();
                        AccountFieldView.this.accountField.setValue(value);
                    }
                }
            });
            fieldEditText.setHint(accountField.getExample());
            fieldEditText.setText(accountField.getValue());
        }

        if (!accountField.isEditable()) {

            fieldEditText.setKeyListener(null);
        }

        requestLayout();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        fieldEditText.setText(parent.getItemAtPosition(position).toString());
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}

