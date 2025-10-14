import java.util.List;
import java.util.Random;

public abstract class Equation extends Indexes{
	
	public static final int EDGE_KNOCKOUT_SIZE = 112;
	public static final int VERTICE_KNOCKOUT_SIZE = 37;
	
	protected static Random rand;
	protected static boolean perturb;
	
	protected static final int[] v = new int[EDGE_KNOCKOUT_SIZE];
	protected static final boolean[] pertubed = new boolean[VERTICE_KNOCKOUT_SIZE];
	protected static final int[] ko = new int[VERTICE_KNOCKOUT_SIZE];
	
	static {
		for(int i = 0; i < EDGE_KNOCKOUT_SIZE; i++) 
			v[i] = 0;
		for(int i = 0; i < VERTICE_KNOCKOUT_SIZE; i++) {
			ko[i] = 0;
			pertubed[i] = false;
		}
	}
	
	public static void setPertub(boolean perturb) {
		Equation.perturb = perturb;
	}
	
	protected void perturb(List<Integer> idx) {
		int k = 0;
		
		int s = (int) (VERTICE_KNOCKOUT_SIZE*0.15);
		for(int i : idx) {
			if(i >= VERTICE_KNOCKOUT_SIZE)continue;
			if(pertubed[i]) {
				pertubed[i] = false;
				ko[i] = 0;
			}
			if(k++ < s && ko[i] == 0) {
				ko[i] = -1;
				pertubed[i] = true;
			}
		}
	}
	
	/*protected double AVG(int... args) {
		if(args.length == 1) return args[0];
		if(args.length == 2) return (args[0] + args[1])/2.0;
		if(args.length == 3) return (args[0] + args[1] + args[2])/3.0;
		double sum  = 0;
		int k = 0;
		for(int arg : args) {
			sum += arg;
			k++;
		}
		return sum/((double)k);
			
	}*/
	
	public static void setRand(Random rand) {
		Equation.rand = rand;
	}
	
	protected int act(int i, int j) {
		if(i == 1)
			return 0;
		if(i == -1)
			return 1;
		if(i == 2)
			return 1 - j;
		return j;
	}
	
	protected double AVG(int... args) { //CORRECT
		if(args.length == 1 && args[0] >= 0) return args[0];
		else if(args.length == 1 && args[0] < 0) return 0;
		double sum  = 0;
		int k = 0;
		for(int arg : args) {
			if(arg >= 0) {
				sum += arg;
				k++;
			}
		}
		return sum/((double)k);
		//return sum;
			
	}
	
//	# QN net running!
//	56, 29, 9, 10, 8
//	15565
//	56, 29, 9, 10, 8
//	14057
//	56, 28, 9, 10, 9
//	13891
//	56, 26, 9, 10, 11
//	13277

	
	public static void knockoutEdge(int i) {
		if(i < 0)return;
		v[i] = 2;
	}
	
	public static void resetEdge(int i) {
		v[i] = 0;
	}
	
	public static void knockoutVertice(int i) {
		if(i < 0)return;
		ko[i] = 1;
	}
	
	public static void flipVertice(int i) {
		if(i < 0)return;
		ko[i] = 2;
	}
	
	public static void knockintVertice(int i) {
		if(i < 0)return;
		ko[i] = -1;
	}
	
	public static void reset() {
		for(int i = 0; i < EDGE_KNOCKOUT_SIZE; i++)
			v[i] = 0;
		for(int i = 0; i < VERTICE_KNOCKOUT_SIZE; i++)
			ko[i] = 0;
	}
	
	public static void resetVertices() {
		for(int i = 0; i < VERTICE_KNOCKOUT_SIZE; i++)
			ko[i] = 0;
	}
	
	protected int NOT1(int v) {
		return  1 - v;
	}
	
	protected int NOT(int v) {
		return  1 - v;
	}
	
	
	protected int AND(int arg1, int arg2) {
		if(arg1 > arg2)return arg2;
		return arg1;
	}

	protected int OR(int arg1) {
		return arg1;
	}
	
	protected int OR(int arg1, int arg2) {
		if(arg1 > arg2)return arg1;
		return arg2;
	}
	
	protected int OR(int arg1, int arg2, int arg3) {
		if(arg1 > OR(arg2, arg3))return arg1;
		return OR(arg2, arg3);
	}
	
	protected int OR(int arg1, int arg2, int arg3, int arg4) {
		if(arg1 > OR(arg2, arg3, arg4))return arg1;
		return OR(arg2, arg3, arg4);
	}
	
	protected int ff(double c, int node) {
		if(c > node) return 1;
		if(c < node) return 0;
		return node;
		
	}
	
	protected int f(double c, int node) {
		int s = 0;
		if(c > node) s = node + 1;
		else if(c < node) s = node - 1;
		else s = node;
		s = s > 0 ? s : 0;
		return s;
		
	}
	
	protected int g(double c, int node) {
		if(c > 0) return 1;
		if(c < 1) return 0;
		return node;
		
	}

