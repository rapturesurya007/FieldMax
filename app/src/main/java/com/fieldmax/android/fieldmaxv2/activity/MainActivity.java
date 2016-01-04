package com.fieldmax.android.fieldmaxv2.activity;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.fieldmax.android.fieldmaxv2.R;

import java.util.List;

/**
 * Created by suraj on 17/12/2015.
 */
public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Menu menu;
    private MenuItem searchItem;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#fffffff'>ActionBarTitle </font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009DE0")));


    }
    @Override
      public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_searchAction:
                searchItem = menu.findItem(R.id.action_searchAction);
                searchView = (SearchView) searchItem.getActionView();
                searchView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_FILTER);
                searchView.setBackgroundColor(getResources().getColor(R.color.ColorPrimary));
                searchItem.expandActionView();
                setupSearchView(searchItem);
                searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                    @Override
                    public boolean onClose() {
                        //SearchView OnClose code comes here
                        return true;
                    }
                });
                return true;

            case R.id.action_alertAction:
                return true;

            case android.R.id.home:

                this.finish();
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


    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("Query", query);
        startActivity(intent);
        searchView.setQuery("", false);
        searchView.clearFocus();
        searchItem.collapseActionView();
        return true;
    }

    public boolean onClose() {
        searchView.clearFocus();
        return false;
    }

    protected boolean isAlwaysExpanded() {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        return true;
    }
}
