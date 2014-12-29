package net.luerything.calibre.metadata;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author keke
 */
public class DateAdapter extends XmlAdapter<String, Date> {
  //2014-12-23T16:23:02+00:00
  private static SimpleDateFormat FMT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

  @Override
  public Date unmarshal(String v) throws Exception {
    return FMT.parse(v);
  }

  @Override
  public String marshal(Date v) throws Exception {
    return FMT.format(v);
  }
}
