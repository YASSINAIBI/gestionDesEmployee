package employes;

public class Mentionnaire extends Employe {

	Mentionnaire(String nom, String prenom, int Age, String dateDES) {
		super(nom, prenom, Age, dateDES);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculerSalaire() {
		return 0;
	}
}
