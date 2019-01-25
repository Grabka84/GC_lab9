package gc_lab9;
import java.text.DecimalFormat; // to decimal format all double outputs
import java.util.ArrayList; // import arrays
import java.util.HashMap; // import maps
import java.util.List; // import lists
import java.util.Map;
import java.util.Scanner;

public class ShoppingList {
	// build specifications ask to use parallel ArrayLists to store the items ordered and prices
	// keeping the lists outside of the main method as I want to populate them within a method, then use in another
	static List<String> cartNames = new ArrayList<>(); // list to keep track of user items ordered
	static List<Double> cartPrices = new ArrayList<>(); // list to keep track of user prices of items ordered
	//static List<Integer> cartQuant = new ArrayList<>(); // list to keep track of quantities of item ordered - didn't end up using it though :(
	static double subTotal = 0; // subtotal for cart, set at 0 at beginning
	// build specifications ask to use a hashtable to keep track of the menu of items
	// using a map to generate price list and display it to the user
	static Map<String, Double> priceList = generatePriceList();
	static DecimalFormat df2 = new DecimalFormat("#.##"); // decimal format for doubles
	
	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
				
		displayHeader();
		
		String userContinue = ""; // would the user like to continue shopping? exit from the do loop if anything with a y
		
		do {
			displayMenu(priceList); // display the menu, doesn't actually return anything here
			shoppingCart(priceList, scnr); // go through the shopping process, will populate the lists in the method, but does nothing with it yet
			System.out.print("Would you like to order anything else (press \"y\" to continue or any other key to end) ? ");
			userContinue = scnr.nextLine();
			System.out.println();
		} while (userContinue.equalsIgnoreCase("y")||userContinue.startsWith("y")||userContinue.startsWith("Y"));
		
		displayCart();
	}
	
	// create a map of items, 8 keys and values
	public static Map<String, Double> generatePriceList(){
		Map<String, Double> priceList = new HashMap<>();
		priceList.put("apples", 0.99);
		priceList.put("bananas", 0.59);
		priceList.put("cream", 5.99);
		priceList.put("bread", 2.99);
		priceList.put("milk", 4.99);
		priceList.put("eggs", 3.29);
		priceList.put("coffee", 9.99);
		priceList.put("butter", 3.59);
		return priceList;
	}
	
	// header 
	public static void displayHeader() {
		System.out.println("Welcome to Krzysztof's Market!");
		System.out.println("We have the freshest prices.");
		System.out.println("==============================");
		System.out.println();
	}
	
	// show the menu, formatted with tabs to make sure the outputs align
	public static void displayMenu(Map<String, Double> priceList) {
		System.out.println("Item #\tItem Name\tPrice\t");
		System.out.println("==============================");
		System.out.println("1.\tapples\t"+"\t$"+priceList.get("apples"));
		System.out.println("2.\tbananas\t"+"\t$"+priceList.get("bananas"));
		System.out.println("3.\tcream\t"+"\t$"+priceList.get("cream"));
		System.out.println("4.\tbread\t"+"\t$"+priceList.get("bread"));
		System.out.println("5.\tmilk\t"+"\t$"+priceList.get("milk"));
		System.out.println("6.\teggs\t"+"\t$"+priceList.get("eggs"));
		System.out.println("7.\tcoffee\t"+"\t$"+priceList.get("coffee"));
		System.out.println("8.\tbutter\t"+"\t$"+priceList.get("butter"));
		System.out.println("==============================");
	}
	
	// if user enters 1-8, adds the string to the cartNames list and 
	// the price to the cartPrices list in the same index
	public static void shoppingCart(Map<String, Double> priceList, Scanner scnr){
		boolean isValid = false;
		String orderNum;
	// do while loop to get a valid input 
		do {
			System.out.print("Please enter the item # of the product you wish to purchase (1-8): ");
			orderNum = scnr.nextLine();
			if(orderNum.matches("[1-8]")) {
				isValid = true;
			} else {
				System.out.println("You have entered an invalid option.");
				isValid = false;
			}
		} while (!isValid);
		// since input is limited to 1-8, switch case to one of 8 options and populate the lists based on input
		switch(orderNum) {
		case("1"):
			cartNames.add("apples");
			cartPrices.add(priceList.get("apples"));
			System.out.println("Adding apples to cart at $" + priceList.get("apples"));
			break;
		case("2"):
			cartNames.add("bananas");
			cartPrices.add(priceList.get("bananas"));
			System.out.println("Adding bananas to cart at $" + priceList.get("bananas"));
			break;
		case("3"):
			cartNames.add("cream");
			cartPrices.add(priceList.get("cream"));
			System.out.println("Adding ice cream to cart at $" + priceList.get("cream"));
			break;
		case("4"):
			cartNames.add("bread");
			cartPrices.add(priceList.get("bread"));
			System.out.println("Adding bread to cart at $" + priceList.get("bread"));
			break;
		case("5"):
			cartNames.add("milk");
			cartPrices.add(priceList.get("milk"));
			System.out.println("Adding milk to cart at $" + priceList.get("milk"));
			break;
		case("6"):
			cartNames.add("eggs");
			cartPrices.add(priceList.get("eggs"));
			System.out.println("Adding eggs to cart at $" + priceList.get("eggs"));
			break;
		case("7"):
			cartNames.add("coffee");
			cartPrices.add(priceList.get("coffee"));
			System.out.println("Adding coffee to cart at $" + priceList.get("coffee"));
			break;
		case("8"):
			cartNames.add("butter");
			cartPrices.add(priceList.get("butter"));
			System.out.println("Adding butter to cart at $" + priceList.get("butter"));
			break;
		} 
		System.out.println();
	}
	
	public static void displayCart() {
		System.out.println("Thanks for your order!");
		System.out.println("Here's what's in your cart:");
		for(int i = 0; i < cartNames.size(); i++) {
		System.out.println(cartNames.get(i) +"\t $"+cartPrices.get(i));
		subTotal += cartPrices.get(i);
		}
		System.out.println();
		System.out.println("Your total is $" + df2.format(subTotal));
		displayStats(subTotal);
		
	}
	
	// display the stats methods at the end of the order
	public static void displayStats(double subTotal) {
		System.out.println();
		System.out.println("The average price per item was $" + df2.format(averageCart(cartPrices)));
		System.out.println("The highest cost item was $" + df2.format(maxCart(cartPrices)));
		System.out.println("The lowest cost item was $"+ df2.format(minCart(cartPrices)));
	}
	
	// method to find the average of the list
	public static double averageCart(List<Double> input) {
		double averageTotal = 0.00;
		for (Double item : input) {
			averageTotal += item;
		}
		return averageTotal/cartPrices.size();
	}
	
	// method to find the max priced item in the list 
	public static double maxCart(List<Double> input) {
		Double max = input.get(0);
		for (Double item : input) {
			if (item > max) {
				max = item;
			}
		}
		return max;
	}
	
	// method to find the min priced item in the list
	public static double minCart(List<Double> input) {
		Double min = input.get(0);
		for (Double item : input) {
			if (item < min) {
				min = item;
			}
		}
		return min;
	}
}
