package com.example.induja.attractionsandrestaurants_app02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AttractionsFragment.AttractionSelectionListener {

    public static String[] mAttractionArray;
    public static String[] mWebURLArray;

    private  WebviewFragment mMyWebFragment = new WebviewFragment();
    private  static AttractionsFragment mAttractionsMainFragment;

    private FragmentManager mFragmentManager;
    private FrameLayout ListFragmentLayout, WebviewLayout;



    private static final String TAG_ATTRACTIONS = "AttractionsFragment";
    private static final String TAG_WEB = "WebviewFragment";
    private int mCurrIdx ;

    //private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "Project3_MainActivity";

    private Boolean rotate_flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initAttraction();
        Log.i(TAG,getClass().getSimpleName() + ":entered onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListFragmentLayout = (FrameLayout) findViewById(R.id.list_container);
        WebviewLayout = (FrameLayout) findViewById(R.id.web_container);

        mFragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        if(mAttractionsMainFragment == null){
            fragmentTransaction.replace(R.id.list_container, new AttractionsFragment(),TAG_ATTRACTIONS);
        }
        else{
            fragmentTransaction.replace(R.id.list_container, mAttractionsMainFragment, TAG_ATTRACTIONS);

            if(mMyWebFragment == null){
                fragmentTransaction.replace(R.id.web_container, new WebviewFragment(), TAG_WEB);
            }
            else{
                fragmentTransaction.replace(R.id.web_container, mMyWebFragment, TAG_WEB);
            }
        }
        fragmentTransaction.commit();

        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                setLayout();
            }
        });
    }

    private void setLayout(){
        int currentOrientation = getResources().getConfiguration().orientation;

        if(currentOrientation == Configuration.ORIENTATION_LANDSCAPE){
            rotate_flag = true;
        }
        else{
            rotate_flag = false;
        }

        if(!mMyWebFragment.isAdded()){
            ListFragmentLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            WebviewLayout.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT));
        }

        else if(mMyWebFragment.isAdded() && rotate_flag){
            ListFragmentLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
            WebviewLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 2f));
        }
        else if(mMyWebFragment.isAdded() && !rotate_flag){
            ListFragmentLayout.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT));
            WebviewLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        }
    }


    public void onAttractionListSelection(int position) {

        if (!mMyWebFragment.isAdded()) {

            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.web_container, mMyWebFragment,TAG_WEB)
                    .addToBackStack(null)
                    .commit();
            mFragmentManager.executePendingTransactions();
        }
        if (mMyWebFragment.getShownIndex() != position) {

            mMyWebFragment.showWebsiteAtIndex(position);
        }
    }

    @Override
    protected void onDestroy(){
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
                Toast.makeText(getBaseContext(), "Already in Attractions", Toast.LENGTH_LONG).show();
                break;
            case R.id.RestaurantActivity:
                startActivity(new Intent(this, SecondActivty.class));
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    public void initAttraction(){
        ArrayList<AttractionListClass> AttractionArrayList = new ArrayList<AttractionListClass>();
        AttractionArrayList.add(new AttractionListClass("Art Institute of Chicago","http://www.artic.edu/"));
        AttractionArrayList.add(new AttractionListClass("Millennium Park","https://en.wikipedia.org/wiki/Millennium_Park"));
        AttractionArrayList.add(new AttractionListClass("Michigan Avenue and the Magnificent Mile","https://www.themagnificentmile.com/"));
        AttractionArrayList.add(new AttractionListClass("Navy Pier","https://navypier.org/"));
        AttractionArrayList.add(new AttractionListClass("Wrigley Field","https://www.mlb.com/cubs/ballpark"));
        AttractionArrayList.add(new AttractionListClass("Shakespeare Theater","https://www.folger.edu/shakespeares-theater"));
        AttractionArrayList.add(new AttractionListClass("Museum of Science and Industry","https://www.msichicago.org/"));
        AttractionArrayList.add(new AttractionListClass("Field Museum of Natural History","https://www.fieldmuseum.org/"));
        AttractionArrayList.add(new AttractionListClass("Lyric Opera of Chicago","https://www.lyricopera.org/"));
        AttractionArrayList.add(new AttractionListClass("Oriental Institute Museum","https://oi.uchicago.edu/museum-exhibits"));

        mAttractionArray = new String[AttractionArrayList.size()];
        mWebURLArray = new String[AttractionArrayList.size()];

        for(int i = 0; i < AttractionArrayList.size(); i++){
            mAttractionArray[i] = AttractionArrayList.get(i).getPlace_name();
            mWebURLArray[i] = AttractionArrayList.get(i).getUrl();
        }
    }
}
