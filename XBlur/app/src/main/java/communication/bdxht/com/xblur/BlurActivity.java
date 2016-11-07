package communication.bdxht.com.xblur;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bdxh.Blur;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;


/**
 *
 * Created by bdxh on 2016/7/22.
 */
public class BlurActivity extends AppCompatActivity{


    private ImageView imageBlur;

    private ImageView circleImageView;

    String url = "http://a.hiphotos.baidu.com/image/h%3D360/sign=a2eb7a0eb6de9c82b965ff895c8080d2/d1160924ab18972be4b49efde3cd7b899e510a7e.jpg";


    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur);

        imageBlur = (ImageView) this.findViewById(R.id.image_blur);

        circleImageView = (ImageView) this.findViewById(R.id.source_image);

        Glide.with(this)
                .load(url)
                .transform(new GlideCircleTransfrom(this))
                .into(new GlideDrawableImageViewTarget(circleImageView){
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                    }
                });

        Glide.with(this)
                .load(url)
//                .bitmapTransform(new MyBlurT(this))
                .into(new GlideDrawableImageViewTarget(imageBlur){
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);

                        imageBlur.buildDrawingCache();

                        Bitmap bmp = imageBlur.getDrawingCache();
                        Matrix matrix = new Matrix();

                        matrix.postScale(0.4f,0.4f); //长和宽放大缩小的比例
                        Bitmap resizeBmp = Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);

                        long startTime = System.currentTimeMillis();

                        Bitmap bitmap = Blur.blurBitmap(resizeBmp,10,true);
                        Log.e("耗时:"," --->" + (System.currentTimeMillis() - startTime));
                        imageBlur.setImageBitmap(bitmap);
                        bmp.recycle();
                    }
                });



    }


//    private void start() {
//
//
//        circleImageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                circleImageView.getViewTreeObserver().removeOnPreDrawListener(this);
//                circleImageView.buildDrawingCache();
//
//                Bitmap bmp = circleImageView.getDrawingCache();
//                Matrix matrix = new Matrix();
//
//                matrix.postScale(0.4f,0.4f); //长和宽放大缩小的比例
//                Bitmap resizeBmp = Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
//
//                long startTime = System.currentTimeMillis();
//
//                Bitmap bitmap = Blur.blurBitmap(resizeBmp,50);
//                Log.e("耗时:"," --->" + (System.currentTimeMillis() - startTime));
//                imageBlur.setBackground(new BitmapDrawable(bitmap));
//                bmp.recycle();
//                return true;
//            }
//        });
//    }


    /**
     * 截取,压缩
     * @param bitmap
     * @param edgeLength
     * @return
     */
    public Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength) {
        if(null == bitmap || edgeLength <= 0) {
            return  null;
        }

        Bitmap result = bitmap;

        int widthOrg = bitmap.getWidth();
        int heightOrg = bitmap.getHeight();

        if(widthOrg > edgeLength && heightOrg > edgeLength) {
            //压缩到一个最小长度是edgeLength的bitmap
            int longerEdge = (int)(edgeLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg, heightOrg));
            int scaledWidth = widthOrg > heightOrg ? longerEdge : edgeLength;
            int scaledHeight = widthOrg > heightOrg ? edgeLength : longerEdge;
            Bitmap scaledBitmap;

            try{
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
            }
            catch(Exception e){
                return null;
            }

            //从图中截取正中间的正方形部分。
            int xTopLeft = (scaledWidth - edgeLength) / 2;
            int yTopLeft = (scaledHeight - edgeLength) / 2;

            try{
                result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, edgeLength, edgeLength);
                scaledBitmap.recycle();

//                //再见将裁剪的图片缩放
//                Matrix matrix = new Matrix();
//                matrix.postScale(1f,1f); //长和宽放大缩小的比例
//
//                resizeBmp = Bitmap.createBitmap(result,0,0,result.getWidth(),result.getHeight(),matrix,true);
//                result.recycle();


            }
            catch(Exception e){
                return null;
            }
        }

        return result;
    }

//    private class AsycMakeBitmap extends AsyncTask<String, Void, Bitmap> {
//        @Override
//        protected Bitmap doInBackground(String... params) {
//            View view = getWindow().getDecorView();
//            view.setDrawingCacheEnabled(true);
//            view.buildDrawingCache(true);
//            Bitmap bmp1 = view.getDrawingCache();
//            return FastBlur.doBlur(bmp1, 30, true);
//        }
//
//        @SuppressLint("NewApi")
//        @Override
//        protected void onPostExecute(Bitmap result) {
//            super.onPostExecute(result);
//            iv_splash.setBackground(new BitmapDrawable(result));
//        }
//
//    }


}
