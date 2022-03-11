public class Encryptor
{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock()
    {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str)
    {
        int count = 0;
        int length = letterBlock.length * letterBlock[0].length - 1;
        for (int row = 0; row < letterBlock.length; row++){
            for (int col = 0; col < letterBlock[0].length; col++){
                if (count < str.length() ){
                    letterBlock[row][col] = str.substring(count, count + 1);
                }
                else{
                    letterBlock[row][col] = "A";
                }
                count++;
            }
        }
    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock()
    {
        String str = "";
        for (int col = 0 ; col < numCols; col++){
            for (int row = 0; row < numRows; row++){

                str += letterBlock[row][col];
            }
        }
        return str;
    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message)
    {
        String newStr = "";
        String temp = message;

        while(temp.length() > 0) {
            String subString = "";
            if (temp.length() > numCols * numRows) {
                subString = temp.substring(0, numRows * numCols);
                temp = temp.substring(numCols * numRows);
            }
            else{
                subString = temp;
                temp = "";
            }
            fillBlock(subString);
            newStr += encryptBlock();
        }
        return newStr;
    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the “encryption key” that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage)
    {
        int index = 0;
        String tempMessage = encryptedMessage;
        String message = "";

        while (index < encryptedMessage.length()){
            for (int col = 0; col < letterBlock[0].length; col++ ){
                for (int row = 0; row < letterBlock.length; row++){
                    if (index < encryptedMessage.length() ){
                        letterBlock[row][col] = encryptedMessage.substring(index, index + 1);
                    }
                    index++;
                }
            }
            for (String[] s : letterBlock){
                for (String l : s){
                    message += l;
                }
            }
        }
        String prev = "";
        for (int i = 1 ; i < message.length(); i++){
            prev = message.substring(i - 1, i );
            if (prev.equals("A") && message.charAt(i) == 'A'){
                message = message.substring(0, i - 1);
            }
        }
        return message;
    }
}
