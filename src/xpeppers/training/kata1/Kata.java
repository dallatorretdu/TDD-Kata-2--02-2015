package xpeppers.training.kata1;


public class Kata {
	
	//Trova la posizione del prossimo delimitatore nella stringa data
	public int nextDelimiterPosition( String numbers, String delimiter){
		int index = 0;
		while ( index<numbers.length() && numbers.charAt(index) != delimiter.charAt(0) && numbers.charAt(index) != '\n'){
			index++;
		}
		return index;
	}
	
	// Trova i numeri negativi presenti in una stringa delimitata, compila una stringa in "Unfolding Recursive" (functional programming-like)
	public String listNegatives(String numbers, String delimiter){
		if ( numbers.length()<=0)
			return "";
		if (numbers.charAt(0) == '\n')
			return listNegatives(numbers.substring(1), delimiter); // <-- bug
		if (numbers.charAt(0) == delimiter.charAt(0))
			return listNegatives(numbers.substring(delimiter.length()), delimiter);
		
		int delimiter_position = nextDelimiterPosition(numbers, delimiter);
		int current_number = Integer.parseInt(numbers.substring(0, delimiter_position));
		
		if(current_number < 0)
			return (current_number + " " + listNegatives(numbers.substring(delimiter_position), delimiter)).trim();
		else
			return listNegatives(numbers.substring(delimiter_position), delimiter);
	}
	
	// Somma i vari numeri tra di loro in "Unfolding Recursive"
	public int addRecursive( String numbers, String delimiter){
		if ( numbers.length()<=0)
			return 0;
		if (numbers.charAt(0) == '\n')
			return addRecursive(numbers.substring(1), delimiter);
		if (numbers.charAt(0) == delimiter.charAt(0))
			return addRecursive(numbers.substring(delimiter.length()), delimiter);
		
		int delimiter_position = nextDelimiterPosition(numbers, delimiter);
		int current_number = Integer.parseInt(numbers.substring(0, delimiter_position));
		
		if(current_number < 0){
			throw new IllegalArgumentException("Cannot calculate on negative numbers\n" + listNegatives(numbers,delimiter));
		}
		else if(current_number >= 1000){
			return addRecursive(numbers.substring(delimiter_position), delimiter);
		}
		else{
			return current_number + addRecursive(numbers.substring(delimiter_position), delimiter);
		}
	}
	
	//Funzione Proxy che controlla solo se è stato definito un nuovo delimitatore
	public int add( String numbers ){
		String delimiter = ",";
		if ( numbers.length()<=4 || numbers.charAt(0) != '/' || numbers.charAt(1) != '/')
			return addRecursive(numbers, delimiter);
		if(numbers.charAt(3) == '\n'){
			delimiter = "" + numbers.charAt(2);
			return addRecursive(numbers.substring(4), delimiter);
		}
		else if(numbers.charAt(2) == '['){
			delimiter = findBigDelimiter(numbers.substring(3));
			numbers = numbers.substring(3 + delimiter.length() + 2);
			return addRecursive(numbers, delimiter);
		}
		else
			throw new IllegalArgumentException("Parsing of the delimiter failed");
	}

	//funzione che mi definisce un delimitatore custom di larghezza variabile
	public String findBigDelimiter(String numbers) {
		if(numbers.length()<0)
			return "";
		if(numbers.charAt(0) == ']')
			return "";
		else
			return ""  + numbers.charAt(0) + findBigDelimiter(numbers.substring(1));
	}
}
