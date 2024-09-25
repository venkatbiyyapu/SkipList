import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class SkipListDriver {
	public static void main(String[] args) throws FileNotFoundException {
		Random r = new Random();
		int n = 0;
		Scanner sc;
		if (args.length > 0) {
			if (args[0].equals("n")) {
				n = Integer.parseInt(args[1]);
				sc = new Scanner(System.in);
			}
			else {
				File file = new File(args[0]);
				sc = new Scanner(file);
			}
		}
		else {
			sc = new Scanner(System.in);
		}
		String operation = "";
		long operand = 0;
		int modValue = 999983;
		long result = 0;
		Long returnValue = null;
		SkipList<Long> skipList = new SkipList<>();
		// Initialize the timer
		Timer timer = new Timer();
		timer.start();
		if (n == 0) {
			while (!((operation = sc.next()).equals("End"))) {
				switch (operation) {
					case "Add": {
						operand = sc.nextLong();
						if (skipList.add(operand)) {
							result = (result + 1) % modValue;
						}
						break;
					}
					case "Ceiling": {
						operand = sc.nextLong();
						returnValue = skipList.ceiling(operand);
						if (returnValue != null) {
							result = (result + returnValue) % modValue;
						}
						break;
					}
					case "First": {
						returnValue = skipList.first();
						if (returnValue != null) {
							result = (result + returnValue) % modValue;
						}
						break;
					}
					case "Get": {
						int intOperand = sc.nextInt();
						returnValue = skipList.get(intOperand);
						if (returnValue != null) {
							result = (result + returnValue) % modValue;
						}
						break;
					}
					case "Last": {
						returnValue = skipList.last();
						if (returnValue != null) {
							result = (result + returnValue) % modValue;
						}
						break;
					}
					case "Floor": {
						operand = sc.nextLong();
						returnValue = skipList.floor(operand);
						if (returnValue != null) {
							result = (result + returnValue) % modValue;
						}
						break;
					}
					case "Remove": {
						operand = sc.nextLong();
						if (skipList.remove(operand) != null) {

							result = (result + 1) % modValue;
						}
						break;
					}
					case "Contains": {
						operand = sc.nextLong();
						if (skipList.contains(operand)) {
							result = (result + 1) % modValue;
						}
						break;
					}

				}
			}
		}
		else {
			int i = 0;
			String[] array = {"Contains", "Add", "Remove"};
			while (i < n) {
				i++;
				int ran = r.nextInt(3);
				operation = array[ran];
				operand=r.nextInt(1,n+1);
				System.out.println(operation+" "+operand);
				switch (operation) {
					case "Add": {

						if (skipList.add(operand)) {
							result = (result + 1) % modValue;
						}
						break;
					}
					case "Contains": {
						if (skipList.contains(operand)) {
							result = (result + 1) % modValue;
						}
						break;
					}
					case "Remove": {
						if (skipList.remove(operand) != null) {

							result = (result + 1) % modValue;
						}
						break;
					}

				}
			}
		}
		timer.end();
		System.out.println(result);
		System.out.println(timer);
	}
}
