package com.example.induja.attractionsandrestaurants_app02;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.lifecycle.ReportFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Induja on 3/31/2018.
 */

public class SecondActivty extends AppCompatActivity implements RestaurantFragment.RestaurantSelectionListener {
    public static String[] mRestaurantArray;
    public static String[] mRestWebURLArray;

    private  RestaurantWebFragment mMyWebFragment = new RestaurantWebFragment();
    private static RestaurantFragment mRestaurantMainFragment;

    private FragmentManager mFragmentManager;
    private FrameLayout ListFragmentLayout, WebviewLayout;



    private static final String TAG_RESTAURANTS = "RstaurantFragment";
    private static final String TAG_WEB = "WebRestaurantFragment";
    private int mCurrIdx ;

    //private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "Project3_SecondActivity";

    private Boolean rotate_flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        initRestaurants();
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        ListFragmentLayout = (FrameLayout) findViewById(R.id.restaurant_list_container);
        WebviewLayout = (FrameLayout) findViewById(R.id.restaurant_web_container);

        mFragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        if(mRestaurantMainFragment==null){
            fragmentTransaction.replace(R.id.restaurant_list_container, new RestaurantFragment(), TAG_RESTAURANTS);
        }
        else{
            fragmentTransaction.replace(R.id.restaurant_list_container, mRestaurantMainFragment, TAG_RESTAURANTS);

            if(mMyWebFragment==null){
                fragmentTransaction.replace(R.id.restaurant_web_container, new RestaurantWebFragment(),TAG_WEB);
            }
            else{
                fragmentTransaction.replace(R.id.restaurant_web_container, mMyWebFragment,TAG_WEB);
            }
        }

        fragmentTransaction.commit();
        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                setLayout();
            }
        });
    }

    private void setLayout() {
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE){
            rotate_flag = true;
        }
        else{
            rotate_flag = false;
        }
        if (!mMyWebFragment.isAdded()) {
            ListFragmentLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            WebviewLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT));
        }
        else if( mMyWebFragment.isAdded() && rotate_flag) {
            ListFragmentLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f));

            WebviewLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 2f));
        }
        else if(mMyWebFragment.isAdded() && !rotate_flag) {
            ListFragmentLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT));
            WebviewLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        }

    }

    @Override
    public void onRestaurantListSelection(int position) {

        if (!mMyWebFragment.isAdded()) {

            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.restaurant_web_container, mMyWebFragment,TAG_WEB)
                    .addToBackStack(null)
                    .commit();

            mFragmentManager.executePendingTransactions();
        }
        if (mMyWebFragment.getShownIndex() != position) {

            mMyWebFragment.showWebsiteAtIndex(position);
        }
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();

    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.AttractionActivity:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.RestaurantActivity:
                Toast.makeText(getBaseContext(), "Already in Restaurants", Toast.LENGTH_LONG).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void initRestaurants(){
        ArrayList<RestaurantListClass> RestaurantArrayList = new ArrayList<RestaurantListClass>();

        RestaurantArrayList.add(new RestaurantListClass("Alinea","http://www.alinearestaurant.com/"));
        RestaurantArrayList.add(new RestaurantListClass("Joe's Seafood, Prime SteakStone Crab","http://joes.net/"));
        RestaurantArrayList.add(new RestaurantListClass("Oriole","http://www.oriolechicago.com/"));
        RestaurantArrayList.add(new RestaurantListClass("Bavette's Bar and Boeuf","http://bavettessteakhouse.com/"));
        RestaurantArrayList.add(new RestaurantListClass("The Chicago Diner", "http://www.veggiediner.com/"));
        RestaurantArrayList.add(new RestaurantListClass("IO Godfrey Rooftop Lounge","http://iogodfrey.com/"));

        mRestaurantArray = new String[RestaurantArrayList.size()];
        mRestWebURLArray = new String[RestaurantArrayList.size()];

        for(int i = 0; i < RestaurantArrayList.size(); i++){
            mRestaurantArray[i] = RestaurantArrayList.get(i).getPlace_name();
            mRestWebURLArray[i] = RestaurantArrayList.get(i).getUrl();
        }
    }
}
