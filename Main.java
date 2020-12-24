package employes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		
		con = DbCon.connDB();
		
		int intput;
		int selectedEmpl;
		int selectedType;
		int MselectedType;
		
		int emplyeeId;
		int empDeletId;
		
		boolean Quit = false;
		
		Personnels personnels = new Personnels();
		
		while(Quit != true) {
			System.out.println("1- employes");
			System.out.println("2- salaire des employee");
			System.out.println("3- Collection des employes");
			System.out.println("4- Quit");

			intput = scanner.nextInt();
			switch(intput) {
				case 1:
					System.out.println("1- ajoute un employee");
					System.out.println("2- modifier un employee");
					System.out.println("3- suprimer un employee");

					selectedEmpl = scanner.nextInt();
					switch(selectedEmpl) {
						case 1:
							System.out.println("entrer type de employee");
								System.out.println("1- vendeur");
								System.out.println("2- representeur");
								System.out.println("3- productor");
								System.out.println("4- mentionnaire");
								
									selectedType = scanner.nextInt();
										switch(selectedType) {
											case 1:
												personnels.addEmployee("vendeur");
											break;
											
											case 2:
												personnels.addEmployee("representeur");
											break;
											
											case 3:
												personnels.addEmployee("productor");
											break;
											
											case 4:
												personnels.addEmployee("Manutentionaire");
											break;
										}
						break;
						
						case 2:
							System.out.println("entrer le id de l'employee pour modifier");
							emplyeeId = scanner.nextInt();
							
							System.out.println("entrer type de employee");
							System.out.println("1- vendeur");
							System.out.println("2- representeur");
							System.out.println("3- productor");
							System.out.println("4- mentionnaire");
							
								MselectedType = scanner.nextInt();
									switch(MselectedType) {
										case 1:
											personnels.modifyEmployee(emplyeeId,"vendeur");
										break;
										
										case 2:
											personnels.modifyEmployee(emplyeeId,"representeur");
										break;
										
										case 3:
											personnels.modifyEmployee(emplyeeId,"productor");
										break;
										
										case 4:
											personnels.modifyEmployee(emplyeeId,"Manutentionaire");
										break;
									}
						break;
						
						case 3:
							System.out.println("entrer le id de l'employee pour suprimer");
							empDeletId = scanner.nextInt();
							
							personnels.RemoveEmployee(empDeletId);
						break;
					}
				break;
				
				case 2:
					try {
						stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						String sql = "SELECT id, nom, prenom, salary  FROM `employee` ORDER BY `updated_at` ASC";
						rs = stat.executeQuery(sql);
						
						while(rs.next()) {

							StringBuilder sb = new StringBuilder();
							sb.append("id: " + rs.getInt("id") + "\n");
							sb.append("nom et prenom de employee: " + rs.getString("nom") + " " + rs.getString("prenom") + "\n");
							sb.append("salaire d'employee: " + rs.getDouble("salary") + " MAD\n");
							sb.append("salaire moyenne d'employee: " + rs.getDouble("salary") / 2  + " MAD\n");
							
							System.out.println(sb.toString());
						}
					} catch (SQLException e) {
						System.err.println(e);
					}
				break;
				
				case 3:
					try {
						stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						String sql2 = "SELECT id, nom, prenom, type  FROM `employee` ORDER BY `updated_at` ASC";
						rs = stat.executeQuery(sql2);
						
						while(rs.next()) {

							StringBuilder sb = new StringBuilder();
							sb.append("id: " + rs.getInt("id") + "\n");
							sb.append("nom et prenom de employee: " + rs.getString("nom") + " " + rs.getString("prenom") + "\n");
							sb.append("type d'employee: " + rs.getString("type") + "\n");
							
							System.out.println(sb.toString());
						}
					} catch (SQLException e) {
						System.err.println(e);
					}
				break;
				
				case 4:
					Quit=true;
					System.out.println("god bye");
				break;
			}
		}		
	}
}


