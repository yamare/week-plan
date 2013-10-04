// Creates a Calendar object and sets its current date as your machine's
// Creating an initial value shouldn't be strictly necessary, but the code seems to fail without it
private Calendar inputCalendar = Calendar.getInstance();

// Removes all dates in the calendar
inputCalendar.clear();

// Set allows you to insert values into a calendar object
// It will only insert an empty value, it can't override existing values. Use clear() before using set()
// All values must be integers. January is 0, not 1
inputCalendar.set(int year, int month, int day, int hour, int minute, int seconds);

// Returns a string of all the relevant values in a localized format
// format() is the formatting function, getDefault() provides the user settings, the string is the values you want to display and the calendar object holds the values you need
DateFormat.format(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyy MMMM d hh mm ss"), inputCalendar);

// There will probably be a need to create a custom function to translate the different months into integers. Remember that it's from 0 to 11 and not 1 to 12. There may be some way to do it through SimpleDateFormat's parse function, but attempts at it have been unsuccessful so far.