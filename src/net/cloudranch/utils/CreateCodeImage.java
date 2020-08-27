package net.cloudranch.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class CreateCodeImage {
	private static int WIDTH = 90;
	private static int HEIGHT = 40;
	/**
	 * 创建二验证码图片
	 * @throws IOException
	 */
	public static Map<String,String> createCodeImage() throws IOException {
		Map<String,String> map = new HashMap<String,String>();
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		char[] code = getCode();
		String str = "";
		for(int i=0;i<4;i++) {
			str += code[i];
		}
		map.put("code", str);
		drawBackgroung(g);
		drawCode(g,code);
		g.dispose();
		String path = BasicUtils.getStoragePath("codeImage");
		String fileName = "code" + new Date().getTime() + ".png";
		map.put("fileName", fileName);
		File file = new File(path+"\\"+fileName);
		if(!file.exists()) {
			file.mkdirs();
		}
		ImageIO.write(image, "png", file);
		return map;
	}
	public static void main(String[] args) throws IOException {
		createCodeImage();
	}
	/**
	 * 获取验证码
	 * @return
	 */
	public static char[] getCode() {
		String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
		char[] code = new char[4];
		for(int i=0;i<4;i++) {
			int rand = (int) (Math.random() * 36);
			code[i] = chars.charAt(rand);
		}
		return code;
	}
	/**
	 * 画验证码
	 * @param g
	 * @param code
	 */
	public static void drawCode(Graphics g,char[] code) {
		g.setColor(Color.BLACK);
		g.setFont(new Font(null,Font.ITALIC|Font.BOLD,18));
		//开始画验证码
		g.drawString(""+code[0], 5, HEIGHT*2/3);
		g.drawString(""+code[1], 5+WIDTH/4, HEIGHT*2/3);
		g.drawString(""+code[2], 5+WIDTH/2, HEIGHT*2/3);
		g.drawString(""+code[3], 5+WIDTH*3/4, HEIGHT*2/3);
	}
	/**
	 * 画背景
	 * @param g
	 */
	private static void drawBackgroung(Graphics g) {
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for(int i=0;i<120;i++) {
			int x = (int) (Math.random() * WIDTH);
			int y = (int) (Math.random() * HEIGHT);
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			g.setColor(new Color(red,green,blue));
			g.drawOval(x, y, 1, 0);
		}
	}
}
