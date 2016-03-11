package com.study.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QrcodeUtil {

	
	/**
	 * ���ɶ�ά��ͼ��
	 * @param content ��Ҫ���ɶ�ά������ֻ����������ݣ�"http://p3.so.qhimg.com/t010caa6ee6c324f16d.jpg"��
	 * @param ImgPath ���ɶ�ά��ͼ�񱣴�ĵ�ַ��"E:\\Personal\\Desktop\\��ϲ����.png"��
	 * @return void �޷���ֵ
	 */
	public static void QrcodeImg(String content,String ImgPath){
		
		int width = 140;
		int height = 140;
		try{
		//ʵ����Qrcode
		Qrcode qrcode = new Qrcode();
		//�����Ŵ���  L(7%) M(15%) Q(25%) H(30%)
		qrcode.setQrcodeErrorCorrect('M');
		qrcode.setQrcodeEncodeMode('B');
		
		//���ö�ά��ߴ�    ȡֵ��Χ��1-40��
		qrcode.setQrcodeVersion(7);
		
		//����ͼƬ�ߴ�
		BufferedImage bufImg = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB); 
		
		//���ƶ�ά��ͼƬ
		 Graphics2D gs = bufImg.createGraphics(); 
		
		 //���ö�ά��ı�����ɫ
		 gs.setBackground(Color.WHITE); 
		
		 //������ά��ľ�������
		 gs.clearRect(0, 0, width, height); 
		
		 //���ö�ά�����ɫ
		 gs.setColor(Color.BLACK); 
		 
		 //��ȡ���ݵ��ֽ����飬���ñ��뼯
		byte[] contentBytes = content.getBytes("utf-8"); 

		//����ƫ���� �����ÿ��ܵ��½�������
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

            System.out.println("����������ݿ��ܳ����������ֵ��"); 

        } 

        // ���������ɶ�ά��ͼƬ����ʱ���ڻ����У������Ǳ��浽����

        //���ɶ�ά��ͼƬ
        File imgFile = new File(ImgPath); 
        ImageIO.write(bufImg,"png", imgFile); 
        System.out.println("��ϲ�㣬��ά�����ɳɹ���");
    
		} catch (Exception e) { 
        e.printStackTrace(); 
    } 
} 
	//Java ���
	public static void main(String[] args) {
			System.err.println("��Һ�");
			String content = "http://p3.so.qhimg.com/t010caa6ee6c324f16d.jpg";
			String imgPath = "E:\\Personal\\Desktop\\��ϲ����.png";
			QrcodeImg(content, imgPath);
	}
}
