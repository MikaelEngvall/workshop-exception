package se.lexicon.exceptions.workshop.data_access;

import se.lexicon.exceptions.workshop.domain.Gender;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.exception.DuplicateNameException;
import se.lexicon.exceptions.workshop.exception.FileWriteException;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

import java.util.List;
import java.util.Random;

public class NameService {


    private List<String> maleFirstNames;
    private List<String> femaleFirstNames;
    private List<String> lastNames;
    private static Random random = new Random();

    //should be no nulls
    public NameService(List<String> maleFirstNames, List<String> femaleFirstNames, List<String> lastNames) {

        this.maleFirstNames = maleFirstNames;
        this.femaleFirstNames = femaleFirstNames;
        this.lastNames = lastNames;
    }

    public Person getNewRandomPerson() {
        Gender gender = getRandomGender();
        Person person = null;
        switch (gender) {
            case MALE:
                person = new Person(getRandomMaleFirstName(), getRandomLastName(), gender);
                break;
            case FEMALE:
                person = new Person(getRandomFemaleFirstName(), getRandomLastName(), gender);
                break;
        }
        return person;
    }


    public String getRandomFemaleFirstName() {
        return femaleFirstNames.get(random.nextInt(femaleFirstNames.size()));
    }

    public String getRandomMaleFirstName() {
        return maleFirstNames.get(random.nextInt(maleFirstNames.size()));
    }

    public String getRandomLastName() {
        return lastNames.get(random.nextInt(lastNames.size()));
    }

    public Gender getRandomGender() {
        return random.nextInt(100) > 50 ? Gender.FEMALE : Gender.MALE;
    }


    /**
     * Here you need to check if List<String> femaleFirstNames already contains the name
     * If name already exists throw a new custom exception you will have to create called
     * DuplicateNameException.
     *
     * @param name
     */
    public void addFemaleFirstName(String name) throws DuplicateNameException {
        if (femaleFirstNames.contains(name)) {
            throw new DuplicateNameException("Duplicate name: " + name);
        }
        femaleFirstNames.add(name);
        try {
            CSVReader_Writer.saveFemaleNames(femaleFirstNames);
        } catch (FileWriteException e) {
            // Handle IO exceptions since CSVReader_Writer.saveFemaleNames can throw them now
            // For now, just rethrowing as a runtime exception, but we should handle appropriately
            throw new RuntimeException("Error saving names", e);
        }
    }


    /**
     * Here you need to check if List<String> maleFirstNames already contains the name
     * If name already exists throw a new custom exception you will have to create called
     * DuplicateNameException.
     *
     * @param name
     */
    public void addMaleFirstName(String name) throws DuplicateNameException {
        if(maleFirstNames.contains(name)) {
            throw new DuplicateNameException("Name already exists in the list");
        }
        maleFirstNames.add(name);
        try {
            CSVReader_Writer.saveFemaleNames(maleFirstNames);
        } catch (FileWriteException e) {
            // Handle IO exceptions since CSVReader_Writer.saveMaleNames can throw them now
            // For now, just rethrowing as a runtime exception, but we should handle appropriately
            throw new RuntimeException("Error saving names", e);
        }
    }

    /**
     * Here you need to check if List<String> lastNames already contains the name
     * If name already exists throw a new custom exception you will have to create called
     * DuplicateNameException.
     *
     * @param lastName
     */
    public void addLastName(String lastName) throws DuplicateNameException {
        if(lastNames.contains(lastName)) {
            throw new DuplicateNameException("The last name " + lastName + " already exists in the list");
        }
        lastNames.add(lastName);
        try {
            CSVReader_Writer.saveFemaleNames(lastNames);
        } catch (FileWriteException e) {
            // Handle IO exceptions since CSVReader_Writer.saveLastNames can throw them now
            // For now, just rethrowing as a runtime exception, but we should handle appropriately
            throw new RuntimeException("Error saving names", e);
        }
    }


}
