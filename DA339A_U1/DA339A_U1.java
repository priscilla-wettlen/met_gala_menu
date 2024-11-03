
/*
  Author: Priscilla Wettlén
  Id: AJ6817
  Study program: Systemutvecklare
*/
import java.util.Scanner;

public class DA339A_U1 {
  static Scanner input = new Scanner(System.in);

  static boolean isRunning = false;

  // an array to use for testing, replace as needed to test your code
  static String[][] guestList = {
      { "Adam Ason", "35" },
      { "Berta Bson", "70" },
      { "Ceasar Cson", "12" },
      { "", "" },
      { "", "" },
      { "", "" },
      { "", "" },
      { "", "" },
      { "", "" },
      { "", "" },
  };

  int parsedAge = 0;

  /*
   * Parameters for methods below may NOT be changed and HAVE TO BE USED as
   * intended for a passing grade.
   */

  /**
   * This method makes sure to print the information about the guests
   * (name and age) from the guest list in the terminal for the user.
   * The print out shall show name, age and a number representing the guest's
   * place in the guest list.
   * Empty places in the guest list will NOT be shown.
   */
  public static void printGuestInformation() {
    for (int names = 0; names < guestList.length; names++) {
      if (!guestList[names][0].equals("")) {
        System.out.printf("%s ", names);
        System.out.printf("%2s %5s", guestList[names][0], guestList[names][1]);
        System.out.println();

      }
    }
  }

  /**
   * This method makes sure to print the whole guest list in the terminal for the
   * user.
   * If a place in the guest list contains data for a guest the guest's name and
   * age is shown.
   * If a place in the guest list is empty a text stating that the place in empty
   * is shown.
   * In both cases a number representing the place in the guest list is shown.
   */

  public static void printGuestList() {
    for (int names = 0; names < guestList.length; names++) {
      System.out.printf("%s ", names);
      for (int ages = 0; ages < guestList[names].length; ages++) {
        if (guestList[names][ages] == "") {
          System.out.printf("%5s", "[vacant]");
        }
        System.out.printf("%2s      ", guestList[names][ages]);
      }
      System.out.println();
    }
  }

  /**
   * This method makes sure to print statistics about the guest list to the user.
   * The following statistics shall be shown:
   * - Total number guests
   * - Total number of adult guests
   * - Total number of child guests
   * - Who the oldest guest is and their age
   * - Who the youngest guest is and their age
   */
  public static void printStatistics() {
    int oldest = -1;
    int youngest = 1000;
    int totalAdults = 0;
    int totalChildren = 0;
    int guestCount = 0;
    int totalGuestCount = 0;

    for (int names = 0; names < guestList.length; names++) {
      for (int ages = 0; ages < guestList[names].length; ages++) {
        if (guestList[names][ages] != "") {
          guestCount++;
          totalGuestCount = guestCount;
          break;
        }
      }
    }

    // Parsed ages array
    int[] agesParsed;
    agesParsed = new int[guestList.length];
    int indexOfOldest = -1;
    int indexOfYoungest = -1;

    for (int i = 0; i < agesParsed.length; i++) {
      if (guestList[i][1] != "") {
        agesParsed[i] = Integer.parseInt(guestList[i][1]);

        if (agesParsed[i] <= 13) {
          totalChildren++;
        } else {
          totalAdults++;
        }

        if (agesParsed[i] > oldest) {
          oldest = agesParsed[i];
          indexOfOldest = i;
        } else if (agesParsed[i] == oldest) {
          indexOfOldest = i;
        }

        if (agesParsed[i] < youngest) {
          youngest = agesParsed[i];
          indexOfYoungest = i;
        } else if (agesParsed[i] == youngest) {
          indexOfYoungest = i;
        }

      }
    }
    // Här visar man statistik
    if (totalGuestCount == 0) {
      System.out.println("There are zero attendees at the MET Gala this year :(");
      System.out.println();
      System.out.println();
    } else {
      System.out.println("There are " + guestCount + " people attending the MET Gala this year.");

      if (totalAdults == 1) {
        System.out.println(totalAdults + " of them is an adult.");
      } else {
        System.out.println(totalAdults + " of them are adults.");
      }

      if (totalChildren == 1) {
        System.out.println(totalChildren + " of them is a child.");
      } else {
        System.out.println(totalChildren + " of them are children");
      }

      System.out.println("The oldest attendee is " + guestList[indexOfOldest][0] + " at age " + oldest + ".");
      System.out.println("The youngest attendee is " + guestList[indexOfYoungest][0] + " at age " + youngest + ".");
      System.out.println();
    }
    // End of statistik
  }

