import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reduced extends QN{
	
	Equation ifn;
	Equation vir;

	public static final int N_ITER = 30;
	public static final int QN_SIZE = 11;
	
	public Reduced(Equation[] equations){
		this(new Equation[] {null, null, null, null, null, null, null, null}, N_ITER);
		ifn = equations[0];
		vir = equations[1];
	}

	public Reduced(Equation[] equations, int iterations){
		super(equations, iterations);
	}
	
	public int compute(int[] x, int[] idx) {
		
		//System.out.println(v[10]);
		List<Integer> net = new ArrayList<>();
		for(Integer i : REDUCED_NET)
			net.add(i);
		
		this.results = new int[iterations][];
		
		for(int i = 0; i < iterations; i++) {
			Collections.shuffle(net, rand);
			for(int caseName : net) {
			    switch (caseName) {
			    case VIRUS: 
			    	x[VIRUS] = x[VIRUS];
			        break;
			    case Inflammation:
			    	x[Inflammation] = f(AVG(x[VIRUS], x[PI3K_AKT]) - x[Stress], x[Inflammation]);
			        break;
			    case Stress:
			    	x[Stress] = f(x[Inflammation] - AVG(x[REPLICATION], x[PI3K_AKT]), x[Stress]); /////
			    	break;
			    case PI3K_AKT:
			    	x[PI3K_AKT] = f(AVG(x[Inflammation], x[VIRUS], x[IFN_R], x[mTOR]) - x[Stress], x[PI3K_AKT]); //IFN_I??? IFN_R!
			        break;
			    case mTOR:
			        x[mTOR] = AND(NOT(x[SIROLIMUS]), f(AVG(x[PI3K_AKT], x[Inflammation]) - x[Stress], x[mTOR]));  
			        break;
			    case REPLICATION:
			    	x[REPLICATION] = vir.compute(x, null);
			        break;
			    case IFN_R:
			    	x[IFN_R] = f(x[IFN_I], x[IFN_R]);
			        break;
			    case IFN_SG:
			        x[IFN_SG] = f(x[IFN_R] - x[REPLICATION], x[IFN_SG]);
			        break;
			    case RIG:
			    	x[RIG] = f(x[REPLICATION], x[RIG]);
			        break;
			    case IFN_I:
			    	x[IFN_I] = ifn.compute(x, null);
			        break;
			    case SIROLIMUS:
			    	x[SIROLIMUS] = x[SIROLIMUS];
			        break;
		        default:
		            throw new IllegalArgumentException("Invalid case: " + caseName);
			    }
			}
			results[i] = x.clone();
		}
		return -1;
	}
	
	public String toString() {
		String str = "Reduced ";
		str += ifn.toString() + " ";
		str += isg.toString() + " ";
		str += p3k.toString() + " "; 
		str += vir.toString();

		return str;
	}

}
