package com.study.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QrcodeUtil {

	
	/**
	 * 生成二维码图像
	 * @param content 需要生成二维码的文字或者其他内容（"http://p3.so.qhimg.com/t010caa6ee6c324f16d.jpg"）
	 * @param ImgPath 生成二维码图像保存的地址（"E:\\Personal\\Desktop\\你喜欢吗.png"）
	 * @return void 无返回值
	 */
	public static void QrcodeImg(String content,String ImgPath){
		
		int width = 140;
		int height = 140;
		try{
		//实例化Qrcode
		Qrcode qrcode = new Qrcode();
		//设置排错率  L(7%) M(15%) Q(25%) H(30%)
		qrcode.setQrcodeErrorCorrect('M');
		qrcode.setQrcodeEncodeMode('B');
		
		//设置二维码尺寸    取值范围（1-40）
		qrcode.setQrcodeVersion(7);
		
		//设置图片尺寸
		BufferedImage bufImg = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB); 
		
		//绘制二维码图片
		 Graphics2D gs = bufImg.createGraphics(); 
		
		 //设置二维码的背景颜色
		 gs.setBackground(Color.WHITE); 
		
		 //创建二维码的矩形区域
		 gs.clearRect(0, 0, width, height); 
		
		 //设置二维码的颜色
		 gs.setColor(Color.BLACK); 
		 
		 //获取内容的字节数组，设置编码集
		byte[] contentBytes = content.getBytes("utf-8"); 

		//设置偏移量 不设置可能导致解析出错
        int pixoff = 2; 


        if (contentBytes.length > 0 && contentBytes.length < 120) { 

            boolean[][] codeOut = qrcode.calQrcode(contentBytes); 

            for (int i = 0; i < codeOut.length; i++) { 

                for (int j = 0; j < codeOut.length; j++) { 

                    if (codeOut[j][i]) { 

                        gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3); 

                    } 

                } 

            } 

        } else { 

            System.out.println("你输入的内容可能超出最大限制值！"); 

        } 

        // 以上是生成二维码图片，暂时存在缓存中，下面是保存到本地

        //生成二维码图片
        File imgFile = new File(ImgPath); 
        ImageIO.write(bufImg,"png", imgFile); 
        System.out.println("恭喜你，二维码生成成功！");
    
		} catch (Exception e) { 
        e.printStackTrace(); 
    } 
} 
	//Java 入口
	public static void main(String[] args) {
			System.err.println("大家好");
			String content = "http://p3.so.qhimg.com/t010caa6ee6c324f16d.jpg";
			String imgPath = "E:\\Personal\\Desktop\\你喜欢吗.png";
			QrcodeImg(content, imgPath);
	}
}
