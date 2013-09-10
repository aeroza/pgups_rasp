package ru.pgups.develop.timetableapp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class unPackZip {
	private File destFile;
	// ������ ������ ��� ����������
	public final int BUFFER = 2048;

	public void unpack(String destinationDirectory, String nameZip) {
		File sourceZipFile = new File(nameZip);
		try {
			File unzipDestinationDirectory = new File(destinationDirectory);
			// �������� zip-������ ��� ������
			ZipFile jFile = new ZipFile(sourceZipFile);
			Enumeration zipFileEntries = jFile.entries();
			while (zipFileEntries.hasMoreElements()) {
				// ���������� ������� ������ �� ������
				ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
				String entryname = entry.getName();
				// entryname = entryname.substring(2);
				System.out.println("Extracting: " + entry);
				destFile = new File(unzipDestinationDirectory, entryname);
				// ����������� ��������
				File destinationParent = destFile.getParentFile();
				// �������� ��������� ���������
				destinationParent.mkdirs();
				// �������������� ������, ���� ��� �� �������
				if (!entry.isDirectory()) {
					writeFile(jFile, entry);
				}
			}
			jFile.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void writeFile(ZipFile jFile, ZipEntry entry) throws IOException {
		BufferedInputStream is = new BufferedInputStream(
				jFile.getInputStream(entry));
		int currentByte;
		byte data[] = new byte[BUFFER];
		// ������ ����� �� ����
		BufferedOutputStream dest = new BufferedOutputStream(
				new FileOutputStream(destFile), BUFFER);
		while ((currentByte = is.read(data, 0, BUFFER)) > 0) {
			dest.write(data, 0, currentByte);
		}
		dest.flush();
		dest.close();
		is.close();
	}
}