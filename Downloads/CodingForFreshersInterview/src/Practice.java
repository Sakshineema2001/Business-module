import java.util.HashSet;
import java.util.Set;

public class Practice
{
	public static void main(String[] args)
	{
		String str = "abcd";
		System.out.println(isIsogram("sakhi"));
		System.out.println(fibonacci(12));
		System.out.println(swap("123455",2,3));
		permute(str,0,str.length()-1);
		reverse("sakshi");
		System.out.println(reverseByLoop("sakshi"));
	}


	// check if string is isogram or not  means not having any duplicate character
	public static Boolean isIsogram(String s){
		boolean isogram = true;
		char[] ch = s.toCharArray();
		Set<Character> chrSet = new HashSet<Character>();
		for(Character c : ch){
			if(chrSet.contains(c)){
				isogram = false;
			}else{
				chrSet.add(c);
			}
		}
		return isogram;
	}

	// check is it is fibonacci
	public static int fibonacci(int n){
		if(n <= 1){
			return n;
		}
		else {
			return fibonacci(n-1)+fibonacci(n-2)+fibonacci(n-3);
		}
	}

	//check string is permutate means each possible way to arrange ex -puzzle
	public static void  permute(String str,int left , int right){
		if(left == right){
			System.out.println(str);
		}else {
			for(int i =left; i<=right; i++){
				str = swap(str,left,i);
				permute(str,left+1,right);
				str = swap(str,left,i);
			}
		}
	}

	//swap on specific position of numbers
	public static String swap(String str, int i, int j){
		char[] ch = str.toCharArray();
		char temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;
		return String.valueOf(ch);
	}

	//reverse using function of string buffer and builder
	public static void reverse(String str){
		StringBuffer stringBuffer = new StringBuffer(str);
		System.out.println(stringBuffer.reverse());
	}

	//reverse String by loop
	public static String reverseByLoop(String str){
       StringBuilder stringBuilder = new StringBuilder();
		for(int i=str.length()-1;i>=0;i--){
          stringBuilder.append(str.charAt(i));
		}
		return stringBuilder.toString();
	}

	//swap two numbers without using 3rd variable

	public static void swapTwo(int a,int b){
		System.out.println("numbers before swaping" + "=" + a + "," + b);
		a = a+b;
		b = a-b;
		a = a-b;
		System.out.println("numbers after swaping" + "=" + a + "," + b);
	}

	//swap two numbers with using 3rd variable
	public static void swaping(int a,int b){
		System.out.println("numbers before swaping" + "=" + a + "," + b);
		int c;
		c = b;
		b = a;
		a = c;
		System.out.println("numbers after swaping" + "=" + a + "," + b);
	}

	//How many of the following numbers are divisible by 3 but not by 9. 4320, 2343, 3474, 4131, 5286, 5340, 6336, 7347, 8115, 9276
	public static  void numbers(){
		int numbers[] ={4320, 2343, 3474, 4131, 5286, 5340, 6336, 7347, 8115, 9276};
		int count =0;
		for(int num: numbers){
			if(num%3 == 0 && num%9 !=0){
				System.out.println(num);
				count++;
			}
		}
		System.out.println(count);
	}
}
