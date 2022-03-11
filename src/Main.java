import java.util.Arrays;
public class Main {
    public static void main(String[] args)
    {
//        Encryptor encryptor = new Encryptor(3, 2);
//        String x = encryptor.decryptMessage("Ti hsiscn ofietdnia!Bl e nto helo okotAu!A");
//        System.out.println(x);

//        Encryptor en = new Encryptor(5, 7);
//        en.fillBlock("Reddit is the answer to everything.");
//        String f = en.encryptBlock();
//        System.out.println(f);

        Encryptor e = new Encryptor (5, 7);
        String x = e.decryptMessage("Riatyesnotd s hdtweiihevntereg   r.");
        System.out.println(x);
    }
}
