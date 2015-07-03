package com.ptrprograms.androidwearnotifications.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;

import com.ptrprograms.androidwearnotifications.R;
import com.ptrprograms.androidwearnotifications.models.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 7/3/15.
 */
public class GridViewPagerAdapter extends FragmentGridPagerAdapter {
    private List<Row> mRows = new ArrayList<Row>();

    public GridViewPagerAdapter( Context context, FragmentManager fm) {
        super(fm);
        Row row = new Row(CardFragment.create("Row 1", "Page 1"));
        row.addBackground( context.getDrawable( R.drawable.bg1 ) );
        mRows.add(row);

        row = new Row( CardFragment.create( "Row 2", "Page 1" ) );
        row.addBackground(context.getDrawable(R.drawable.bg2));
        mRows.add(row);

        row = new Row( CardFragment.create( "Row 3", "Page 1" ), CardFragment.create( "Row 3", "Page 2" ), CardFragment.create( "Row 3", "Page 1" ) );
        row.addBackgrounds(context.getDrawable(R.drawable.bg3), context.getDrawable(R.drawable.bg4), context.getDrawable(R.drawable.bg5));
        mRows.add( row );

        row = new Row( CardFragment.create( "Row 4", "Page 1" ), CardFragment.create( "Row 4", "Page 2" ) );
        row.addBackground( context.getDrawable( R.drawable.bg4 ) );
        mRows.add(row);

        row = new Row( CardFragment.create( "Row 5", "asdfgf asfdgfh asdfgf gsdfasf gafg eg sgsadg sadgagewrh adbdbewrb ewrhewb ewbewvreg" ) );
        row.addBackground( context.getDrawable( R.drawable.bg5 ) );
        mRows.add( row );


    }

    @Override
    public Fragment getFragment(int rowIndex, int columnIndex) {
        Row row = mRows.get( rowIndex );
        return row.getColumn( columnIndex );
    }

    @Override
    public int getRowCount() {
        return mRows.size();
    }

    @Override
    public int getColumnCount(int i) {
        return mRows.get( i ).getColumnCount();
    }

    @Override
    public Drawable getBackgroundForRow(int row) {
        if( mRows.get( row ).getBackgrounds() == null || mRows.get( row ).getBackgrounds().isEmpty() )
            return GridPagerAdapter.BACKGROUND_NONE;

        return mRows.get( row ).getBackground( 0 );
    }

    @Override
    public Drawable getBackgroundForPage(int row, int column) {
        if( mRows.get( row ).getBackgrounds() == null || column > mRows.get( row ).getBackgrounds().size() - 1 )
            return GridPagerAdapter.BACKGROUND_NONE;

        return mRows.get( row ).getBackground( column );
    }
}
