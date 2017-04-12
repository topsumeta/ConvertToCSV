import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	private static String Inbound = "";
	private static String Outbound = "";
	private static Path currentRelativePath = Paths.get("");
	private static List<String> list = new ArrayList<String>();

	public static void main(String[] args) {
		String path = currentRelativePath.toAbsolutePath().toString();
		//System.out.println(path);
		Inbound = path + "/Inbound";
		File csvDir = new File(Inbound);
		if (!csvDir.exists()) {
			csvDir.mkdirs();
		}

		File folder = new File(csvDir.toString());
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.isFile()) {
				String Filename = file.getName();
				System.out.println(Filename);
				readfile(Filename);
				writeCSV(Filename);
			}
		}
	}
	


	private static void readfile(String Filename) {

		BufferedReader br = null;
		FileReader fr = null;

		try {
			String fileDir = Inbound + "\\" + Filename;
			fr = new FileReader(fileDir);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(fileDir));
			list.clear();
			while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
				list.add(sCurrentLine);

			}

			//System.out.println(list);

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	private static void writeCSV(String Filename) {
		String csvFile = Filename;
		String path = currentRelativePath.toAbsolutePath().toString();
		//System.out.println(path);
		Outbound = path + "/Outbound";
		File csvDir = new File(Outbound);
		if (!csvDir.exists()) {
			csvDir.mkdirs();
		}

		try {
			String csvFiles = csvDir.toString() + "/" + csvFile + ".csv";
			//System.out.println(csvFiles);
			FileWriter writer = new FileWriter(csvFiles);
			int row = 0;
			for (int i = 0; i < list.size(); i++) {
				String line = list.get(i);
				String no = Integer.toString(i + 1);

				String[] parts = line.split("=");
				String part1 = parts[0];
				if (parts.length == 1) {
					CSVUtils.writeLine(writer, Arrays.asList(no, part1));
				} else if (parts.length == 2) {
					String part2 = parts[1];
					row++;
					String r = Integer.toString(row);
					CSVUtils.writeLine(writer, Arrays.asList(no, part1, part2, r));
				} else if (parts.length == 3) {
					String part2 = parts[1];
					String part3 = parts[2];
					System.out.println(part3);
					row++;
					String r = Integer.toString(row);
					CSVUtils.writeLine(writer, Arrays.asList(no, part1, part2 + part3, r));
				} else if (parts.length == 4) {
					String part2 = parts[1];
					String part3 = parts[2];
					String part4 = parts[3];
					System.out.println(part4);
					row++;
					String r = Integer.toString(row);
					CSVUtils.writeLine(writer, Arrays.asList(no, part1, part2 + part3 + part4, r));
				}

			}

			writer.flush();
			writer.close();
			System.out.println("Export CSV Success!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
