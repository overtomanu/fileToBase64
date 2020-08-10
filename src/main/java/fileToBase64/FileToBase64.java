package fileToBase64;

//Run following command in terminal for large files
//java -cp fileToBase64-0.0.1-SNAPSHOT.jar fileToBase64.FileToBase64 "C:\Users\overtomanu\Downloads\010820_50K.zip" > %TEMP%/base64out

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.Base64;

public class FileToBase64 {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please supply file path");
			return;
		}
		final String zipFilePath = args[0];
		String encodedString = encodeBase64(zipFilePath);
		System.out.println("Encoding file:"+zipFilePath);
		System.out.println("Encoded String lenght:"+encodedString.length());
		System.out.println("Encoded String:"+encodedString);
	}

	public static String encodeBase64(String filePath) {

		String encodedBase64 = null;
		byte bytes[] = null;
		try (FileInputStream fis = new FileInputStream(new File(filePath))) {
			try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
				byte[] buffer = new byte[1024];
				int read = -1;
				while ((read = fis.read(buffer)) != -1) {
					baos.write(buffer, 0, read);
				}
				bytes = baos.toByteArray();
			} catch (IOException exp) {
				exp.printStackTrace();
			}
			encodedBase64 = new String(Base64.getEncoder().encodeToString(bytes));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return encodedBase64;
	}

}
