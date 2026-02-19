import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	/**
	 * some weird indexes:
	 * 0 = scenario 1
	 * 2 = scenario 2
	 * 4 = scenario 3
	 * 6 = scenario 4
	 * 
	 * 0 = no treatment; 5 = Sirolimus
	 */
	
	public static void main(String[] args)throws IOException{
		System.out.println("# QN net running!");

		Exec.run60 = false;
		
		//runs a single virtual experiment 
		//Exec.runSingle(Exec.NET_SCENARIO_1, Exec.IN_INFECTED, false);
		
		runValidationUnshuffled(false);
		
//		String baseFileName = "/Users/deassis/Documents/Projects/publications/Achyudhan/data/variance_test_full";
//		File last15 = null;
//		File lastiter1 = null;
//		File lastiter0 = null;
//		
//		int[] idx = null;
//		
//		for(int i = 0; i < 400; i++) {
//			last15 = new File(baseFileName + "_15_" + i + ".csv");
//			lastiter1 = new File(baseFileName + "_last_" + i + ".csv");
//			lastiter0 = new File(baseFileName + "_last_minus1_" + i + ".csv");
//			
//			idx = Exec.convergence(Exec.NET_SCENARIO_2, Exec.IN_INFECTED, 	  true, last15);
//				  Exec.printSingle(Exec.NET_SCENARIO_2, Exec.IN_INFECTED, 30, idx,  lastiter1, lastiter0);
//			
//			if(i%10 == 0)System.out.println(i);
//		}
		
//		Exec.convergence(Exec.NET_SCENARIO_2, Exec.IN_INFECTED, true, null);
//		Exec.printSingle(Exec.NET_SCENARIO_2, Exec.IN_INFECTED, 15, true);
//		for(double c : conv)
//			System.out.println(c);
		
		//runs the validation
		//runValidationUnshuffled(false);
//		runSirolimusReduced();
		//runs the Random Networks (for validation)
//		runValidationShuffled();
		
		//runs the sirolimus experiment.
//		runSirolimus();

		//runs the knock-ins experiments
//		runPerturbation1();
//		runPerturbation2();
//		runPerturbation3();
//		runPerturbation4();
		
		/**
		 * # QN net running!
56, 29, 9, 10, 8
9181
56, 29, 9, 10, 8
9126
56, 28, 9, 10, 9
9034
56, 26, 9, 10, 11
8941
		 */
		
		//runs the systematic edge perturbation analysis
//		runSirolimusWithEdgePerturbation("1");
//		runSirolimusWithEdgePerturbation("2");
//		runSirolimusWithEdgePerturbation("3");
//		runSirolimusWithEdgePerturbation("4");
		
	}

	
	/* Tables 1, 2, and 4*/
	private static void runValidationUnshuffled(boolean map) throws FileNotFoundException {
		String name = "unshuffled_original"; //"rvalidation_fit_ifn";//"t2_experiment_fit_ifn";//"bvalidation_fit_ifn";//"experiments";
		int iterations = 1;
		boolean shuffle = false;
		boolean inVivo = false;
		//Exec.validation(name, iterations, shuffle, inVivo);
		
		Exec.useFit(false);
		Exec.useMap(map);
		//name = "unshuffled_fit";
		Exec.validation(name, iterations, shuffle, inVivo);
	}
	private static void runValidationShuffled(boolean map) throws FileNotFoundException {
		String name = "shuffled_original"; //"rvalidation_fit_ifn";//"t2_experiment_fit_ifn";//"bvalidation_fit_ifn";//"experiments";
		int iterations = 400;
		boolean shuffle = true;
		boolean inVivo = false;
		Exec.validation(name, iterations, shuffle, inVivo);
		
		Exec.useFit(false);
		Exec.useMap(map);
		name = "shuffled_unfit";
		Exec.validation(name, iterations, shuffle, inVivo);
	}
	
	