	public abstract int compute(int[] x, int[] idx);
	
}

class One extends Equation{

	@Override
	public int compute(int[] x, int[] idx) {
		return 1;
	}
	
}

class Zero extends Equation{

	@Override
	public int compute(int[] x, int[] idx) {
		return 0;
	}
	
}

class TR1 extends Equation{

	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[ANG_2_89]] - v[13]), x[ANG_2_T1R]);
	}
	
}

class HIF extends Equation {

	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[ROS_32]] - v[37], x[idx[Hypoxia_33]] - v[38], x[idx[mTORC1_91]] - v[39]), x[HIF_1a]);
	}
	
}

class BaseIFN extends Equation {

	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[IRF3_34]] - v[40], x[idx[FOXO3A_35]] - v[41]), x[IFN_a_b]);
	}
	
}

class ViralInhIFN extends Equation {

	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[IRF3_34]] - v[40], x[idx[FOXO3A_35]] - v[41]) - AVG(x[idx[Viral_Repl_37]] - v[42]), x[IFN_a_b]);
	}
	
}

class MTORCInhIFN extends Equation {

	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[IRF3_34]] - v[40], x[idx[FOXO3A_35]] - v[41]) - AVG(x[idx[mTORC1_36]] - v[43]), x[IFN_a_b]);
	}
	
}

class AllIFN extends Equation {

	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[IRF3_34]] - v[40], x[idx[FOXO3A_35]] - v[41], x[idx[IKK_a_b_104]] - v[108]) - AVG(x[idx[Viral_Repl_37]] - v[42]), x[IFN_a_b]);
	}
	
}

class MTORCActIFN extends Equation {

	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[IRF3_34]] - v[40], x[idx[FOXO3A_35]] - v[41], x[idx[mTORC1_36]] - v[43]), x[IFN_a_b]);
	}
	
}

class ViralAndMTORCInhIFN extends Equation {

	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[IRF3_34]] - v[40], x[idx[FOXO3A_35]] - v[41]) - AVG(x[idx[mTORC1_36]] - v[43], x[idx[Viral_Repl_37]] - v[42]), x[IFN_a_b]);
	}
	
}

class ViralInhMTORCActIFN extends Equation {

	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[IRF3_34]] - v[40], x[idx[FOXO3A_35]] - v[41], x[idx[mTORC1_36]] - v[43]) - AVG(x[idx[Viral_Repl_37]] - v[42]), x[IFN_a_b]);
	}
	
}

class BaseIL6 extends Equation {

	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[NFKB_39]] - v[45], x[idx[MAPK_p38_40]] - v[46]), x[IL6]);
	}
	
}

class MTORCIL6 extends Equation {

	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[NFKB_39]] - v[45], x[idx[MAPK_p38_40]] - v[46], x[idx[mTORC1_41]] - v[47]), x[IL6]);
	}
	
}

class BaseTNF extends Equation {

	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[ADAM_17_74]] - v[85], x[idx[NFKB_75]] - v[86], x[idx[MAPK_p38_76]] - v[87]), x[TNF]);
	}
	
}

class MTORCTNF extends Equation {

	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[ADAM_17_74]] - v[85], x[idx[NFKB_75]] - v[86], x[idx[MAPK_p38_76]] - v[87], x[idx[mTORC1_77]] - v[88]), x[TNF]);
	}
	
}

class BaseISG extends Equation {

	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[STAT1_46]] - v[53]), x[ISG]);
	}
	
}

class ViralInhISG extends Equation {

	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[STAT1_46]] - v[53]) - AVG(x[idx[Viral_Repl_47]] - v[54]), x[ISG]);
	}
	
}

class BaseViralRep extends Equation {

	@Override
	public int compute(int[] x, int[] idx) {
		//return f(AVG(x[idx[Virus_87]] - v[93], AND((rand.nextDouble()<0.25 ? 0 : 1), x[idx[Virus_87]]) - v[94]) - AVG(x[idx[ISG_82]] - v[95]), x[Viral_Repl]);
		return f(AVG(x[idx[Virus_87]] - v[93]) - AVG(x[idx[ISG_82]] - v[95]), x[Viral_Repl]);
	}
	
}

class ViralRepmTORC extends Equation {

	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[Virus_87]] - v[93], AND(x[idx[mTORC1_81]], x[idx[Virus_87]]) - v[94]) - AVG(x[idx[ISG_82]] - v[95]), x[Viral_Repl]);
	}
	
}

class BasePI3K extends Equation {
	
	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[TLR4_63]] - v[71], x[idx[ROS_64]] - v[72], x[idx[IL6R_65]] - v[73]), x[PI3K]);
	}
	
}

class IFNPI3K extends Equation {
	
	@Override
	public int compute(int[] x, int[] idx) {
		return f(AVG(x[idx[TLR4_63]] - v[71], x[idx[ROS_64]] - v[72], x[idx[IL6R_65]] - v[73], x[idx[IFNR]] - v[74]), x[PI3K]);
	}
	
}



