
public class StringManipulation {
    public String truncateAInFirstTwoPositions(String val) {
        if(val.length() <= 2) {
            return val.replaceAll("A", "");
        }

        String firstTwoChars = val.substring(0, 2);
        String theRest = val.substring(2);

        return firstTwoChars.replaceAll("A", "") + theRest;
    }

    public boolean areFirstAndLastTwoCharactersTheSame(String val) {
        if(val.length() <= 1) {
            return false;
        } else if(val.length() == 2) {
            return true;
        }
        String firstTwo = val.substring(0,2);
        String lastTwo = val.substring(val.length()-2);

        return firstTwo.equals(lastTwo);
    }
}
