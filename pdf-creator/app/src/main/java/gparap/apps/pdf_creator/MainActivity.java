package gparap.apps.pdf_creator;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by gparap on 2020-10-07.
 */
public class MainActivity extends AppCompatActivity {
    Button buttonCreatePDF,
            buttonOpenPDF;
    EditText editTextPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //get widgets
        buttonCreatePDF = findViewById(R.id.buttonCreatePDF);
        buttonOpenPDF = findViewById(R.id.buttonOpenPDF);
        editTextPDF = findViewById(R.id.editTextPDF);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE",
                                            "android.permission.READ_EXTERNAL_STORAGE"}, 999);
        }
    }

    /**
     * Creates a PDF file.
     *
     * @param view button
     */
    public void onClickCreatePDF(View view) {
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();
        String pdfText = "";

        //get PDF text to write to file
        if (!editTextPDF.getText().toString().isEmpty()){
            pdfText = editTextPDF.getText().toString();
        }else{
            Toast.makeText(this, "Please input file data", Toast.LENGTH_SHORT).show();
        }

        //get device screen dimensions
        int width;
        int height;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            width = getWindowManager().getCurrentWindowMetrics().getBounds().width();
            height = getWindowManager().getCurrentWindowMetrics().getBounds().height();
        }
        else{
            Display display = getWindowManager().getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            width = point.x;
            height = point.y;
        }

        paint.setTextSize(64);

        //create PDF document page
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(width, height, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        //draw text on document page
        page.getCanvas().drawText(pdfText, 0, paint.getTextSize(), paint);

        //finish page
        pdfDocument.finishPage(page);

        //create PDF file
        File file = new File(Environment.getExternalStorageDirectory() + "/documents/myFile.pdf");
        try {
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //write to PDF file
        OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(file));
            pdfDocument.writeTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //close stream
                assert outputStream != null;
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // close PDF document
        pdfDocument.close();
    }

    /**
     * Opens a PDF file.
     *
     * @param view button
     */
    public void onClickOpenPDF(View view) { //TODO: NOT working...
        File file = new File(Environment.getExternalStorageDirectory() + "/documents/myFile.pdf");

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        intent.setPackage("com.adobe.reader");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                startActivity(intent);
            } catch (android.os.FileUriExposedException e) {
                e.printStackTrace();
            }
        }else{
            try {
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}