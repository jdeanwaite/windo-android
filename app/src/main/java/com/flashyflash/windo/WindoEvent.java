package com.flashyflash.windo;

/**
 * Created by jhenders on 2/24/16.
 */

// TBD: how this should all work
public class WindoEvent
{
   private String mName;
   private String mLocation;
   private String mOwner;

   public WindoEvent(String name, String location, String owner)
   {
      mName = name;
      mLocation = location;
      mOwner = owner;
   }

   public String getName() { return mName; }
   public String getLocation() { return mLocation; }
   public String getOwner() { return mOwner; }
}
