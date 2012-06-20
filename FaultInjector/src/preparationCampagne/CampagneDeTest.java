package preparationCampagne;

import java.util.Vector;

public class CampagneDeTest {
	private String name;
	private String xmlPath;
	private Vector<CasDeTest> tests;
	
	public CampagneDeTest(String n, String x){
		name = n;
		xmlPath = x;
	}
	
	public void ajouterCasDeTest(CasDeTest cdt){
		tests.add(cdt);
	}
}