  /**
   * This method adds a guest to the guest list.
   * The information that is added to the guest list is defined in the method
   * parameters.
   * If a user tries to add a guest to a full guest list an error messages
   * informing the user
   * about this will be shown and no guest added to the list.
   * 
   * @param name The name of the guest to add
   * @param age  The age of the guest to add as a String
   */
  public static void addGuest(String name, String age) {

    int guestCount = 0;
    int totalGuestCount = 0;
    String vacantSeatIndex = "";

    while (!containsDigits(age)) {
      System.out.println("WHOOPS! Make sure age is a number.");
      age = input.next();

    }

    String[] newGuest = { name, age };

    int parsedAge = 0;
    parsedAge = Integer.parseInt(age);

    for (int names = 0; names < guestList.length; names++) {
      for (int ages = 0; ages < guestList[0].length; ages++) {
        if (guestList[names][ages] != "") {
          guestCount++;
          totalGuestCount = guestCount;
          break;
        }
      }
    }

    if (totalGuestCount >= 10) {
      System.out.println(
          "Dear " + name
              + ", unfortunately the MET Gala is fully booked this year. But you're welcome again next year! :D");
      System.out.println();
      System.out.println();
    } else {
      System.out.println("Choose a vacant seat:");
      System.out.println();
      printGuestList();
      System.out.println();
      vacantSeatIndex = input.next();
      int parsedSeat = -1;
      boolean inputIsInvalid = true;
      while (inputIsInvalid) {
        if (!containsDigits(vacantSeatIndex)) {
          System.out.println("WHOOPS! Make sure you entered a number.");
          vacantSeatIndex = input.next();
          continue;
        }
        parsedSeat = Integer.parseInt(vacantSeatIndex);
        if (parsedSeat > guestList.length) {
          System.out.println("WHOOPS! Make sure you entered a valid seat number.");
          vacantSeatIndex = input.next();
          continue;
        }
        inputIsInvalid = false;
      }
      printGuestList();

      while (guestList[parsedSeat][0] != "") {
        System.out.println("WHOOPS! That seat is already taken. Please select another one.");
        parsedSeat = input.nextInt();
      }

      guestList[parsedSeat][0] = newGuest[0];
      guestList[parsedSeat][1] = newGuest[1];

      System.out.println();
      printGuestInformation();
    }

  }

  /**
   * This method changes the name of a specific guest in the guest list.
   * Which guest's name is changed is decided by a method parameter.
   * If a user tries to change the name at a place in the list that has
   * no current guest an error message informing the user about this
   * will be shown and no data in the guest list is changed.
   * 
   * @param index   The index of the guest whose name shall be changed.
   * @param newName The new name of the guest at place given by index.
   */
  public static void changeNamneOfGuest(int index, String newName) {
    for (int i = 0; i < guestList.length; i++) {
      guestList[index][0] = newName;
    }

  }

  /**
   * This method changes the age of a specific guest in the guest list.
   * Which guest's age is changed is decided by a method parameter.
   * If a user tries to change the age at a place in the list that has
   * no current guest an error message informing the user about this
   * will be shown and no data in the guest list is changed.
   * 
   * @param index  The index of the guest whose age shall be changed.
   * @param newAge The new age, as a String, of the guest at place given by index.
   */
  public static void changeAgeOfGuest(int index, String newAge) {
    for (int i = 0; i < guestList.length; i++) {
      guestList[index][1] = newAge;
    }
  }

