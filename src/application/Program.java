package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Product;

public class Program {

	public static void main(String[] args) {

		File file = new File("c:\\temp\\source.csv");
		List<Product> list = new ArrayList<>();
		String path = file.getParent();
		new File(path + "\\out").mkdir();

		System.out.println("Folder creation: success!");
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line = br.readLine();
			while (line != null) {
				String[] vect = line.split(";");
				list.add(new Product(vect[0], Double.parseDouble(vect[1]), Integer.parseInt(vect[2])));
				line = br.readLine();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + "\\out\\summary.csv"))) {

			bw.write("NAME;PRICE;QUANTITY;TOTAL");
			bw.newLine();

			for (Product p : list) {
				bw.write(p.toString());
				bw.newLine();
			}
			
			System.out.println("Exportation successful!");
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
