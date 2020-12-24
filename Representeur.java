package employes;

public class Representeur extends Employe {
	
	private double ChifreAffaire = 2000;

	Representeur(String nom, String prenom, int Age, String dateDES) {
		super(nom, prenom, Age, dateDES);
	}

	@Override
	public double calculerSalaire() {
		return ChifreAffaire*0.2+2500;
	}
}
