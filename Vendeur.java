package employes;

public class Vendeur extends Employe {
	
	private double ChifreAffaire = 1000;

	Vendeur(String nom, String prenom, int Age, String dateDES) {
		super(nom, prenom, Age, dateDES);
	}
	

	@Override
	public double calculerSalaire() {
		return ChifreAffaire*0.2+1500;
	}
}
