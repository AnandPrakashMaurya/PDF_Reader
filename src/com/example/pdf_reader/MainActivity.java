package com.example.pdf_reader;

import java.io.File;
import java.io.FilenameFilter;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class MainActivity extends ListActivity{
	String[] pdflist;
    File[] imagelist;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
      //  File images =new File("/sdcard/images.jpg");
       File images = Environment.getExternalStorageDirectory();
        /*String path = Environment.getExternalStorageDirectory()+ "/pdf/images.jpg";
 
        File imgFile = new File(path);*/      
        imagelist = images.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {            	
                return ((name.endsWith(".pdf"))); 
            }
        });
        System.out.println("imagelist"+imagelist);
        pdflist = new String[imagelist.length];
        System.out.println("Image"+imagelist.length);
        for (int i = 0; i < imagelist.length; i++) {
            pdflist[i] = imagelist[i].getName();
            
        }
        this.setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, pdflist));
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String path = imagelist[(int) id].getAbsolutePath();
        openPdfIntent(path);
    }

    private void openPdfIntent(String path) {
        try {
            final Intent intent = new Intent(MainActivity.this, Second.class);
            intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, path);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}