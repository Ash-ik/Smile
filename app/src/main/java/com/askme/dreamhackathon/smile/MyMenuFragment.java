package com.askme.dreamhackathon.smile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.makeramen.RoundedImageView;
import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout;
import com.mxn.soul.flowingdrawer_core.MenuFragment;


public class MyMenuFragment extends MenuFragment {

    public LeftDrawerLayout leftDrawerLayout;
    public NavigationView vNavigation;
    private RoundedImageView ivMenuUserProfilePhoto;
    private TextView ivMenuUserName;
    private LinearLayout accountLayout;
    private ImageButton openAddAccountLayout;
    private ListView accountsListView;
    String userName="",userPassword="",userEmail="";
    private final String firebaseURL="https://askme-dreamhackathon-smile-database.firebaseio.com/user";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container,
                false);
        vNavigation = (NavigationView) view.findViewById(R.id.vNavigation);

        ivMenuUserName=(TextView)view.findViewById(R.id.ivMenuUserName);

        ivMenuUserProfilePhoto = (RoundedImageView) view.findViewById(R.id.ivMenuUserProfilePhoto);
        try {
            ivMenuUserName.setText(userName);
            view.findViewById(R.id.addAccount).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    leftDrawerLayout.closeDrawer();
                    startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
                }
            });
        }catch (Exception e)
        {

        }




/*        ivMenuUserProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftDrawerLayout.closeDrawer();
                startActivity(new Intent(getActivity().getApplicationContext(), RegistrationActivity.class));
            }
        });*/


    return setupReveal(view);

}

    private void setupHeader() {
    }

    public void onOpenMenu(){
    }
    public void onCloseMenu(){
    }

    public void setUser(String userName, String userEmail,String userPassword) {
        this.userName=userName;
        this.userEmail=userEmail;
        this.userPassword=userPassword;
    }
}
