/**
 * Generates a Checksum for a given file, compares if 2 files specified.
 * Improved upon CRC32, uses sha algorithms instead.
 */

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class SHA {
    public static void main(String[] args) throws Exception
    {
        if(args.length < 1){
            System.out.println("Usage: SHA /path/to/file or SHA /path/to/file /path/to/another/file");
        }

        if(args.length == 1){
            String file = args[0];
            FileInputStream in = new FileInputStream(file);

            System.out.println("Checksum: " + getSHA(in,"SHA-256"));
            in.close();
        }
        else if(args.length == 2){
            String file = args[0];
            String file2 = args[1];
            FileInputStream in = new FileInputStream(file);
            FileInputStream in2 = new FileInputStream(file2);

            if(getSHA(in,"SHA-256").equals(getSHA(in2,"SHA-256"))){
                System.out.println("MATCH?: OK");
            }
            else{
                System.out.println("MATCH?: BAD");
            }

            in.close();
            in2.close();
        }
        else{
            System.out.println("Usage: SHA filename or SHA /path/to/file /path/to/another/file");
        }

    }

    public static String getSHA(InputStream in, String shaAlgo) throws Exception {
        MessageDigest mDigest = MessageDigest.getInstance(shaAlgo);

        byte[] data = new byte[1024];

        int readData = 0;
        while((readData = in.read(data)) != -1){
            mDigest.update(data,0,readData);
        }
        byte[] digestBytes = mDigest.digest();

        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < digestBytes.length; i++){
            hex.append(Integer.toHexString(0xFF & digestBytes[i]));
        }
        return hex.toString();
    }
}
