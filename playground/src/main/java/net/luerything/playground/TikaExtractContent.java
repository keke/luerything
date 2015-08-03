package net.luerything.playground;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * @author keke
 */
public class TikaExtractContent {
  private static final Logger LOG = LoggerFactory.getLogger(TikaExtractContent.class);

  public TikaExtractContent(Path filePath) throws IOException, TikaException {
    Tika tika = new Tika();
    Metadata metadata = new Metadata();
    String content = tika.parseToString(Files.newInputStream(filePath, StandardOpenOption.READ), metadata);
//    System.out.println("Content is " + content);
    LOG.debug("Metadata is {}", metadata);
  }

  public static void main(String... args) throws IOException, XMLStreamException, TikaException {
    CalibreMetadataReader reader = new CalibreMetadataReader("/Users/keke/Calibre Library/jowen/Evans Data Cloud 2014_2 (1867)/metadata.opf");
    new TikaExtractContent(reader.getMetadata().getFilePath()[0]);
  }
}
