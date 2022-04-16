package src;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class TestExcercise12_12 {

  @Test
  void test() throws Exception {
    File sourceFile = getSourceFile("src/src/TestFile.java");
    StringBuilder buffer = refactorFileToBuffer(sourceFile);
    assertEquals(buffer.toString(),
        "\r\n" + "public class TestFile  {\r\n" + "  public static void main(String[] args) {\r\n"
            + "    // Some statements\r\n" + "  }\r\n" + "}");

  }

  private File getSourceFile(String path) {
    File sourceFile = new File(path);
    if (!sourceFile.exists()) {
      System.out.println("Source file " + path + " not exist");
      System.exit(2);
    }
    return sourceFile;
  }

  private StringBuilder refactorFileToBuffer(File file) throws FileNotFoundException {
    Scanner input = new Scanner(file);
    return refactorInputToBuffer(input);
  }

  private StringBuilder refactorInputToBuffer(Scanner input) {
    StringBuilder buffer = new StringBuilder();
    while (input.hasNext()) {
      String line = input.nextLine();
      refactorLine(line, buffer);
    }
    input.close();
    return buffer;
  }

  private void refactorLine(String line, StringBuilder buffer) {
    String trim = line.trim();
    if (trim.charAt(0) == '{')
      appendRefactoredLeftCurlyBrace(line, buffer);
    else
      buffer.append("\r\n" + line);
  }

  private void appendRefactoredLeftCurlyBrace(String line, StringBuilder buffer) {
    String trim = line.trim();
    buffer.append(" {");
    if (trim.length() > 1)
      buffer.append("\r\n" + line.replace('{', ' '));
  }

}