  /**
   * This method removes a guest at a given index from the guest list.
   * The data at this index is replaced by empty Strings.
   * If no guest exists at the given index an error message informing
   * the user about this will be shown and no data in the guest list is changed.
   * 
   * @param index The index of the place where a guest is to be removed.
   */
  public static void removeGuest(int index) {
    for (int i = 0; i < guestList.length; i++) {
      if (i == index) {
        guestList[i][1] = "";
        guestList[i][0] = "";
      }
    }

  }

  /**
   * This method changes places in the guest list for the data existing
   * at those places given by the parameters. It is possible to change
   * places between data even if one or both places contains empty strings.
   * This gives the user the possibility to change the place of one guest
   * to an empty place in the guest list.
   * If the two parameters have the same value an error message informing
   * the user about this will be shown and no data in the guest list is changed.
   * 
   * @param index1 First index involved in the change of places
   * @param index2 Second index involved in the change of places
   */
  public static void changePlaces(int index1, int index2) {
    printGuestList();

    String[] guestOnHold;
    guestOnHold = new String[2];

    guestOnHold[0] = guestList[index1][0];
    guestOnHold[1] = guestList[index1][1];

    while (index1 == index2) {
      System.out.println("WHOOPS! That guest already sits on that spot. Choose another seat to move them.");
      System.out.println("Enter the number of the second guest you would like to move:");
      index2 = input.nextInt();
    }

    guestList[index1][0] = guestList[index2][0];
    guestList[index1][1] = guestList[index2][1];

    guestList[index2][0] = guestOnHold[0];
    guestList[index2][1] = guestOnHold[1];

  }

  /**
   * This method prints the program menu in the terminal for the user to view.
   */
  public static void printMenu() {
    System.out.println("*ੈ✩‧₊˚༺☆༻*ੈ✩‧₊˚*ੈ✩‧₊˚༺☆༻*ੈ✩‧₊˚*ੈ✩‧₊˚༺☆༻*ੈ✩‧₊˚*ੈ✩‧₊˚༺☆༻*ੈ✩‧₊˚*ੈ✩‧₊˚༺☆༻*ੈ✩‧₊˚");
    System.out.println("Welcome to the MET Gala! Choose an option from the menu below:");
    System.out.println("*ੈ✩‧₊˚༺☆༻*ੈ✩‧₊˚*ੈ✩‧₊˚༺☆༻*ੈ✩‧₊˚*ੈ✩‧₊˚༺☆༻*ੈ✩‧₊˚*ੈ✩‧₊˚༺☆༻*ੈ✩‧₊˚*ੈ✩‧₊˚༺☆༻*ੈ✩‧₊˚");

    String[] menuOptions = {
        "1. Add new guest",
        "2. Modify a guest's name",
        "3. Modify a guest's age",
        "4. Print guest information",
        "5. View statistics about guests",
        "6. Print entire guest list",
        "7. Remove guest",
        "8. Ask two guests to switch seats",
        "0. Exit menu"
    };

    for (int i = 0; i < menuOptions.length; i++) {
      System.out.printf("%2s\n", menuOptions[i]);
      System.out.println();
    }

  }

  /**
   * This method reads the menu choice from the user and returns the choice the
   * user made as an integer.
   * 
   * @return The menu choice made by the user represented by an integer value of
   *         type int.
   */
  public static int readMenuChoice() {
    int option = -1;
    boolean isValidInput = false;

    while (!isValidInput) {
      System.out.println("What would you like to do?");

      if (!input.hasNextInt()) {
        System.out.println("WHOOPS! Please choose ONLY a valid number from the options above.");
        input.next();
        continue;
      }

      option = input.nextInt();

      if (option >= 0 && option <= 8) {
        isValidInput = true;
      } else {
        System.out.println("WHOOPS! Please choose ONLY a valid number from the options above.");
      }

    }

    return option;

  }

