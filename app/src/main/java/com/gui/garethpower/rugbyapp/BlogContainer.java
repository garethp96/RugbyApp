package com.gui.garethpower.rugbyapp;

/**
 * Created by Gareth on 27/02/2017.
 */

import android.support.v7.widget.RecyclerView;

import junit.framework.Assert;

import java.util.ArrayList;

/**
 * This class contains the blog objects, and are held within a container.
 */
public class BlogContainer {
    public ArrayList<Blog> BlogItems = new ArrayList<>();
    /**
     * The Recyclerview where the items should be displayed
     */
    public RecyclerView BlogView;
    public boolean AddBlog(Blog blog)  {
        Assert.assertNotNull(blog);
        if(BlogItems.contains(blog))
            return false;
        BlogItems.add(blog);
        //ToDo update bloglist, data and possible listeners
        return true;
    }
    public void setBlogView(RecyclerView rv)    {
        Assert.assertNotNull(rv);
        BlogView = rv;
        //ToDo maybe some updates
    }


}
