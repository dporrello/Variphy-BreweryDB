package variphy.util;

public class IConstants {
	// Welcome/Exit statements
	public static final String APP_WELCOME = "Brewery DB Console Application...";
	public static final String APP_EXIT = "Thank you for using the simple Brewery DB API Console App.";

	// Menu constants
	public static final String MENU = "Menu";
	public static final String MENU_ABV = "1. Search by ABV (min/max)";
	public static final String MENU_QUERY = "2. Search by query";
	public static final String MENU_ADVANCED = "3. Advanced search";
	public static final String MENU_EXIT = "q. Quit application";
	public static final String MENU_EXIT_INPUT = "q";
	public static final String MENU_ABV_INPUT = "1";
	public static final String MENU_QUERY_INPUT = "2";
	public static final String MENU_ADVANCED_INPUT = "3";

	// User Input Constants
	public static final String USER_INPUT = ">> ";
	public static final String USER_ABV_INPUT = "Enter %s ABV (optional: enter '-' for no value): ";
	public static final String USER_QUERY_INPUT = "Enter value to search by: ";
	public static final String USER_QUERY_INPUT_OPTIONAL = "Enter value to search by (optional: leave blank for no value): ";

	// Output Constants
	public static final String BEER_NONE_FOUND = "No beers where found with that input.";
	public static final String BEER_NAME = "Name: ";
	public static final String BEER_ID = "Id: ";
	public static final String BEER_DESCRIPTION = "Description: ";
	public static final String BEER_ABV = "ABV: ";
	public static final String BEER_IBU = "IBU: ";
	public static final String BEER_CREATE_DATE = "Create Date: ";
	public static final String PROCESSING_ABV = "Processing Search for ABV (min,max): (%s,%s)...%n";
	public static final String PROCESSING_QUERY = "Processing Search for Keyword: %s...%n";
	public static final String PROCESSING_ADVANCED = "Processing Search for Keyword: %s and ABV (min,max): (%s,%s)...%n";

	// Exception Constants
	public static final String INVALID_INPUT = "Invalid user input. Please select a valid choice.";
	public static final String INVALID_ABV_INPUT = "Invalid input, please enter a positive number or '-': ";
	public static final String INVALID_MIN_MAX_INPUT = "Invalid input, please enter a max ABV that is larger than min ABV.";

	// ABV Searching Options
	public enum SearchType {
		JUST_MIN, JUST_MAX, BOTH_ABV
	};

	// Miscellanious Constants
	public static final String IS_DOUBLE_REGEX = "^[0-9]+\\.?[0-9]*$";
	public static final String LINE_SEPARATOR = "line.separator";
	public static final String SEARCH_ALL = "*";
	public static final String NO_ABV = "-";

}