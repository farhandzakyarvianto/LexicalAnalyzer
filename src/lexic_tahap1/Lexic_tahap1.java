/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexic_tahap1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author FarhanDzaky
 */
public class Lexic_tahap1 {

    public static String temp = "";
    public static String a;
    public static boolean fail_state = false;
    public static boolean stop = false;
    public static int state = 0;
    public static char[] arr = new char[100];

    public static String[] arr1 = new String[100];
    public static String[] hasil = new String[100];
    
     
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        
        
        Scanner input = new Scanner(System.in);
        System.out.print("Input (Gunakan 'SPASI' antar lexic)  : ");
        a = input.nextLine();

        printChar();

        for (int i = 0; i < temp.length(); i++) {
            arr[i] = temp.charAt(i);
        }

        int akhir = 0;
        int awal = 0;

        for (int i = 0; i < a.length(); i++) {

            if (a.charAt(i) == ' ') {
                akhir = i;
                arr1[i] = a.substring(awal, akhir);
                awal = akhir + 1;
            }

            if (i == a.length() - 1) {
                arr1[i] = a.substring(akhir, a.length());
            }

        }
        lexical();
//        Parsing_tahap2 pars = new Parsing_tahap2();
//        pars.parsing(hasil);
    }

    public static void printChar() {
        temp = a;
    }

    public static char curr = ' ';

    private static void lexical() {
        int i = 0;

        while (i < temp.length() && !fail_state && !stop) {

            if (state == 0) {
                if (arr[i] == ' ') {
                    i++;
                }
                curr = arr[i];
                if (isKurungBuka(curr) == true) {
                    if (!fail_state) {
                        state = 0;
                        System.out.println(curr + " : Kurung Buka" + " -> State " + state);
                        hasil[i] = "kurbuka";
                    } else {
                        state = 0;
                        System.out.println(curr + " : ");
                    }
                    i++;
                } else if (isDigit(curr) == true) {
                    if (!fail_state) {
                        state = 1;
                        System.out.println(curr + " : Number" + " -> State " + state);
                        hasil[i] = "num";
                    } else {
                        state = 1;
                        System.out.println(curr + " : ");
                    }
                    i++;
                } else if (curr == '+' || curr == '-') {
                    if (!fail_state) {
                        state = 5;
                        System.out.println(curr + " : Number" + " -> State " + state);
                        hasil[i] = "num";
                    } else {
                        state = 5;
                        System.out.println(curr + " : ");
                    }
                    i++;

                } else {

                    i++;
                    if (i == temp.length()) {
                        fail_state = true;
                        hasil[i] = "error";
                    }
                }
            }

            if (state == 1) {
                if (arr[i] == ' ') {
                    i++;
                }
                curr = arr[i];
                if (isKurungTutup(curr) == true) {
                    if (!fail_state) {
                        state = 1;
                        System.out.println(curr + " : Kurung Tutup" + " -> State " + state);
                        hasil[i] = "kurtutup";
                    } else {
                        state = 1;
                        System.out.println(curr + " : ");
                    }
                    i++;

                } else if (isDigit(curr) == true) {
                    if (!fail_state) {
                        state = 1;
                        System.out.println(curr + " : Number" + " -> State " + state);
                        hasil[i] = "num";
                    } else {
                        state = 1;
                        System.out.println(curr + " : ");
                    }
                    i++;

                } else if (isOperator(curr) == true) {
                    if (!fail_state) {
                        state = 0;
                        System.out.println(curr + " : Operator" + " -> State " + state);
                        hasil[i] = "opr";
                    } else {
                        state = 0;
                        System.out.println(curr + " : ");
                    }
                    i++;

                } else if (curr == 'E') {
                    if (!fail_state) {
                        state = 2;
                        System.out.println(curr + " : Number" + " -> State " + state);
                        hasil[i] = "num";
                    } else {
                        state = 2;
                        System.out.println(curr + " : ");
                    }
                    i++;

                } else if (curr == ',') {
                    if (!fail_state) {
                        state = 4;
                        System.out.println(curr + " : Number" + " -> State " + state);
                        hasil[i] = "num";
                    } else {
                        state = 4;
                        System.out.println(curr + " : ");
                    }
                    i++;

                } else {

                    if (i == temp.length()) {
                        stop = true;
                    } else {
                        fail_state = true;
                        hasil[i] = "error";
                        System.out.println(curr + " : Error");
                    }

                }
            }
            if (state == 2) {
                if (arr[i] == ' ') {
                    i++;
                }
                curr = arr[i];
                if (curr == '+' || curr == '-') {
                    if (!fail_state) {
                        state = 3;
                        System.out.println(curr + " : Number" + " -> State " + state);
                        hasil[i] = "num";
                    } else {
                        state = 3;
                        System.out.println(curr + " : ");
                    }

                    i++;
                } else if (isDigit(curr) == true) {
                    if (!fail_state) {
                        state = 6;
                        System.out.println(curr + " : Number" + " -> State " + state);
                        hasil[i] = "num";
                    } else {
                        state = 6;
                        System.out.println(curr + " : ");
                    }
                    i++;

                } else {
                    i++;
                    if (i == temp.length()) {
                        fail_state = true;
                        hasil[i] = "error";
                    }
                }
            }
            if (state == 3) {
                if (arr[i] == ' ') {
                    i++;
                }
                curr = arr[i];
                if (isDigit(curr) == true) {
                    if (!fail_state) {
                        state = 6;
                        System.out.println(curr + " : Number" + " -> State " + state);
                        hasil[i] = "num";
                    } else {
                        state = 6;
                        System.out.println(curr + " : ");
                    }

                    i++;
                } else {
                    i++;
                    if (i == temp.length()) {
                        fail_state = true;
                        hasil[i] = "error";
                    }
                }
            }
            if (state == 4) {
                if (arr[i] == ' ') {
                    i++;
                }
                curr = arr[i];
                if (isDigit(curr) == true) {
                    if (!fail_state) {
                        state = 6;
                        System.out.println(curr + " : Number" + " -> State " + state);
                        hasil[i] = "num";
                    } else {
                        state = 6;
                        System.out.println(curr + " : ");
                    }

                    i++;
                } else {
                    i++;
                    if (i == temp.length()) {
                        fail_state = true;
                        hasil[i] = "error";
                    }
                }
            }
            if (state == 5) {
                if (arr[i] == ' ') {
                    i++;
                }
                curr = arr[i];
                if (isDigit(curr) == true) {
                    if (!fail_state) {
                        state = 1;
                        System.out.println(curr + " : Number" + " -> State " + state);
                        hasil[i] = "num";
                    } else {
                        state = 1;
                        System.out.println(curr + " : ");
                    }

                    i++;
                } else {
                    i++;
                    if (i == temp.length()) {
                        fail_state = true;
                        hasil[i] = "error";
                    }
                }
            }
            if (state == 6) {
                if (arr[i] == ' ') {
                    i++;
                }
                curr = arr[i];
                if (isKurungTutup(curr) == true) {
                    if (!fail_state) {
                        state = 6;
                        System.out.println(curr + " : Kurung Tutup" + " -> State " + state);
                        hasil[i] = "kurtutup";
                    } else {
                        state = 6;
                        System.out.println(curr + " : ");
                    }

                    i++;
                } else if (isDigit(curr) == true) {
                    if (!fail_state) {
                        state = 6;
                        System.out.println(curr + " : Number" + " -> State " + state);
                        hasil[i] = "num";
                    } else {
                        state = 6;
                        System.out.println(curr + " : ");
                    }

                    i++;
                } else if (isOperator(curr) == true) {
                    if (!fail_state) {
                        state = 0;
                        System.out.println(curr + " : Operator" + " -> State " + state);
                        hasil[i] = "opr";
                    } else {
                        state = 0;
                        System.out.println(curr + " : ");
                    }

                    i++;
                } else {
                    if (i == temp.length()) {
                        stop = true;
                    }
                }
            }
        }
        if (!fail_state) {
            if (state == 6 || state == 1) {
                if (i == temp.length()) {
                    if (state == 6) {
                        System.out.println("State akhir 6,  String Valid");
                    }
                    if (state == 1) {
                        System.out.println("State akhir 1,  String Valid");
                    }
                } else {
                    System.out.println("Tidak Valid");
                }
            } else {
                System.out.println("Semua string diterima tetapi State akhir bukan di accepted, TIDAK VALID");
            }
        } else {
            System.out.println("Tidak Diterima");
        }

        view();
    }
    
    

    public static void view() {
        int j = 0;

//       int j = 0;

        while (j < 50) {
            if (hasil[j] == hasil[j + 1]) {
                hasil[j] = null;
            }
            j++;
        }

        System.out.println("====================================================================================================================================");
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != null) {

                System.out.print(String.format("%8s %6s", arr1[i], "||"));

            }
        }
        System.out.println("");
        System.out.println("====================================================================================================================================");
        for (int i = 0; i < hasil.length; i++) {
            if (hasil[i] != null) {
                System.out.print(String.format("%8s %6s", hasil[i], "||"));
                if (hasil[i] == "error") {
                    break;
                }
            }
        }
        System.out.println("");
        System.out.println("====================================================================================================================================");

    }

    public static boolean isDigit(char c) {
        return c == '0' || c == '1' || c == '2'
                || c == '3' || c == '4' || c == '5'
                || c == '6' || c == '7' || c == '8'
                || c == '9';
    }

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == 'x' || c == ':';
    }

    public static boolean isKurungBuka(char c) {
        return c == '(';
    }

    public static boolean isKurungTutup(char c) {
        return c == ')';
    }

}