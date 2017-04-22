/**
 * Generates a Checksum for a given file, compares if 2 files specified.
 */

import java.io.*;
import java.util.zip.*;

public class GenCRC32 {
    public static void main(String[] args) throws IOException
    {
        if(args.length < 1){
            System.out.println("Usage: GenCRC32 /path/to/file or GenCRC32 /path/to/file /path/to/another/file");
        }

        if(args.length == 1){
            String file = args[0];

            FileInputStream in = new FileInputStream(file);
            System.out.println("Checksum: " + getCRC32(in));
            in.close();
        }
        else if(args.length == 2){
            String file = args[0];
            String file2 = args[1];
            long sumFile;
            long sumfile2;

            FileInputStream in = new FileInputStream(file);
            FileInputStream in2 = new FileInputStream(file2);
            sumFile = getCRC32(in);
            sumfile2 = getCRC32(in2);

            if(sumFile == sumfile2){
                System.out.println("MATCH?: OK");
            }
            else{
                System.out.println("MATCH?: BAD");
            }

            in.close();
            in2.close();
        }
        else{
            System.out.println("Usage: GenCRC32 filename or GenCRC32 /path/to/file");
        }

    }
    
    public static long getCRC32(InputStream in) throws IOException {
        Checksum sum = new CRC32();
        for (int b = in.read(); b != -1; b = in.read()) {
            sum.update(b);
        }
        return sum.getValue();
    }
}
