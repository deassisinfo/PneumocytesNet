
public abstract class Indexes {

	public static final int ACE2 = 0;
	public static final int ADAM_17 = 1;
	public static final int AKT = 2;
	public static final int AMPK = 3;
	public static final int ANG_2 = 4;
	public static final int ANG_2_T1R = 5;
	public static final int Apoptosis = 6;
	public static final int BCL_2 = 7;
	public static final int CASP8 = 8;
	public static final int CASP9 = 9;
	public static final int C_FLIP = 10;
	public static final int CREB_1 = 11;
	public static final int FADD = 12;
	public static final int FOXO3A = 13;
	public static final int HIF_1a = 14;
	public static final int Hypoxia = 15;
	public static final int IFN_a_b = 16;
	public static final int IFNR = 17;
	public static final int IL6 = 18;
	public static final int IL6R = 19;
	public static final int IRF3 = 20;
	public static final int IKK_a_b = 21;
	public static final int ISG = 22;
	public static final int MAPK_p38 = 23;
	public static final int mTORC1 = 24;
	public static final int mTORC2 = 25;
	public static final int NFKB = 26;
	public static final int Nutr_Depr = 27;
	public static final int p53 = 28;
	public static final int PI3K = 29;
	public static final int PTEN = 30;
	public static final int RIG1 = 31;
	public static final int ROS = 32;
	public static final int SIL6R = 33;
	public static final int STAT1 = 34;
	public static final int TLR4 = 35;
	public static final int TNF = 36;
	public static final int TNFR = 37;
	public static final int TSC2 = 38;
	public static final int Viral_Repl = 39;
	public static final int Virus = 40;
	public static final int RAPAMICINA = 41;
	public static final int TNF_e = 42;
	public static final int ANG_2_e = 43;
	public static final int TLR_LIG = 44;
	public static final int IFN_e = 45;
	public static final int METFORMIN_e = 46;
	public static final int PP242_e = 47;
	public static final int Wortmannin = 48;
	public static final int MK2206 = 49;
	public static final int PX478 = 50;
	public static final int IL6_e = 51;
	
	public static final int Virus_i = 41;
	
	
	
	
	
	public static final int VIRUS = 0;
	public static final int Inflammation = 1;
	public static final int Stress = 2;
	public static final int PI3K_AKT = 3;
	public static final int mTOR = 4;
	public static final int REPLICATION = 5;
	public static final int IFN_R = 6;
	public static final int IFN_SG = 7;
	public static final int RIG = 8;
	public static final int IFN_I = 9;
	public static final int SIROLIMUS = 10;
	
	
	public static final int[] REDUCED_NET = {
			VIRUS, Inflammation, Stress, PI3K_AKT, mTOR, 
			REPLICATION, IFN_R, IFN_SG, RIG, IFN_I, 
			SIROLIMUS
			
	};
	
	
	
	
	
	
	
	private static final int[] IDX = new int[] { //5 per line
			Virus, ACE2, ANG_2_T1R, mTORC2, PI3K, 
			IKK_a_b, PTEN, Nutr_Depr, p53, AKT, 
			CASP8, CASP9, NFKB, CREB_1, p53, 
			FADD, p53, C_FLIP, FOXO3A, p53, 
			ROS, BCL_2, NFKB, p53, AKT, //
			AKT, Viral_Repl, TNFR, MAPK_p38, AMPK, //
			IKK_a_b, AKT, ROS, Hypoxia, IRF3, //
			FOXO3A, mTORC1, Viral_Repl, IFN_a_b, NFKB, //
			MAPK_p38, mTORC1, RIG1, TLR4, TNFR, //
			AKT, STAT1, Viral_Repl, ANG_2_T1R, TLR4, //
			ROS, IKK_a_b, TSC2, PI3K, AMPK, //
			IKK_a_b, IKK_a_b, ROS, FOXO3A, Hypoxia, //
			AMPK, ROS, Viral_Repl, TLR4, ROS, //
			IL6R, p53, ANG_2_T1R, TLR4, FOXO3A, //
			p53, ADAM_17, IL6R, IFNR, ADAM_17, //
			NFKB, MAPK_p38, mTORC1, AMPK, HIF_1a, //
			AKT, mTORC1, ISG, ADAM_17, Viral_Repl, //
			Virus, TNF, Virus, IL6, ANG_2, //
			IFN_a_b, mTORC1, 
			
			TNFR, IKK_a_b, IL6R, AKT, AKT, 
			AKT, AKT, IKK_a_b, IKK_a_b, AMPK,
			AMPK, p53, IKK_a_b,
			
			NFKB, MAPK_p38, TLR4, Viral_Repl, Viral_Repl
	};
	
