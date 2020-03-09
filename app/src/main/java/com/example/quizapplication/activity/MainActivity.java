package com.example.quizapplication.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapplication.R;
import com.example.quizapplication.adapters.CategoryAdapter;
import com.example.quizapplication.constrants.AppConstants;
import com.example.quizapplication.data_sqLite.NotificationDbController;
import com.example.quizapplication.listeners.ListItemClickListener;
import com.example.quizapplication.models.notification.NotificationModel;
import com.example.quizapplication.models.quiz.CategoryModel;
import com.example.quizapplication.utilities.ActivityUtilities;
import com.example.quizapplication.utilities.AppUtilities;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private Activity activity;
    private Context context;

    private Toolbar toolbar;
    private RelativeLayout mNotificationView;
    private AccountHeader header = null;
    private Drawer drawer = null;

    private ArrayList<CategoryModel> categoryList;
    private CategoryAdapter adapter = null;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // if database is need to be initializated for the first time

     /* StartInit start = new StartInit();
        start.StartInit();*/

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activity = MainActivity.this;
        context = getApplicationContext();


        mNotificationView = (RelativeLayout) findViewById(R.id.notificationView);

        recyclerView = (RecyclerView) findViewById(R.id.rvContent);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false));

        activity = MainActivity.this;
        context = getApplicationContext();
        categoryList = new ArrayList<>();
        adapter = new CategoryAdapter(context, activity, categoryList);
        recyclerView.setAdapter(adapter);

        initLoader();
        loadData();
        initListener();

        final IProfile profile = new ProfileDrawerItem().withIcon(R.drawable.androidos);

        header = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.quiz3)
                .withOnAccountHeaderProfileImageListener(new AccountHeader.OnAccountHeaderProfileImageListener() {
                    @Override
                    public boolean onProfileImageClick(View view, IProfile profile, boolean current) {
                        ActivityUtilities.getInstance().invokeCustomUrlActivity(activity, CustomUrlActivity.class,
                                getResources().getString(R.string.site), getResources().getString(R.string.site_url), false);
                        return false;
                    }

                    @Override
                    public boolean onProfileImageLongClick(View view, IProfile profile, boolean current) {
                        return false;
                    }
                })
                .addProfiles(profile)
                .build();
        SharedPreferences sPref = getSharedPreferences("sPrefer",MODE_PRIVATE);
        String role= sPref.getString("role","");

        if(role.equals("moderator")){
            drawer = new DrawerBuilder()
                    .withActivity(this)
                    .withToolbar(toolbar)
                    .withHasStableIds(true)
                    .withAccountHeader(header)
                    .addDrawerItems(
                            new PrimaryDrawerItem().withName("О приложении").withIcon(R.drawable.ic_dev).withIdentifier(10).withSelectable(false),
                            new PrimaryDrawerItem().withName("My page").withIcon(R.drawable.profileb).withIdentifier(13).withSelectable(false),
                            new PrimaryDrawerItem().withName("Books").withIcon(R.drawable.bookb).withIdentifier(14).withSelectable(false),
                            new PrimaryDrawerItem().withName("Links").withIcon(R.drawable.linkb).withIdentifier(17).withSelectable(false),
                            new PrimaryDrawerItem().withName("Rating").withIcon(R.drawable.list_numbered_black_18dp).withIdentifier(19).withSelectable(false),

                            new PrimaryDrawerItem().withName("AddTest").withIcon(R.drawable.edit_black_18dp).withIdentifier(18).withSelectable(false),
                            new DividerDrawerItem(),
                            new SecondaryDrawerItem().withName("YouTube").withIcon(R.drawable.ic_youtube).withIdentifier(20).withSelectable(false),
                            new SecondaryDrawerItem().withName("Facebook").withIcon(R.drawable.ic_facebook).withIdentifier(21).withSelectable(false),
                            new SecondaryDrawerItem().withName("Twitter").withIcon(R.drawable.ic_twitter).withIdentifier(22).withSelectable(false),
                            new SecondaryDrawerItem().withName("Google+").withIcon(R.drawable.ic_google_plus).withIdentifier(23).withSelectable(false),

                            new DividerDrawerItem(),
                            new SecondaryDrawerItem().withName("Настройки").withIcon(R.drawable.ic_settings).withIdentifier(30).withSelectable(false),
                            new SecondaryDrawerItem().withName("Оцените приложение").withIcon(R.drawable.ic_rating).withIdentifier(31).withSelectable(false),
                            new SecondaryDrawerItem().withName("Поделитесь").withIcon(R.drawable.ic_share).withIdentifier(32).withSelectable(false),
                            new SecondaryDrawerItem().withName("Соглашения").withIcon(R.drawable.ic_privacy_policy).withIdentifier(33).withSelectable(false),

                            new DividerDrawerItem(),
                            new SecondaryDrawerItem().withName("Выход").withIcon(R.drawable.ic_exit).withIdentifier(40).withSelectable(false)

                    )
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                            if (drawerItem != null) {
                                Intent intent = null;
                                if (drawerItem.getIdentifier() == 10) {
                                    ActivityUtilities.getInstance().invokeNewActivity(activity, AboutDev.class, false);

                                } else if (drawerItem.getIdentifier() == 20) {
                                    AppUtilities.youtubeLink(activity);
                                }
                                else if (drawerItem.getIdentifier() == 13) {
                                    AppUtilities.openProfile(activity);
                                }

                                else if (drawerItem.getIdentifier() == 14) {
                                    AppUtilities.openBooksPage(activity);
                                }
                                else if (drawerItem.getIdentifier() == 17) {
                                    //  AppUtilities.youtubeLink(activity);
                                }
                                else if (drawerItem.getIdentifier() == 18) {
                                  AppUtilities.openTestAddPage(activity);
                                }
                                else if (drawerItem.getIdentifier() == 19) {
                                    AppUtilities.openRatingPage(activity);
                                }
                                else if (drawerItem.getIdentifier() == 21) {
                                    AppUtilities.facebookLink(activity);
                                } else if (drawerItem.getIdentifier() == 22) {
                                    AppUtilities.twitterLink(activity);
                                } else if (drawerItem.getIdentifier() == 23) {
                                    AppUtilities.googlePlusLink(activity);
                                } else if (drawerItem.getIdentifier() == 30) {
                                    // TODO: invoke SettingActivity
                                } else if (drawerItem.getIdentifier() == 31) {
                                    AppUtilities.rateThisApp(activity);
                                } else if (drawerItem.getIdentifier() == 32) {
                                    AppUtilities.shareApp(activity);
                                } else if (drawerItem.getIdentifier() == 33) {
                                    ActivityUtilities.getInstance().invokeCustomUrlActivity(activity, CustomUrlActivity.class,
                                            getResources().getString(R.string.privacy), getResources().getString(R.string.privacy_url), false);
                                } else if (drawerItem.getIdentifier() == 40) {
                                    setSharedPreferencesExit();
                                    startActivity(new Intent(MainActivity.this , StartActivity.class));
                                }
                            }

                            return false;
                        }
                    })
                    .withSavedInstance(savedInstanceState)
                    .withShowDrawerOnFirstLaunch(true)
                    .withShowDrawerUntilDraggedOpened(true)
                    .build();
        }
        else{
            drawer = new DrawerBuilder()
                    .withActivity(this)
                    .withToolbar(toolbar)
                    .withHasStableIds(true)
                    .withAccountHeader(header)
                    .addDrawerItems(
                            new PrimaryDrawerItem().withName("О приложении").withIcon(R.drawable.ic_dev).withIdentifier(10).withSelectable(false),
                            new PrimaryDrawerItem().withName("My page").withIcon(R.drawable.profileb).withIdentifier(13).withSelectable(false),
                            new PrimaryDrawerItem().withName("Books").withIcon(R.drawable.bookb).withIdentifier(14).withSelectable(false),
                            new PrimaryDrawerItem().withName("Links").withIcon(R.drawable.linkb).withIdentifier(17).withSelectable(false),

                            new DividerDrawerItem(),
                            new SecondaryDrawerItem().withName("YouTube").withIcon(R.drawable.ic_youtube).withIdentifier(20).withSelectable(false),
                            new SecondaryDrawerItem().withName("Facebook").withIcon(R.drawable.ic_facebook).withIdentifier(21).withSelectable(false),
                            new SecondaryDrawerItem().withName("Twitter").withIcon(R.drawable.ic_twitter).withIdentifier(22).withSelectable(false),
                            new SecondaryDrawerItem().withName("Google+").withIcon(R.drawable.ic_google_plus).withIdentifier(23).withSelectable(false),

                            new DividerDrawerItem(),
                            new SecondaryDrawerItem().withName("Настройки").withIcon(R.drawable.ic_settings).withIdentifier(30).withSelectable(false),
                            new SecondaryDrawerItem().withName("Оцените приложение").withIcon(R.drawable.ic_rating).withIdentifier(31).withSelectable(false),
                            new SecondaryDrawerItem().withName("Поделитесь").withIcon(R.drawable.ic_share).withIdentifier(32).withSelectable(false),
                            new SecondaryDrawerItem().withName("Соглашения").withIcon(R.drawable.ic_privacy_policy).withIdentifier(33).withSelectable(false),

                            new DividerDrawerItem(),
                            new SecondaryDrawerItem().withName("Выход").withIcon(R.drawable.ic_exit).withIdentifier(40).withSelectable(false)

                    )
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                            if (drawerItem != null) {
                                Intent intent = null;
                                if (drawerItem.getIdentifier() == 10) {
                                    ActivityUtilities.getInstance().invokeNewActivity(activity, AboutDev.class, false);

                                } else if (drawerItem.getIdentifier() == 20) {
                                    AppUtilities.youtubeLink(activity);
                                }
                                else if (drawerItem.getIdentifier() == 13) {
                                    AppUtilities.openProfile(activity);
                                }
                                else if (drawerItem.getIdentifier() == 14) {
                                    AppUtilities.openBooksPage(activity);
                                }
                                else if (drawerItem.getIdentifier() == 17) {
                                    //  AppUtilities.youtubeLink(activity);
                                }

                                else if (drawerItem.getIdentifier() == 21) {
                                    AppUtilities.facebookLink(activity);
                                } else if (drawerItem.getIdentifier() == 22) {
                                    AppUtilities.twitterLink(activity);
                                } else if (drawerItem.getIdentifier() == 23) {
                                    AppUtilities.googlePlusLink(activity);
                                } else if (drawerItem.getIdentifier() == 30) {
                                    // TODO: invoke SettingActivity
                                } else if (drawerItem.getIdentifier() == 31) {
                                    AppUtilities.rateThisApp(activity);
                                } else if (drawerItem.getIdentifier() == 32) {
                                    AppUtilities.shareApp(activity);
                                } else if (drawerItem.getIdentifier() == 33) {
                                    ActivityUtilities.getInstance().invokeCustomUrlActivity(activity, CustomUrlActivity.class,
                                            getResources().getString(R.string.privacy), getResources().getString(R.string.privacy_url), false);
                                } else if (drawerItem.getIdentifier() == 40) {
                                    setSharedPreferencesExit();
                                    startActivity(new Intent(MainActivity.this , StartActivity.class));
                                }
                            }

                            return false;
                        }
                    })
                    .withSavedInstance(savedInstanceState)
                    .withShowDrawerOnFirstLaunch(true)
                    .withShowDrawerUntilDraggedOpened(true)
                    .build();
        }




    }


    public void setSharedPreferencesExit(){
        SharedPreferences sPref = getSharedPreferences("sPrefer",MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sPref.edit();
        prefEditor.putString("login","");
        prefEditor.putString("email","");
        prefEditor.putString("role","");
        prefEditor.putBoolean("is_auth",false);
        prefEditor.commit();


    }


    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            AppUtilities.tapPromtToExit(this);
        }
    }

    private void loadData() {
        showLoader();
        loadJson();
    }

    private void loadJson() {

        File path = this.getFilesDir();
        File file = new File( path,AppConstants.JSON_Quiz_category_fileName);


        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        DataInputStream in = new DataInputStream(fis);



        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try{
           // br = new BufferedReader(new InputStreamReader(getAssets().open(AppConstants.CONTENT_FILE)));
            br = new BufferedReader(new InputStreamReader(in));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        parseJson(sb.toString());
    }

    private void parseJson(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray(AppConstants.JSON_KEY_ITEMS);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                String categoryId = object.getString(AppConstants.JSON_KEY_CATEGORY_ID);
                String categoryName = object.getString(AppConstants.JSON_KEY_CATEGORY_NAME);

                categoryList.add(new CategoryModel(categoryId, categoryName));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        hideLoader();
        adapter.notifyDataSetChanged();
    }

    private void initListener() {

        //notification view click listener
        mNotificationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtilities.getInstance().invokeNewActivity(activity, NotificationListActivity.class, false);
            }
        });
        // recycler list item click listener
        adapter.setItemClickListener(new ListItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {

                CategoryModel model = categoryList.get(position);
                ActivityUtilities.getInstance().invokeCommonQuizActivity(activity, QuizPromptActivity.class, model.getCategoryId(), true);
            }
        });
    }

    // received new broadcast
    private BroadcastReceiver newNotificationReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            initNotification();
        }
    };


    public void initNotification() {
        NotificationDbController notificationDbController = new NotificationDbController(context);
        TextView notificationCount = (TextView) findViewById(R.id.notificationCount);
        notificationCount.setVisibility(View.INVISIBLE);

        ArrayList<NotificationModel> notiArrayList = notificationDbController.getUnreadData();

        if (notiArrayList != null && !notiArrayList.isEmpty()) {
            int totalUnread = notiArrayList.size();
            if (totalUnread > 0) {
                notificationCount.setVisibility(View.VISIBLE);
                notificationCount.setText(String.valueOf(totalUnread));
            } else {
                notificationCount.setVisibility(View.INVISIBLE);
            }
        }

    }
}