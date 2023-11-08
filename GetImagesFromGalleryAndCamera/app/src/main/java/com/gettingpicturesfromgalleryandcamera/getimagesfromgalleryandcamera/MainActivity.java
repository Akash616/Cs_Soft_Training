package com.gettingpicturesfromgalleryandcamera.getimagesfromgalleryandcamera;

import static android.Manifest.permission.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
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

            if (checkPerm()) {
                Toast.makeText(this, "Permission Granted Already", Toast.LENGTH_SHORT).show();
                nowCodeForOpenCamera();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, CAMERA_REQ_CODE);
            }

        } else if (view.getId() == R.id.btn_openGallery) {
            if (checkPerm()) {
                Toast.makeText(this, "Permission Granted Already", Toast.LENGTH_SHORT).show();
                nowCodeForOpenGallery();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, Gallery_REQ_CODE);
            }
        }
    }

    private boolean checkPerm() {
        int resultCamera = ActivityCompat.checkSelfPermission(this, CAMERA);
        int resultReadStorage = ActivityCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE);
        int resultWriteStorage = ActivityCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
        return resultCamera == PackageManager.PERMISSION_GRANTED && resultReadStorage == PackageManager.PERMISSION_GRANTED
                && resultWriteStorage == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_REQ_CODE) {

            if (grantResults.length > 0) { //if per. granted then, length is > 0

                int camera = grantResults[0];
                int readStorage = grantResults[1];
                int writeStorage = grantResults[2];

                boolean checkCamera = camera == PackageManager.PERMISSION_GRANTED;
                boolean checkReadStorage = readStorage == PackageManager.PERMISSION_GRANTED;
                boolean checkWriteStorage = writeStorage == PackageManager.PERMISSION_GRANTED;

                if (checkCamera && checkReadStorage && checkWriteStorage) {
                    Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                    nowCodeForOpenCamera();
                } else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == Gallery_REQ_CODE) {

            if (grantResults.length > 0) {
                int camera = grantResults[0];
                int readStorage = grantResults[1];
                int writeStorage = grantResults[2];

                boolean checkCamera = camera == PackageManager.PERMISSION_GRANTED;
                boolean checkReadStorage = readStorage == PackageManager.PERMISSION_GRANTED;
                boolean checkWriteStorage = writeStorage == PackageManager.PERMISSION_GRANTED;

                if (checkCamera && checkReadStorage && checkWriteStorage) {
                    Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                    nowCodeForOpenGallery();
                } else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }

            }

        }

    }

    private void nowCodeForOpenGallery() {
        Intent iGallery = new Intent(Intent.ACTION_PICK);
        iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //user gallery access
        startActivityForResult(iGallery, Gallery_REQ_CODE);
    }

    private void nowCodeForOpenCamera() {
        Intent iCamera =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(iCamera, CAMERA_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){

            if (requestCode == CAMERA_REQ_CODE){

                
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                iv_profileImage.setImageBitmap(bitmap);
                Toast.makeText(this, "Request Code: "+requestCode, Toast.LENGTH_SHORT).show();

            } else if (requestCode == Gallery_REQ_CODE) {

                iv_profileImage.setImageURI(data.getData());
                Toast.makeText(this, "Request Code: "+requestCode, Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(this, "Result Code: "+resultCode, Toast.LENGTH_SHORT).show();
        }

    }



}