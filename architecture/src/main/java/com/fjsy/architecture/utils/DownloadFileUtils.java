package com.fjsy.architecture.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.math.BigDecimal;

public class DownloadFileUtils {

	public static String geFileFromAssets(Context context, String fileName) {
		if (context == null || TextUtils.isEmpty(fileName)) {
			return null;
		}

		StringBuilder s = new StringBuilder("");
		try {
			InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
			BufferedReader br = new BufferedReader(in);
			String line;
			while ((line = br.readLine()) != null) {
				s.append(line);
			}
			return s.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String geFileFromRaw(Context context, int resId) {
		if (context == null) {
			return null;
		}

		StringBuilder s = new StringBuilder();
		try {
			InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
			BufferedReader br = new BufferedReader(in);
			String line;
			while ((line = br.readLine()) != null) {
				s.append(line);
			}
			return s.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	//删除文件夹和文件夹里面的文件
	public static void deleteDir(final String pPath) {
		File dir = new File(pPath);
		deleteDirWihtFile(dir);
	}

	public static void deleteDirWihtFile(File dir) {
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;
		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete(); // 删除所有文件
			else if (file.isDirectory())
				deleteDirWihtFile(file); // 递规的方式删除文件夹
		}
		dir.delete();// 删除目录本身
	}


	/**
	 * 格式化单位
	 *
	 * @param size
	 * @return
	 */
	public static String getFormatSize(double size) {
		double kiloByte = size / 1024;
		if (kiloByte < 1) {
			return size + "B";
		}

		double megaByte = kiloByte / 1024;
		if (megaByte < 1) {
			BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
			return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "KB";
		}

		double gigaByte = megaByte / 1024;
		if (gigaByte < 1) {
			BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
			return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "MB";
		}

		double teraBytes = gigaByte / 1024;
		if (teraBytes < 1) {
			BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
			return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "GB";
		}
		BigDecimal result4 = new BigDecimal(teraBytes);
		return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
				+ "TB";
	}

	/**
	 * 将文件写入本地
	 *
	 * @param destFileDir  目标文件夹
	 * @param destFileName 目标文件名
	 * @return 写入完成的文件
	 * @throws IOException IO异常
	 */
	public static File saveFile(InputStream is, String destFileDir, String destFileName) throws IOException {
		byte[] buf = new byte[2048];
		int len;
		FileOutputStream fos = null;
		try {
			File dir = new File(destFileDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File(dir, destFileName);
			if (file.exists()) {
				file.delete();
			}
			fos = new FileOutputStream(file);
			while ((len = is.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
			fos.flush();
			return file;

		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param is
	 * @param start        起始位置
	 * @param destFileDir
	 * @param destFileName
	 * @return
	 */
	public static File saveFile(InputStream is, long start, String destFileDir, String destFileName) {
		RandomAccessFile raf = null;
		File file = null;
		int len;
		try {
			file = new File(destFileDir, destFileName);

			raf = new RandomAccessFile(file, "rw");
			byte[] fileReader = new byte[4096];

			raf.seek(start);

			while ((len = is.read(fileReader)) != -1) {
				raf.write(fileReader, 0, len);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return file;

	}

}
