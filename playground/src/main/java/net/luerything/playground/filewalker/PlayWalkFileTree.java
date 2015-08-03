package net.luerything.playground.filewalker;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author keke
 */
public class PlayWalkFileTree {
  public static final String PATH = "/Users/keke/Calibre Library";
//private static final Logger
  public static void main(String... args) throws IOException {
    Path path = FileSystems.getDefault().getPath(PATH);
    path = Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        return super.visitFile(file, attrs);
      }

      @Override
      public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("Checking " + dir);
        Path metadataPath = dir.resolve("metadata.opf");
        if (Files.exists(metadataPath)) {
          //add to process queue
          return FileVisitResult.SKIP_SUBTREE;
        }
        return super.preVisitDirectory(dir, attrs);
      }
    });
    System.out.println("Start visiting " + path);
  }
}
