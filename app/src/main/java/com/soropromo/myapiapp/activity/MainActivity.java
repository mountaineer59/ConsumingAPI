package com.soropromo.myapiapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.soropromo.myapiapp.R;
import com.soropromo.myapiapp.model.User;
import com.soropromo.myapiapp.rest.APIClient;
import com.soropromo.myapiapp.rest.UserEndPoints;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageView avatarImg;
    TextView userNameTV;
    TextView followersTV;
    TextView followingTV;
    TextView logIn;
    TextView email;
    Button ownedrepos;

    Bundle extras;
    String newString;

    Bitmap myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        avatarImg = (ImageView) findViewById(R.id.avatar);
        userNameTV = (TextView) findViewById(R.id.username);
        followersTV = (TextView) findViewById(R.id.followers);
        followingTV = (TextView) findViewById(R.id.following);
        logIn = (TextView) findViewById(R.id.logIn);
        email = (TextView) findViewById(R.id.email);
        ownedrepos = (Button) findViewById(R.id.ownedReposBtn);

        extras = getIntent().getExtras();
        newString = extras.getString("STRING_I_NEED");

        System.out.println(newString);
        loadData();

    }

    //called when user clicks on repositories button
    public void loadOwnRepos(View view) {
        Intent intent = new Intent(MainActivity.this,Repositories.class);
        intent.putExtra("username", newString);
        startActivity(intent);
    }

    //string goes as a parameter and bitmap is the result.
    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }
            return null;
        }
    }

    private void loadData() {
        final UserEndPoints apiService =
                APIClient.getClient().create(UserEndPoints.class);

        Call<User> call = apiService.getUser(newString);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                ImageDownloader task = new ImageDownloader();

                try {
                    myImage = task.execute(response.body().getAvatar()).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                avatarImg.setImageBitmap(myImage);
                avatarImg.getLayoutParams().height=220;
                avatarImg.getLayoutParams().width=220;

                if(response.body().getName() == null){
                    userNameTV.setText("No name provided");
                } else {
                    userNameTV.setText("Username: " + response.body().getName());
                }

                followersTV.setText("Followers: " + response.body().getFollowers());
                followingTV.setText("Following: " + response.body().getFollowing());
                logIn.setText("LogIn: " + response.body().getLogin());

                if(response.body().getEmail() == null){
                    email.setText("No email provided");
                } else{
                    email.setText("Email: " + response.body().getEmail());
                }


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("Failed!" + t.toString());

            }
        });

    }
}
