//Michael Adams
//Tu Th 1pm
//Program simulates transactions for a food cart

import java.util.*; //access to Scanner class

public class hw1 //dont understand why everything needs to be in a class
{
	public static void main(String[] args) //not sure why there needs to be string args
	{
		
		System.out.println("Welcome to Dumbledores Delicious Delicacies!");
		System.out.println("Is there a customer in line? (1 = yes, 2 = no)");
		
		int isCustomer = 0;
		
		Scanner inScan = new Scanner(System.in);
		isCustomer = inScan.nextInt();
		while (isCustomer != 1 && isCustomer != 2) //checks isCustomer valid
		{
			System.out.println("Enter only a 1 or a 2 you fool");
			isCustomer = inScan.nextInt();
		}//while isCustomer not valid
		
		boolean isMember = true;
		String passphrase, garbage; 
		
		while (isCustomer == 1)
		{
			System.out.println("Enter the password for Dumbledore's Army Discount!");
			passphrase = inScan.next();
			garbage = inScan.nextLine();
			
			if (passphrase.toLowerCase().equals("hocuspocus")) //checks password
			{
				isMember = true;
				System.out.println("welcome back, loyal customer");
				System.out.println("Enjoy discounts on Cauldron Cakes, Chocolate Frogs, and Large Butterbeers!");
			}//if
			else if (!passphrase.toLowerCase().equals("hocuspocus"))
			{
				System.out.println("Sorry but that is not right.  We will give you one more chance.");
				passphrase = inScan.next();
				garbage = inScan.nextLine();
				
				if (passphrase.toLowerCase().equals("hocuspocus"))
				{
					isMember = true;
					System.out.println("welcome back, loyal customer");
					System.out.println("Enjoy discounts on Cauldron Cakes, Chocolate Frogs, and Large Butterbeers!");
				}
				else if (!passphrase.toLowerCase().equals("hocuspocus"))
				{
					System.out.println("Please enjoy our items at their regular prices.");
					isMember = false;
				}
			}//elseif
		
			if (isMember) //outputs appropriate prices
			{
				System.out.println("Here is our price list:");
				System.out.println("\t Cauldron Cakes (each) \t\t 10 Knuts");
				System.out.println("\t Cauldron Cakes (bag of 6) \t 50 Knuts");
				System.out.println("\t Chocolate Frogs (each) \t 15 Knuts");
				System.out.println("\t Butterbeer (small) \t\t 50 Knuts");
				System.out.println("\t Butterbeer (large) \t\t 50 Knuts");
			}//if
			else if (!isMember)
			{
				System.out.println("Here is our price list:");
				System.out.println("\t Cauldron Cakes (each) \t\t 10 Knuts");
				System.out.println("\t Cauldron Cakes (bag of 6) \t 55 Knuts");
				System.out.println("\t Chocolate Frogs (each) \t 20 Knuts");
				System.out.println("\t Butterbeer (small) \t\t 50 Knuts");
				System.out.println("\t Butterbeer (large) \t\t 75 Knuts");
			}//elseif
			
			
			int transAction;
			int numEntered, amtSaved, amtPaid, changeKnuts, changeSickles, changeGalleons, rmndr;
			int orderTotal, oldOrderTotal, bagTotal, cakeTotal, frogTotal, smallButterTotal, largeButterTotal;
			int numBagCakes, numCakes, numFrogs, numSmallButter, numLargeButter, refVal;
			double amtSavedDouble;
			
			transAction = 0;
			numEntered = amtSaved = amtPaid = changeKnuts = changeSickles = changeGalleons = rmndr = 0;
			orderTotal = oldOrderTotal = bagTotal = cakeTotal = frogTotal = smallButterTotal = largeButterTotal = 0;
			numBagCakes = numCakes = numFrogs = numSmallButter = numLargeButter = refVal = 0;
			amtSavedDouble = 0;
			
			do //while transaction
			{	
				System.out.println("Please Choose an option:");
				System.out.println("\t 1) Update Cauldron Cakes Order");
				System.out.println("\t 2) Update Chocolate Frogs Order");
				System.out.println("\t 3) Update Butterbeer Order");
				System.out.println("\t 4) Check Out");
				transAction = inScan.nextInt();
				
				while (transAction != 1 && transAction != 2 && transAction != 3 && transAction != 4)
				{
					System.out.println("Please Choose an option:");
					System.out.println("\t 1) Update Cauldron Cakes Order");
					System.out.println("\t 2) Update Chocolate Frogs Order");
					System.out.println("\t 3) Update Butterbeer Order");
					System.out.println("\t 4) Check Out");
					transAction = inScan.nextInt();
				}
				
				switch (transAction)
				{
					case 1: //Update culdron cake order
					System.out.println("Here is your current order:");
					System.out.println(numCakes + " Culdron Cakes ordered so far");
					System.out.println(" ");
					System.out.println("How many Culdron Cakes would you like to order for:");
					System.out.println("(please indicate only the total number of cakes)");
				
					if (isMember) //lists price for members
					{
						System.out.println("\t Cauldron Cakes (each) \t\t 10 Knuts");
						System.out.println("\t Cauldron Cakes (bag of 6) \t 50 Knuts");
					}
					else if (!isMember) //lists price for nonmembers
					{
						System.out.println("\t Cauldron Cakes (each) \t\t 10 Knuts");
						System.out.println("\t Cauldron Cakes (bag of 6) \t 55 Knuts");
					}
					
					numEntered = 0; 
					numEntered = inScan.nextInt();
					if (numEntered < 0) //checks input less than 0
					{
						System.out.println("Negative number taken as 0");
						numCakes = numCakes;
					}
					else if (numEntered >= 0) //sums total cakes ordered
					{
						numCakes = numEntered;
					}
					
					break;
				
					case 2: //Update chocolate frog order
					System.out.println("Here is your current order:");
					System.out.println(numFrogs + " Chocolate Frogs ordered so far");
					System.out.println(" ");
					System.out.println("How many Chocolate Frogs would you like to order for:");
				
					if (isMember) //lists price for members
					{
						System.out.println("\t Chocolate Frogs (each) \t 15 Knuts");
					}
					else if (!isMember) //lists price for nonmembers
					{
						System.out.println("\t Chocolate Frogs (each) \t 20 Knuts");
					}
					
					numEntered = 0;
					numEntered = inScan.nextInt();
					if (numEntered < 0) //checks input less than 0
					{
						System.out.println("Negative number taken as 0");
						numFrogs = numFrogs;
					}
					else if (numEntered >= 0) //sums total frogs ordered
					{
						numFrogs = numEntered;
					}

					break;
				
					case 3: //Update butterbeer order
					System.out.println("Here is your current order:");
					System.out.println((numSmallButter + numLargeButter) + " Butterbeer(s) ordered so far");
					System.out.println(" ");
					System.out.println("How many Butterbeers would you like to order for:");
				
					if (isMember) //gets order for members
					{
						System.out.println("\t Butterbeer (small) \t\t 50 Knuts");
						
						numEntered = 0;
						numEntered = inScan.nextInt();
						if (numEntered < 0)
						{
							System.out.println("Negative number taken as 0");
							numSmallButter = numSmallButter;
						}
						else if (numEntered >= 0)
						{
							numSmallButter = numEntered;
						}
						
						System.out.println("\t Butterbeer (large) \t\t 50 Knuts");
						
						numEntered = 0;
						numEntered = inScan.nextInt();
						if (numEntered < 0)
						{
							System.out.println("Negative number taken as 0");
							numLargeButter = numLargeButter;
						}
						else if (numEntered >= 0)
						{
							numLargeButter = numEntered;
						}
					}//if member
					else if (!isMember) //gets order for nonmembers
					{
						System.out.println("\t Butterbeer (small) \t\t 50 Knuts");
						
						numEntered = 0;
						numEntered = inScan.nextInt();
						if (numEntered < 0)
						{
							System.out.println("Negative number taken as 0");
							numSmallButter = numSmallButter;
						}
						else if (numEntered >= 0)
						{
							numSmallButter = numEntered;
						}
						
						System.out.println("\t Butterbeer (large) \t\t 75 Knuts");
						
						numEntered = 0;
						numEntered = inScan.nextInt();
						if (numEntered < 0)
						{
							System.out.println("Negative number taken as 0");
							numLargeButter = numLargeButter;
						}
						else if (numEntered >= 0)
						{
							numLargeButter = numEntered;
						}
					}//elseif not member

					break;
				
					case 4: //Give total for order
					System.out.println("Here is your subtotal:");
					
					numBagCakes = (numCakes - (numCakes % 6))/6;
					numCakes = (numCakes % 6);
					
					if (isMember) //gives subtotal for members
					{
						bagTotal = numBagCakes * 50;
						cakeTotal = numCakes * 10;
						frogTotal = numFrogs * 15;
						smallButterTotal = numSmallButter * 50;
						largeButterTotal = numLargeButter * 50;
						
						orderTotal = bagTotal + cakeTotal + frogTotal + smallButterTotal + largeButterTotal;
						
						if (orderTotal > 290) //applies discount if total is greater than 10 sickles
						{
							amtSavedDouble = orderTotal * 0.10;
							
							if (amtSavedDouble - refVal >= 0.5)
							{
								amtSaved = (int) Math.ceil(amtSavedDouble);
							}
							else if (amtSavedDouble < 0.5)
							{
								amtSaved = (int) Math.floor(amtSavedDouble);
							}
							
							oldOrderTotal = orderTotal;
							orderTotal = oldOrderTotal - amtSaved;
							
							System.out.println(numBagCakes + " bag(s) of Cauldron Cakes at 50 Knuts each: \t" + bagTotal);
							System.out.println(numCakes + " Cauldron Cake(s) at 10 Knuts each: \t\t" + cakeTotal);
							System.out.println(numFrogs + " Chocolate Frog(s) at 15 Knuts each: \t\t" + frogTotal);
							System.out.println(numSmallButter + " small Butterbeer(s) at 50 Knuts each: \t" + smallButterTotal);
							System.out.println(numLargeButter + " large Butterbeer(s) at 50 Knuts each: \t" + largeButterTotal);
							System.out.println("\t\t\t\t\t     -----");
							System.out.println("Total: \t\t\t\t\t\t" + oldOrderTotal);
							System.out.println("Bonus discount of 10%: \t\t\t\t -" + amtSaved);
							System.out.println("\t\t\t\t\t     -----");
							System.out.println("New Total: \t\t\t\t\t" + orderTotal);	
						}
						else if (orderTotal > 0 && orderTotal <= 290)
						{
							System.out.println(numBagCakes + " bag(s) of Cauldron Cakes at 50 Knuts each: \t" + bagTotal);
							System.out.println(numCakes + " Cauldron Cake(s) at 10 Knuts each: \t\t" + cakeTotal);
							System.out.println(numFrogs + " Chocolate Frog(s) at 15 Knuts each: \t\t" + frogTotal);
							System.out.println(numSmallButter + " small Butterbeer(s) at 50 Knuts each: \t" + smallButterTotal);
							System.out.println(numLargeButter + " large Butterbeer(s) at 50 Knuts each: \t" + largeButterTotal);
							System.out.println("\t\t\t\t\t     -----");
							System.out.println("Total: \t\t\t\t\t\t" + orderTotal);
						}
						else if (orderTotal == 0)
						{
							System.out.println("Nothing Ordered! Well thanks for stopping by, have a magical day!");
						}
						
						
					}
					else if (!isMember) //gives subtotal for nonmembers
					{
						
						bagTotal = numBagCakes * 55;
						cakeTotal = numCakes * 10;
						frogTotal = numFrogs * 20;
						smallButterTotal = numSmallButter * 50;
						largeButterTotal = numLargeButter * 75;
						
						orderTotal = bagTotal + cakeTotal + frogTotal + smallButterTotal + largeButterTotal;
						
						if (orderTotal < 0)
						{
							System.out.println("Nothing Ordered! Well thanks for stopping by, have a magical day!");
						}
						else if (orderTotal > 0)
						{
						System.out.println(numBagCakes + " bag(s) of Cauldron Cakes at 55 Knuts each: \t" + bagTotal);
						System.out.println(numCakes + " Cauldron Cake(s) at 10 Knuts each: \t\t" + cakeTotal);
						System.out.println(numFrogs + " Chocolate Frog(s) at 20 Knuts each: \t\t" + frogTotal);
						System.out.println(numSmallButter + " small Butterbeer(s) at 50 Knuts each: \t" + smallButterTotal);
						System.out.println(numLargeButter + " large Butterbeer(s) at 75 Knuts each: \t" + largeButterTotal);
						System.out.println("\t\t\t\t\t     -----");
						System.out.println("Total: \t\t\t\t\t\t" + orderTotal);
						}
						
					}
					transAction = 37; //will fail while condition
					break;
				}//switch
	
			}while (transAction == 1 || transAction == 2 || transAction == 3 || transAction == 4); //while transaction 
			
			amtPaid = 0;
			while ((orderTotal - amtPaid) > 0)
			{
				System.out.println("Please enter your payment amount in the following format:");
				System.out.println("\t <amount><space><currency>");
				System.out.println("\t\t Where <amount> = an integer");
				System.out.println("\t\t Where <space> = a blank space");
				System.out.println("\t\t Where <amount> = {Knuts, Sickles, Galleons}");
			
				changeKnuts = 0;
				String pmtType;
				
				amtPaid = inScan.nextInt();
				pmtType = inScan.next();
				
				if (pmtType.toLowerCase().equals("sickles"))
				{
					amtPaid = amtPaid * 29;
				}
				else if (pmtType.toLowerCase().equals("galleons"))
				{
					amtPaid = amtPaid * 493;
				}

				while (amtPaid < orderTotal) //cant underpay
				{
					System.out.println("Not enough yo, you owe " + orderTotal + " Knuts but only gave " + amtPaid);
					System.out.println("Please enter your payment amount in the following format:");
					System.out.println("\t <amount><space><currency>");
					System.out.println("\t\t Where <amount> = an integer");
					System.out.println("\t\t Where <space> = a blank space");
					System.out.println("\t\t Where <amount> = {Knuts, Sickles, Galleons}");
				
					amtPaid = inScan.nextInt();
					pmtType = inScan.next();
				
					if (pmtType.toLowerCase().equals("sickles"))
					{
						amtPaid = amtPaid * 29;
					}
					else if (pmtType.toLowerCase().equals("galleons"))
					{
						amtPaid = amtPaid * 493;
					}
				}//while thief
			
				if (amtPaid == orderTotal) //if not change
				{
					System.out.println("Thanks for coming to DDD, have a magical day!");
				}
				else if (amtPaid > orderTotal) //if change
				{
					changeKnuts = amtPaid - orderTotal;
				
					changeGalleons = (changeKnuts - (changeKnuts % 493))/493;
					rmndr = changeKnuts - (changeGalleons * 493);
				
					changeSickles = (rmndr - (rmndr % 29))/29;
					rmndr = rmndr - (changeSickles * 29);
				
					changeKnuts = rmndr;
				
					System.out.println("Here's your change:");
					if (changeGalleons != 0)
					{
						System.out.println("\t\t" + changeGalleons + " Galleons");
					}
					if (changeSickles != 0)
					{
						System.out.println("\t\t" + changeSickles + " Sickles");
					}
					if (rmndr != 0)
					{
						System.out.println("\t\t" + rmndr + " Knuts");
					}
				
					System.out.println("Thanks for coming to DDD, have a magical day!");
				}
				
			}//while order > 0	
		
		System.out.println(" ");
		System.out.println("Is there another customer? (1 = yes, 2 = no)");
		isCustomer = inScan.nextInt();
		
		}//while isCustomer		
	}//main
}//hw1 class
