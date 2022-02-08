package ishara.atigapaha.librarymanagement.Publisher;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ishara.atigapaha.librarymanagement.R;

public class PublisherActivity extends AppCompatActivity implements View.OnClickListener {

    EditText NAME,ADDRESS,PHONE;
    Button btnAdd,btnDelete,btnModify,btnView,btnViewAll;
    SQLiteDatabase db;
//    publishDbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher);





        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#808080"));


        // Initializing controls
        NAME=(EditText)findViewById(R.id.name);
        ADDRESS=(EditText)findViewById(R.id.address);
        PHONE=(EditText)findViewById(R.id.phone);
        btnAdd=(Button)findViewById(R.id.button);
        btnDelete=(Button)findViewById(R.id.button2);
        btnModify=(Button)findViewById(R.id.button3);
        btnView=(Button)findViewById(R.id.button5);
        btnViewAll=(Button)findViewById(R.id.button4);


// Registering event handlers

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener( this);



// Creating database and table

        db=openOrCreateDatabase("publisherDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS publisher(NAME VARCHAR(20) PRIMARY KEY,address VARCHAR(30),phone VARCHAR(10));");


    }


    public void clearText() {
        NAME.setText("");
        ADDRESS.setText("");
        PHONE.setText("");
        NAME.requestFocus(); }

    @Override
    public void onClick(View view) {

        // Adding a record
        if(view==btnAdd)
        {
            // Checking empty fields
            if(NAME.getText().toString().trim().length()==0||ADDRESS.getText().toString().trim().length()==0|| PHONE.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }

            // Inserting record
            db.execSQL("INSERT INTO publisher VALUES('"+NAME.getText()+"','"+ADDRESS.getText()+"','"+PHONE.getText()+"');");
            showMessage("Success", "Record added");
            clearText();
        }
        // Deleting a record
        if(view==btnDelete)
        {
            // Checking empty roll number
            if(NAME.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Name");
                return;         }
            // Searching roll number
            Cursor c=db.rawQuery("SELECT * FROM publisher WHERE NAME='"+NAME.getText()+"'", null);
            if(c.moveToFirst())
            {
                // Deleting record if found
                db.execSQL("DELETE FROM publisher WHERE NAME='"+ NAME.getText()+"'");
                showMessage("Success", "Record Deleted");
            }         else
            {
                showMessage("Error", "Invalid name");
            }         clearText();     }

        // Modifying a record
        if(view==btnModify)     {
            // Checking empty roll number
            if(NAME.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter name");
                return;
            }
            // Searching roll number
            Cursor c=db.rawQuery("SELECT * FROM publisher WHERE NAME='"+NAME.getText()+"'", null);
            if(c.moveToFirst())
            {
                // Modifying record if found
                db.execSQL("UPDATE publisher SET ADDRESS='"+ADDRESS.getText()+"',PHONE='"+PHONE.getText()+                     "' " +
                        "WHERE NAME='"+NAME.getText()+"'");
                showMessage("Success", "Record Modified");
            }         else         {
                showMessage("Error", "Invalid name");         }
            clearText();
        }


        //Viewing a record
        if(view==btnView) {
            if (NAME.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter name");
                return;
            }

// Searching roll number
            Cursor c = db.rawQuery("SELECT * FROM publisher WHERE NAME='" + NAME.getText() + "'", null);
            if (c.moveToFirst()) {
                // Displaying record if found
                ADDRESS.setText(c.getString(1));
                PHONE.setText(c.getString(2));

            } else {
                showMessage("Error", "Invalid name");
                clearText();
            }
        }
//Viewing all records
        if(view==btnViewAll)     {
// Retrieving all records
            Cursor c=db.rawQuery("SELECT * FROM publisher", null);
// Checking if no records found
            if(c.getCount()==0)         {
                showMessage("Error", "No records found");
                return;
            }



// Appending records to a string buffer

            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())         {
                buffer.append("name: "+c.getString(0)+"\n");
                buffer.append("address: "+c.getString(1)+"\n");
                buffer.append("phone: "+c.getString(2)+"\n\n");
            }
            // Displaying all records

            showMessage("Publisher Details", buffer.toString());
        }

    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }


}

