package com.flashyflash.windo;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.*;

import java.util.Random;

public class HomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

   ListView mMainEventList;
   ArrayAdapter mAdapter;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_home_screen);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener()
      {
         @Override
         public void onClick(View view)
         {
            Snackbar.make(view, "making a default event for now...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            if ( mAdapter != null )
            {
               Random generator = new Random();

               mAdapter.add(new WindoEvent("Existential Angst", (generator.nextInt(1000)+1) + " Crisis St.", "You") );
            }
         }
      });

      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
      ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
              this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
      drawer.setDrawerListener(toggle);
      toggle.syncState();

      NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
      navigationView.setNavigationItemSelectedListener(this);

      // populate the event listview with some default rubbish
      ProgressBar progressBar = new ProgressBar(this);
      progressBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
      progressBar.setIndeterminate(true);

      mMainEventList = (ListView) findViewById(R.id.mainEventList);
      mMainEventList.setEmptyView(progressBar);

      final ViewGroup root = (ViewGroup) findViewById(R.id.contentLayout);
      root.addView(progressBar);

      mAdapter = new ArrayAdapter(this, R.layout.home_event_list_layout)
      {
         @Override
         public View getView(int position, View convertView, ViewGroup parent)
         {
            View eventItem = getLayoutInflater().inflate(R.layout.home_event_list_layout, null);

            WindoEvent event = (WindoEvent) getItem(position);

            ((TextView) eventItem.findViewById(R.id.eventName)).setText(event.getName());
            ((TextView) eventItem.findViewById(R.id.eventDetail)).setText(event.getLocation());
            ((TextView) eventItem.findViewById(R.id.eventStatus)).setText("You need to respond");

            return eventItem;
         }
      };

      WindoEvent defaultEvents[] = { new WindoEvent("Wicked Event", "Party Town, USA", "Henderson"), new WindoEvent("Concert: Rag Night with J. S. Henderson", "Marriot Centre", "Everyone") };

      mAdapter.addAll(defaultEvents);
      mAdapter.setNotifyOnChange(true);

      mMainEventList.setAdapter(mAdapter);
   }

   @Override
   public void onBackPressed()
   {
      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
      if (drawer.isDrawerOpen(GravityCompat.START))
      {
         drawer.closeDrawer(GravityCompat.START);
      }
      else
      {
         super.onBackPressed();
      }
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu)
   {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.home_screen, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item)
   {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();

      //noinspection SimplifiableIfStatement
      if (id == R.id.action_settings)
      {
         return true;
      }

      return super.onOptionsItemSelected(item);
   }

   @SuppressWarnings("StatementWithEmptyBody")
   @Override
   public boolean onNavigationItemSelected(MenuItem item)
   {
      // Handle navigation view item clicks here.
      int id = item.getItemId();

      if (id == R.id.nav_camera)
      {
         // Handle the camera action
      }
      else if (id == R.id.nav_gallery)
      {

      }
      else if (id == R.id.nav_slideshow)
      {

      }

      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
      drawer.closeDrawer(GravityCompat.START);
      return true;
   }
}
