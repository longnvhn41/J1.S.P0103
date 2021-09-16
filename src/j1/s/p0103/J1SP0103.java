/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0103;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */

public class J1SP0103 {
    Scanner scanner =new Scanner(System.in);
    public String checkInputDateOfBirth() {
        while (true) {
            try {
                String inputDate = scanner.nextLine().trim();
                SimpleDateFormat fomat = new SimpleDateFormat("d/MM/yyyy");
                Date date = fomat.parse(inputDate);
                if (inputDate.equalsIgnoreCase(fomat.format(date))) {
                    return inputDate;
                } else {
                    System.out.println("Please input date matches with form (dd/MM/yyyy)! Please input again: ");
                }
            } catch (Exception e) {
                System.out.print("Date is not exist! Please input again: ");
            }
        }
    }
    
    public static void main(String[] args) {
        J1SP0103 ad=new J1SP0103();
        System.out.print("Please enter the first date: ");
        String date1=ad.checkInputDateOfBirth();
        System.out.print("Please enter the second date: ");
        String date2=ad.checkInputDateOfBirth();
        if (date1.compareTo(date2) > 0) {
            System.out.println("Date1 is after Date2");
        } else if (date1.compareTo(date2) < 0) {
            System.out.println("Date1 is before Date2");
        } else if(date1.compareTo(date2) == 0) {
            System.out.println("Date1 is equal to Date2");
        }
    }
    //Nguyen Van Long_HA140412
}
