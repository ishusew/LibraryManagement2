package ishara.atigapaha.librarymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import ishara.atigapaha.librarymanagement.Author.AuthorActivity;
import ishara.atigapaha.librarymanagement.Branch.BranchActivity;
import ishara.atigapaha.librarymanagement.Publisher.PublisherActivity;


//import android.widget.Toolbar;

public class MemberDashboardActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_dashboard);




    }


    public void book(View view) {
        Intent intent = new Intent(getApplicationContext(), BookDetails.class);
        startActivity(intent);

    }

    public void publisher(View view) {
        Intent intent = new Intent(getApplicationContext(), PublisherActivity.class);
        startActivity(intent);
    }

    public void member(View view) {
        Intent intent = new Intent(getApplicationContext(), MembershipActivity.class);
        startActivity(intent);
    }

    public void author(View view) {
        Intent intent = new Intent(getApplicationContext(), AuthorActivity.class);
        startActivity(intent);

    }

    public void Branches(View view) {
        Intent intent = new Intent(getApplicationContext(), BranchActivity.class);
        startActivity(intent);
    }
}