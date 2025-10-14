import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QN2 extends QN {
	
	public static final int N_ITER = 30;
	public static final int QN_SIZE = 52;
	
	public QN2(Equation[] equations){
		this(equations, N_ITER);
	}

	public QN2(Equation[] equations, int iterations){
		super(equations, iterations);
	}
	
	public int compute(int[] x, int[] idx) {
		//System.out.println(v[10]);
		List<Integer> net = new ArrayList<>();
		for(Integer i : NET)
			net.add(i);
		
		this.results = new int[iterations][];
		
		x[Indexes.TSC2] = 1;
		x[Indexes.ACE2]  = 1;	
		
		for(int i = 0; i < iterations; i++) {
			Collections.shuffle(net, rand);
			if(perturb)perturb(net);
			for(int caseName : net) {
			    switch (caseName) {
			    case ACE2: 
			    	x[ACE2] = act(ko[0], f(AVG(AND(NOT(x[idx[Virus_0]]), x[idx[ACE2_1]]) - v[0]) - AVG(x[idx[ADAM_17_83]] - v[1]), x[ACE2]));
			        break;
			    case ADAM_17:
			    	if(fit)
			    		x[ADAM_17] = act(ko[1], f(AVG(x[idx[ANG_2_T1R_2]] - v[2], x[idx[Virus_16]] - v[3]), x[ADAM_17])); //f(AVG(NOT(x[idx[ACE2]])), x[ADAM_17]); --back to normal! https://doi.org/10.1016/j.bbrc.2023.08.063
			    	else
			    		x[ADAM_17] = act(ko[1], f(AVG(x[idx[ANG_2_T1R_2]] - v[2]), x[ADAM_17])); //f(AVG(NOT(x[idx[ACE2]])), x[ADAM_17]); --back to normal!
			        break;
			    case AKT:
			        x[AKT] = act(ko[2], AND(NOT(x[MK2206]), f(AVG(x[idx[mTORC2_3]] - v[4], x[idx[PI3K_4]] - v[5], x[idx[IKK_a_b_5]] - v[6]) - AVG(x[idx[PTEN_6]] - v[7]), x[AKT])));
			        //x[AKT] = act(ko[2], AND(NOT(x[MK2206]), f(AVG(x[idx[mTORC2_3]] - v[4], x[idx[PI3K_4]] - v[5], x[idx[TLR4_43]] - v[6]) - AVG(x[idx[PTEN_6]] - v[7]), x[AKT])));
			        break;
			    case AMPK:
			    	if(fit)
			    		x[AMPK] = act(ko[3], OR(x[METFORMIN_e], f(AVG(x[idx[Nutr_Depr_7]] - v[8], x[idx[p53_8]] - v[9], NOT(x[idx[mTORC1_19]]) - v[10]) - AVG(x[idx[AKT_9]] - v[11]), x[AMPK]))); //10.1038/s42255-019-0157-1
			    	else
			    		x[AMPK] = act(ko[3], OR(x[METFORMIN_e], f(AVG(x[idx[Nutr_Depr_7]] - v[8], x[idx[p53_8]] - v[9]) - AVG(x[idx[AKT_9]] - v[11]), x[AMPK])));
			        break;
			    case ANG_2:
			        x[ANG_2] = act(ko[4], f(AVG(NOT(x[ACE2]) - v[12]), x[ANG_2]));  
			        break;
			    case ANG_2_T1R:
			    	x[ANG_2_T1R] = act(ko[5], OR(x[ANG_2_e], ang.compute(x, idx)));
			        break;
			    case Apoptosis:
			    	if(fit) {
			    		x[Apoptosis] = act(ko[6], f(AVG(x[idx[CASP8_10]] - v[14], x[idx[CASP9_11]] - v[15], x[idx[p53_59]] - v[16]), x[Apoptosis])); // + p53? PMID: 32344470      idx[p53_16]
			    	}else {
			    		x[Apoptosis] = act(ko[6], f(AVG(x[idx[CASP8_10]] - v[14], x[idx[CASP9_11]] - v[15]), x[Apoptosis]));
			    	}
			        break;
			    case BCL_2:
			        x[BCL_2] = act(ko[7], f(AVG(x[idx[NFKB_12]] - v[17], x[idx[CREB_1_13]] - v[18]) - AVG(x[idx[p53_14]] - v[19], x[idx[AKT_97]] - v[101]), x[BCL_2]));
			        break;
			    case CASP8:
			    	//if(fit) {
			    	//	x[CASP8] = act(ko[8], f(AVG(x[idx[FADD_15]] - v[20], x[idx[p53_16]] - v[111]) - AVG(x[idx[C_FLIP_17]] - v[21]), x[CASP8]));  //-p53?  , x[idx[p53_16]]
			    	//}else {
			    		x[CASP8] = act(ko[8], f(AVG(x[idx[FADD_15]] - v[20], x[idx[p53_16]] - v[22]) - AVG(x[idx[C_FLIP_17]] - v[21]), x[CASP8]));
			    	//}
			        break;
			    case CASP9:
			    	if(fit)
			    		x[CASP9] = act(ko[9], f(AVG(x[idx[FOXO3A_18]] - v[23], x[idx[ROS_20]] - v[24], x[idx[p53_19]] - v[110]) - AVG(x[idx[BCL_2_21]] - v[25], x[idx[AKT_95]] - v[99]), x[CASP9])); //, 
			    	else
			    		x[CASP9] = act(ko[9], f(AVG(x[idx[FOXO3A_18]] - v[23], x[idx[p53_19]] - v[26], x[idx[ROS_20]] - v[24]) - AVG(x[idx[BCL_2_21]] - v[25]), x[CASP9]));
			        break;
			    case C_FLIP:
			        x[C_FLIP] = act(ko[10], f(AVG(x[idx[NFKB_22]] - v[27], x[idx[p53_23]] - v[28]) - AVG(x[idx[AKT_24]] - v[29]), x[C_FLIP]));
			        break;
			    case CREB_1:
			        x[CREB_1] = act(ko[11], f(AVG(x[idx[AKT_25]] - v[30], x[idx[Viral_Repl_26]] - v[31], x[idx[AKT_96]] - v[100]), x[CREB_1]));
			        break;
			    case FADD:
			        x[FADD] = act(ko[12], f(AVG(x[idx[TNFR_27]] - v[32]), x[FADD]));
			        break;
			    case FOXO3A:
			        x[FOXO3A] = act(ko[13], f(AVG(x[idx[MAPK_p38_28]] - v[33], x[idx[AMPK_29]] - v[34], x[idx[IL6R_94]] - v[98]) - AVG(x[idx[IKK_a_b_30]] - v[35], x[idx[AKT_31]] - v[36]), x[FOXO3A]));
			        break;
			    case HIF_1a:
			    	x[HIF_1a] = act(ko[14], AND(NOT(x[PX478]), hif.compute(x, idx)));
			        break;
			    case Hypoxia:
			        x[Hypoxia] = x[Hypoxia];
			        break;
			    case IFN_a_b:
			    	x[IFN_a_b] = act(ko[15], ifn.compute(x, idx));
			        break;
			    case IFNR:
			        x[IFNR] = act(ko[16], OR(x[IFN_e], f(AVG(x[idx[IFN_a_b_38]] - v[44]), x[IFNR])));
			        break;
			    case IL6:
			    	x[IL6] = act(ko[17], il6.compute(x, idx));
			        break;
			    case IL6R:
			        x[IL6R] = act(ko[18], OR(x[IL6_e], f(AVG(x[idx[IL6_88]] - v[48]), x[IL6R])));
			        break;
			    case IRF3:
			        x[IRF3] = act(ko[19], f(AVG(x[idx[RIG1_42]] - v[49]), x[IRF3]));
			        break;
			    case IKK_a_b:
			        x[IKK_a_b] = act(ko[20], f(AVG(x[idx[TLR4_43]] - v[50], x[idx[TNFR_44]] - v[51], x[idx[AKT_45]] - v[52]), x[IKK_a_b]));
			    	//x[IKK_a_b] = act(ko[20], f(AVG(x[idx[TNFR_44]] - v[51]), x[IKK_a_b]));
			        break;
			    case ISG:
			    	x[ISG] = act(ko[21], isg.compute(x, idx));
			        break; 
			    case MAPK_p38:
			        x[MAPK_p38] = act(ko[22], f(AVG(x[idx[ANG_2_T1R_48]] - v[55], x[idx[TLR4_49]] - v[56], x[idx[ROS_50]] - v[57], x[idx[TNFR_92]] - v[96]), x[MAPK_p38]));
			        break;
			    case mTORC1:
			        x[mTORC1] = act(ko[23], AND(NOT(OR(x[RAPAMICINA], x[PP242_e])), f(AVG(x[idx[IKK_a_b_51]] - v[58], NOT(x[idx[TSC2_52]]) - v[59]) - AVG(x[idx[AMPK_101]] - v[105]), x[mTORC1])));
			        break;
			    case mTORC2:
			    	//although this equation has AMPK as activator and inhibitor only one at a time is used. This is for the knock-out/knock-in experiment.
			        x[mTORC2] = act(ko[24], AND(NOT(x[PP242_e]), f(AVG(x[idx[PI3K_53]] - v[60], x[idx[AMPK_54]] - v[61], x[idx[IKK_a_b_55]] - v[62]) - AVG(x[idx[AMPK_102]] - v[106]), x[mTORC2])));
			        break;
			    case NFKB:
			        x[NFKB] = act(ko[25], f(AVG(x[idx[IKK_a_b_56]] - v[63], x[idx[ROS_57]] - v[64]) - AVG(x[idx[FOXO3A_58]] - v[65]), x[NFKB]));
			        //x[NFKB] = act(ko[25], f(AVG(x[idx[TLR4_43]] - v[63], x[idx[ROS_57]] - v[64]) - AVG(x[idx[FOXO3A_58]] - v[65]), x[NFKB]));
			    	//x[NFKB] = act(ko[25], f(AVG(x[idx[TLR4_43]] - v[63], x[idx[ROS_57]] - v[64], x[idx[IKK_a_b_99]] - v[103]) - AVG(x[idx[FOXO3A_58]] - v[65]), x[NFKB]));
			        break;
			    case Nutr_Depr:
			        x[Nutr_Depr] = x[Nutr_Depr];
			        break;
			    case p53:
			    	if(fit)
			    		x[p53] = act(ko[26], f(AVG(x[idx[AMPK_60]] - v[66], x[idx[TNFR_61]] - v[67], x[idx[ROS_61]] - v[109]) - AVG(x[idx[Viral_Repl_62]] - v[68], x[idx[AKT_98]] - v[102]), x[p53])); //, x[idx[ROS_61]] [-hypoxia] +TNFR PMID: 32344470
			    	else
			    		x[p53] = act(ko[26], f(AVG(x[idx[Hypoxia_59]] - v[69], x[idx[AMPK_60]] - v[66], x[idx[ROS_61]] - v[70]) - AVG(x[idx[Viral_Repl_62]] - v[68]), x[p53]));
			        break;
			    case PI3K:
			        x[PI3K] = act(ko[27], AND(NOT(x[Wortmannin]), p3k.compute(x, idx)));// f(AVG(x[idx[TLR4_63]], x[idx[ROS_64]], x[idx[IL6R_65]], x[IFN_a_b]), x[PI3K]);
			        break;
			    case PTEN:
			        x[PTEN] = act(ko[28], f(AVG(x[idx[p53_66]] - v[75]) - AVG(x[idx[IKK_a_b_93]] - v[97]), x[PTEN]));
			        break;
			    case RIG1:
			        x[RIG1] = act(ko[29], f(AVG(x[idx[Viral_Repl_84]] - v[76]), x[RIG1]));
			        break;
			    case ROS:
			    	if(fit)
			    		x[ROS] = act(ko[30], f(AVG(x[idx[ANG_2_T1R_67]] - v[77], x[idx[TLR4_68]] - v[78], x[idx[Hypoxia_90]] - v[79]) - AVG(x[idx[FOXO3A_69]] - v[80], x[idx[p53_70]] - v[81]), x[ROS]));  // + hypoxia
			    	else
			    		x[ROS] = act(ko[30], f(AVG(x[idx[ANG_2_T1R_67]] - v[77], x[idx[TLR4_68]] - v[78]) - AVG(x[idx[FOXO3A_69]] - v[80], x[idx[p53_70]] - v[81]), x[ROS]));
			        break;
			    case SIL6R:
			        x[SIL6R] = act(ko[31], f(AVG(x[idx[ADAM_17_71]] - v[82]), x[SIL6R])); //, x[idx[IL6R_72]]
			        break;
			    case STAT1:
			        x[STAT1] = act(ko[32], f(AVG(x[idx[IFNR_73]] - v[83]), x[STAT1]));
			        break;
			    case TLR4:
			    	x[TLR4] = act(ko[33], OR(x[TLR_LIG], f(AVG(x[idx[Virus_85]] - v[84]), x[TLR4])));
			        break;
			    case TNF:
			    	x[TNF] = act(ko[34], tnf.compute(x, idx));
			        break;
			    case TNFR:
			        x[TNFR] = act(ko[35], OR(x[TNF_e], f(AVG(x[idx[TNF_86]] - v[89]), x[TNFR])));
			        break;
			    case TSC2:
			        x[TSC2] = act(ko[36], f(AVG(x[idx[AMPK_78]] - v[90], x[idx[HIF_1a_79]] - v[91], x[idx[p53_103]] - v[107]) - AVG(x[idx[AKT_80]] - v[92], x[idx[IKK_a_b_100]] - v[104]), x[TSC2]));
			        break;
			    case Viral_Repl:
			    	x[Viral_Repl] = vir.compute(x, idx);
			        break;
			    case Virus:
			        x[Virus] = x[Virus];
			        break;
			    case RAPAMICINA:
			    	x[RAPAMICINA] = x[RAPAMICINA];
			        break;
			    case TNF_e:
			    	x[TNF_e] = x[TNF_e];
			        break;
			    case ANG_2_e:
			    	x[ANG_2_e] = x[ANG_2_e];
			        break;
			    case TLR_LIG:
			    	x[TLR_LIG] = x[TLR_LIG];
			    	break;
			    case IFN_e:
			    	x[IFN_e] = x[IFN_e]; //(rand.nextDouble() < 0.125 ? 1 : 0);
			        break;
			    case METFORMIN_e:
			    	x[METFORMIN_e] = x[METFORMIN_e];
			        break;
			    case PP242_e:
			    	x[PP242_e] = x[PP242_e];
			        break;
			    case Wortmannin:
			    	x[Wortmannin] = x[Wortmannin];
			        break;
			    case MK2206:
			    	x[MK2206] = x[MK2206];
			        break;
			    case PX478:
			    	x[PX478] = x[PX478];
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
		String str = "QN2 ";
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




