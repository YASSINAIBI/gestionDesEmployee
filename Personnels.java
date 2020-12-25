package employes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Personnels extends Employe implements ProductionRisquesInterface, ManutentionRisquesInterface {
	Scanner scanner = new Scanner(System.in);
	Connection con = null;
	
	private String type;
	private String Mtype;
	
	private double vendeurChifreAffaire = 10000;
	private double representeurChifreAffaire = 20000;
	private int unitesProduitesMensuellemen = 10;
	private int nombreHeureTravailler = 100;
	
	private int employeeId;
	private int empDeletId;

	Personnels(String nom, String prenom, int Age, String dateDES) {
		super(nom, prenom, Age, dateDES);
		// TODO Auto-generated constructor stub
	}

	Personnels() {
		
	}
	public double calculerSalaire() {
		if(this.type == "vendeur" || this.Mtype == "vendeur")
			return this.vendeurChifreAffaire*0.2 + 1500; //3500
		if(this.type == "representeur" || this.Mtype == "representeur")
			return this.representeurChifreAffaire*0.2 + 2500; //6500
		if(this.type == "productor" || this.Mtype == "productor")
			return this.unitesProduitesMensuellemen*5 + ProductionRisquesInterface.salaireFixeDeProducteur; //250
		if(this.type == "Manutentionaire" || this.Mtype == "Manutentionaire")
			return this.nombreHeureTravailler*50 + ManutentionRisquesInterface.salaireFixeDeManutionaire; //5200
		return 0;
	}

	public void addEmployee(String type) throws SQLException {
		
		//System.out.println(ManutentionRisquesInterface.salaireFixeDeManutionaire);
		//System.out.println(ProductionRisquesInterface.salaireFixeDeProducteur);
		
		this.type = type;
		
		Statement stat = null;
		ResultSet rs = null;
		
		String EmpFirstName;
		String EmpLastName;
		int EmpAge;
		int DateDES;
		
		con = DbCon.connDB();
		try {
			String sql = "INSERT INTO `employee`(`nom`, `prenom`, `age`, `dataDES`, `type`, `salary`) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			
			System.out.println("");
			
			System.out.println("entrer le nom de employee");
			EmpFirstName = scanner.next();
			
			System.out.println("entrer le prenom de employee");
			EmpLastName = scanner.next();
			
			System.out.println("entrer age de employee");
			EmpAge = scanner.nextInt();
			
			System.out.println("entrer DateDES de employee");
			DateDES = scanner.nextInt();

			ps.setString(1, EmpFirstName);
			ps.setString(2, EmpLastName);
			ps.setInt(3, EmpAge);
			ps.setInt(4, DateDES);
			ps.setString(5, this.type);
			ps.setDouble(6, calculerSalaire());
			ps.executeUpdate();
			
			System.out.println("the data inserted sucssesfuly !!!!!!");
			
		} catch (SQLException e) {
			System.out.println("this name is already existe");
		}
	}
	
	public void modifyEmployee(int employeeId, String Mtype) throws SQLException {
		this.employeeId = employeeId;
		this.Mtype = Mtype;
		
		String UEmpFirstName;
		String UEmpLastName;
		int UEmpAge;
		int UDateDES;
		
		con = DbCon.connDB();

		try {
			String sql = "UPDATE `employee` SET `nom`=?, `prenom`=?, `age`=?, `dataDES`=?, `type`=?, `salary`=? WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			System.out.println("entrer le nom de employee");
			UEmpFirstName = scanner.next();
			
			System.out.println("entrer le prenom de employee");
			UEmpLastName = scanner.next();
			
			System.out.println("entrer age de employee");
			UEmpAge = scanner.nextInt();
			
			System.out.println("entrer DateDES de employee");
			UDateDES = scanner.nextInt();

			ps.setString(1, UEmpFirstName);
			ps.setString(2, UEmpLastName);
			ps.setInt(3, UEmpAge);
			ps.setInt(4, UDateDES);
			ps.setString(5, this.Mtype);
			ps.setDouble(6, calculerSalaire());
			ps.setDouble(7, this.employeeId);
			ps.executeUpdate();

			System.out.println("the data updated sucssesfuly !!!!!!");
			
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	public void RemoveEmployee(int empDeletId) throws SQLException {
		this.empDeletId = empDeletId;
		
		con = DbCon.connDB();

		try {
			String sql = "DELETE FROM `employee` WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, this.empDeletId);
			ps.executeUpdate();
			
			System.out.println("the data deleted sucssesfuly !!!!!!");
			
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
}
