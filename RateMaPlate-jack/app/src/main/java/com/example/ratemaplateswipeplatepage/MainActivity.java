package com.example.ratemaplateswipeplatepage;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ratemaplateswipeplatepage.Model.Image;

public class MainActivity extends AppCompatActivity {

    int counter = 0;

    //create the list of images
    int[] imageArray = {
            R.drawable.dish1,
            R.drawable.dish2,
            R.drawable.dish3
    };

    Image dish1 = new Image("Salmon Dish", "Succccccculent Saucy Sssssssalmon deesh");
    Image dish2 = new Image("Some wraps", "Dno whats in them");
    Image dish3 = new Image("Tacos", "Pepperoni flat based taco with cheese");
    Image[] imageDescriptions = {
            dish1,
            dish2,
            dish3
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //set the initial values of all the fields
        ImageView image = findViewById(R.id.uploadedImage);
        image.setImageResource(imageArray[counter]);
        TextView dishTitle = findViewById(R.id.dishTitle);
        dishTitle.setText(imageDescriptions[counter].getTitle());
        TextView dishSummary = findViewById(R.id.dishSummary);
        dishSummary.setText(imageDescriptions[counter].getDescription());




    }

    public void animateRight(){
        ImageView image = findViewById(R.id.uploadedImage);
        ObjectAnimator animationToRight = ObjectAnimator.ofFloat(image, "translationX", 1000f);
        animationToRight.setDuration(200);
        animationToRight.start();
    }



    public void setImage(){
        ImageView image = findViewById(R.id.uploadedImage);
        image.setImageResource(imageArray[counter]);
    }

    public void setDescription(){
        TextView dishSummary = findViewById(R.id.dishSummary);
        dishSummary.setText(imageDescriptions[counter].getDescription());
    }

    public void setTitle(){
        TextView dishTitle = findViewById(R.id.dishTitle);
        dishTitle.setText(imageDescriptions[counter].getTitle());
    }

    /*
     * This method add the image currently displayed in the imageView to the
     * users liked images list and changes the view of the imageView to the next
     * image on the list as well as the Title and descriptions.
     */
    public void likeButtonClick(View view){
        counter++;
        //animateRight();
        setImage();
        setDescription();
        setTitle();
    }

    public void dislikeButtonClick(View view){
        counter++;
        setImage();
        setDescription();
        setTitle();
    }


}
