package App;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class teste {
	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data1 = LocalDate.parse("10/09/2019",formatter);
		LocalDate data2 = LocalDate.parse("21/10/2019", formatter);
		LocalDate data3 = LocalDate.parse("09/09/2019",formatter);
		if (data1.compareTo(data3) >= 0 && data1.compareTo(data2) <= 0){
			System.out.println("entrou");
		}
		System.out.println(data1.compareTo(data2));
		System.out.println(data1);
		System.out.println(data2);
	}
	
}
