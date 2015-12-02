package com.cis232.semesterproject;

import java.util.StringTokenizer;

public class CheckWriter {
	public static String main(String amount) {
		//Check for leading "."
		if (amount.charAt(0)=='.')
		{
			amount = "0." + amount;
		}
		else
		{
			amount = amount.concat(".00");
		}
		
		//Tokenize the string in order to spilt up dollars and cents
		StringTokenizer amountSplit = new StringTokenizer(amount,".");
		String strDollars = amountSplit.nextToken();
		String strCents = amountSplit.nextToken();
		
		//Check if the amount enter is valid using isValidAmount method
		boolean goodCheck = isValidAmount(strDollars,strCents,amount);		
		
		//If the input is valid the programs runs the method checkWriter
		if(goodCheck)
		{
			if(strCents.length()>=2)
			{
				strCents=strCents.substring(0,2);
			}
			else if(strCents.length()==1)
			{
				strCents=strCents.concat("0");
			}
			int dollarInt = Integer.parseInt(strDollars),
					centsInt = Integer.parseInt(strCents);
			return checkWriter(dollarInt, centsInt).toUpperCase();
		}
		else
		{
			return "Invalid Amount";
		}
		
		
	}

	private static String checkWriter(int dollarInt, int centsInt) {
		String strCents,
			   strAmount;
		//Convert cents to long hand
		if(centsInt>1){
			strCents = centsInt + " cents";
		}
		else if(centsInt==1)
		{
			strCents = "1 cent";
		}
		else
		{
			strCents = "00 cents";
		}
		
		//Convert dollar amount to long hand
		if(dollarInt!=0)
		{
			int ones=0,
					tens=0,
					hundreds=0,
					tenThousands=0,
					hundredThousands=0,
					millions=0;
			String checkDol="",
					strMil="",
					strHunTh="",
					strTenTh="",
					strHun="",
					strTen="";
				//Figure out the digit in the ten-millions/millions place and convert to the long hand string value
				millions = (dollarInt-(dollarInt%1000000))/1000000;
					if(millions>=1)
					{
						strMil = numToWord2Digits(millions) + " million ";
					}
					else
					{
						millions=0;
					}
				//Figure out the digit in the hundred-thousands place and convert to the long hand string value
				hundredThousands = ((dollarInt-millions*1000000)-(dollarInt%100000))/100000;
					if (hundredThousands>=1) 
					{ 
						strHunTh = numToWord2Digits(hundredThousands) + " hundred thousand ";
					}
					else
					{
						hundredThousands = 0;
					}
				//Figure out the digit in the ten-thousands/thousands place and convert to the long hand string value
				tenThousands = ((dollarInt-(millions*1000000)-(hundredThousands*100000)) - 
								(dollarInt%1000))/1000;
					if (tenThousands>=1)
					{
						strHunTh = strHunTh.replaceAll("thousand ", "");
						strTenTh = numToWord2Digits(tenThousands) + " thousand ";
					}
					else
					{
						tenThousands=0;
					}
				//Figure out the digit in the hundreds place and convert to the long hand string value
				hundreds = ((dollarInt-(millions*1000000)-(hundredThousands*100000)-
							(tenThousands*1000)) - (dollarInt%100))/100;
					if (hundreds>=1)
					{
						strHun = numToWord2Digits(hundreds) + " hundred ";
					}
					else
					{
						hundreds=0;
					}
				//Figure out the digit in the tens/ones place and convert to the long hand string value 
				tens = ((dollarInt-(millions*1000000)-(hundredThousands*100000)-
						(tenThousands*1000)-(hundreds*100))-(dollarInt%10));
				ones = dollarInt%10;
					if ((tens+ones)>=1)
					{
						strTen = numToWord2Digits(tens+ones);
					}
					
				//Set the value of the string that will be returned by this method
				strAmount = strMil + 
								strHunTh + 
								strTenTh + 
								strHun + 
								strTen +
								" dollars and " 
								+ strCents;
		}
		else
		{
			//This is only for amounts that are less than one dollar
			strAmount = strCents;
		}

//			Capitalize the first letter
//			String Flet = strAmount.substring(0,1).toUpperCase();
//			strAmount = strAmount.replaceFirst(strAmount.substring(0,1), Flet);
		
		//Replaces any occurrence of double spaces to single spaces
		strAmount = strAmount.replaceAll("  ", " ");
		
		//Returns the long hand number
		return strAmount;
	}

