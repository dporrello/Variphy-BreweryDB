package variphy.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import variphy.exception.InvalidInputException;
import variphy.model.Beer;
import variphy.service.BreweryService;
import variphy.service.impl.BreweryServiceImpl;
import variphy.util.IConstants;

public class MainApp {
	private static BreweryService breweryService;

	public static void main(String[] args) throws Exception {
		List<Beer> beers = new ArrayList<Beer>();

		try {
			breweryService = new BreweryServiceImpl();
		} catch (Exception exception) {
			// Exception reading the properties file. Report issue and exit
			// program.
			System.out.println(exception.getMessage());
			return;
		}

		Scanner scanner = new Scanner(System.in);
		String userInput;

		System.out.println(IConstants.APP_WELCOME);

		do {
			userInput = displayMenu(scanner);

			try {
				beers = processResponse(userInput, scanner);
				if (beers.size() > 0)
					for (Beer beer : beers)
						System.out.println(beer);
				else if (!userInput
						.equalsIgnoreCase(IConstants.MENU_EXIT_INPUT))
					System.out.println(IConstants.BEER_NONE_FOUND);

			} catch (InvalidInputException iException) {
				// Exception with user inputing invalid input for menu. Inform
				// user and reprompt.
				System.out.println(iException.getErrorMessage());
			} catch (IOException exception) {
				// Exception executing the GET Request with API. Inform user and
				// quit program.
				System.out.println(exception.getMessage());
				userInput = IConstants.MENU_EXIT_INPUT;
			}
		} while (!userInput.equalsIgnoreCase(IConstants.MENU_EXIT_INPUT));

		scanner.close();
		System.out.println(IConstants.APP_EXIT);
	}

	private static String displayMenu(Scanner scanner) {
		System.out.println(IConstants.MENU);
		System.out.println(IConstants.MENU_ABV);
		System.out.println(IConstants.MENU_QUERY);
		System.out.println(IConstants.MENU_ADVANCED);
		System.out.println(IConstants.MENU_EXIT);

		System.out.print(IConstants.USER_INPUT);

		return scanner.nextLine();
	}

	private static List<Beer> processResponse(String userInput, Scanner scanner)
			throws InvalidInputException, IOException {
		List<Beer> beers = new ArrayList<Beer>();
		String minAbv = null;
		String maxAbv = null;
		String query = null;

		switch (userInput) {
		case IConstants.MENU_ABV_INPUT:
			minAbv = getValidAbv(
					String.format(IConstants.USER_ABV_INPUT, "min"), scanner);
			maxAbv = getValidAbv(
					String.format(IConstants.USER_ABV_INPUT, "max"), scanner);

			if (isMinGreaterThanMax(minAbv, maxAbv))
				throw new InvalidInputException(
						IConstants.INVALID_MIN_MAX_INPUT);

			System.out.format(IConstants.PROCESSING_ABV, minAbv, maxAbv);
			beers = breweryService.searchByAbv(minAbv, maxAbv);
			break;
		case IConstants.MENU_QUERY_INPUT:
			query = getValidQuery(IConstants.USER_QUERY_INPUT, scanner);

			System.out.format(IConstants.PROCESSING_QUERY, query);
			beers = breweryService.searchByQuery(query);
			break;
		case IConstants.MENU_ADVANCED_INPUT:
			minAbv = getValidAbv(
					String.format(IConstants.USER_ABV_INPUT, "min"), scanner);
			maxAbv = getValidAbv(
					String.format(IConstants.USER_ABV_INPUT, "max"), scanner);

			if (isMinGreaterThanMax(minAbv, maxAbv))
				throw new InvalidInputException(
						IConstants.INVALID_MIN_MAX_INPUT);

			query = getValidQuery(IConstants.USER_QUERY_INPUT_OPTIONAL, scanner);

			System.out.format(IConstants.PROCESSING_ADVANCED, query, minAbv,
					maxAbv);
			beers = breweryService.searchByAdvanced(minAbv, maxAbv, query);

			break;
		case IConstants.MENU_EXIT_INPUT:
			break;
		default:
			throw new InvalidInputException(IConstants.INVALID_INPUT);
		}

		return beers;
	}

	private static String getValidAbv(String prompt, Scanner scanner) {
		String input = null;
		boolean validInput = false;

		System.out.print(prompt);

		do {
			input = scanner.nextLine();

			if (input.matches(IConstants.IS_DOUBLE_REGEX)
					|| input.equals(IConstants.NO_ABV))
				validInput = true;
			else
				System.out.print(IConstants.INVALID_ABV_INPUT);

		} while (!validInput);

		return input;
	}

	private static String getValidQuery(String prompt, Scanner scanner) {
		System.out.print(prompt);
		return scanner.nextLine();
	}

	private static boolean isMinGreaterThanMax(String minAbv, String maxAbv) {
		return !minAbv.equals(IConstants.NO_ABV)
				&& !maxAbv.equals(IConstants.NO_ABV)
				&& Double.parseDouble(minAbv) > Double.parseDouble(maxAbv);
	}

}