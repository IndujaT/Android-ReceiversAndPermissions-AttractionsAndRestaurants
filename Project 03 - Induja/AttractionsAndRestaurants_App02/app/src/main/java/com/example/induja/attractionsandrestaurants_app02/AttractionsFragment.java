package com.example.induja.attractionsandrestaurants_app02;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/**
 * Created by Induja on 3/31/2018.
 */

public class AttractionsFragment extends ListFragment {
    private static final String TAG = "AttractionsFragment";
    private AttractionSelectionListener mAttrListener = null;
    private int mCurrIdx = -1;

    // Callback interface that allows this Fragment to notify the MainActivity when
    // user clicks on a List Item
    public interface AttractionSelectionListener {
        void onAttractionListSelection(int index);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {

            // Set the ListSelectionListener for communicating with the MainActivity
            mAttrListener = (AttractionSelectionListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        // Set the list choice mode to allow only one selection at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Set the list adapter for the ListView
        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.attractions, MainActivity.mAttractionArray));

        // If an item has been selected, set its checked state
        if (-1 != mCurrIdx) {
            getListView().setItemChecked(mCurrIdx, true);
            mAttrListener.onAttractionListSelection(mCurrIdx);
        }

    }

    // Called when the user selects an item from the List
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        if (mCurrIdx != pos) {
            mCurrIdx = pos;

            // Inform the QuoteViewerActivity that the item in position pos has been selected
            mAttrListener.onAttractionListSelection(pos);
        }
        // Indicates the selected item has been checked
        l.setItemChecked(mCurrIdx, true);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, getClass().getSimpleName() + ":onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, getClass().getSimpleName() + ":onDetach()");
        super.onDetach();
    }

    @Override
    public void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":onPause()");
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":onResume()");
        super.onResume();
    }

    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":onStart()");
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":onStop()");
        super.onStop();
    }
}
