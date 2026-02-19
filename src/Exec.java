import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Exec {
	
	public static final int SIROLIMUS = 1;
	public static final int PP242 = 2;
	public static final int WORTMANNIN = 3;
	public static final int MK2206 = 4;
	public static final int PX478 = 5;
	public static final int METFORMIN = 6;
	
	public static final int NET_SCENARIO_1 = 0;
	public static final int NET_SCENARIO_2 = 2;
	public static final int NET_SCENARIO_3 = 4;
	public static final int NET_SCENARIO_4 = 6;
	
	
	public static final double EPS = 1e-5;
	private static boolean qn2 = false;
	
	
	//TYPES
	/*public static final int NET_BASE = 0;
	public static final int NET_ANG = 1;
	public static final int NET_VIRAL_INH = 2;
	public static final int NET_VIRAL_INH_ANG = 3;
	public static final int NET_MTORC_ACT = 4;
	public static final int NET_MTORC_ACT_ANG = 5;
	public static final int NET_MTORC_ACT_VIRAL_INH = 6;
	public static final int NET_MTORC_ACT_VIRAL_INH_ANG = 7;
	
	public static final int NET_MTORC_INH = 8;
	public static final int NET_MTORC_INH_ANG = 9;
	public static final int NET_MTORC_INH_VIRAL_INH = 10;
	public static final int NET_MTORC_INH_VIRAL_INH_ANG = 11;
	
	
	public static final int NET_MTORC_ = 8;
	public static final int NET_MTORC_ANG_ = 9;
	public static final int NET_MTORC_VIRAL_INH_ = 10;
	public static final int NET_MTORC_VIRAL_INH_ANG_ = 11;*/
	
	private static Random seedGenerator = new Random(42739740234L);
	
	
	//INPUTS
	public static final int IN_INFECTED = 0;
	public static final int IN_HYPOXIA = 1;
	public static final int IN_STARVATION = 2;
	public static final int IN_TNF = 3;
	public static final int IN_ANG_II = 4;
	public static final int IN_INFECTED_SIROLIMUS = 5;
	public static final int IN_INFECTED_HYPOXIA = 6;
	public static final int IN_INFECTED_STARVATION = 7;
	public static final int IN_LPS = 8;
	public static final int IN_IFN = 9;
	public static final int IN_SIROLIMUS = 10;
	public static final int IN_INFECTED_METFORMIN = 11;
	public static final int IN_PP242 = 12;
	public static final int IN_INFECTED_PP242 = 13;
	public static final int IN_INFECTED_WORTMANNIN= 14;
	public static final int IN_INFECTED_MK2206 = 15;
	public static final int IN_INFECTED_PX478 = 16;
	
	public static final int HIF_KO = 1;
	public static final int NO_VIR_REP = 2;
	
	private static final int SAMPLE_SIZE = 30;//CHANGED HERE!!!
	private static final int ENS_SIZE = 500;
	
	public static boolean run60 = false;
	
	
	private static String path = "/Users/deassis/Documents/Projects/publications/Achyudhan/data/";
	private static double sigma = 1.96;//CHANGED HERE!!!
	private static int bnIterations = QN.N_ITER;
	private static int sampleSize = SAMPLE_SIZE;
	private static int ensembleSize = ENS_SIZE;
	
	private static boolean fit = false;
	private static boolean map = false;
	private static boolean reduced = false;
	private static int bnSize = QN.QN_SIZE;
	
	
	public static void useFit(boolean fit) {
		Exec.map = false;
		Exec.reduced = false;
		Exec.bnSize = QN.QN_SIZE;
		Exec.fit = fit;
	}
	
	public static void useMap(boolean map) {
		Exec.fit = false;
		Exec.qn2 = false;
		Exec.reduced = false;
		Exec.bnSize = QN.QN_SIZE;
		Exec.map = map;
	}
	
	public static void useReduced() {
		Exec.fit = false;
		Exec.qn2 = false;
		Exec.map = false;
		Exec.bnSize = Reduced.QN_SIZE;
		Exec.reduced = true;
	}
	
	public static void setPath(String path) {
		Exec.path = path;
	}
	
	public static void setSigma(double sigma) {
		Exec.sigma = sigma;
	}
	
	public static void setBnIterations(int bnIterations) {
		Exec.bnIterations = bnIterations;
	}
	
	public static void setSampleSize(int sampleSize) {
		Exec.sampleSize = sampleSize;
	}
	
	public static void setEnsembleSize(int ensembleSize) {
		Exec.ensembleSize = ensembleSize;
	}
	
	public static QN networkFactory(int type) {
		return networkFactory(type, 0);
	}
	
	public static QN networkFactory(int type, int ko) {
		if(type > 7 && type%2 == 0)
			System.err.println("Unknown network " + type  + " BASE created!");
		if(type > 7 && type%2 != 0)
			System.err.println("Unknown network " + type  + " BASE_ANG created!");
		
		Equation ang = new Zero();
		Equation hif = ko == HIF_KO ? new Zero() : new HIF();
		Equation ifn = map ? new BaseIFNMap() : new BaseIFN();
		Equation il6 = new BaseIL6();
		Equation isg = new BaseISG();
		Equation tnf = new BaseTNF();
		Equation vir = new BaseViralRep();
		Equation p3k = new IFNPI3K();
		
		if(type%2 != 0) {
			ang = new TR1();
			type--;
		}
		
		
		Equation rvir = null;
		Equation rifn = null;
		
		if(type == NET_SCENARIO_1) { //mTORC1 -> vir-rep
			if(map) {
				ifn = new ViralInhIFNMap();
			}else {
				ifn = new ViralInhIFN();
			}
			isg = new ViralInhISG();
			vir = new ViralRepmTORC();
			
			
			rifn = new ReducedViralInhIFN();
			rvir = new ReducedViralRepmTORC();
			
		} else if(type == NET_SCENARIO_3) { //mTORC1 -| IFN
			//ifn = new ViralInhIFN();
			if(map) {
				isg = new ViralInhISG();
				ifn = new ViralAndMTORCInhIFNMap();
			}else {
				isg = new ViralInhISG();
				ifn = new ViralAndMTORCInhIFN();
			}
			
			
			rifn = new ReducedViralAndMTORCInhIFN();
			rvir = new ReducedBaseViralRep();
			
			//p3k = new IFNPI3K();
		} else if(type == NET_SCENARIO_2) {//mTORC1 -| IFN & mTORC1 -> vir-rep
			if(map) {
				ifn = new ViralAndMTORCInhIFNMap();
				isg = new ViralInhISG();
				vir = new ViralRepmTORC();
			}else {
				ifn = new ViralAndMTORCInhIFN();
				isg = new ViralInhISG();
				vir = new ViralRepmTORC();
			}
			
			
			rifn = new ReducedViralAndMTORCInhIFN();
			rvir = new ReducedViralRepmTORC();
			
			//p3k = new IFNPI3K();
		} else if(type == NET_SCENARIO_4) { //None
			if(map) {
				ifn = new ViralInhIFNMap();
				isg = new ViralInhISG();
			}else {
				ifn = new ViralInhIFN();
				isg = new ViralInhISG();
			}
			
			
			rifn = new ReducedViralInhIFN();
			rvir = new ReducedBaseViralRep();
			//ifn = new ViralInhMTORCActIFN();
			/*ifn = new ViralInhMTORCActIFN();
			isg = new ViralInhISG();
			vir = new ViralRepmTORC();
			il6 = new MTORCIL6();
			tnf = new MTORCTNF();*/
			//p3k = new IFNPI3K();
		}
		
		vir = ko == NO_VIR_REP ? new Zero() : vir;
		QN qn = null;
		if(reduced) {
			return new Reduced(new Equation[] {rifn, rvir});
		}
		if(map)qn = new QN4(new Equation[] {ang, hif, ifn, il6, isg, tnf, vir, p3k});
		else if(qn2)qn = new QN2(new Equation[] {ang, hif, ifn, il6, isg, tnf, vir, p3k});
		else qn = new QN(new Equation[] {ang, hif, ifn, il6, isg, tnf, vir, p3k});
		if(!map)qn.setFit(fit);
		return qn;
	}
	
	public static int[] inputFactory(int type) {
		return Exec.inputFactory(null, type);
	}
	
	public static int[] inputFactory(int[] input, int type) {
		if(input == null)
			input = new int[bnSize];
		
		if(bnSize == Reduced.QN_SIZE) {
			
			if(type == IN_INFECTED) 
				input[Indexes.VIRUS] = 1;
			else if(type == IN_INFECTED_SIROLIMUS) {
				input[Indexes.VIRUS] = 1;
				input[Indexes.SIROLIMUS] = 1;
			}else if(type == IN_SIROLIMUS) {
				input[Indexes.SIROLIMUS] = 1;
				input[Indexes.VIRUS] = 0;
			}
			
			return input;
		}
		
		if(type == IN_INFECTED) 
			input[Indexes.Virus] = 1;
		else if(type == IN_HYPOXIA)
			input[Indexes.Hypoxia] = 1;
		else if(type == IN_STARVATION)
			input[Indexes.Nutr_Depr] = 1;
		else if(type == IN_TNF)
			input[Indexes.TNF_e] = 1;
		else if(type == IN_ANG_II)
			input[Indexes.ANG_2_e] = 1;
		else if(type == IN_INFECTED_SIROLIMUS) {
			input[Indexes.Virus] = 1;
			input[Indexes.RAPAMICINA] = 1;
		} else if(type == IN_INFECTED_HYPOXIA) {
			input[Indexes.Virus] = 1;
			input[Indexes.Hypoxia] = 1;
		} else if(type == IN_INFECTED_STARVATION) {
			input[Indexes.Virus] = 1;
			input[Indexes.Nutr_Depr] = 1;
		} else if(type == IN_LPS) 
			input[Indexes.TLR_LIG] = 1;
		else if(type == IN_IFN) 
			input[Indexes.IFN_e] = 1;
		else if(type == IN_SIROLIMUS) {
			input[Indexes.RAPAMICINA] = 1;
			input[Indexes.Virus] = 0;
		}else if(type == IN_INFECTED_METFORMIN) {
			input[Indexes.METFORMIN_e] = 1;
			input[Indexes.Virus] = 1;
		}else if(type == IN_PP242) {
			input[Indexes.PP242_e] = 1;
			input[Indexes.Virus] = 0;
		}else if(type == IN_INFECTED_PP242) {
			input[Indexes.PP242_e] = 1;
			input[Indexes.Virus] = 1;
		}else if(type == IN_INFECTED_WORTMANNIN) {
			input[Indexes.Wortmannin] = 1;
			input[Indexes.Virus] = 1;
		}else if(type == IN_INFECTED_MK2206) {
			input[Indexes.MK2206] = 1;
			input[Indexes.Virus] = 1;
		}else if(type == IN_INFECTED_PX478) {
			input[Indexes.MK2206] = 1;
			input[Indexes.Virus] = 1;
		}
		
		return input;
  	}
	
	public static int[] clearInputs(int[] input) {
		input[Indexes.Virus] = 0;
		input[Indexes.Hypoxia] = 0;
		input[Indexes.Nutr_Depr] = 0;
		input[Indexes.TNF_e] = 0;
		input[Indexes.ANG_2_e] = 0;
		input[Indexes.RAPAMICINA] = 0;
		input[Indexes.TLR_LIG] = 0;
		input[Indexes.IFN_e] = 0;
		return input;
	}
	
	private static List<double[][]> run(QN qn, int inputType, int k, boolean shuffle) {
		//System.out.println(qn);
		
		List<double[][]> result = new ArrayList<>(); //mat1 == all the last-lines; mat2 == all the last but one line
		List<double[]> list = null;
		
		double[][] mat1 = new double[k][];
		double[][] mat2 = new double[k][];
		
		int[] idx = Indexes.getIndex(fit);
		if(shuffle) qn.shuffle(idx);
		for(int i = 0; i < k; i++) {
			list = run(qn, idx, inputType);
			mat1[i] = list.get(0);
			mat2[i] = list.get(1);
		}
		
		result.add(mat1);
		result.add(mat2);
		
		return result;
	}
	
	private static List<double[][]> run(QN qn, int inputType, int k, int[] idx) {
		//System.out.println(qn);
		
		List<double[][]> result = new ArrayList<>(); //mat1 == all the last-lines; mat2 == all the last but one line
		List<double[]> list = null;
		
		double[][] mat1 = new double[k][];
		double[][] mat2 = new double[k][];
		
		for(int i = 0; i < k; i++) {
			list = run(qn, idx, inputType);
			mat1[i] = list.get(0);
			mat2[i] = list.get(1);
		}
		
		result.add(mat1);
		result.add(mat2);
		
		return result;
	}
	
	

	
	private static List<double[]> run(QN qn, int[] idx, int inputType) {
		
		List<double[]> result = new ArrayList<>();
		
		double[][] bn  = new double[bnIterations][bnSize];
		double[][] nbn = new double[bnIterations][bnSize];
		
		int[] x = null;
		
		for(int j = 0; j < ensembleSize; j++) {
			x = new int[bnSize];
			qn.compute(x, idx);
			if(run60)qn.compute(x, idx);
			Utils.add(bn, qn.getResults());	

			int[] y = inputFactory(x, inputType);
			qn.compute(y, idx);
			Utils.add(nbn, qn.getResults());
		}
		
		Utils.div(bn,  ensembleSize);
		Utils.div(nbn, ensembleSize);

		
		nbn = Utils.sub(bn, nbn);
			
		result.add(nbn[bnIterations-2].clone());
		result.add(nbn[bnIterations-1].clone());
		
		return result;
	}
	
	
	
	
	private static List<double[][]> runKO(int netType, int koIdx, int k, int ko, boolean resetAll) {
		//System.out.println(qn);
		
		List<double[][]> result = new ArrayList<>(); //mat1 == all the last-lines; mat2 == all the last but one line
		List<double[]> list = null;
		
		double[][] mat1 = new double[k][];
		double[][] mat2 = new double[k][];
		
		int[] idx = Indexes.getIndex(fit);
		for(int i = 0; i < k; i++) {
			list = runKO(netType, idx, koIdx, -1, ko, resetAll);
			mat1[i] = list.get(0);
			mat2[i] = list.get(1);
		}
		
		result.add(mat1);
		result.add(mat2);
		
		return result;
	}
	
	private static List<double[][]> runKO(int netType, int koIdx, int j, int k, int ko, boolean resetAll) {
		//System.out.println(qn);
		
		List<double[][]> result = new ArrayList<>(); //mat1 == all the last-lines; mat2 == all the last but one line
		List<double[]> list = null;
		
		double[][] mat1 = new double[k][];
		double[][] mat2 = new double[k][];
		
		int[] idx = Indexes.getIndex(fit);
		for(int i = 0; i < k; i++) {
			list = runKO(netType, idx, koIdx, j, ko, resetAll);
			mat1[i] = list.get(0);
			mat2[i] = list.get(1);
		}
		
		result.add(mat1);
		result.add(mat2);
		
		return result;
	}
	
	
	private static List<double[]> runKO(int netType, int[] idx, int koIdx, int i, int ko, boolean resetAll) {
		
		List<double[]> result = new ArrayList<>();
		
		double[][] bn  = new double[bnIterations][bnSize];
		double[][] nbn  = new double[bnIterations][bnSize];
		
		int[] x = null;
		QN qn = null;
		
		for(int j = 0; j < ensembleSize; j++) {
			QN.knockoutEdge(i);
			long seed = seedGenerator.nextLong();
			seed = seed < 0  ? -seed : seed;
			//System.out.println(seed);
			qn = networkFactory(netType);
			QN.setRand(new Random(seed));
			x = new int[bnSize];
			qn.compute(x, idx);
			if(run60)qn.compute(x, idx);
			int[] y = inputFactory(x, Exec.IN_INFECTED);
			qn.compute(y, idx);
			Utils.add(bn, qn.getResults());	

			if(ko==1) QN.knockoutVertice(koIdx);
			else if(ko==-1) QN.flipVertice(koIdx);
			else QN.knockintVertice(koIdx);
			qn = networkFactory(netType);
			QN.setRand(new Random(seed));
			x = new int[bnSize];
			qn.compute(x, idx);
			if(run60)qn.compute(x, idx);
			y = inputFactory(x, Exec.IN_INFECTED);
			qn.compute(y, idx);
			Utils.add(nbn, qn.getResults());
			if(resetAll)QN.reset();
			else QN.resetVertices();
			
			
		}
		
		Utils.div(bn,  ensembleSize);
		Utils.div(nbn,  ensembleSize);
		
		bn = Utils.div(nbn, bn, EPS);
			
		result.add(bn[bnIterations-2].clone());
		result.add(bn[bnIterations-1].clone());
		
		return result;
	}
	
	
	
	/**
	 * return one if the average - 1.96 * std > 0. Zero otherwise. One means above zero (up-regulated); 
	 * Zero means equal or below zero (non-up-regulated/down-regulated);
	 * @param result two lists of zeros and ones. One for the last and one for the second to last iterations of the simulation.
	 * @return
	 */

	
	private static int[] test(List<double[][]> input) {
		double[] avg = null;
		double[] var = null;
		
		
		int[] upregulated = null;
		List<int[]> result = new ArrayList<>();
		
		for(double[][] mat : input) {
			avg = Utils.average(mat, bnSize, sampleSize);
			var = Utils.var(mat, avg, bnSize, sampleSize);
			upregulated = Utils.test(avg, var, 1, sigma);//CHANGED HERE!!!
			
			result.add(upregulated);
		}
		
		int[] bresult = new int[result.get(0).length];
		for(int i = 0; i < result.get(0).length; i++) 
			if(result.get(0)[i] == 1 || result.get(1)[i] == 1)
				bresult[i]  = 1;
		
		
		return bresult;
	}
	
	private static double[] getPr(List<double[][]> input) {		
		double[] avg = null;
		List<double[]> avgs = new ArrayList<>();
		
		for(double[][] mat : input) {
			avg = Utils.average(mat, bnSize, sampleSize);
			avgs.add(avg);
		}
		
		double[] result = new double[avgs.get(0).length];
		for(int i = 0; i < avgs.get(0).length; i++) 
			result[i]  = avgs.get(0)[i] + avgs.get(1)[i];//1.0/(1.0 + Math.exp(-(avgs.get(0)[i] + avgs.get(1)[i])));
		
		return result;
	}
	
	public static void validation(String name, int iterations, boolean shuffle, boolean inVivo) throws FileNotFoundException {
		PrintWriter[] pws = new PrintWriter[9];
		PrintWriter[] pwPr = new PrintWriter[9];
		
		long start  = 0;
		long end = 0;
		
		int type = -1;
		int[] types = {NET_SCENARIO_1, NET_SCENARIO_2, NET_SCENARIO_3, NET_SCENARIO_4};
		
		for(int i = 0; i < types.length; i++) {
			type = inVivo ? types[i] + 1 : types[i];
			
			
			
			pws[0] = new PrintWriter(path + name + "_vir_"  + type + ".csv");
			pws[1] = new PrintWriter(path + name + "_ptr_"  + type + ".csv");
			pws[2] = new PrintWriter(path + name + "_hpx_"  + type + ".csv");
			pws[3] = new PrintWriter(path + name + "_dpr_"  + type + ".csv");
			pws[4] = new PrintWriter(path + name + "_tlr_"  + type + ".csv");
			pws[5] = new PrintWriter(path + name + "_tnf_"  + type + ".csv");
			pws[6] = new PrintWriter(path + name + "_ang_"  + type + ".csv");
			pws[7] = new PrintWriter(path + name + "_sir_"  + type + ".csv");
			pws[8] = new PrintWriter(path + name + "_"  + type + ".csv");
			
			pwPr[0] = new PrintWriter(path + name + "_vir_pr_"  + type + ".csv");
			pwPr[1] = new PrintWriter(path + name + "_ptr_pr_"  + type + ".csv");
			pwPr[2] = new PrintWriter(path + name + "_hpx_pr_"  + type + ".csv");
			pwPr[3] = new PrintWriter(path + name + "_dpr_pr_"  + type + ".csv");
			pwPr[4] = new PrintWriter(path + name + "_tlr_pr_"  + type + ".csv");
			pwPr[5] = new PrintWriter(path + name + "_tnf_pr_"  + type + ".csv");
			pwPr[6] = new PrintWriter(path + name + "_ang_pr_"  + type + ".csv");
			pwPr[7] = new PrintWriter(path + name + "_sir_pr_"  + type + ".csv");
			
			
		    start = System.currentTimeMillis();
		    for(int k = 0; k < iterations; k++) {
		    	long s = System.currentTimeMillis();
		    	
		    	List<double[][]> virData = run(networkFactory(type), Exec.IN_INFECTED, sampleSize, shuffle);
		    	List<double[][]> ptrData = run(networkFactory(type, NO_VIR_REP), Exec.IN_INFECTED, sampleSize, shuffle);
		    	List<double[][]> hpxData = run(networkFactory(type), Exec.IN_HYPOXIA, sampleSize, shuffle);
		    	List<double[][]> dprData = run(networkFactory(type), Exec.IN_STARVATION, sampleSize, shuffle);
		    	List<double[][]> tlrData = run(networkFactory(type, HIF_KO), Exec.IN_LPS, sampleSize, shuffle);
		    	List<double[][]> tnfData = run(networkFactory(type), Exec.IN_TNF, sampleSize, shuffle);
		    	List<double[][]> angData = run(networkFactory(type), Exec.IN_ANG_II, sampleSize, shuffle);
		    	List<double[][]> sirData = run(networkFactory(type), Exec.IN_SIROLIMUS, sampleSize, shuffle);
		    	
	        	int[] vir = test(virData);
	        	int[] ptr = test(ptrData);
	        	int[] hpx = test(hpxData);
	        	int[] dpr = test(dprData);
	        	int[] tlr = test(tlrData);
	        	int[] tnf = test(tnfData);
	        	int[] ang = test(angData);
	        	int[] sir = test(sirData);
	        	
	        	
	        	double[] pvir = getPr(virData);
	        	double[] pptr = getPr(ptrData);
	        	double[] phpx = getPr(hpxData);
	        	double[] pdpr = getPr(dprData);
	        	double[] ptlr = getPr(tlrData);
	        	double[] ptnf = getPr(tnfData);
	        	double[] pang = getPr(angData);
	        	double[] psir = getPr(sirData);
	        	
	        	
	        	
	        	int[] counts = new int[5];
	        	int[] tmp;

		        //Utils.count(counts, virus, Indexes.TMP_RES, Indexes.TMP_IDX);	        	
		        
		        Utils.count(counts, vir, Indexes.EXP1_RES_UB, Indexes.EXP1_IDX_UB);
		        pws[0].println(counts[Utils.TT] + ", " + counts[Utils.TP] + ", " + counts[Utils.TN] + ", " + counts[Utils.FP] + ", " + counts[Utils.FN]);
		        tmp = counts.clone();
		        Utils.count(counts, ptr, Indexes.EXP2_RES_UB, Indexes.EXP2_IDX_UB);
		        pws[1].println((counts[Utils.TT] - tmp[Utils.TT]) + ", " + (counts[Utils.TP] - tmp[Utils.TP]) + ", " + (counts[Utils.TN] - tmp[Utils.TN]) + ", " + 
		        		(counts[Utils.FP] - tmp[Utils.FP]) + ", " + (counts[Utils.FN] - tmp[Utils.FN]));
		        tmp = counts.clone();
		        Utils.count(counts, hpx, Indexes.EXP3_RES_UB, Indexes.EXP3_IDX_UB);
		        pws[2].println((counts[Utils.TT] - tmp[Utils.TT]) + ", " + (counts[Utils.TP] - tmp[Utils.TP]) + ", " + (counts[Utils.TN] - tmp[Utils.TN]) + ", " + 
				        (counts[Utils.FP] - tmp[Utils.FP]) + ", " + (counts[Utils.FN] - tmp[Utils.FN]));
		        tmp = counts.clone();
		        Utils.count(counts, dpr, Indexes.EXP4_RES_UB, Indexes.EXP4_IDX_UB);
		        pws[3].println((counts[Utils.TT] - tmp[Utils.TT]) + ", " + (counts[Utils.TP] - tmp[Utils.TP]) + ", " + (counts[Utils.TN] - tmp[Utils.TN]) + ", " + 
				        (counts[Utils.FP] - tmp[Utils.FP]) + ", " + (counts[Utils.FN] - tmp[Utils.FN]));
		        tmp = counts.clone();
		        Utils.count(counts, tlr, Indexes.EXP5_RES_UB, Indexes.EXP5_IDX_UB);
		        pws[4].println((counts[Utils.TT] - tmp[Utils.TT]) + ", " + (counts[Utils.TP] - tmp[Utils.TP]) + ", " + (counts[Utils.TN] - tmp[Utils.TN]) + ", " + 
				        (counts[Utils.FP] - tmp[Utils.FP]) + ", " + (counts[Utils.FN] - tmp[Utils.FN]));
		        tmp = counts.clone();
		        Utils.count(counts, tnf, Indexes.EXP6_RES_UB, Indexes.EXP6_IDX_UB);
		        pws[5].println((counts[Utils.TT] - tmp[Utils.TT]) + ", " + (counts[Utils.TP] - tmp[Utils.TP]) + ", " + (counts[Utils.TN] - tmp[Utils.TN]) + ", " + 
				        (counts[Utils.FP] - tmp[Utils.FP]) + ", " + (counts[Utils.FN] - tmp[Utils.FN]));
		        tmp = counts.clone();
		        Utils.count(counts, ang, Indexes.EXP7_RES_UB, Indexes.EXP7_IDX_UB);
		        pws[6].println((counts[Utils.TT] - tmp[Utils.TT]) + ", " + (counts[Utils.TP] - tmp[Utils.TP]) + ", " + (counts[Utils.TN] - tmp[Utils.TN]) + ", " + 
				        (counts[Utils.FP] - tmp[Utils.FP]) + ", " + (counts[Utils.FN] - tmp[Utils.FN]));
		        tmp = counts.clone();
		        Utils.count(counts, sir, Indexes.EXP8_RES_UB, Indexes.EXP8_IDX_UB);
		        pws[7].println((counts[Utils.TT] - tmp[Utils.TT]) + ", " + (counts[Utils.TP] - tmp[Utils.TP]) + ", " + (counts[Utils.TN] - tmp[Utils.TN]) + ", " + 
				        (counts[Utils.FP] - tmp[Utils.FP]) + ", " + (counts[Utils.FN] - tmp[Utils.FN]));
	        	

		        pws[8].println(counts[Utils.TT] + ", " + counts[Utils.TP] + ", " + counts[Utils.TN] + ", " + counts[Utils.FP] + ", " + counts[Utils.FN]);
		        pwPr[0].println(Utils.toString(vir));
		        pwPr[1].println(Utils.toString(ptr));
		        pwPr[2].println(Utils.toString(hpx));
		        pwPr[3].println(Utils.toString(dpr));
		        pwPr[4].println(Utils.toString(tlr));
		        pwPr[5].println(Utils.toString(tnf));
		        pwPr[6].println(Utils.toString(ang));
		        pwPr[7].println(Utils.toString(sir));
		        System.out.println(counts[Utils.TT] + ", " + counts[Utils.TP] + ", " + counts[Utils.TN] + ", " + counts[Utils.FP] + ", " + counts[Utils.FN]);
		        long e = System.currentTimeMillis();
		        //System.out.println((e - s));
		    }
		    
		    pwPr[0].close();
		    pwPr[1].close();
		    pwPr[2].close();
		    pwPr[3].close();
		    pwPr[4].close();
		    pwPr[5].close();
		    pwPr[6].close();
		    pwPr[7].close();
		    
		    pws[0].close();
		    pws[1].close();
		    pws[2].close();
		    pws[3].close();
		    pws[4].close();
		    pws[5].close();
		    pws[6].close();
		    pws[7].close();
		    pws[8].close();
		    
		    
		    end = System.currentTimeMillis();
		    System.out.println((end - start));
		    //System.exit(0);
		}
	}
	
	public static void runSingle(int netType, int inputType, int iterations, boolean shuffle) {
		runSingle(netType, inputType, iterations, shuffle, -1);
	}

	public static void runSingle(int netType, int inputType, int iterations, boolean shuffle, int ko) {
		System.out.println("This method prints average and variance.");
		List<double[][]> result = null;
		if(ko<0)result = run(networkFactory(netType), inputType, iterations, shuffle);
		else result = run(networkFactory(netType, ko), inputType, iterations, shuffle);
		double[] avg = null;
		double[] var = null;
		
		Utils.print(Indexes.NAMES);
		for(double[][] mat : result) {
			avg = Utils.average(mat, bnSize, iterations);
			var = Utils.var(mat, avg, bnSize, iterations);
			
			Utils.print(avg);
			Utils.print(var);
			System.out.println();
		}
		
	}
	
	public static void runSingle(int netType, int inputType, boolean shuffle) {
		int[] idx = Indexes.getIndex(fit);
		QN qn = networkFactory(netType);
		if(shuffle) qn.shuffle(idx);
		List<double[]> r = run(qn, idx, inputType);
		double[] result1 = r.get(0);
		
		r = run(networkFactory(netType), idx, inputType);
		double[] result2 = r.get(0);
		
		for(int i =  0; i < result1.length; i++) {
			System.out.println(Indexes.NAMES[i] + ": " + result1[i] + "\t" + result2[i]);
		}
		
	}
	
	
	public static void printSingle(int netType, int inputType, int iterations, int[] idx, File file, File file0) throws FileNotFoundException {
		List<double[][]> list = run(networkFactory(netType), inputType, iterations, idx);
		
		double[][] result = list.get(1);
		double[][] result0 = list.get(0);
		
		if(file == null) {
			for(double[] mat : result) {
				System.out.print("c(");
				for(int i = 0; i < QN.QN_SIZE; i++) {
					if(i == (QN.QN_SIZE-1))
						System.out.print(mat[i]);
					else
						System.out.print(mat[i] + ", ");
				}
				System.out.println("),");
			}
		}else {
			PrintWriter pw = new PrintWriter(file);
			for(double[] mat : result) {
				for(int i = 0; i < QN.QN_SIZE; i++) {
					if(i == (QN.QN_SIZE-1))
						pw.print(mat[i]);
					else
						pw.print(mat[i] + ", ");
				}
				pw.println();
			}
			pw.close();
		}
		if(file !=null) {
			PrintWriter pw = new PrintWriter(file0);
			for(double[] mat : result0) {
				for(int i = 0; i < QN.QN_SIZE; i++) {
					if(i == (QN.QN_SIZE-1))
						pw.print(mat[i]);
					else
						pw.print(mat[i] + ", ");
				}
				pw.println();
			}
			pw.close();
		}
		
	}
	
	
	public static int[] convergence(int netType, int inputType, boolean shuffle, File file) throws FileNotFoundException {
		
		QN qn = networkFactory(netType);
		int[] idx = Indexes.getIndex(fit);
		if(shuffle) qn.shuffle(idx);
		
		double[][] bn  = new double[bnIterations][bnSize];
		double[][] nbn = new double[bnIterations][bnSize];
		
		int[] x = null;
		
		for(int j = 0; j < ensembleSize; j++) {
			x = new int[bnSize];
			qn.compute(x, idx);
			if(run60)qn.compute(x, idx);
			Utils.add(bn, qn.getResults());	

			int[] y = inputFactory(x, inputType);
			qn.compute(y, idx);
			Utils.add(nbn, qn.getResults());
		}
		
		Utils.div(bn,  ensembleSize);
		Utils.div(nbn, ensembleSize);
		
		
		nbn = Utils.sub(bn, nbn);
		
		int lastJ = QN.QN_SIZE - 1;
		int lastI = bnIterations - 1;
//		double eps = 1e-16;
		double[] mean = new double[QN.QN_SIZE];
		double[] std = new double[QN.QN_SIZE];
		
		
		
		if(file == null) {
			for(int i = 15; i < bnIterations; i++) {
				System.out.print("c(");
				for(int j = 0; j < QN.QN_SIZE; j++) {
					if(j == lastJ)
						System.out.print(nbn[i][j]);
					else
						System.out.print(nbn[i][j] + ", ");
					
				}
				if(i == lastI)
					System.out.println(")");
				else
					System.out.println("),");
			}
		}else {
			PrintWriter pw = new PrintWriter(file);
			for(int i = 0; i < bnIterations; i++) {
				for(int j = 0; j < QN.QN_SIZE; j++) {
					if(j == lastJ)
						pw.print(nbn[i][j]);
					else
						pw.print(nbn[i][j] + ", ");
					
				}
				pw.println();

			}
			pw.close();
		}
		
		return idx;
		
//		System.out.print("c(");
//		for(int j = 0; j < QN.QN_SIZE; j++) {
//			for(int i = 14; i < (bnIterations-1); i++) {
//				mean[j] += nbn[i+1][j]-nbn[i][j];
//			}
//			mean[j] /= 15.0;
//			System.out.print(mean[j] + ", ");
//		}
//		System.out.println(")");
//		
//		System.out.print("c(");
//		for(int j = 0; j < QN.QN_SIZE; j++) {
//			for(int i = 14; i < (bnIterations-1); i++) {
//				std[j] += Math.pow((nbn[i+1][j]-nbn[i][j]) - mean[j], 2.0);
//			}
//			std[j] = Math.sqrt(std[j]/15.0);
//			System.out.print(std[j] + ", ");
//		}
//		System.out.println(")");
	}
	
	
	
	public static void runSirolimus(int inputType, int k) {
		System.out.println("runSirolimus");

		final int[] netTypes = {
				NET_SCENARIO_3,
				NET_SCENARIO_2,
				NET_SCENARIO_4,
				NET_SCENARIO_1
		};
		
		int j = 0;
		
		double[] mtorc1 = new double[k];
		double[] ifn = new double[k];
		double[] viralRep = new double[k];
		
		//QN.setPertub(true);
		
		for(int i = 0; i < netTypes.length; i++) {
			List<double[][]> result = run(networkFactory(netTypes[i]), inputType, k, false);
			double[][] r = result.get(0);
			j = 0;
			for(double[] mat : r) {
				mtorc1[j] = mat[reduced ? Reduced.mTOR : QN.mTORC1];
				ifn[j] = mat[reduced ? Reduced.IFN_I : QN.IFN_a_b];
				viralRep[j] = mat[reduced ? Reduced.REPLICATION : QN.Viral_Repl];
				j++;
			}
			System.out.print("mtorc_" + inputType + "_" + netTypes[i] + " = ");
			Utils.printR(mtorc1);
			System.out.print("ifn_" + inputType + "_" + netTypes[i] + " = ");
			Utils.printR(ifn);
			System.out.print("veralRep_" + inputType + "_" + netTypes[i] + " = ");
			Utils.printR(viralRep);
			System.out.println("\n");
		}
		
		
	}
	
	public static void runSirolimus3PrintAll(int inputType, int k) {
		System.out.println("runSirolimus");
		
		QN3 qn = new QN3();
		
		QN.setPertub(false);
		
		double[][] rT;
		
		bnSize = QN3.QN_SIZE;
		
		List<double[][]> result = run(qn, inputType, k, false);
		double[][] r = result.get(0);
		
		rT = new double[r[0].length][r.length];
		
		//for(double[] mat : r) {
		for(int i = 0; i < r.length; i++) 
			for(int j = 0; j < r[0].length; j++) 
				rT[j][i] = r[i][j];
		
		int i = 0;
		for(double[] v : rT) {
			System.out.print(QN.NAMES[i] + " = ");
			Utils.printR(v);
			i++;
		}
		
		
	}
	
//	public static void runSirolimusKO(int inputType, int k) {
//		
//		int[] nodeIdx = {2, 3, 14, 16, 20, 22, 23, 25, 26, 30, 33, 35};
//		
//		final int[] netTypes = {
//				NET_SCENARIO_3,
//				NET_SCENARIO_2,
//				NET_SCENARIO_4,
//				NET_SCENARIO_1
//		};
//		
//		int j = 0;
//		
//		double[] mtorc1 = new double[k];
//		double[] ifn = new double[k];
//		double[] viralRep = new double[k];
//		
//		QN.setPertub(true);
//		
//		//int idx = 58;
//		//for(int idx = 0; idx < QN.EDGE_KNOCKOUT_SIZE; idx++) {
//			//knock(idx);
//			//QN.knockoutVertice(idx);
//			//System.out.println("}else if(IDX == " + idx + "){");
//			for(int i = 0; i < netTypes.length; i++) {
//				List<double[][]> result = run(networkFactory(netTypes[i]), inputType, k, false);
//				double[][] r = result.get(0);
//				j = 0;
//				for(double[] mat : r) {
//					mtorc1[j] = mat[QN.mTORC1];
//					ifn[j] = mat[QN.IFN_a_b];
//					viralRep[j] = mat[QN.Viral_Repl];
//					j++;
//				}
//				System.out.print("mtorc_" + inputType + "_" + netTypes[i] + " = ");
//				Utils.printR(mtorc1);
//				System.out.print("ifn_" + inputType + "_" + netTypes[i] + " = ");
//				Utils.printR(ifn);
//				System.out.print("veralRep_" + inputType + "_" + netTypes[i] + " = ");
//				Utils.printR(viralRep);
//				System.out.println("\n");
//			}
//			System.out.println("\n");
//			QN.reset();
//		//}
//		
//		
//	}
	
	public static void runKOAnalysis(int inputType, int  netType, int ko, boolean koIKK) {		
		
		String[] nodes = {
				"ACE2", "ADAM_17", "AKT", "AMPK", "ANG_2", "ANG_2_T1R", "Apoptosis", "BCL_2", "CASP8",
				"CASP9", "C_FLIP", "CREB_1", "FADD", "FOXO3A", "HIF_1a", "IFN_a_b", "IFNR", "IL6", "IL6R",
				"IRF3", "IKK_a_b", "ISG", "MAPK_p38", "mTORC1", "mTORC2", "NFKB", "p53", "PI3K", "PTEN", 
				"RIG1", "ROS", "SIL6R", "STAT1", "TLR4", "TNF", "TNFR", "TSC2"
		};
		
		//int[] nodeIdx = {1, 2, 3, 4, 7, 10, 11, 12, 13, 14, 16, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 32, 33, 35, 36};
		//int[] nodeIdx = {2, 3, 14, 13, 16, 20, 22, 23, 25, 26, 30, 33, 35};
		int[] nodeIdx = {2, 3, 13, 14, 16, 20, 22, 23, 25, 26, 29, 30, 35};
		List<double[][]> result = null;
		double[][] mat = null;
		double[] avg = null;
		double[] var = null;
		
		String avgName = null;
		String stdName = null;
		
		System.out.println("s  = 1.96");
		System.out.println("names=c(\"IFN\", \"IL6\", \"Vir_repl\", \"Apoptosis\")");
		
		int iter  = 30;
		double sigma  = 1.96;
		double eps = 1e-4;
		QN.setPertub(true);
		
		for(int j : nodeIdx) {
			QN.knockoutEdge(68);
			if(koIKK)result = runKO(netType, j, iter, 58, ko, true);
			else result = runKO(netType, j, iter, ko,  true); 
			
			mat = result.get(0);
			avg = Utils.average(mat, bnSize, iter);
			var = Utils.var(mat, avg, bnSize, iter);
			
				
			System.out.println("\n # " + nodes[j] + "\t" + j + "\n");

			avgName = "avg_" + nodes[j] + "_" + netType;
			stdName = "std_" + nodes[j] + "_" + netType;
			
			
			System.out.print(avgName + " = ");
			Utils.printR(new double[] {avg[QN.IFN_a_b], avg[QN.IL6], avg[QN.Viral_Repl], avg[QN.Apoptosis]});
			System.out.print(stdName +  " = sqrt(");
			Utils.printR(new double[] {var[QN.IFN_a_b], var[QN.IL6], var[QN.Viral_Repl], var[QN.Apoptosis]});
			System.out.println(")");
			System.out.println("mat_" + netType + " = as.matrix(cbind(" + avgName + ", " + avgName + " - s*" + stdName +  ", " + avgName + " + s*" + stdName + ", " + stdName + "))");
			System.out.println("rownames(mat_" + netType + ") = names");
		}
		
	}
	
	private static void reset() {
		QN.reset();
		for(int i = 96; i < QN.EDGE_KNOCKOUT_SIZE; i++)
			QN.knockoutEdge(i);
		
	}
	
	private static void knock(int i) {
		if(i < 96)
			QN.knockoutEdge(i);
		else
			QN.resetEdge(i);
	}
	
	public static void runPerturbationAll(int netType) {	
		Exec.qn2 = true;
		//int[] nodeIdx = {   2, 3,    13, 14, 16, 20, 22, 23, 25, 26, 30, 35};
		int[] nodeIdx   = {1, 2, 3, 5, 13,     16, 20, 22, 23, 25, 26, 30};
		//int[] nodeIdx = {16, 20};
		
		List<double[][]> result = null;
		double[][] mat = null;
		double[] avg = null;
		
		int iter = 1;
		//QN.setPertub(true);
		
		int jj = 0;
		
		Exec.ensembleSize = 100;
				
		
		int[] countVirp = new int[12];
		int[] countIL6p = new int[12];
		int[] countApop = new int[12];
		int[] countIFNp = new int[12];
		
		int[] countVirn = new int[12];
		int[] countIL6n = new int[12];
		int[] countApon = new int[12];
		int[] countIFNn = new int[12];
		
		int count = 0;
		
		long start = System.currentTimeMillis();;
		long end = 0;
		
		double threshold = 0.05;
		
		for(int i = 0; i < QN.EDGE_KNOCKOUT_SIZE; i++) {
			knock(i);
			
			avg = null;
			jj = 0;
			for(int j : nodeIdx) {
				result = runKO(netType, j, iter, 0, false);
				
				mat = result.get(0);
				avg = Utils.average(mat, bnSize, iter);
				
				if((avg[QN.IFN_a_b] - 1.0) >  threshold)    countIFNp[jj]++;
				if((avg[QN.IFN_a_b] - 1.0) < -threshold)    countIFNn[jj]++;
				if((avg[QN.IL6] - 1.0) >  threshold) 	    countIL6p[jj]++;
				if((avg[QN.IL6] - 1.0) < -threshold) 	    countIL6n[jj]++;
				if((avg[QN.Viral_Repl] - 1.0) >  threshold) countVirp[jj]++;
				if((avg[QN.Viral_Repl] - 1.0) < -threshold) countVirn[jj]++;
				if((avg[QN.Apoptosis] - 1.0) >  threshold)  countApop[jj]++;
				if((avg[QN.Apoptosis] - 1.0) < -threshold)  countApon[jj]++;
				
				jj++;
			}
			reset();
				
		}
		
		System.out.print("positive Viral repl: ");
		Utils.print(countVirp);
		System.out.print("positive Type-I IFN: ");
		Utils.print(countIFNp);
		System.out.print("positive IL-6: ");
		Utils.print(countIL6p);
		System.out.print("positive Apoptosis:  ");
		Utils.print(countApop);
		
		System.out.print("negative Viral repl: ");
		Utils.print(countVirn);
		System.out.print("negative Type-I IFN: ");
		Utils.print(countIFNn);
		System.out.print("negative IL-6: ");
		Utils.print(countIL6n);
		System.out.print("negative Apoptosis:  ");
		Utils.print(countApon);
		
		for(int i = 0; i < QN.EDGE_KNOCKOUT_SIZE; i++) {
			for(int k = (i+1); k < QN.EDGE_KNOCKOUT_SIZE; k++) {
				knock(i);
				knock(k);
				
				avg = null;
				jj = 0;
				for(int j : nodeIdx) {
					result = runKO(netType, j, iter, 0, false);
					
					mat = result.get(0);
					avg = Utils.average(mat, bnSize, iter);
					
					if((avg[QN.IFN_a_b] - 1.0) >  threshold)    countIFNp[jj]++;
					if((avg[QN.IFN_a_b] - 1.0) < -threshold)    countIFNn[jj]++;
					if((avg[QN.IL6] - 1.0) >  threshold) 	    countIL6p[jj]++;
					if((avg[QN.IL6] - 1.0) < -threshold) 	    countIL6n[jj]++;
					if((avg[QN.Viral_Repl] - 1.0) >  threshold) countVirp[jj]++;
					if((avg[QN.Viral_Repl] - 1.0) < -threshold) countVirn[jj]++;
					if((avg[QN.Apoptosis] - 1.0) >  threshold)  countApop[jj]++;
					if((avg[QN.Apoptosis] - 1.0) < -threshold)  countApon[jj]++;
					
					jj++;
				}
				reset();
				if(count++%20==0) {
					end = System.currentTimeMillis();
					System.out.println(count + "  " + (end - start));
					start = System.currentTimeMillis();
				}
				
			}
		}

		System.out.print("positive Viral repl: ");
		Utils.print(countVirp);
		System.out.print("positive Type-I IFN: ");
		Utils.print(countIFNp);
		System.out.print("positive IL-6: ");
		Utils.print(countIL6p);
		System.out.print("positive Apoptosis:  ");
		Utils.print(countApop);
		
		System.out.print("negative Viral repl: ");
		Utils.print(countVirn);
		System.out.print("negative Type-I IFN: ");
		Utils.print(countIFNn);
		System.out.print("negative IL-6: ");
		Utils.print(countIL6n);
		System.out.print("negative Apoptosis:  ");
		Utils.print(countApon);
		
	}
	
//	public static void printNetwork(int type, int input, boolean shuffle, int prt) {
//		int[] idx = Indexes.getIndex(fit);
//		QN qn = networkFactory(type);
//		if(shuffle) qn.shuffle(idx);
//		
//		
//		double[][] bn  = new double[bnIterations][bnSize];
//		double[][] nbn = new double[bnIterations][bnSize];
//		
//		int[] x = null;
//		
//		for(int j = 0; j < ensembleSize; j++) {
//			x = new int[bnSize];
//			qn.compute(x, idx);
//			if(prt == 2)qn.compute(x, idx);
//			Utils.add(bn, qn.getResults());	
//			
//			if(prt > 0) {
//				int[] y = inputFactory(x, input);
//				qn.compute(y, idx);
//				Utils.add(nbn, qn.getResults());
//			}
//		}
//		
//		Utils.div(bn,  ensembleSize);
//		Utils.div(nbn, ensembleSize);
//
//		if(prt == 2) {
//			nbn = Utils.sub(bn, nbn);
//			Utils.printR(nbn);
//		}else if(prt == 1)
//			Utils.printR(nbn);
//		else
//			Utils.printR(bn);
//	}
	
	
	
	
	
	
	public static void runSirolimusWithEdgePerturbations(int netType) {

		Exec.fit = false;
		QN.setPertub(false);		
		Exec.qn2 = true;
		
		int count = 0;
		long start = System.currentTimeMillis();;
		long end = 0;
		
		Exec.ensembleSize = 100;
		
		for(int i = 0; i < QN.EDGE_KNOCKOUT_SIZE; i++) {
			knock(i);
			
			Exec.runSirolimus(netType, Exec.IN_INFECTED, 100);
			Exec.runSirolimus(netType, Exec.IN_INFECTED_SIROLIMUS, 100);
			
			end = System.currentTimeMillis();
			System.out.println("# " + count++ + "  " + (end - start));
			start = System.currentTimeMillis();

			reset();
				
		}
		
		for(int i = 0; i < QN.EDGE_KNOCKOUT_SIZE; i++) {
			for(int k = (i+1); k < QN.EDGE_KNOCKOUT_SIZE; k++) {
				knock(i);
				knock(k);
				
				Exec.runSirolimus(netType, Exec.IN_INFECTED, 100);
				Exec.runSirolimus(netType, Exec.IN_INFECTED_SIROLIMUS, 100);
				
				reset();
				end = System.currentTimeMillis();
				System.out.println("# " + count + "  " + (end - start));
				start = System.currentTimeMillis();
				
			}
		}
		
		
	}
	
	private static void runSirolimus(int netType, int inputType, int k) {
		System.out.println("# runSirolimus");
		int j = 0;
		
		double[] mtorc1 = new double[k];
		double[] ifn = new double[k];
		double[] viralRep = new double[k];
		
		QN.setPertub(true);
		
		List<double[][]> result = run(networkFactory(netType), inputType, k, false);
		double[][] r = result.get(0);
		j = 0;
		for(double[] mat : r) {
			mtorc1[j] = mat[QN.mTORC1];
			ifn[j] = mat[QN.IFN_a_b];
			viralRep[j] = mat[QN.Viral_Repl];
			j++;
		}
		System.out.print("mtorc_" + inputType + "_" + netType + " = ");
		Utils.printR(mtorc1);
		System.out.print("ifn_" + inputType + "_" + netType + " = ");
		Utils.printR(ifn);
		System.out.print("veralRep_" + inputType + "_" + netType + " = ");
		Utils.printR(viralRep);
		System.out.println("\n");
		
		
	}
	
}
