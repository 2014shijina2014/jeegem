package com.jeegem.common.utils.twoCode;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

/**
 * TwoDimensionCodeImage 类：二维码图片对象
 */
public class TwoDimensionCodeImage implements QRCodeImage{
	
	BufferedImage bufImg;
	 
    public TwoDimensionCodeImage(BufferedImage bufImg) {
        this.bufImg = bufImg;
    }
 
    @Override
    public int getHeight() {
        return bufImg.getHeight();
    }
 
    @Override
    public int getPixel(int x, int y) {
        return bufImg.getRGB(x, y);
    }
 
    @Override
    public int getWidth() {
        return bufImg.getWidth();
    }

}