	private static boolean isValidAmount(String strDollars, String strCents, String amount)
	{
		//The variable dblAmt will be used to test whether the user entered zero
		double dblAmt = -1;
		
		//Run checks to see if the the value of the amount enter is a valid amount
		boolean goodCheck=true;
		for(int i=0;i<strDollars.length();i++)
		{
			//Does the input only contain numbers or decimals
			if(!Character.isDigit(amount.charAt(i))||amount.charAt(i)=='.'){
				goodCheck=false;
			}
			
			//Check to see if the user enter more than 2 decimal places
			if(strCents.length()>2){
				String exZero = strCents.substring(2,strCents.length()-1);
				//Checks for trailing zeros
				for(int j=0;j<exZero.length();j++)
				{
					if(exZero.charAt(j)!='0')
					{
						goodCheck=false;
					}
				}
			}

			//Checks the length of the dollar portion of input
			if(strDollars.length()>8){
				goodCheck=false;
			}
		}
		if(goodCheck)
		{
			dblAmt = Double.parseDouble(strDollars + "." + strCents);
		}
		//Check if the user entered zero
		if (dblAmt==0)
		{
			goodCheck=false;
		}
		
		return goodCheck;
	}
	
	private static String numToWord2Digits(int num) {
		//Initialize the string to be returned by this method
		String numStr = "";
		
		//Converts the numeric value to a word
		while(num!=0)
		{
			//Hard coded 1-19
			if(num<20)
			{
				switch(num)
				{
					case 1:
						numStr = numStr.concat("one");
						break;
					case 2:
						numStr = numStr.concat("two");
						break;
					case 3:
						numStr = numStr.concat("three");
						break;
					case 4:
						numStr = numStr.concat("four");
						break;
					case 5:
						numStr = numStr.concat("five");
						break;
					case 6:
						numStr = numStr.concat("six");
						break;
					case 7:
						numStr = numStr.concat("seven");
						break;
					case 8:
						numStr = numStr.concat("eight");
						break;
					case 9:
						numStr = numStr.concat("nine");
						break;
					case 10:
						numStr = "ten";
						break;
					case 11:
						numStr = numStr.concat("eleven");
						break;
					case 12:
						numStr = numStr.concat("tweleve");
						break;
					case 13:
						numStr = numStr.concat("thirteen");
						break;
					case 14:
						numStr = numStr.concat("fourteen");
						break;
					case 15:
						numStr = numStr.concat("fifteen");
						break;
					case 16:
						numStr = numStr.concat("sixteen");
						break;
					case 17:
						numStr = numStr.concat("seventeen");
						break;
					case 18:
						numStr = numStr.concat("eighteen");
						break;
					case 19:
						numStr = numStr.concat("nineteen");
						break;
				}
				num=0;
			}
			//Handles numbers from 20 to 29
			else if(num<30)
			{
				numStr="twenty";
				num=num-20;
				if(num!=0)
				{
					numStr=numStr.concat("-");
				}
			}
			//Handles numbers from 30 to 39
			else if(num<40)
			{
				numStr="thirty";
				num=num-30;
				if(num!=0)
				{
					numStr=numStr.concat("-");
				}
			}
			//Handles numbers from 40 to 49
			else if(num<50)
			{
				numStr="forty";
				num=num-40;
				if(num!=0)
				{
					numStr=numStr.concat("-");
				}
			}
			//Handles numbers from 50 to 59
			else if(num<60)
			{
				numStr="fifty";
				num=num-50;
				if(num!=0)
				{
					numStr=numStr.concat("-");
				}
			}
			//Handles numbers from 60 to 69
			else if(num<70)
			{
				numStr="sixty";
				num=num-60;
				if(num!=0)
				{
					numStr=numStr.concat("-");
				}
			}
			//Handles numbers from 70 to 79
			else if(num<80)
			{
				numStr="seventy";
				num=num-70;
				if(num!=0)
				{
					numStr=numStr.concat("-");
				}
			}
			//Handles numbers from 80 to 89
			else if(num<90)
			{
				numStr="eighty";
				num=num-80;
				if(num!=0)
				{
					numStr=numStr.concat("-");
				}
			}
			//Handles numbers from 90 to 99
			else if(num<100)
			{
				numStr="ninety";
				num=num-90;
				if(num!=0)
				{
					numStr=numStr.concat("-");
				}
			}
		}

		//Return the word value of the numeric input
		return numStr;
	}

}

