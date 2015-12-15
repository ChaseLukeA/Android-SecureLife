package edu.cvtc.android.securelife.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.cvtc.android.securelife.R;
import edu.cvtc.android.securelife.Helper.Utility;


public class SearchActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchButton;

    private final String SAVED_SEARCH_TERM = "searchTerm";
    private String searchTerm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchEditText = (EditText) findViewById(R.id.search_accounts_edittext);
        searchEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                Utility.hideSoftKeyboard(SearchActivity.this);
            }
        });

        searchButton = (Button) findViewById(R.id.search_accounts_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utility.hideSoftKeyboard(SearchActivity.this);

                searchTerm = searchEditText.getText().toString();
                final Intent intent = new Intent(SearchActivity.this, AccountSummaryActivity.class);
                intent.putExtra("searchTerm", searchTerm);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.search_cancel:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

        super.onSaveInstanceState(outState, outPersistentState);

        outState.putString(SAVED_SEARCH_TERM, searchTerm);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        searchTerm = savedInstanceState.getString(SAVED_SEARCH_TERM);
    }

}

