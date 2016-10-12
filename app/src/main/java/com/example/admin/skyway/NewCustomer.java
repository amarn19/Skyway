package com.example.admin.skyway;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewCustomer extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText nm1;
    private EditText ad1;
    private EditText ph1;
    private EditText alt1;
    private EditText tent1;
    private CheckBox m;
    private CheckBox y;
    private CheckBox h;
    private Spinner spinner;
    static final int REQUEST_TAKE_PHOTO = 1;
    private ImageView mImageView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);
        initUi();
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.documents_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(getApplicationContext(), About.class));
                return true;
//            case R.id.help:
//                showHelp();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void upload(View v)
    {
        dispatchTakePictureIntent();
        //galleryAddPic();
//setPic();
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
}
    private void setPic() {
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
    }
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }
    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
    private void initUi() {

        nm1 = (EditText) findViewById(R.id.nm);
        ad1 = (EditText) findViewById(R.id.address);
        ph1 = (EditText) findViewById(R.id.phone_no);
        alt1 = (EditText) findViewById(R.id.altphone_no);
        tent1 = (EditText) findViewById(R.id.tent);
        m = (CheckBox) findViewById(R.id.monthly);
        y = (CheckBox) findViewById(R.id.full);
        h = (CheckBox) findViewById(R.id.half);
        spinner = (Spinner) findViewById(R.id.documents_spinner);
    }

    public String getdata() {

        final StringBuilder paymentStringBuilder = new StringBuilder();
        if (m.isChecked()) paymentStringBuilder.append(m.getText().toString());
        if (h.isChecked()) paymentStringBuilder.append(h.getText().toString());
        if (y.isChecked()) paymentStringBuilder.append(y.getText().toString());
        return "\nName: " + nm1.getText().toString() +
                "\nAddress: " + ad1.getText().toString() +
                "\nPhone No.: " + ph1.getText().toString() +
                "\nAlternate No.: " + alt1.getText().toString() +
                "\nTenant of: " + tent1.getText().toString() +
                "\nDocuments: " + spinner.getSelectedItem().toString() +
                "\nPayment Mode: " + paymentStringBuilder.toString();
    }

    public void submit(final View v) {
        final Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("application/image");
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"amarn19@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "NewCustomerDetails");
        email.putExtra(Intent.EXTRA_TEXT, getdata());
        email.putExtra(Intent.EXTRA_STREAM, Uri.parse(mCurrentPhotoPath));
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
}
