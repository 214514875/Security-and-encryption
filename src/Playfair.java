
/**
 * Denver Pillay
 * 214514875
 * Security and Encryption: Playfair Cipher
 */
import java.util.*;

public class Playfair {

    /*All alphabets in a string except J*/
    String alpha = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

    /*Function that removes duplicates chars*/
    String removeDuplicates(String s) {
        StringBuilder noDupes = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String si = s.substring(i, i + 1);
            if (noDupes.indexOf(si) == -1) {
                noDupes.append(si);
            }
        }
        return noDupes.toString();
    }

    /*Function that gets exclusive chars between two strings*/
    String getExclus(String str, String rem) {
        for (int i = 0; i < rem.length(); i++) {
            str = str.replaceAll(("" + rem.charAt(i)), "");
        }
        return str;
    }

    void Display(char arr[][]) {
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 5; i++) {
                System.out.print(arr[j][i]);
            }
            System.out.println();
        }
    }

    String getRowCol(char ch, char[][] table) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (ch == table[i][j]) {
                    return i + "" + j + "";
                }
            }
        }
        return "";
    }

    String PlayFair(String key, String text, boolean flag) {
        /*If True then decrpytion otherwise Encryption*/
        if (flag) {
            /*Decryption*/
        } else {
            /*Encryption*/

            char[][] table = new char[5][5];
            /*[rows][columns]*/
 /*To upper case and remove all Spaces also J->I* and removing Duplicate chars*/
            key = removeDuplicates(key.toUpperCase().replaceAll(" ", "").replaceAll("J", "I"));

            /*Populating the 2D array with the keyword*/
            for (int i = 0; i < key.length(); i++) {
                table[i / 5][i % 5] = key.charAt(i);
            }

            /*Populating with the remaining alphabets*/
            String exclus = getExclus(alpha, key);
            /*Getting Remaining alphabets*/
            int count = 0;
            for (int i = key.length(); i < 25; i++) {
                table[i / 5][i % 5] = exclus.charAt(count);
                count++;
            }

            /*Getting the encrypted text*/
            text = text.replaceAll(" ", "").toUpperCase().replaceAll("J", "I");

            /*If even length leave alone otherwise add a filler X*/
            if (text.length() % 2 != 0) {
                text += "X";
            }

            /*For loop for text length divided by two*/
            String message = "";
            int row1, col1, row2, col2, cnt = 0;
            for (int i = 0; i < text.length() / 2; i++) {
                row1 = ((int) getRowCol(text.charAt(cnt), table).charAt(0)) - 48;
                col1 = ((int) getRowCol(text.charAt(cnt), table).charAt(1)) - 48;
                row2 = ((int) getRowCol(text.charAt(cnt + 1), table).charAt(0)) - 48;
                col2 = ((int) getRowCol(text.charAt(cnt + 1), table).charAt(1)) - 48;

                System.out.println(row1 + " " + col1 + " " + row2 + " " + col2);
                if (row1 == row2) {
                    message = table[row1][(col1 + 1) % 5] + "" + table[row2][(col2 + 1) % 5];
                } else if (col1 == col2) {
                    message = table[(row1 + 1) % 5][col1] + "" + table[(row2 + 1) % 5][col2];
                } else {
                    message = table[row1][col2] + "" + table[row2][col1];
                }

                cnt += 2;
            }

            System.out.println(text);
            Display(table);
        }
        return "";
    }

    public static void main(String[] args) {
        Playfair obj = new Playfair();
        Scanner kb = new Scanner(System.in);

        String key = "playfair";
        String text = "I am a big boy a";

        obj.PlayFair(key, text, false);
        //System.out.println(obj.getExclus(temp, tempp));
    }

}
