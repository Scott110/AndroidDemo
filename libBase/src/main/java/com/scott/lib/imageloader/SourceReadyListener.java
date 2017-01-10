package com.scott.lib.imageloader;

/**
 * author: heshantao
 * data: 2017/1/10.
 * 通知准备就绪
 */
public interface SourceReadyListener {

    void onResourceReady(int width, int height);
}
