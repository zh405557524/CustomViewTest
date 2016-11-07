//package communication.bdxht.com.xblur;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Matrix;
//import android.graphics.Paint;
//import android.graphics.PorterDuff;
//import android.graphics.PorterDuffXfermode;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.util.AttributeSet;
//import android.widget.ImageView;
//
///**
// *http://a.hiphotos.baidu.com/image/h%3D360/sign=a2eb7a0eb6de9c82b965ff895c8080d2/d1160924ab18972be4b49efde3cd7b899e510a7e.jpg
// * Created by bdxh on 2016/7/22.
// */
//public class CircleImageView extends ImageView{
//
//    private Paint paint;
//    private Paint paintBorder;
//    private Bitmap mSrcBitmap;
//
//
//    public CircleImageView(Context context) {
//        this(context, null);
//    }
//
//    public CircleImageView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//
//        int srcResource = attrs.getAttributeResourceValue(
//                "http://schemas.android.com/apk/res/android", "src", 0);
//        if (srcResource != 0)
//            mSrcBitmap = BitmapFactory.decodeResource(getResources(),
//                    srcResource);
//    }
//
//
//    private void initPaint(){
//        paint = new Paint();
//        paint.setAntiAlias(true);
//        paintBorder = new Paint();
//        paintBorder.setAntiAlias(true);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        int width = canvas.getWidth() - getPaddingLeft() - getPaddingRight();
//        int height = canvas.getHeight() - getPaddingTop() - getPaddingBottom();
//        Bitmap image = drawableToBitmap(getDrawable());
//        Bitmap reSizeImage = reSizeImageC(image, width, height);
//        canvas.drawBitmap(createCircleImage(reSizeImage, width, height),
//                getPaddingLeft(), getPaddingTop(), null);
//    }
//
//    /**
//     * 画圆
//     *
//     * @param source
//     * @param width
//     * @param height
//     * @return
//     */
//    private Bitmap createCircleImage(Bitmap source, int width, int height) {
//
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        Bitmap target = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(target);
//        canvas.drawCircle(width / 2, height / 2, Math.min(width, height) / 2,
//                paint);
//        // 核心代码取两个图片的交集部分
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(source, (width - source.getWidth()) / 2,
//                (height - source.getHeight()) / 2, paint);
//        return target;
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        int height = MeasureSpec.getSize(heightMeasureSpec);
//        setMeasuredDimension(width, height);
//    }
//
//    /**
//     * drawable转bitmap
//     *
//     * @param drawable
//     * @return
//     */
//    private Bitmap drawableToBitmap(Drawable drawable) {
//        if (drawable == null) {
//            if (mSrcBitmap != null) {
//                return mSrcBitmap;
//            } else {
//                return null;
//            }
//        } else if (drawable instanceof BitmapDrawable) {
//            return ((BitmapDrawable) drawable).getBitmap();
//        }
//        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
//                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//        drawable.draw(canvas);
//        return bitmap;
//    }
//
//    /**
//     * 重设Bitmap的宽高
//     *
//     * @param bitmap
//     * @param newWidth
//     * @param newHeight
//     * @return
//     */
//    private Bitmap reSizeImageC(Bitmap bitmap, int newWidth, int newHeight) {
//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();
//        int x = (newWidth - width) / 2;
//        int y = (newHeight - height) / 2;
//        if (x > 0 && y > 0) {
//            return Bitmap.createBitmap(bitmap, 0, 0, width, height, null, true);
//        }
//
//        float scale = 1;
//
//        if (width > height) {
//            // 按照宽度进行等比缩放
//            scale = ((float) newWidth) / width;
//
//        } else {
//            // 按照高度进行等比缩放
//            // 计算出缩放比
//            scale = ((float) newHeight) / height;
//        }
//        Matrix matrix = new Matrix();
//        matrix.postScale(scale, scale);
//        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
//    }
//}
