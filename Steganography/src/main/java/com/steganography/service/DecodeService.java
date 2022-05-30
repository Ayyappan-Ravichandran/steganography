package com.steganography.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DecodeService {

	static final String DECODEDMESSAGEFILE = "C:\\Users\\AYYAPPAN\\Steganography\\result.txt";
	public static String b_msg = "";
	public static int len = 0;

	public boolean SteganoDecode(MultipartFile image, String key) throws Exception {

		System.out.println("image : " + image);
		System.out.println("key : " + key);
		File convFile = new File(image.getOriginalFilename());
		String imageFilePath = convFile.getAbsolutePath();
		System.out.println("imageAbsoluteFileName : " + convFile.getAbsolutePath());

		final String STEGIMAGEFILE = imageFilePath;
		
		System.out.println("imageStegFileName : " + STEGIMAGEFILE);

		BufferedImage yImage = readImageFile(STEGIMAGEFILE);

		DecodeTheMessage(yImage);
		String msg = "";
		// System.out.println("len is "+len*8);
		for (int i = 0; i < len * 8; i = i + 8) {

			String sub = b_msg.substring(i, i + 8);

			int m = Integer.parseInt(sub, 2);
			char ch = (char) m;
			// System.out.println("m "+m+" c "+ch);
			msg += ch;
		}
		System.out.println("\n\nSuccessfully Decoded...");

		System.out.println("\n\nDecoded Message: \n" + msg);

		System.out.println("\n\nResult in File: " + DECODEDMESSAGEFILE);

		PrintWriter out = new PrintWriter(new FileWriter(DECODEDMESSAGEFILE, false), true);
		out.write(msg);
		out.close();

		return true;
	}

	public static BufferedImage readImageFile(String STEGIMAGEFILE) {
		
		
		BufferedImage theImage = null;
		System.out.println(STEGIMAGEFILE);
		File p = new File(STEGIMAGEFILE);
		try {
			theImage = ImageIO.read(p);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return theImage;
	}

	public static void DecodeTheMessage(BufferedImage yImage) throws Exception {

		// int j = 0;
		int currentBitEntry = 0;
		String bx_msg = "";
		for (int x = 0; x < yImage.getWidth(); x++) {
			for (int y = 0; y < yImage.getHeight(); y++) {
				if (x == 0 && y < 8) {
					// System.out.println("enc "+yImage.getRGB(x, y)+" dec "+yImage.getRGB(x, y)+"
					// "+b_msg);
					int currentPixel = yImage.getRGB(x, y);
					int red = currentPixel >> 16;
					red = red & 255;
					int green = currentPixel >> 8;
					green = green & 255;
					int blue = currentPixel;
					blue = blue & 255;
					String x_s = Integer.toBinaryString(blue);
					bx_msg += x_s.charAt(x_s.length() - 1);
					len = Integer.parseInt(bx_msg, 2);

				} else if (currentBitEntry < len * 8) {
					// System.out.println("enc "+yImage.getRGB(x, y)+" dec "+yImage.getRGB(x, y)+"
					// "+b_msg);
					int currentPixel = yImage.getRGB(x, y);
					int red = currentPixel >> 16;
					red = red & 255;
					int green = currentPixel >> 8;
					green = green & 255;
					int blue = currentPixel;
					blue = blue & 255;
					String x_s = Integer.toBinaryString(blue);
					b_msg += x_s.charAt(x_s.length() - 1);

					currentBitEntry++;
					// System.out.println("curre "+currentBitEntry);
				}
			}
		}
		// System.out.println("bin value of msg hided in img is "+b_msg);
	}

}
