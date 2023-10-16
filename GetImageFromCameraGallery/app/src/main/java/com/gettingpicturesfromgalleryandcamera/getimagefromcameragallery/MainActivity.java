package com.gettingpicturesfromgalleryandcamera.getimagefromcameragallery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView iv_profileImage;
    Button btn_openCamera, btn_openGallery;
    private final int CAMERA_REQ_CODE = 100;
    private final int Gallery_REQ_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_profileImage = findViewById(R.id.iv_profileImage);
        btn_openCamera = findViewById(R.id.btn_openCamera);
        btn_openGallery = findViewById(R.id.btn_openGallery);

        btn_openCamera.setOnClickListener(this);
        btn_openGallery.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_openCamera) {
            openCamera();
        } else if (view.getId() == R.id.btn_openGallery) {
            openGallery();
        }
    }

    private void openCamera() {
        //Implicit Intent passing
        Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(iCamera, CAMERA_REQ_CODE);
    }

    private void openGallery() {
        Intent iGallery = new Intent(Intent.ACTION_PICK);
        iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //user gallery access
        startActivityForResult(iGallery, Gallery_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { //not null, data is there

            //diff. request code ka leya if else-if
            if (requestCode == CAMERA_REQ_CODE) {

                //for camera
                //data convert -> bitmap -> set image on imageview
                //In Android, image ko handle karna ka leya jo DATATYPE hota hai -> Bitmap
                //Bitmap img = (Bitmap) data.getExtras().get("data"); //get return-type object?(kisi bhi type ka data ko handle kar sakta hai).
                Bundle bundle = data.getExtras();
                Bitmap img = (Bitmap) bundle.get("data");
                iv_profileImage.setImageBitmap(img);
                Toast.makeText(this, "Request Code: " + requestCode, Toast.LENGTH_SHORT).show();

            } else if (requestCode == Gallery_REQ_CODE) {

                //for Gallery
                iv_profileImage.setImageURI(data.getData());
                Toast.makeText(this, "Request Code: " + requestCode, Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(this, "Result Code: " + resultCode, Toast.LENGTH_SHORT).show();
        }
    }


}