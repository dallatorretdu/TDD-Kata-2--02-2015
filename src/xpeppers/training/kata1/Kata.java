package xpeppers.training.kata1;


public class Kata {
	
	//Trova la posizione del prossimo delimitatore nella stringa data
	public int next_delimiter_position( String numbers, String delimiter){
		int index = 0;
		while ( index<numbers.length() && numbers.charAt(index) != delimiter.charAt(0) && numbers.charAt(index) != '\n'){
			index++;
		}
		return index;
	}
	
	// Trova i numeri negativi presenti in una stringa delimitata, compila una stringa in "Unfolding Recursive" (functional programming-like)
	public String List_Negatives(String numbers, String delimiter){
		if ( numbers.length()<=0)
			return "";
		if (numbers.charAt(0) == '\n')
			return List_Negatives(numbers.substring(1), delimiter); // <-- bug
		if (numbers.charAt(0) == delimiter.charAt(0))
			return List_Negatives(numbers.substring(delimiter.length()), delimiter);
		
		int delimiter_position = next_delimiter_position(numbers, delimiter);
		int current_number = Integer.parseInt(numbers.substring(0, delimiter_position));
		
		if(current_number < 0)
			return (current_number + " " + List_Negatives(numbers.substring(delimiter_position), delimiter)).trim();
		else
			return List_Negatives(numbers.substring(delimiter_position), delimiter);
	}
	
	// Somma i vari numeri tra di loro in "Unfolding Recursive"
	public int Add_R( String numbers, String delimiter){
		if ( numbers.length()<=0)
			return 0;
		if (numbers.charAt(0) == '\n')
			return Add_R(numbers.substring(1), delimiter);
		if (numbers.charAt(0) == delimiter.charAt(0))
			return Add_R(numbers.substring(delimiter.length()), delimiter);
		
		int delimiter_position = next_delimiter_position(numbers, delimiter);
		int current_number = Integer.parseInt(numbers.substring(0, delimiter_position));
		
		if(current_number < 0){
			throw new IllegalArgumentException("Cannot calculate on negative numbers\n" + List_Negatives(numbers,delimiter));
		}
		else if(current_number >= 1000){
			return Add_R(numbers.substring(delimiter_position), delimiter);
		}
		else{
			return current_number + Add_R(numbers.substring(delimiter_position), delimiter);
		}
	}
	
	//Funzione Proxy che controlla solo se è stato definito un nuovo delimitatore
	public int Add( String numbers ){
		String delimiter = ",";
		if ( numbers.length()<=4 || numbers.charAt(0) != '/' || numbers.charAt(1) != '/')
			return Add_R(numbers, delimiter);
		if(numbers.charAt(3) == '\n'){
			delimiter = "" + numbers.charAt(2);
			return Add_R(numbers.substring(4), delimiter);
		}
		else if(numbers.charAt(2) == '['){
			delimiter = Find_Big_Delimiter(numbers.substring(3));
			numbers = numbers.substring(3 + delimiter.length() + 2);
			return Add_R(numbers, delimiter);
		}
		else
			throw new IllegalArgumentException("Parsing of the delimiter failed");
	}

	//funzione che mi definisce un delimitatore custom di larghezza variabile
	public String Find_Big_Delimiter(String numbers) {
		if(numbers.length()<0)
			return "";
		if(numbers.charAt(0) == ']')
			return "";
		else
			return ""  + numbers.charAt(0) + Find_Big_Delimiter(numbers.substring(1));
	}
}
