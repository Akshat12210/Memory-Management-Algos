package OS;

public class BestFit {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] process_size= {4,1,3,2};
		int[] block_size= {1,6,5,4};
		//size are in MBs
		/*
		 * Best Fit - Use the smallest hole that will fit the requested partition. 
		 */
		// Process are coming from P1 to P4
		fixedPartitioning(makeCopy(process_size),makeCopy(block_size));
		System.out.println("------------------------");
		variablePartitioning(process_size,block_size);
		
		
	}

	public static int[] makeCopy(int[] arr) {
		int[] newArr=new int[arr.length];
		for (int i = 0; i < newArr.length; i++) newArr[i]=arr[i];
		return newArr;
	}
	
	
	private static void variablePartitioning(int[] process_size, int[] block_size) {
		System.out.println("Variable Partitioning using Best Fit");
		//in this i have used a function which returns the index of the block which is a best fit
		for(int i=0;i<process_size.length;i++) {
			int index=findBestBlock(process_size[i],block_size);
			if(index==-1) {
				System.out.println("No space to store process P"+(i+1));
			}
			else {
				System.out.println("P"+(i+1)+" is in block "+(index+1));
				block_size[index]-=process_size[i];
			}
		}
		
		
		for (int i = 0; i < block_size.length; i++) {
			if(block_size[i]>0) {
				System.out.println("Holes present in Block "+(i+1)+" => "+block_size[i]);
			}
		}
	}

	/*
	 * In this function A loop runs overs the block array and checks the minimum difference of the process size and block size
	 * and returns that index if not found any it will return -1 
	 */
	private static int findBestBlock(int size, int[] block_size) {
		int min=Integer.MAX_VALUE;
		int index=-1;
		for (int i = 0; i < block_size.length; i++) {
			int diff=block_size[i]-size;
			
			if(diff>=0 && diff<min) {
				min=diff;
				index=i;
			}
		}
		return index;
	}

	private static void fixedPartitioning(int[] process_size, int[] block_size) {
		System.out.println("Fixed Size Partitioning using Best Fit");
		
		boolean[] blocksFilled=new boolean[process_size.length];
		for(int i=0;i<process_size.length;i++) {
			int index=findBestBlock(process_size[i],block_size,blocksFilled);
			if(index==-1) {
				System.out.println("No space to store process P"+(i+1));
			}
			else {
				System.out.println("P"+(i+1)+" is in block "+(index+1));
				blocksFilled[index]=true;
				block_size[index]-=process_size[i];
			}
		}
		
		
		for (int i = 0; i < block_size.length; i++) {
			if(block_size[i]>0) {
				System.out.println("Holes present in Block "+(i+1)+" => "+block_size[i]);
			}
		}
	}

	/*
	 * Same as the above findBestBlock function the only change is that it also takes blocksFilled array in account
	 * which tells us which block is available to use
	 */
	private static int findBestBlock(int size, int[] block_size, boolean[] blocksFilled) {
		int min=Integer.MAX_VALUE;
		int index=-1;
		for (int i = 0; i < block_size.length; i++) {
			if(!blocksFilled[i]) {
				int diff=block_size[i]-size;
				if((diff)>=0 && diff<min) {
					min=diff;
					index=i;
				}
			}
		}
		return index;
	}
}
