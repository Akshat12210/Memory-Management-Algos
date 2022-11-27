package OS;

public class NextFit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] process_size= {4,1,3,2};
		int[] block_size= {1,6,5,4};
		//size are in MBs
		
		/*
		 * Next Fit - it starts searching from the last block that is filled and again start from 0 if reach to end 
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
	// In this function we are using a greedy approach same as first fit
	/*
	* We are looping over the process array and for each process we are checking
	* if there is any block available to store that process as soon as we get any block we break the process
	* the search of block starts from the previous filled block and if we don't find any block than we start from 0
	*
	*/
	private static void variablePartitioning(int[] process_size, int[] block_size) {
		System.out.println("Variable Partitioning using First Fit");
		int prev=0;
		// TODO Auto-generated method stub
		for(int i=0;i<process_size.length;i++) {
			boolean val=false;
			for(int j=prev;j<block_size.length;j++) {
				if(process_size[i]<=block_size[j]) {
					System.out.println("P"+(i+1)+" is in block "+(j+1));
					block_size[j]-=process_size[i];
					prev=j;
					val=true;
					break;
				}
			}
			if(!val) {
				for(int j=0;j<prev;j++) {
					if(process_size[i]<=block_size[j]) {
						System.out.println("P"+(i+1)+" is in block "+(j+1));
						block_size[j]-=process_size[i];
						val=true;
						prev=j;
						break;
					}
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
		
		int prev=0;
		boolean[] blocksFilled=new boolean[process_size.length];
		for(int i=0;i<process_size.length;i++) {
			boolean val=false;
			for(int j=prev;j<block_size.length;j++) {
				if(!blocksFilled[j] && process_size[i]<=block_size[j]) {
					System.out.println("P"+(i+1)+" is in block "+(j+1));
					block_size[j]-=process_size[i];
					val=true;
					prev=j;
					blocksFilled[j]=true;
					break;
				}
			}
			if(!val) {
				for(int j=0;j<prev;j++) {
					if(!blocksFilled[j] && process_size[i]<=block_size[j]) {
						System.out.println("P"+(i+1)+" is in block "+(j+1));
						block_size[j]-=process_size[i];
						val=true;
						prev=j;
						break;
					}
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
