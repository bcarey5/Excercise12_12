package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise12_12 {

  public static void main(String[] args) throws Exception {
    checkParameterUsage(args);
    File sourceFile = getSourceFile(args[0]);
    StringBuilder buffer = refactorFileToBuffer(sourceFile);
    writeBufferToFile(buffer, sourceFile);
  }

  private static void checkParameterUsage(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java Exercise12_12 filename");
      System.exit(1);
    }
  }

  private static File getSourceFile(String path) {
    File sourceFile = new File(path);
    if (!sourceFile.exists()) {
      System.out.println("Source file " + path + " not exist");
      System.exit(2);
    }
    return sourceFile;
  }

  private static StringBuilder refactorFileToBuffer(File file) throws FileNotFoundException {
    Scanner input = new Scanner(file);
    return refactorInputToBuffer(input);
  }

  private static StringBuilder refactorInputToBuffer(Scanner input) {
    StringBuilder buffer = new StringBuilder();
    while (input.hasNext()) {
      String line = input.nextLine();
      refactorLine(line, buffer);
    }
    input.close();
    return buffer;
  }

  private static void refactorLine(String line, StringBuilder buffer) {
    String trim = line.trim();
    if (trim.charAt(0) == '{')
      appendRefactoredLeftCurlyBrace(line, buffer);
    else
      buffer.append("\r\n" + line);
  }

  private static void appendRefactoredLeftCurlyBrace(String line, StringBuilder buffer) {
    String trim = line.trim();
    buffer.append(" {");
    if (trim.length() > 1)
      buffer.append("\r\n" + line.replace('{', ' '));
  }

  private static void writeBufferToFile(StringBuilder buffer, File file)
      throws FileNotFoundException {
    PrintWriter output = new PrintWriter(file);
    output.print(buffer.toString());
    output.close();
  }
}