	private static final int[] IDXFit = new int[] { //5 per line
			Virus, ACE2, ANG_2_T1R, mTORC2, PI3K, 
			IKK_a_b, PTEN, Nutr_Depr, p53, AKT, 
			CASP8, CASP9, NFKB, CREB_1, p53, 
			FADD,Virus, C_FLIP, FOXO3A, mTORC1, 
			ROS, BCL_2, NFKB, p53, AKT, 
			AKT, Viral_Repl, TNFR, MAPK_p38, AMPK, 
			IKK_a_b, AKT, ROS, Hypoxia, IRF3, 
			FOXO3A, mTORC1, Viral_Repl, IFN_a_b, NFKB, 
			MAPK_p38, mTORC1, RIG1, TLR4, TNFR, 
			AKT, STAT1, Viral_Repl, ANG_2_T1R, TLR4, 
			ROS, IKK_a_b, TSC2, PI3K, AMPK, 
			IKK_a_b, IKK_a_b, ROS, FOXO3A, p53, 
			AMPK, TNFR, Viral_Repl, TLR4, ROS, 
			IL6R, p53, ANG_2_T1R, TLR4, FOXO3A, 
			p53, ADAM_17, IL6R, IFNR, ADAM_17, 
			NFKB, MAPK_p38, mTORC1, AMPK, HIF_1a, 
			AKT, mTORC1, ISG, ADAM_17, Viral_Repl, 
			Virus, TNF, Virus, IL6, ANG_2, IFN_a_b,
			Hypoxia, mTORC1
	};
	
	
	private static final int[] IDXFit2 = new int[] { //5 per line
			Virus, ACE2, ANG_2_T1R, mTORC2, PI3K, 
			IKK_a_b, PTEN, Nutr_Depr, p53, AKT, 
			CASP8, CASP9, NFKB, CREB_1, p53, 
			FADD,Virus, C_FLIP, FOXO3A, mTORC1, 
			ROS, BCL_2, NFKB, p53, AKT, 
			AKT, Viral_Repl, TNFR, MAPK_p38, AMPK, 
			IKK_a_b, AKT, ROS, Hypoxia, IRF3, 
			FOXO3A, mTORC1, Viral_Repl, IFN_a_b, NFKB, 
			MAPK_p38, mTORC1, RIG1, TLR4, TNFR, 
			AKT, STAT1, Viral_Repl, ANG_2_T1R, TLR4, 
			ROS, IKK_a_b, TSC2, PI3K, AMPK, 
			IKK_a_b, IKK_a_b, ROS, FOXO3A, p53, 
			AMPK, TNFR, Viral_Repl, TLR4, ROS, 
			IL6R, p53, ANG_2_T1R, TLR4, FOXO3A, 
			p53, ADAM_17, IL6R, IFNR, ADAM_17, 
			NFKB, MAPK_p38, mTORC1, AMPK, HIF_1a, 
			AKT, mTORC1, ISG, ADAM_17, Viral_Repl, 
			Virus, TNF, Virus, IL6, ANG_2, 
			IFN_a_b, Hypoxia, mTORC1,
			
			TNFR, IKK_a_b, IL6R, AKT, AKT, 
			AKT, AKT, IKK_a_b, IKK_a_b, AMPK,
			AMPK, p53, IKK_a_b
	};
	
