package com.example.sangnv.appnews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sangnv.appnews.RssGet.Item_RSS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
         RssItemFragment.OnListFragmentInteractionListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public TabLayout tabLayout;

    public static int inDex = 0;

    /// Viet
    public static List<Item_RSS> list_item_RSS_saved;
    ///

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(ConstVar.LIST_WEB_NAME[inDex]);

        /// viet
        /// async task load data rss item
        new ReadRSSSaved().execute("");
        ///

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set drawer layout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //set viewpager
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        setListData(list_item_RSS_saved);
    }

    @Override
    protected void onStop() {
        super.onStop();

        setListData(list_item_RSS_saved);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_0) {
            if (inDex != 0) {
                inDex = 0;
                mViewPager.removeAllViews();
                tabLayout.removeAllTabs();
                setTitle(ConstVar.LIST_WEB_NAME[inDex]);

                mViewPager.getAdapter().notifyDataSetChanged();
                tabLayout.setupWithViewPager(mViewPager);
            }

        } else if (id == R.id.nav_1) {
            if (inDex != 1) {
                inDex = 1;
                mViewPager.removeAllViews();
                tabLayout.removeAllTabs();
                setTitle(ConstVar.LIST_WEB_NAME[inDex]);

                mViewPager.getAdapter().notifyDataSetChanged();
                tabLayout.setupWithViewPager(mViewPager);
            }

        } else if (id == R.id.nav_2) {
            if (inDex != 2) {
                inDex = 2;
                mViewPager.removeAllViews();
                tabLayout.removeAllTabs();
                setTitle(ConstVar.LIST_WEB_NAME[inDex]);

                mViewPager.getAdapter().notifyDataSetChanged();
                tabLayout.setupWithViewPager(mViewPager);
            }
        } else if (id == R.id.nav_3) {
            if (inDex != 3) {
                inDex = 3;
                mViewPager.removeAllViews();
                tabLayout.removeAllTabs();
                setTitle(ConstVar.LIST_WEB_NAME[inDex]);

                mViewPager.getAdapter().notifyDataSetChanged();
                tabLayout.setupWithViewPager(mViewPager);
            }
        } else if (id == R.id.nav_da_luu) {
            if (inDex != 4) {
                inDex = 4;

                mViewPager.removeAllViews();
                tabLayout.removeAllTabs();

                setTitle("Bài báo đã lưu");

                mViewPager.getAdapter().notifyDataSetChanged();
                tabLayout.setupWithViewPager(mViewPager);
            }

        } else if (id == R.id.nav_info) {

            StringBuilder info = new StringBuilder();
            info.append("Đội ngũ phát triển" + "\n");
            info.append("Nguyễn Văn Việt" + "\n");
            info.append("Nguyễn Viết Sang" + "\n");
            info.append("Nguyễn Bật Xuân TRường" + "\n");

            Toast.makeText(getApplicationContext(), info.toString(), Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(Item_RSS item) {
        //even click vao item, lấy ra link và chuyển ra webview
        //Toast.makeText(getApplicationContext(), item.getLink(), Toast.LENGTH_SHORT).show();
        if (!item.isNull()){
            Intent myIntent = new Intent(MainActivity.this, WebViewActivity.class);
            myIntent.putExtra("url", item.getLink()); //send messeage to WebViewActivity
            myIntent.putExtra("title", item.getTitle());
            startActivity(myIntent);
        }
    }

    //sang sua tinh nang refresh
    @Override
    public void onRefreshSwipe() {
        mViewPager.removeAllViews();
        tabLayout.removeAllTabs();

        setTitle(ConstVar.LIST_WEB_NAME[inDex]);

        mViewPager.getAdapter().notifyDataSetChanged();
        tabLayout.setupWithViewPager(mViewPager);
    }

    //sang
    // cập nhật lại danh sách khi xóa item đã lưu
    @Override
    public void onClickButton() {
        mViewPager.getAdapter().notifyDataSetChanged();
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
        private Context mContext;
        private FragmentManager mFragmentManager;
        private Map<Integer, String> mFragmentTags;

        public SectionsPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mFragmentManager = fm;
            mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            return  RssItemFragment.newInstance(ConstVar.LIST_LINK[inDex][position]);
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            // refresh all fragments when data set changed
            return POSITION_NONE;
        }


        @Override
        public int getCount() {
            // Show 5 total pages.
            //1 link rss/1tab
            //list rss size -> so tab
            return ConstVar.LIST_KEY[inDex].length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return ConstVar.LIST_KEY[inDex][position];
        }
    }

    /// viet
    public List<Item_RSS> getListData()
    {
        List<Item_RSS> listData = new ArrayList<>();
        String FILE_NAME = "data_rss_saved";

        StringBuffer stringBuffer;

        try {
            InputStream inputStream = openFileInput(FILE_NAME);

            if (inputStream != null)
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String str = "";
                stringBuffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    stringBuffer.append(str + "\n");
                }
                inputStream.close();
            }
            else
            {
                Log.e("Read_Write_RSS_Storage", "error read file" );
                return null;
            }
            Log.e("stringBuffer", stringBuffer.toString() );

            /// string to JSON
            JSONArray jsonArray = new JSONArray(stringBuffer.toString());
            Gson gson = new Gson();

            Type type = new TypeToken<List<Item_RSS>>() {}.getType();
            listData = gson.fromJson(jsonArray.toString(), type);

            return listData;
        } catch (Exception e)
        {
            Log.e("Read_Write_RSS_Storage", "error read file");
            Log.e("Lỗi", e.getMessage());
            return null;
        }
    }

    public boolean setListData(List<Item_RSS> listData)
    {
        String FILE_NAME = "data_rss_saved";
        try {
            // creat Json to save
            Type type = new TypeToken<List<Item_RSS>>() {}.getType();
            Gson gson = new Gson();

            String json_to_string = gson.toJson(listData,type);
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(FILE_NAME, 0));

            out.write(json_to_string);
            out.close();

            return true;

        } catch (FileNotFoundException e) {
            Log.e("Read_Write_RSS_Storage", "error write file");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Read_Write_RSS_Storage", "error write file");
            return false;
        }
    }
    ///


    /// viet
    public class ReadRSSSaved extends AsyncTask<String, Long, List<Item_RSS>>{
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected List<Item_RSS> doInBackground(String... strings) {
            // load data item_rss saved

            List<Item_RSS> list_data_read = new ArrayList<>();
            list_data_read = getListData();

            if (list_data_read == null)
            {
                // khoi tao
                Item_RSS item = new Item_RSS();
                item.setDate("Tue, 15 May 2018 11:29:19 GMT+7");
                item.setDescription("<a href=\"http://dantri.com.vn/suc-manh-so/galaxy-s10-ro-ri-khien-fan-phan-khich-boi-tinh-nang-duoc-mong-doi-tu-lau-2018051511273755.htm\"><img src=\"https://dantricdn.com/zoom/80_50/2018/5/15/galaxy-s9-15263580221671931230421.jpg\" /></a>");
                item.setLink("http://dantri.com.vn/suc-manh-so/galaxy-s10-ro-ri-khien-fan-phan-khich-boi-tinh-nang-duoc-mong-doi-tu-lau-2018051511273755.htm");
                item.setTitle("Galaxy S10 rò rỉ khiến fan phấn khích bởi tính năng được mong đợi từ lâu");
                item.setSaved(true);

                List<Item_RSS> list_to_write = new ArrayList<>();
                list_to_write.add(item);
                setListData(list_to_write);

                // get lại data
                list_data_read = getListData();
            }

            if (list_data_read != null)
            {
                return list_data_read;
            }
            else
            {
                Log.e("read FAIL", "FAIL");
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Item_RSS> item_rsses) {

            if (item_rsses != null)
            {
                list_item_RSS_saved = item_rsses;
                Log.e("Get rss saved OK", "OK");
            }
            else
            {
                list_item_RSS_saved = null;
                Log.e("Get rss saved FAIL", "FAIL");
            }
        }
    }
}

