package com.example.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

/**
 * 单例模式的 图片处理类，用于压缩图片，存储图片到缓存等
 * 
 * @author Administrator
 * 
 */
public class ImagesLaod {

	/**
	 * 图片缓存技术的核心类，用于缓存所有下载好的图片，在程序内存达到设定值时会将最少最近使用的图片移除掉。
	 */
	private static LruCache<String, Bitmap> mLruCache;

	/**
	 * ImageLoader的实例。
	 */
	//private static ImagesLaod mImagesLaod;
	private static ImagesLaod mImagesLaod = new ImagesLaod();

	private ImagesLaod() {
		// 获取应用程序最大可用内存
		int maxCache = (int) Runtime.getRuntime().maxMemory();
		// 设置图片缓存大小为程序最大可用内存的1/8
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
	 * 将一张图片存储到LruCache中。
	 * 
	 * @param key
	 *            LruCache的键，这里传入图片的URL地址。
	 * @param bitmap
	 *            LruCache的键，这里传入从网络上下载的Bitmap对象。
	 */
	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		// 判断图片是否已经在缓存中了
		if (getBitmapFromMemoryCache(key) == null) {
			mLruCache.put(key, bitmap);
		}

	}

	/**
	 * 从LruCache中获取一张图片，如果不存在就返回null。
	 * 
	 * @param key
	 *            LruCache的键，这里传入图片的URL地址。
	 * @return 对应传入键的Bitmap对象，或者null。
	 */
	public Bitmap getBitmapFromMemoryCache(String key) {

		return mLruCache.get(key);
	}

	/**
	 * 根据目标宽度获取压缩比例
	 * 
	 * @param options
	 * @param reqWidth
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth) {
		// 源图片的宽度
		final int width = options.outWidth;
		int inSampleSize = 1;
		// 如果图片实际宽度大于目标宽度计算压缩比例
		if (width > reqWidth) {
			// 返回计算后的整数结果
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = widthRatio;
		}

		return inSampleSize;
	}

	public static Bitmap decodeSampledBitmapFromResource(String pathName,
			int reqWidth) {
		// 第一次解析将inJustDecodeBounds设置为true(不分配内存)，来获取图片大小
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName, options);
		// 调用上面定义的方法计算inSampleSize值
		options.inSampleSize = calculateInSampleSize(options, reqWidth);
		// 使用获取到的inSampleSize值再次解析图片
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(pathName, options);
	}
}
