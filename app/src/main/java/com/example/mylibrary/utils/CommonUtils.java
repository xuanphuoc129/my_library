package com.example.mylibrary.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.mylibrary.R;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class CommonUtils {

    public CommonUtils() {
    }

    /**
     * Chức năng: Kiểm tra null và rỗng
     *
     * @created_by xuan phuoc on 2019-04-27
     */
    public static boolean checkNullEmpty(String data) {
        return data == null || data.trim().isEmpty();
    }

    /**
     * Chức năng: đọc file json từ assets
     *
     * @created_by xuan phuoc on 2019-04-27
     */
    public static String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * Chức năng: Random 1 số trong khoảng
     *
     * @created_by xuan phuoc on 2019-04-27
     */
    public static int randomInt(int start, int end) {
        Random r = new Random();
        return r.nextInt(end - start) + start;
    }

    /**
    *
    * @created_by xuan phuoc on 2019-04-27
    */
    public static void loadImageFromUrl(Context context, ImageView imageView, String url) {
        Glide
                .with(context)
                .load(url)
                .apply(RequestOptions.centerCropTransform())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(5)))
                .placeholder(R.drawable.ic_image)
                .into(imageView);
    }
}