	public static int[] getIndex(boolean fit) {
		if(fit)return IDXFit2.clone();
		return IDX.clone();
	}
	
	
	/*public static final int[] IDXFit = new int[] { //5 per line
			Virus, ACE2, ANG_2_T1R, _VirusYYY_, mTORC2, PI3K, 
			IKK_a_b, PTEN, Nutr_Depr, p53, _mTORC1YYY_, AKT, 
			CASP8, CASP9, _p53YYY_, NFKB, CREB_1, p53, 
			FADD,VirusYYY_XXX, C_FLIP, FOXO3A, mTORC1YYY_XXX, 
			ROS, BCL_2, NFKB, p53, AKT, 
			AKT, Viral_Repl, TNFR, MAPK_p38, AMPK, 
			IKK_a_b, AKT, ROS, Hypoxia, IRF3, 
			FOXO3A, mTORC1, Viral_Repl, IFN_a_b, NFKB, 
			MAPK_p38, mTORC1, RIG1, TLR4, TNFR, 
			AKT, STAT1, Viral_Repl, ANG_2_T1R, TLR4, 
			ROS, IKK_a_b, TSC2, PI3K, AMPK, 
			IKK_a_b, IKK_a_b, ROS, FOXO3A, p53YYY_XXX, 
			AMPK, TNFRYYY_XXX, _TNFRYYY_, Viral_Repl, TLR4, ROS, 
			IL6R, p53, ANG_2_T1R, TLR4, _HypoxiaYYY_, FOXO3A, 
			p53, ADAM_17, IL6R, IFNR, ADAM_17, 
			NFKB, MAPK_p38, mTORC1, AMPK, HIF_1a, 
			AKT, mTORC1, ISG, ADAM_17, Viral_Repl, 
			Virus, TNF, Virus, IL6, ANG_2, IFN_a_b,
			HypoxiaYYY
	};*/
	
	
	public static final int[] NET = {
			ACE2, ADAM_17, AKT, AMPK, ANG_2, 
			ANG_2_T1R, Apoptosis, BCL_2, CASP8, CASP9, 
			C_FLIP, CREB_1, FADD, FOXO3A, HIF_1a, 
			Hypoxia, IFN_a_b, IFNR, IL6, IL6R, 
			IRF3, IKK_a_b, ISG, MAPK_p38, mTORC1, 
			mTORC2, NFKB, Nutr_Depr, p53, PI3K, 
			PTEN, RIG1, ROS, SIL6R, STAT1, 
			TLR4, TNF, TNFR, TSC2, Viral_Repl, 
			Virus, RAPAMICINA, TNF_e, ANG_2_e, TLR_LIG, 
			IFN_e
			
	};
	
	public static final String[] NAMES = {
			"ACE2", "ADAM_17", "AKT", "AMPK", "ANG_2", 
			"ANG_2_T1R", "Apoptosis", "BCL_2", "CASP8", "CASP9", 
			"C_FLIP", "CREB_1", "FADD", "FOXO3A", "HIF_1a", 
			"Hypoxia", "IFN_a_b", "IFNR", "IL6", "IL6R", 
			"IRF3", "IKK_a_b", "ISG", "MAPK_p38", "mTORC1", 
			"mTORC2", "NFKB", "Nutr_Depr", "p53", "PI3K", 
			"PTEN", "RIG1", "ROS", "SIL6R", "STAT1", 
			"TLR4", "TNF", "TNFR", "TSC2", "Viral_Repl", 
			"Virus", "RAPAMICINA", "TNF_e", "ANG_2_e", "TLR_LIG",
			"IFN_e", "METFORMIN_e", "PP242_e", "Wortmannin", "MK2206",
			"PX478", "IL6_e"
	};
	
