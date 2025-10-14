import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QN3 extends QN {
	
	public static final int N_ITER = 30;
	public static final int QN_SIZE = 52;
	
	public QN3(){
		this(N_ITER);
	}

	public QN3(int iterations){
		super(new Equation[8], iterations);
	}
	
	public int compute(int[] x, int[] idx) {
		
		//System.out.println(v[10]);
		List<Integer> net = new ArrayList<>();
		for(Integer i : NET)
			net.add(i);
		
		this.results = new int[iterations][];
			
		
		for(int i = 0; i < iterations; i++) {
			Collections.shuffle(net, rand);
			if(perturb)perturb(net);
			for(int caseName : net) {
			    switch (caseName) {
			    case ACE2: 
			        break;
			    case ADAM_17:
			        break;
			    case AKT:
			        x[AKT] = act(ko[2], f(AVG(x[idx[mTORC2_3]] - v[4], x[idx[PI3K_4]] - v[5], x[idx[IKK_a_b_5]] - v[6]) - AVG(x[idx[PTEN_6]] - v[7]), x[AKT]));
			        break;
			    case AMPK:
			    	x[AMPK] = act(ko[3], f(AVG(x[idx[p53_8]] - v[9]) - AVG(x[idx[AKT_9]] - v[11], x[idx[mTORC1_19]] - v[10]), x[AMPK])); //10.1038/s42255-019-0157-1
			        break;
			    case ANG_2:
			        break;
			    case ANG_2_T1R:
			        break;
			    case Apoptosis:
			    	x[Apoptosis] = act(ko[6], f(AVG(x[idx[CASP8_10]] - v[14], x[idx[CASP9_11]] - v[15], x[idx[p53_59]] - v[16]), x[Apoptosis])); // + p53? PMID: 32344470      idx[p53_16]
			        break;
			    case BCL_2:
			        x[BCL_2] = act(ko[7], f(AVG(x[idx[NFKB_12]] - v[17], x[idx[CREB_1_13]] - v[18]) - AVG(x[idx[p53_14]] - v[19]), x[BCL_2]));
			        break;
			    case CASP8:
			    	x[CASP8] = act(ko[8], f(AVG(x[idx[FADD_15]] - v[20], x[idx[p53_16]] - v[22]) - AVG(x[idx[C_FLIP_17]] - v[21]), x[CASP8]));
			        break;
			    case CASP9:
			    	x[CASP9] = act(ko[9], f(AVG(x[idx[FOXO3A_18]] - v[23], x[idx[ROS_20]] - v[24]) - AVG(x[idx[BCL_2_21]] - v[25]), x[CASP9])); //, 
			        break;
			    case C_FLIP:
			        x[C_FLIP] = act(ko[10], f(AVG(x[idx[NFKB_22]] - v[27], x[idx[p53_23]] - v[28]) - AVG(x[idx[AKT_24]] - v[29]), x[C_FLIP]));
			        break;
			    case CREB_1:
			        x[CREB_1] = act(ko[11], f(AVG(x[idx[AKT_25]] - v[30], x[idx[Viral_Repl_26]] - v[31]), x[CREB_1]));
			        break;
			    case FADD:
			        x[FADD] = act(ko[12], f(AVG(x[idx[TNFR_27]] - v[32]), x[FADD]));
			        break;
			    case FOXO3A:
			        x[FOXO3A] = act(ko[13], f(AVG(x[idx[MAPK_p38_28]] - v[33], x[idx[AMPK_29]] - v[34]) - AVG(x[idx[IKK_a_b_30]] - v[35], x[idx[AKT_31]] - v[36]), x[FOXO3A]));
			        break;
			    case HIF_1a:
			    	x[HIF_1a] = act(ko[14], f(AVG(x[idx[ROS_32]] - v[37], x[idx[mTORC1_91]] - v[39]), x[HIF_1a]));
			        break;
			    case Hypoxia:
			        break;
			    case IFN_a_b:
			    	x[IFN_a_b] = act(ko[15], f(AVG(x[idx[IRF3_34]] - v[40], x[idx[FOXO3A_35]] - v[41]) - AVG(x[idx[mTORC1_36]] - v[43], x[idx[Viral_Repl_37]] - v[42]), x[IFN_a_b]));
			        break;
			    case IFNR:
			        x[IFNR] = act(ko[16], f(AVG(x[idx[IFN_a_b_38]] - v[44], x[IFN_e]), x[IFNR]));
			        break;
			    case IL6:
			    	x[IL6] = act(ko[17], f(AVG(x[idx[NFKB_39]] - v[45], x[idx[MAPK_p38_40]] - v[46]), x[IL6]));
			        break;
			    case IL6R:
			        x[IL6R] = act(ko[18], f(AVG(x[idx[IL6_88]] - v[48], x[IL6_e]), x[IL6R]));
			        break;
			    case IRF3:
			        x[IRF3] = act(ko[19], f(AVG(x[idx[RIG1_42]] - v[49]), x[IRF3]));
			        break;
			    case IKK_a_b:
			        x[IKK_a_b] = act(ko[20], f(AVG(x[idx[TLR4_43]] - v[50], x[idx[TNFR_44]] - v[51], x[idx[AKT_45]] - v[52]), x[IKK_a_b]));
			        break;
			    case ISG:
			    	//System.out.println(x[ISG]);
			    	x[ISG] = act(ko[21], f(AVG(x[idx[STAT1_46]] - v[53]) - AVG(x[idx[Viral_Repl_47]] - v[54]), x[ISG]));
			        break; 
			    case MAPK_p38:
			        x[MAPK_p38] = act(ko[22], f(AVG(x[idx[TLR4_49]] - v[56], x[idx[ROS_50]] - v[57]), x[MAPK_p38]));
			        break;
			    case mTORC1:
			        x[mTORC1] = act(ko[23], f(AVG(x[idx[IKK_a_b_51]], x[AKT]) - AVG(x[AMPK], x[HIF_1a]), x[mTORC1]));
			        break;
			    case mTORC2:
			        x[mTORC2] = act(ko[24], f(AVG(x[idx[PI3K_53]] - v[60], x[idx[AMPK_54]] - v[61], x[idx[IKK_a_b_55]] - v[62]), x[mTORC2]));
			        break;
			    case NFKB:
			        x[NFKB] = act(ko[25], f(AVG(x[idx[IKK_a_b_56]] - v[63], x[idx[ROS_57]] - v[64]) - AVG(x[idx[FOXO3A_58]] - v[65]), x[NFKB]));
			        break;
			    case Nutr_Depr:
			        break;
			    case p53:
			    	//x[p53] = act(ko[26], f(AVG(x[idx[AMPK_60]] - v[66], x[idx[TNFR_61]] - v[67]) - AVG(x[idx[Viral_Repl_62]] - v[68]), x[p53])); //, x[idx[ROS_61]] [-hypoxia] +TNFR PMID: 32344470
			    	x[p53] = act(ko[26], f(AVG(x[idx[AMPK_60]] - v[66], x[idx[TNFR_61]] - v[67], x[idx[Viral_Repl_62]] - v[68]), x[p53])); //, x[idx[ROS_61]] [-hypoxia] +TNFR PMID: 32344470
			        break;
			    case PI3K:
			        x[PI3K] = act(ko[27], f(AVG(x[idx[TLR4_63]] - v[71], x[idx[ROS_64]] - v[72], x[idx[IL6R_65]] - v[73], x[idx[IFNR]] - v[74]), x[PI3K]));
			        break;
			    case PTEN:
			        x[PTEN] = act(ko[28], f(AVG(x[idx[p53_66]] - v[75]), x[PTEN]));
			        break;
			    case RIG1:
			        x[RIG1] = act(ko[29], f(AVG(x[idx[Viral_Repl_84]] - v[76]), x[RIG1]));
			        break;
			    case ROS:
			    	x[ROS] = act(ko[30], f(AVG(x[idx[TLR4_68]] - v[78]) - AVG(x[idx[FOXO3A_69]] - v[80], x[idx[p53_70]] - v[81]), x[ROS]));
			        break;
			    case SIL6R:
			        break;
			    case STAT1:
			        x[STAT1] = act(ko[32], f(AVG(x[idx[IFNR_73]] - v[83]), x[STAT1]));
			        break;
			    case TLR4:
			    	x[TLR4] = act(ko[33], f(AVG(x[idx[Virus_85]] - v[84]), x[TLR4]));
			        break;
			    case TNF:
			    	x[TNF] = act(ko[34], f(AVG(x[idx[NFKB_75]] - v[86], x[idx[MAPK_p38_76]] - v[87]), x[TNF]));
			        break;
			    case TNFR:
			        x[TNFR] = act(ko[35], f(AVG(x[idx[TNF_86]] - v[89], x[TNF_e]), x[TNFR]));
			        break;
			    case TSC2:
			        break;
			    case Viral_Repl:
			    	x[Viral_Repl] = f(AVG(x[idx[Virus_87]] - v[93]) - AVG(x[idx[ISG_82]] - v[95]), x[Viral_Repl]);
			        break;
			    case Virus:
			        x[Virus] = x[Virus];
			        break;
			    case RAPAMICINA:
			        break;
			    case TNF_e:
			    	x[TNF_e] = x[TNF_e];
			        break;
			    case ANG_2_e:
			        break;
			    case TLR_LIG:
			    	x[TLR_LIG] = x[TLR_LIG];
			    	break;
			    case IFN_e:
			    	x[IFN_e] = x[IFN_e]; //(rand.nextDouble() < 0.125 ? 1 : 0);
			        break;
			    case METFORMIN_e:
			        break;
			    case PP242_e:
			        break;
			    case Wortmannin:
			        break;
			    case MK2206:
			        break;
			    case PX478:
			        break;
			    case IL6_e:
			    	x[IL6_e] = x[IL6_e];
		        default:
		            throw new IllegalArgumentException("Invalid case: " + caseName);
			    }
			}
			results[i] = x.clone();
		}
		return -1;
	}
	
	public String toString() {
		String str = "QN3 ";
		str += ang.toString() + " ";
		str += hif.toString() + " ";
		str += ifn.toString() + " ";
		str += il6.toString() + " ";
		str += isg.toString() + " ";
		str += tnf.toString() + " ";
		str += p3k.toString() + " "; 
		str += vir.toString();

		return str;
	}
}