//	private static void runKO() {
//		
//		System.out.println("scenarios = c(" + Exec.NET_SCENARIO_1 + ", " + Exec.NET_SCENARIO_2  + ", " + 
//				Exec.NET_SCENARIO_3  + ", " +  Exec.NET_SCENARIO_4 + ")");
//		
//		Exec.useFit(true);
//		
//		//virus inhibits IFN + mTORC1 induce viral-replication
//		System.out.println("# Scenario: " + Exec.NET_SCENARIO_1);
//		Exec.runKOAnalysis(Exec.IN_INFECTED,Exec.NET_SCENARIO_1, 0, false);
//		System.out.println("\n\n");
//		
//		//virus inhibits IFN + mTORC1 induce viral-replication + mTORC1 inhibits IFN
//		System.out.println("# Scenario: " + Exec.NET_SCENARIO_2);
//		Exec.runKOAnalysis(Exec.IN_INFECTED,Exec.NET_SCENARIO_2, 0, false);
//		System.out.println("\n\n");
//		
//		//virus inhibits IFN + mTORC1 inhibits IFN
//		System.out.println("# Scenario: " + Exec.NET_SCENARIO_3);
//		Exec.runKOAnalysis(Exec.IN_INFECTED,Exec.NET_SCENARIO_3, 0, false);
//		System.out.println("\n\n");
//		
//		//virus inhibits IFN + mTORC1 induce viral-replication + mTORC1 induce IFN, TNF, and IL6
//		System.out.println("# Scenario: " + Exec.NET_SCENARIO_4);
//		Exec.runKOAnalysis(Exec.IN_INFECTED,Exec.NET_SCENARIO_4, 0, false);
//		
//		
//	}
	
	/* Figures 5 and 11 */
	private static void runPerturbation1() {
		Exec.useFit(false);
		Exec.runPerturbationAll(Exec.NET_SCENARIO_1);
	}
	private static void runPerturbation2() {
		Exec.useFit(false);
		Exec.runPerturbationAll(Exec.NET_SCENARIO_2);
	}
	private static void runPerturbation3() {
		Exec.useFit(false);
		Exec.runPerturbationAll(Exec.NET_SCENARIO_3);
	}
	private static void runPerturbation4() {
		Exec.useFit(false);
		Exec.runPerturbationAll(Exec.NET_SCENARIO_4);
	}
	
	/* Figure 10 */
	private static void runSirolimusFit() {
		Exec.useFit(true);
		Exec.runSirolimus(Exec.IN_INFECTED, 100);
		System.out.println("\n\n");
		Exec.runSirolimus(Exec.IN_INFECTED_SIROLIMUS, 100);
	}
	
	/* Figure 4 */
	private static void runSirolimus() {
		Exec.useFit(false);
		Exec.runSirolimus(Exec.IN_INFECTED, 100);
		System.out.println("\n\n");
		Exec.runSirolimus(Exec.IN_INFECTED_SIROLIMUS, 100);
	}

	
	private static void runSirolimusMap() {
		Exec.useMap(true);
		Exec.runSirolimus(Exec.IN_INFECTED, 100);
		System.out.println("\n\n");
		Exec.runSirolimus(Exec.IN_INFECTED_SIROLIMUS, 100);
	}
	
	private static void runSirolimusReduced() {
		Exec.useReduced();
		Exec.runSirolimus(Exec.IN_INFECTED, 100);
		System.out.println("\n\n");
		Exec.runSirolimus(Exec.IN_INFECTED_SIROLIMUS, 100);
	}
	
//	private static void runSirolimusFitKO() {
//		/*Exec.useFit(true);
//		Exec.runSirolimusKO(Exec.IN_INFECTED, 100);
//		System.out.println("\n\n");
//		Exec.runSirolimusKO(Exec.IN_INFECTED_SIROLIMUS, 100);*/
//	}
//	
//	private static void runSirolimus3PrintAll() {
//		Exec.useFit(false);
//		Exec.runSirolimus3PrintAll(Exec.IN_INFECTED, 100);
//	}
	
	/* Table 3 */
	private static void runSirolimusWithEdgePerturbation(String arg) {
		int k = Integer.parseInt(arg);
		if(k == 1)
			Exec.runSirolimusWithEdgePerturbations(Exec.NET_SCENARIO_1);
		else if(k == 2)
			Exec.runSirolimusWithEdgePerturbations(Exec.NET_SCENARIO_2);
		else if(k == 3)
			Exec.runSirolimusWithEdgePerturbations(Exec.NET_SCENARIO_3);
		else if(k == 4)
			Exec.runSirolimusWithEdgePerturbations(Exec.NET_SCENARIO_4);
	}
}
