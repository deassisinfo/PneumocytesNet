import java.io.PrintWriter;
import java.util.List;

public class Utils {
	
	public static final int TT = 0;
	public static final int TP = 1;
	public static final int TN = 2;
	public static final int FP = 3;
	public static final int FN = 4;
	
	public static int hamming(int[] a1, int[] a2) {
		if(a1.length != a2.length) {
			System.err.println("Arrays size differ for hamming distance!");
			System.exit(1);
		}
		
		int d = 0;
		for(int i = 0; i < a1.length; i++)
			if(a1[i] != a2[i])d++;
		
		return d;
	}
	
	public static double euclid(double[] a1, double[] a2) {
		if(a1.length != a2.length) {
			System.err.println("Arrays size differ for euclidian distance!");
			System.exit(1);
		}
		
		double d = 0;
		for(int i = 0; i < a1.length; i++)
			d += (a1[i] - a2[i])*(a1[i] - a2[i]);
		
		return Math.sqrt(d);
	}
	
	public static void add(double[][] result, int[][] array) {
		for(int i = 0; i < result.length; i++)
			for(int j = 0; j < result[i].length; j++)
				result[i][j] += array[i][j];
	}
	
	public static void add(double[][] result, double[][] array) {
		for(int i = 0; i < result.length; i++)
			for(int j = 0; j < result[i].length; j++)
				result[i][j] += array[i][j];
	}
	
	public static void div(double[] array, double d) {
		for(int i = 0; i < array.length; i++)
			array[i] = array[i]/d;
	}
	
	public static void div(double[][] array, double d) {
		for(int i = 0; i < array.length; i++)
			for(int j = 0; j < array[i].length; j++)
				array[i][j] = array[i][j]/d;
	}
	
	public static double[][] div(double[][] a1, double[][] a2, double eps) {
		double[][] d = new double[a1.length][a1[0].length];
		for(int i = 0; i < a1.length; i++)
			for(int j = 0; j < a1[i].length; j++)
				d[i][j] = a1[i][j]/(((double)a2[i][j] + eps));
		return d;
	}
	
	public static double[] sub(double[] a1, double[] a2) {
		for(int i = 0; i < a1.length; i++)
			a2[i] -= a1[i];
		return a2;
	}
	
	public static double[][] sub(double[][] a1, double[][] a2) {
		for(int i = 0; i < a1.length; i++)
			for(int j = 0; j < a1[i].length; j++)
				a2[i][j] -= a1[i][j];
		return a2;
	}
	
	public static double[] average(double[][] array, int cols, int lines) {
		double[] avgs = new double[cols];
		
		for(int j = 0; j < cols; j++) 
			for(int i = 0; i < lines; i++) 
				avgs[j] += array[i][j]/((double)lines);
		
		return avgs;
	}
	
	public static double[] var(double[][] array, double[] avgs, int cols, int lines) {
		double[] stds = new double[cols];
		
		for(int j = 0; j < cols; j++) 
			for(int i = 0; i < lines; i++) 
				stds[j] += ((avgs[j] - array[i][j]) * (avgs[j] - array[i][j]))/((double)lines);
		
		return stds;
	}
	
	public static int[] test(double[] avgs, double[] vars, double n, double sigma) {
		if(avgs.length != vars.length) {
			System.err.println("avgs and stds have different lengths.");
			System.exit(0);
		}
		int[] array = new int[avgs.length];
		for(int i = 0; i < avgs.length; i++)
			array[i] = avgs[i] - (sigma * Math.sqrt(vars[i]/n)) > 0 ? 1 : 0; //That is correct. Is either positive of not! There is no need to test for negative avg.
		
		return array;
	}
	
	public static void count(int[] counts, int[] simulation, int[] literature, int[] litIdx) {
		for(int i = 0; i < literature.length; i++) {
			counts[TT]++;
            if(simulation[litIdx[i]] == 1 && literature[i] == 1) {
            	counts[TP]++;
            	//System.out.println(Indexes.NAMES[litIdx[i]] + " " +  simulation[litIdx[i]] + " " +  literature[i]);
            }else if (simulation[litIdx[i]] == 0 && literature[i] == 0)
            	counts[TN]++;
            else if(simulation[litIdx[i]] == 1 && literature[i] == 0)
            	counts[FP]++;
            else if(simulation[litIdx[i]] == 0 && literature[i] == 1) 
            	counts[FN]++;
        }
	}
	
	public static void print(double[] array, PrintWriter pw) {
		int i = 0;
		for(double d : array) {
			if(++i == array.length)
				pw.println(d);
			else
				pw.print(d + ", ");
		}
	}
	
	public static void print(double[] array) {
		int i = 0;
		for(double d : array) {
			if(++i == array.length)
				System.out.println(d);
			else
				System.out.print(d + ", ");
		}
	}
	
	public static void printR(double[] array, String ch) {
		int i = 1;
		for(double d : array) {
			if(i == array.length)
				System.out.println(d + ")" + ch);
			else if(i == 1)
				System.out.print("c(" + d + ", ");
			else
				System.out.print(d + ", ");
			i++;
		}
	}
	
	public static String toString(double[] array) {
		int i = 0;
		String str = "";
		for(double d : array) {
			if(++i == array.length)
				str += d;
			else
				str += d + ", ";
		}
		return str;
	}
	
	public static String toString(int[] array) {
		int i = 0;
		String str = "";
		for(int d : array) {
			if(++i == array.length)
				str += d;
			else
				str += d + ", ";
		}
		return str;
	}
	
	public static void printR(double[] array) {
		printR(array, "");
	}
	
	public static void print(double[][] array) {
		for(double[] arr : array)
			print(arr);
	}
	
	public static void printR(double[][] array) {
		System.out.println("rbind(");
		int i = 1;
		for(double[] arr : array) {
			if(i == array.length)
				printR(arr);
			else
				printR(arr, ",");
			i++;
		}
		System.out.println(")");
	}
	
	
	public static void print(int[] array) {
		int i = 0;
		for(int d : array) {
			if(++i == array.length)
				System.out.println(d);
			else
				System.out.print(d + ", ");
		}
	}
	
	public static void print(List<double[]> array, PrintWriter pw) {
		for(double[] arr : array) 
			print(arr, pw);
	}
	
	public static void print(List<double[]> array) {
		for(double[] arr : array) 
			print(arr);
	}
	
	
	
	public static void print(String[] array) {
		int i = 0;
		for(String d : array) {
			if(++i == array.length)
				System.out.println("\"" + d + "\"");
			else
				System.out.print("\"" + d + "\", ");
		}
	}
	
	public static boolean testInterestNodes(double[] avg, double[] var, double s, double eps) {
		double[] intAvg = {avg[QN.IFN_a_b], avg[QN.IL6], avg[QN.Viral_Repl], avg[QN.Apoptosis]};
		double[] intVar = {var[QN.IFN_a_b], var[QN.IL6], var[QN.Viral_Repl], var[QN.Apoptosis]};
		
		for(int i = 0; i < 4; i++) 
			if(((intAvg[i] > 1 && (intAvg[i] - s*Math.sqrt(intVar[i])) > 1) || (intAvg[i] < 1 && (intAvg[i] + s*Math.sqrt(intVar[i])) < 1)) && (Math.abs(intAvg[i] - 1.0) >  eps))
				return true;
		return false;
	}
	
}
