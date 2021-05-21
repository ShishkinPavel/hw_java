package cz.muni.fi.pb162.hw02.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Pavel Shishkin
 */
public class Similar {
    private String first;
    private String second;
    private int min;
    private List<String> file;

    /**
     * @param file is file
     */
    public Similar(ArrayList<String> file){
        first = file.get(0);
        second = file.get(1);
        min = dist(first, second);
        for (int i = 0; i < file.size(); i++) {
            for (int j = i+1; j < file.size(); j++) {
                if((!file.get(i).equals(first) && !file.get(j).equals(second)) ||
                        (!file.get(i).equals(second) && !file.get(j).equals(first))){
                    int x = dist(file.get(i), file.get(j));
                    if(x < min){
                        min = x;
                        first = file.get(i);
                        second = file.get(j);
                    }
                }
            }
        }
    }

    /**
     * @return string
     */

    public String getResult(){
        return String.format(Locale.US, "Distance of %d\n%s ~= %s", min, first, second);
    }

    /**
     * @param first is first string
     * @param second is second string
     * @return dist
     */
    public static int dist(String first, String second) {

        char[] s1 = first.toCharArray();
        char[] s2 = second.toCharArray();
        int[] prev = new int[ s2.length + 1 ];

        for( int j = 0; j < s2.length + 1; j++ ) {
            prev[ j ] = j;
        }

        for( int i = 1; i < s1.length + 1; i++ ) {

            int[] curr = new int[ s2.length + 1 ];
            curr[0] = i;

            for( int j = 1; j < s2.length + 1; j++ ) {
                int d1 = prev[ j ] + 1;
                int d2 = curr[ j - 1 ] + 1;
                int d3 = prev[ j - 1 ];
                if ( s1[ i - 1 ] != s2[ j - 1 ] ) {
                    d3 += 1;
                }
                curr[ j ] = Math.min( Math.min( d1, d2 ), d3 );
            }

            prev = curr;
        }
        return prev[ s2.length ];
    }
}
