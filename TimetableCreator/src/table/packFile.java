package table;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.Deflater;

public class packFile {
	public static void pack(String[] filesToJar, String jarFileName, byte[] buffer)
	{
		try
		{
			JarOutputStream jos = new JarOutputStream(new FileOutputStream(jarFileName));
			// метод сжатия
			jos.setLevel(Deflater.BEST_COMPRESSION);
			for (int i = 0; i < filesToJar.length; i++)
			{
				System.out.println(i);
				jos.putNextEntry(new JarEntry(filesToJar[i]));
				FileInputStream in = new FileInputStream(filesToJar[i]);
				int len;	
				while ((len = in.read(buffer)) > 0)	
				jos.write(buffer, 0, len);
				jos.closeEntry();
				in.close();
			}
		jos.close();
		} catch (IllegalArgumentException e) 
		{
			e.printStackTrace();
			System.err.println("Некорректный аргумент");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Файл не найден");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Ошибка доступа");
		}
	}
}
