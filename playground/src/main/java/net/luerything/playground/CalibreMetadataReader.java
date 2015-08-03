package net.luerything.playground;

import net.luerything.playground.calibre.CalibreMetadata;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CalibreMetadataReader {
  private static final XMLInputFactory INPUT_FACTORY = XMLInputFactory.newFactory();
  private static final String PURL_SCHEMA = "http://purl.org/dc/elements/1.1/";
  private static final QName TITLE = new QName(PURL_SCHEMA, "title");
  private static final QName IDENTIFIER = new QName(PURL_SCHEMA, "identifier");
  private static final QName ID_SCHEME = new QName("http://www.idpf.org/2007/opf", "scheme");

  public CalibreMetadata getMetadata() {
    return metadata;
  }

  private final CalibreMetadata metadata;

  public CalibreMetadataReader(String path) throws IOException, XMLStreamException {
    this(FileSystems.getDefault().getPath(path));
  }

  public CalibreMetadataReader(Path path) throws IOException, XMLStreamException {

    XMLEventReader reader = INPUT_FACTORY.createXMLEventReader(Files.newBufferedReader(path));
    metadata = new CalibreMetadata(path.getParent());
    readMetadata(metadata, reader);

  }

  private void readMetadata(CalibreMetadata metadata, XMLEventReader eventReader) throws XMLStreamException {
    while (eventReader.hasNext()) {
      XMLEvent event = eventReader.nextEvent();
      switch (event.getEventType()) {
        case XMLEvent.START_ELEMENT:
          StartElement elem = event.asStartElement();
          if (elem.getName().equals(TITLE)) {
            readTitle(metadata, eventReader);
          } else if (elem.getName().equals(IDENTIFIER)) {
            readIdentifier(metadata, eventReader, elem);
          }
      }
//      readMetadata(metadata, eventReader);
    }
  }

  private void readIdentifier(CalibreMetadata metadata, XMLEventReader eventReader, StartElement elem) throws XMLStreamException {

    metadata.getIdentifiers().add(new CalibreMetadata.Identifier(elem.getAttributeByName(ID_SCHEME).getValue(), eventReader.getElementText()));
  }

  private void readTitle(CalibreMetadata metadata, XMLEventReader eventReader) throws XMLStreamException {
    metadata.setTitle(eventReader.getElementText());
  }

  public static void main(String... args) throws IOException, XMLStreamException {
    CalibreMetadataReader reader = new CalibreMetadataReader("/Users/keke/Calibre Library/jowen/Evans Data Cloud 2014_2 (1867)/metadata.opf");
    System.out.println("Title is " + reader.getMetadata().getTitle());
    System.out.println("Identifiers are " + reader.getMetadata().getIdentifiers());
    System.out.println("Files are " + reader.getMetadata().getFilePath().length);
  }
}
