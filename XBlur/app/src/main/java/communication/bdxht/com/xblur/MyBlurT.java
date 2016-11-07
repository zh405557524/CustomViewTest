package communication.bdxht.com.xblur;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

/**
 *
 * Created by bdxh on 2016/7/26.
 */
public class MyBlurT implements Transformation<Bitmap> {

    private static int MAX_RADIUS = 25;
    private Context mContext;
    private BitmapPool mBitmapPool;

    private int mRadius;

    public MyBlurT(Context context) {
        this(context, Glide.get(context).getBitmapPool(), MAX_RADIUS);
    }

    public MyBlurT(Context context, int radius) {
        this(context, Glide.get(context).getBitmapPool(), radius);
    }

    public MyBlurT(Context context, BitmapPool pool, int radius) {
        mContext = context.getApplicationContext();
        mBitmapPool = pool;
        mRadius = radius;
    }


    @Override
    public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
        Bitmap source = resource.get();

//        Matrix matrix = new Matrix();
//
//        matrix.postScale(1f,1f); //长和宽放大缩小的比例
//
//        Bitmap resizeBmp = Bitmap.createBitmap(source,0,0,source.getWidth(),source.getHeight(),matrix,true);

//        Bitmap bitmap = Blur.blurBitmap(resizeBmp,1);

        return BitmapResource.obtain(source, mBitmapPool);
    }

    @Override
    public String getId() {
        return "BlurTransformation";
    }
}
