package OS;

public class FirstFit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] process_size= {4,1,3,2};
		int[] block_size= {1,6,5,4};
		//size are in MBs
		
		/*
		 * First Fit  - First block that can store process is selected
		 */
		
		// Process are coming from P1 to P4
		//Applying first fit algorithm when there is fixed partitioning
		fixedPartitioning(makeCopy(process_size),makeCopy(block_size));
		
		System.out.println("------------------------");
		//Applying first fit algorithm when there is variable partitioning	
		variablePartitioning(process_size,block_size);
		
		
	}
	
	//this function is used to create a copy of an array
	public static int[] makeCopy(int[] arr) {
		int[] newArr=new int[arr.length];
		for (int i = 0; i < newArr.length; i++) newArr[i]=arr[i];
		return newArr;
	}
	
	
	private static void variablePartitioning(int[] process_size, int[] block_size) {
		System.out.println("Variable Partitioning using First Fit");
		
		// In this function we are using a greedy approach
		/*
		 * We are looping over the process array and for each process we are checking
		 * if there is any block available to store that process as soon as we get any block we break the process
		 * 
		 * In variable partitioning we will also check the holes also
		 */
		for(int i=0;i<process_size.length;i++) {
			boolean val=false;
			for(int j=0;j<block_size.length;j++) {
				if(process_size[i]<=block_size[j]) {
					System.out.println("P"+(i+1)+" is in block "+(j+1));
					block_size[j]-=process_size[i];
					val=true;
					break;
				}
			}
			if(!val) {
				System.out.println("No space to store process P"+(i+1));
			}
		}
		
		
		for (int i = 0; i < block_size.length; i++) {
			if(block_size[i]>0) {
				System.out.println("Holes present in Block "+(i+1)+" => "+block_size[i]);
			}
		}
	}

	private static void fixedPartitioning(int[] process_size, int[] block_size) {
		System.out.println("Fixed Size Partitioning using First Fit");
		 
		/*
		 * We are looping over the process array and for each process we are checking
		 * if there is any block available to store that process as soon as we get any block we break the process
		 * 
		 * In fixed partitioning if any block is partially filled we will not consider that block again
		 * that's why we are using a boolean array (blocksFilled) to store that information
		 * 
		 */
		boolean[] blocksFilled=new boolean[process_size.length];
		for(int i=0;i<process_size.length;i++) {
			boolean val=false;
			for(int j=0;j<block_size.length;j++) {
				if(!blocksFilled[j] && process_size[i]<=block_size[j]) {
					System.out.println("P"+(i+1)+" is in block "+(j+1));
					block_size[j]-=process_size[i];
					val=true;
					blocksFilled[j]=true;
					break;
				}
			}
			if(!val) {
				System.out.println("No space to store process P"+(i+1));
			}
		}
		
		
		for (int i = 0; i < block_size.length; i++) {
			if(block_size[i]>0) {
				System.out.println("Holes present in Block "+(i+1)+" => "+block_size[i]);
			}
		}
	}

}