  public static boolean containsDigits(String age) {
    for (int i = 0; i < age.length(); i++) {
      char c = age.charAt(i);
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  public static int isInteger(int num, String message) {
    boolean isValidInput = false;

    while (!isValidInput) {
      System.out.println(message);

      if (!input.hasNextInt()) {
        System.out.println("WHOOPS! Please enter a valid number.");
        input.next();
        continue;
      }

      num = input.nextInt();

      if (num >= guestList.length) {
        System.out.println("WHOOPS! Please enter a valid seat number.");
        continue;

      }

      if (guestList[num][0] == "") {
        System.out.println("WHOOPS! Please enter a valid seat number.");
        continue;
      } else {
        isValidInput = true;
      }

    }

    return num;
  }

  public static int canGuestSeatThere(int num, String message) {
    boolean isValidInput = false;

    while (!isValidInput) {
      System.out.println(message);

      if (!input.hasNextInt()) {
        System.out.println("WHOOPS! Please enter a valid number.");
        input.next();
        continue;
      }

      num = input.nextInt();

      if (num >= guestList.length) {
        System.out.println("WHOOPS! Please enter a valid seat number.");
        continue;
      } else {
        isValidInput = true;
      }

    }

    return num;
  }

  public static void main(String[] args) {
    // Add you code here with the loop that handles the user's menu choices.

    do {
      printMenu();

      int option = readMenuChoice();

      switch (option) {
        case 1:
          System.out.println("Enter guest's name:");
          String name = input.next();
          System.out.println("Enter guest's age:");
          String age = input.next();
          addGuest(name, age);
          isRunning = true;
          break;
        case 2:
          printGuestList();
          System.out.println("Enter guest's new name:");
          String newName = input.next();
          int index = isInteger(option, "Enter guest seat number:");
          changeNamneOfGuest(index, newName);
          printGuestInformation();
          isRunning = true;
          break;
        case 3:
          printGuestList();
          System.out.println("Enter guest's new age:");
          String newAge = input.next();
          while (!containsDigits(newAge)) {
            System.out.println("WHOOPS! Age must be a number.");
            newAge = input.next();
            System.out.println();
          }
          // Jag använder while-loopen här för att annars skulle programmet hoppa över
          // direkt till "enter seat number"
          // utan att hantera fel inputen från newAge. Om användaren skrev in en ogiligt
          // input för både newAge och index
          // skulle programmet hantera båda två i slutet, vilket är mycket förvirrande
          index = isInteger(option, "Enter guest's seat number:");
          changeAgeOfGuest(index, newAge);
          printGuestInformation();
          isRunning = true;
          break;
        case 4:
          printGuestInformation();
          System.out.println();
          isRunning = true;
          break;
        case 5:
          printStatistics();
          System.out.println();
          System.out.println();
          isRunning = true;
          break;
        case 6:
          printGuestList();
          System.out.println();
          System.out.println();
          isRunning = true;
          break;
        case 7:
          printGuestList();
          index = isInteger(option, "Enter the guest number you'd like to remove:");
          removeGuest(index);
          printGuestList();
          isRunning = true;
          break;
        case 8:
          int index1 = canGuestSeatThere(option, "Enter the number of the first guest you'd like to move:");
          int index2 = canGuestSeatThere(option, "Enter the number of the second guest you'd like to move:");
          changePlaces(index1, index2);
          System.out.println();
          printGuestList();
          isRunning = true;
          break;
        case 0:
          System.out.println("Goodbye!❤");
          isRunning = false;
          break;
        default:
          System.out.println("WHOOPS! Please choose ONLY a valid number from the options above.");
          readMenuChoice();
          isRunning = true;
          break;
      }

    } while (isRunning);
  }
}