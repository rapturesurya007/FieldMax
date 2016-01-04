package com.fieldmax.android.fieldmaxv2.activity;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.fieldmax.android.fieldmaxv2.R;

import java.util.List;

/**
 * Created by suraj on 12/1/2015.
 */
public class SearchResultActivity extends BaseActivity {
    private MenuItem searchItem;
    private Menu menu;
    private SearchManager searchManager;
    private SearchView searchView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        handleIntent(getIntent());
    }


    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);



        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        searchItem = menu.findItem(R.id.action_searchAction);
        searchView = (SearchView) searchItem.getActionView();

        searchItem.expandActionView();
        searchView.setIconifiedByDefault(true);
        searchView.setIconified(false);
        searchView.setFocusable(true);
        String query=getIntent().getStringExtra("Query");
        Toast.makeText(getApplicationContext(), "You Submited Query...." + query, Toast.LENGTH_SHORT).show();
        searchView.setQuery(query, false);
        onQuerySubmit(query);
        setupSearchView(searchItem);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                //SearchView OnClose code comes here
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            // Alter action

            case R.id.action_searchAction:
                searchView.setFocusable(true);
                searchView.requestFocus();
                searchView.requestFocusFromTouch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupSearchView(MenuItem searchItem) {

        if (isAlwaysExpanded()) {
            searchView.setIconifiedByDefault(true);
            searchView.setIconified(false);
            searchView.setFocusable(true);
            searchView.requestFocus();
            searchView.requestFocusFromTouch();
        } else {
            searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        }
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            List<SearchableInfo> searchables = searchManager.getSearchablesInGlobalSearch();
            SearchableInfo info = searchManager.getSearchableInfo(getComponentName());
            for (SearchableInfo inf : searchables) {

                if (inf.getSuggestAuthority() != null && inf.getSuggestAuthority().startsWith("applications")) {
                    info = inf;
                }
            }
            searchView.setSearchableInfo(info);
        }
        searchView.setOnQueryTextListener(this);
    }

    public boolean onQueryTextChange(String newText) {
        // Code for text change comes here
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        // Code for text submission comes here
        onQuerySubmit(query);
        return true;
    }
    public boolean onClose() {
        searchView.clearFocus();
        return false;
    }
    protected boolean isAlwaysExpanded() {
        return true;
    }
    private void onQuerySubmit(String query)
    {
        // Toast.makeText(getApplicationContext(),"submitted query in SearchResults..."+query,Toast.LENGTH_SHORT).show();
    }

}
