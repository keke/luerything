package net.luerything.playground.calibre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author keke
 */
public class CalibreMetadata implements Serializable {
  private static final Logger LOG = LoggerFactory.getLogger(CalibreMetadata.class);
  private String title;
  private List<Identifier> identifiers = new ArrayList<>();
  private Path path;

  public Path getPath() {
    return path;
  }

  public CalibreMetadata() {

  }

  public CalibreMetadata(Path path) {

    this.path = path;
  }

  public List<Identifier> getIdentifiers() {
    return identifiers;
  }

  public void setIdentifiers(List<Identifier> identifiers) {
    this.identifiers = identifiers;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Path[] getFilePath() throws IOException {
    return Files.list(path).filter(f -> {
      LOG.debug("Checking: {}", f.getFileName().toString().contains(getTitle()));
      return f.getFileName().toString().contains(getTitle());
    }).toArray(Path[]::new);

  }

  public static class Identifier implements Serializable {
    private String scheme;
    private String value;

    public Identifier(String scheme, String value) {
      this.scheme = scheme;
      this.value = value;
    }

    public String getScheme() {
      return scheme;
    }

    public void setScheme(String scheme) {
      this.scheme = scheme;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return "Identifier{" +
          "scheme='" + scheme + '\'' +
          ", value='" + value + '\'' +
          '}';
    }
  }
}
