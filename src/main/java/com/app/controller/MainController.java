package com.app.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.app.dao.CountryDAO;
import com.app.dao.CountryJPADAO;
import com.app.dao.RegistrationNumberDAO;
import com.app.dao.RegistrationNumberJPADAO;
import com.app.entity.Country;
import com.app.entity.RegistrationNumber;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController {

    private CountryDAO countryDAO = new CountryJPADAO();
    private RegistrationNumberDAO registrationNumberDAO = new RegistrationNumberJPADAO();
    private static final int AMOUNT_OF_CHARS_IN_WORD = 4;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button searchAppButton;

    @FXML
    private TextField planeNumberField;

    @FXML
    private Button aboutAppButton;

    @FXML
    private TextField countryField;

    @FXML
    void initialize() {
        searchAppButton.setOnAction(event -> {
            String planeNumber = planeNumberField.getText().toLowerCase().trim();
            readAllCountryTable().forEach(System.out::println);
            readAllNumberTable().forEach(System.out::println);

            countryField.setText(findCountryByID(findRegistrationID(iteration(readAllNumberTable(),planeNumber),readAllNumberTable())));
            System.out.println(countryField);
        });
    }

    private String iteration(List<RegistrationNumber> regisNumber, String planeNumber) {
        List<String> sortedRegisNumber = regisNumber.stream()
                .map(RegistrationNumber::getName)
                .sorted(Comparator.comparing(String::length).reversed())
                .collect(Collectors.toList());
        for (String regNumber : sortedRegisNumber) {
            if (regNumber == null || planeNumber == null)
                System.out.println("Input some data, String is empty");
            assert planeNumber != null;
            if (planeNumber.length() > AMOUNT_OF_CHARS_IN_WORD){
            String planeNumberString = planeNumber.substring(0, AMOUNT_OF_CHARS_IN_WORD).toLowerCase();
            assert regNumber != null;
            String regNumberString = regNumber.toLowerCase();
            for (int i = 0; ( i < planeNumberString.length()); i++) {
                if (contains(planeNumber,regNumberString)) {
                    System.out.println(regNumber);
                    return regNumber;
                }
            }
            }
        }
        return "Returned string is empty";
    }

    private long findRegistrationID(String name, List<RegistrationNumber> registrationNumberList) {
        for (RegistrationNumber tempNumber:registrationNumberList) {
            if (tempNumber.getName().equals(name))
                return tempNumber.getId();
        }
        return 404;
    }

    private String findCountryByID(long id){
        return countryDAO.getCountryName(id);
    }

    private static boolean contains (String string1, String string2){
        return string1.toLowerCase().contains(string2.toLowerCase());
    }

    private List<Country> readAllCountryTable() {
        List<Country> countryList = countryDAO.getAll();
        if (countryList == null || countryList.isEmpty()) {
            System.out.println("Country list is empty!");
            return new ArrayList<>();
        }
        return countryList;
    }

    private List<RegistrationNumber> readAllNumberTable() {
        List<RegistrationNumber> registrationNumberList = registrationNumberDAO.getAll();
        if (registrationNumberList == null || registrationNumberList.isEmpty()) {
            System.out.println("Registration number list is empty!");
            return new ArrayList<>();
        }
        return registrationNumberList;
    }
}
