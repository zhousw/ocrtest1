package com.zl.tesseract.scanner.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zl.tesseract.R;

public class ImageDialog extends Dialog {

    private Bitmap bmp;

    private String title;

    private Context context;

    public ImageDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public ImageDialog addBitmap(Bitmap bmp) {
        if (bmp != null){
            this.bmp = bmp;
        }
        return this;
    }

    public ImageDialog addTitle(String title) {
        if (title != null){
            this.title = title;
        }
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_dialog);

        ImageView imageView = (ImageView)findViewById(R.id.image_dialog_imageView);
        TextView textView = (TextView)findViewById(R.id.image_dialog_textView);

        if (bmp != null){
            imageView.setImageBitmap(bmp);
        }

        if(title!=null){
            textView.setText(this.title);
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + ImageDialog.this.title);
                intent.setData(data);
                ImageDialog.this.context.startActivity(intent);
                ImageDialog.this.dismiss();
            }
        });
    }

    @Override
    public void dismiss() {
        bmp.recycle();
        bmp = null;
        System.gc();
        super.dismiss();
    }
}