	public static final int Virus_0 = 0;
	public static final int ACE2_1 = 1;
	public static final int ANG_2_T1R_2 = 2;
	public static final int mTORC2_3 = 3;
	public static final int PI3K_4 = 4;
	public static final int IKK_a_b_5 = 5;
	public static final int PTEN_6 = 6;
	public static final int Nutr_Depr_7 = 7;
	public static final int p53_8 = 8;
	public static final int AKT_9 = 9;
	public static final int CASP8_10 = 10;
	public static final int CASP9_11 = 11;
	public static final int NFKB_12 = 12;
	public static final int CREB_1_13 = 13;
	public static final int p53_14 = 14;
	public static final int FADD_15 = 15;
	public static final int p53_16 = 16;
	public static final int C_FLIP_17 = 17;
	public static final int FOXO3A_18 = 18;
	public static final int p53_19 = 19;
	public static final int ROS_20 = 20;
	public static final int BCL_2_21 = 21;
	public static final int NFKB_22 = 22;
	public static final int p53_23 = 23;
	public static final int AKT_24 = 24;
	public static final int AKT_25 = 25;
	public static final int Viral_Repl_26 = 26;
	public static final int TNFR_27 = 27;
	public static final int MAPK_p38_28 = 28;
	public static final int AMPK_29 = 29;
	public static final int IKK_a_b_30 = 30;
	public static final int AKT_31 = 31;
	public static final int ROS_32 = 32;
	public static final int Hypoxia_33 = 33;
	public static final int IRF3_34 = 34;
	public static final int FOXO3A_35 = 35;
	public static final int mTORC1_36 = 36;
	public static final int Viral_Repl_37 = 37;
	public static final int IFN_a_b_38 = 38;
	public static final int NFKB_39 = 39;
	public static final int MAPK_p38_40 = 40;
	public static final int mTORC1_41 = 41;
	public static final int RIG1_42 = 42;
	public static final int TLR4_43 = 43;
	public static final int TNFR_44 = 44;
	public static final int AKT_45 = 45;
	public static final int STAT1_46 = 46;
	public static final int Viral_Repl_47 = 47;
	public static final int ANG_2_T1R_48 = 48;
	public static final int TLR4_49 = 49;
	public static final int ROS_50 = 50;
	public static final int IKK_a_b_51 = 51;
	public static final int TSC2_52 = 52;
	public static final int PI3K_53 = 53;
	public static final int AMPK_54 = 54;
	public static final int IKK_a_b_55 = 55;
	public static final int IKK_a_b_56 = 56;
	public static final int ROS_57 = 57;
	public static final int FOXO3A_58 = 58;
	public static final int Hypoxia_59 = 59;
	public static final int AMPK_60 = 60;
	public static final int ROS_61 = 61;
	public static final int Viral_Repl_62 = 62;
	public static final int TLR4_63 = 63;
	public static final int ROS_64 = 64;
	public static final int IL6R_65 = 65;
	public static final int p53_66 = 66;
	public static final int ANG_2_T1R_67 = 67;
	public static final int TLR4_68 = 68;
	public static final int FOXO3A_69 = 69;
	public static final int p53_70 = 70;
	public static final int ADAM_17_71 = 71;
	public static final int IL6R_72 = 72;
	public static final int IFNR_73 = 73;
	public static final int ADAM_17_74 = 74;
	public static final int NFKB_75 = 75;
	public static final int MAPK_p38_76 = 76;
	public static final int mTORC1_77 = 77;
	public static final int AMPK_78 = 78;
	public static final int HIF_1a_79 = 79;
	public static final int AKT_80 = 80;
	public static final int mTORC1_81 = 81;
	public static final int ISG_82 = 82;
	public static final int ADAM_17_83 = 83;
	public static final int Viral_Repl_84 = 84;
	public static final int Virus_85 = 85;
	public static final int TNF_86 = 86;
	public static final int Virus_87 = 87;
	public static final int IL6_88 = 88;
	public static final int ANG_2_89 = 89;
	public static final int IFN_a_b_90 = 90;
	public static final int mTORC1_91 = 91;
	
	public static final int Virus_16 = 16;
	public static final int mTORC1_19 = 19;
	public static final int p53_59 = 59;
	public static final int TNFR_61 = 61;
	public static final int Hypoxia_90 = 90;
	
	public static final int TNFR_92 = 92;
	public static final int IKK_a_b_93 = 93;
	public static final int IL6R_94 = 94;
	public static final int AKT_95 = 95;
	public static final int AKT_96 = 96;
	public static final int AKT_97 = 97;
	public static final int AKT_98 = 98;
	public static final int IKK_a_b_99 = 99;
	public static final int IKK_a_b_100 = 100;
	public static final int AMPK_101 = 101;
	public static final int AMPK_102 = 102;
	public static final int p53_103 = 103;
	public static final int IKK_a_b_104 = 104;
	
	public static final int NFKB_105 = 105;
	public static final int MAPK_p38_106 = 106;
	public static final int TLR4_107 = 107;
	public static final int Viral_Repl_108 = 108;
	public static final int Viral_Repl_109 = 109;
	
	
	
	public static final int EXP_DATA_SIZE = 48;
	
	public static final int[] TMP_IDX = new int[] {
			CASP8, CASP9, IFN_a_b, IL6, ISG, 
			ISG, IRF3, IRF3, MAPK_p38, STAT1, 
			STAT1, RIG1, TNF, IRF3, HIF_1a, 
			NFKB, NFKB, ADAM_17,
			IL6, TNF, IL6, IL6, NFKB, 
			NFKB, STAT1, TNF, NFKB, ADAM_17, 
			ACE2, AKT
	};
	
	public static final int[] TMP_RES = new int[] {
			1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1,
            1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1
	};
	
	
	public static final int[] EXP1_IDX = new int[] {
			CASP8, CASP9, IFN_a_b, IL6, ISG, 
			ISG, IRF3, IRF3, MAPK_p38, STAT1, 
			STAT1, RIG1, TNF, IRF3, HIF_1a, 
			NFKB, NFKB, ADAM_17
	};
	
