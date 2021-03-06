package mx.itesm.kuali.kuali;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class CacheImage {
    static LruCache<String, Bitmap> mMemoryCache;

    public static void crearCache(){
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public static void agregarBitmapACache(String key, Bitmap bitmap) {
        if (obtenerBitmapDeCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public static Bitmap obtenerBitmapDeCache(String key) {
        return mMemoryCache.get(key);
    }

    public static void vaciarCache(){
        mMemoryCache.evictAll();
    }

}
