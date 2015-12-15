package edu.cvtc.android.securelife.View;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import edu.cvtc.android.securelife.Controller.AccountCursorAdapter;
import edu.cvtc.android.securelife.Model.AccountContentProvider;
import edu.cvtc.android.securelife.R;


public class AccountSummaryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private AccountSummaryView selectedView;

    private AccountCursorAdapter accountCursorAdapter;

    private ListView accountSummaryLayout;

    protected int sortValue;

    public static boolean searchQuery;
    public static String searchTerm;

    public static final int SORT_BY_NAME = 0,
                            SORT_BY_TYPE = 1;

    protected static final String SAVED_SORT_VALUE = "sortValue";

    private static final int LOADER_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_account_summary);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.accountCursorAdapter = new AccountCursorAdapter(this, null, 0);

        sortValue = SORT_BY_NAME;

        searchTerm = getIntent().getStringExtra("searchTerm");
        if (null != searchTerm && !searchTerm.isEmpty()) {

            searchQuery = true;
        }
        else {
            searchQuery = false;
        }

        initLayout();

        this.getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_account_summary, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final Intent intent;

        switch (item.getItemId()) {
            case R.id.search:
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.add_account:
                intent = new Intent(this, AccountDetailActivity.class);
                intent.putExtra("detailType", AccountDetailActivity.DETAIL_TYPE_ADD);
                startActivity(intent);
                return true;
            case R.id.account_list_cancel:
                finish();
                return true;
            case R.id.show_all:
                showAllAccounts();
            case R.id.sort_by_name:
                sortAccounts(SORT_BY_NAME);
                return true;
            case R.id.sort_by_type:
                sortAccounts(SORT_BY_TYPE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    protected void initLayout() {

        accountSummaryLayout = (ListView) findViewById(R.id.account_summary_listview);
        accountSummaryLayout.setAdapter(accountCursorAdapter);
        accountSummaryLayout.setClickable(true);
        accountSummaryLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AccountSummaryActivity.this, R.string.activity_summary_toast_click_action, Toast.LENGTH_SHORT).show();
            }
        });
        
        accountSummaryLayout.setLongClickable(true);
        accountSummaryLayout.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                selectedView = (AccountSummaryView) view;

                final Intent intent;

                intent = new Intent(AccountSummaryActivity.this, AccountDetailActivity.class);
                intent.putExtra("account", selectedView.getAccount());
                intent.putExtra("detailType", AccountDetailActivity.DETAIL_TYPE_DETAIL);
                startActivity(intent);
                return true;
            }
        });

    }


    private void showAllAccounts() {

        searchTerm = "";
        refreshList();
    }


    private void sortAccounts(int sortValue) {

        this.sortValue = sortValue;
        refreshList();
    }


    public void refreshList() {

        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
        accountSummaryLayout.setAdapter(accountCursorAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        Uri uri = Uri.parse(AccountContentProvider.CONTENT_URI + "/sorters/" + sortValue);

        CursorLoader cursorLoader = new CursorLoader(this, uri, null, null, null, null);

        return cursorLoader;
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        accountCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        accountCursorAdapter.swapCursor(null);
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

        super.onSaveInstanceState(outState, outPersistentState);

        outState.putInt(SAVED_SORT_VALUE, sortValue);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        sortValue = savedInstanceState.getInt(SAVED_SORT_VALUE);
        sortAccounts(sortValue);
    }


    @Override
    protected void onPause() {

        super.onPause();
    }


    @Override
    protected void onResume() {

        super.onResume();

        refreshList();
    }

}
