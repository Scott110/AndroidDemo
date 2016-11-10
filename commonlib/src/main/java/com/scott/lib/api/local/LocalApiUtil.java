package com.scott.lib.api.local;

import java.io.File;

import io.rx_cache.internal.RxCache;
import io.victoralbertos.jolyglot.JacksonSpeaker;

/**
 * Created by scott_he on 16/10/17.
 */

public class LocalApiUtil {
    public static <T> T getInstance(Class<T> tClass, File cacheFile) {
        T t = new RxCache.Builder()
                .persistence(cacheFile, new JacksonSpeaker())
                .using(tClass);
        return t;
    }


}
