package edu.cvtc.android.securelife;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import edu.cvtc.android.securelife.Helper.AccountDatabaseHelper;
import edu.cvtc.android.securelife.View.AccountDetailActivity;
import edu.cvtc.android.securelife.View.AccountSummaryActivity;
import edu.cvtc.android.securelife.View.SearchActivity;


public class MainActivity extends AppCompatActivity {

    private Button accountListButton;

    private boolean firstLoad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        accountListButton = (Button) findViewById(R.id.main_account_list_button);

        firstLoad = databaseExists() ? false : true;

        initButtonListener();
        initButtonText();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final Intent intent;

        switch (item.getItemId()) {
            case R.id.search:
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                return true;
            case R.id.add_account:
                intent = new Intent(this, AccountDetailActivity.class);
                intent.putExtra("detailType", AccountDetailActivity.DETAIL_TYPE_ADD);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void initButtonListener() {

        accountListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent;

                if (firstLoad) {

                    intent = new Intent(MainActivity.this, AccountDetailActivity.class);
                    intent.putExtra("detailType", AccountDetailActivity.DETAIL_TYPE_ADD);
                    Toast.makeText(MainActivity.this, R.string.activity_main_toast_first_load, Toast.LENGTH_SHORT).show();
                }
                else {

                    intent = new Intent(MainActivity.this, AccountSummaryActivity.class);
                }

                startActivity(intent);
            }
        });
    }


    private void initButtonText() {

        if (firstLoad) {
            accountListButton.setText(R.string.activity_main_button_first_load);
        }
        else {
            accountListButton.setText(R.string.activity_main_button);
        }
    }


    private boolean databaseExists() {
        File db = this.getDatabasePath(AccountDatabaseHelper.DB_NAME);
        return db.exists();
    }

    @Override
    protected void onRestart() {

        super.onRestart();

        firstLoad = databaseExists() ? false : true;

        initButtonListener();
        initButtonText();
    }

    @Override
    protected void onResume() {

        super.onResume();

        firstLoad = databaseExists() ? false : true;

        initButtonListener();
        initButtonText();
    }

}