	/*, IL6, TNF, 
	IL6, IL6, NFKB, NFKB, STAT1, 
	TNF, NFKB, ADAM_17, ACE2, AKT*/
	
	public static final int[] EXP1_RES = new int[] {
			1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1
	};
	
	public static final int[] EXP2_IDX = new int[] {
			IL6, TNF, IL6, IL6, NFKB, 
			NFKB, STAT1, TNF, NFKB, ADAM_17, 
			ACE2, AKT
	};
	
	public static final int[] EXP2_RES = new int[] {
			1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1
	};
	
	public static final int[] EXP3_IDX = new int[] {
			HIF_1a, mTORC1, p53, MAPK_p38, BCL_2
	};
	
	public static final int[] EXP3_RES = new int[] {1, 0, 0, 1, 0};
	
	public static final int[] EXP4_IDX = new int[] {
			AMPK, AKT, AMPK, IL6, mTORC1, FOXO3A
	};
	
	public static final int[] EXP4_RES = new int[] {1, 0, 0, 0, 0, 1};
	
	public static final int[] EXP5_IDX = new int[] {IL6};
	
	public static final int[] EXP5_RES = new int[] {1};
	
	public static final int[] EXP6_IDX = new int[] {
			C_FLIP, CASP9, CASP8, Apoptosis, IL6,
			NFKB, p53, BCL_2
	};
	
	public static final int[] EXP6_RES = new int[] {1, 0, 0, 1, 1, 1, 1, 0};
	
	public static final int[] EXP7_IDX = new int[] {
			Apoptosis, ACE2, ADAM_17, NFKB, TNF, IL6
	};
	
	public static final int[] EXP7_RES = new int[] {1, 0, 1, 1, 1, 1};
	
	
	
	public static final int[] EXP8_IDX = new int[] {
			p53, AKT, Apoptosis, AKT, mTORC1,
			FOXO3A, AMPK, mTORC1
	};
	
	public static final int[] EXP8_RES = new int[] {0, 1, 1, 0, 0, 1, 1, 0};
	
	
	
	
	
	
	
	
	public static final int[] EXP1_IDX_UB = new int[] {
			CASP8, CASP9, IFN_a_b, IL6,
			ISG, IRF3, MAPK_p38, STAT1, 
			RIG1, TNF, HIF_1a, 
			NFKB, ADAM_17
	};	
	
	public static final int[] EXP1_RES_UB = new int[] {
			1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1,
            1, 1 
	};
	
	public static final int[] EXP2_IDX_UB = new int[] {
			IL6, TNF, IL6, NFKB, 
			STAT1, TNF, ADAM_17, 
			ACE2, AKT
	};
	
	public static final int[] EXP2_RES_UB = new int[] {
			1, 1, 1, 1, 0, 0, 1, 0, 1
	};
	
	public static final int[] EXP3_IDX_UB = new int[] {
			HIF_1a, mTORC1, p53, MAPK_p38, BCL_2
	};
	
	public static final int[] EXP3_RES_UB = new int[] {1, 0, 0, 1, 0};
	
	public static final int[] EXP4_IDX_UB = new int[] {
			AMPK, AKT, AMPK, IL6, mTORC1, FOXO3A
	};
	
	public static final int[] EXP4_RES_UB = new int[] {1, 0, 0, 0, 0, 1};
	
	public static final int[] EXP5_IDX_UB = new int[] {IL6};
	
	public static final int[] EXP5_RES_UB = new int[] {1};
	
	public static final int[] EXP6_IDX_UB = new int[] {
			C_FLIP, CASP9, CASP8, Apoptosis, IL6,
			NFKB, p53, BCL_2
	};
	
	public static final int[] EXP6_RES_UB = new int[] {1, 0, 0, 1, 1, 1, 1, 0};
	
	public static final int[] EXP7_IDX_UB = new int[] {
			Apoptosis, ACE2, ADAM_17, NFKB, TNF, IL6
	};
	
	public static final int[] EXP7_RES_UB = new int[] {1, 0, 1, 1, 1, 1};
	
	
	
	public static final int[] EXP8_IDX_UB = new int[] {
			p53, AKT, Apoptosis, AKT, mTORC1,
			FOXO3A, AMPK, mTORC1
	};
	
	public static final int[] EXP8_RES_UB = new int[] {0, 1, 1, 0, 0, 1, 1, 0};
	
}


	
	



