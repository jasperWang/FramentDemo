package com.example.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

/**
 * ����ģʽ�� ͼƬ�����࣬����ѹ��ͼƬ���洢ͼƬ�������
 * 
 * @author Administrator
 * 
 */
public class ImagesLaod {

	/**
	 * ͼƬ���漼���ĺ����࣬���ڻ����������غõ�ͼƬ���ڳ����ڴ�ﵽ�趨ֵʱ�Ὣ�������ʹ�õ�ͼƬ�Ƴ�����
	 */
	private static LruCache<String, Bitmap> mLruCache;

	/**
	 * ImageLoader��ʵ����
	 */
	//private static ImagesLaod mImagesLaod;
	private static ImagesLaod mImagesLaod = new ImagesLaod();

	private ImagesLaod() {
		// ��ȡӦ�ó����������ڴ�
		int maxCache = (int) Runtime.getRuntime().maxMemory();
		// ����ͼƬ�����СΪ�����������ڴ��1/8
		int cacheSize = maxCache / 8;
		mLruCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getByteCount();
			}
		};
	}

	public static ImagesLaod getInstance() {

//		if(mImagesLaod==null){
//			mImagesLaod=new ImagesLaod();
//		}
		return mImagesLaod;
	}

	/**
	 * ��һ��ͼƬ�洢��LruCache�С�
	 * 
	 * @param key
	 *            LruCache�ļ������ﴫ��ͼƬ��URL��ַ��
	 * @param bitmap
	 *            LruCache�ļ������ﴫ������������ص�Bitmap����
	 */
	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		// �ж�ͼƬ�Ƿ��Ѿ��ڻ�������
		if (getBitmapFromMemoryCache(key) == null) {
			mLruCache.put(key, bitmap);
		}

	}

	/**
	 * ��LruCache�л�ȡһ��ͼƬ����������ھͷ���null��
	 * 
	 * @param key
	 *            LruCache�ļ������ﴫ��ͼƬ��URL��ַ��
	 * @return ��Ӧ�������Bitmap���󣬻���null��
	 */
	public Bitmap getBitmapFromMemoryCache(String key) {

		return mLruCache.get(key);
	}

	/**
	 * ����Ŀ���Ȼ�ȡѹ������
	 * 
	 * @param options
	 * @param reqWidth
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth) {
		// ԴͼƬ�Ŀ��
		final int width = options.outWidth;
		int inSampleSize = 1;
		// ���ͼƬʵ�ʿ�ȴ���Ŀ���ȼ���ѹ������
		if (width > reqWidth) {
			// ���ؼ������������
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = widthRatio;
		}

		return inSampleSize;
	}

	public static Bitmap decodeSampledBitmapFromResource(String pathName,
			int reqWidth) {
		// ��һ�ν�����inJustDecodeBounds����Ϊtrue(�������ڴ�)������ȡͼƬ��С
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName, options);
		// �������涨��ķ�������inSampleSizeֵ
		options.inSampleSize = calculateInSampleSize(options, reqWidth);
		// ʹ�û�ȡ����inSampleSizeֵ�ٴν���ͼƬ
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(pathName, options);
	}
}
