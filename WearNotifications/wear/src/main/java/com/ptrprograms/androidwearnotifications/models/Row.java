package com.ptrprograms.androidwearnotifications.models;

import android.app.Fragment;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 7/3/15.
 */
public class Row {
    private final List<Fragment> columns = new ArrayList<Fragment>();
    private List<Drawable> backgrounds = new ArrayList<Drawable>();

    public Row(Fragment... fragments) {
        for (Fragment f : fragments) {
            add(f);
        }
    }

    public void add(Fragment f) {
        columns.add(f);
    }

    public Fragment getColumn(int i) {
        return columns.get(i);
    }

    public int getColumnCount() {
        return columns.size();
    }

    public List<Drawable> getBackgrounds() {
        return backgrounds;
    }

    public void setBackgrounds(List<Drawable> backgrounds) {
        this.backgrounds = backgrounds;
    }

    public void addBackground( Drawable background ) {
        this.backgrounds.add( background );
    }

    public void addBackgrounds( Drawable... backgrounds ) {
        for( Drawable background : backgrounds ) {
            this.backgrounds.add( background );
        }
    }

    public Drawable getBackground( int index ) {
        return backgrounds.get( index );
    }
}
