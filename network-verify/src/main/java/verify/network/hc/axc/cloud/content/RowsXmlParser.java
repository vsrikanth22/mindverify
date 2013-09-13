package verify.network.hc.axc.cloud.content;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import verify.network.hc.axc.ContentHandler;

public class RowsXmlParser implements ContentHandler<Rows> {

	private XMLInputFactory f = XMLInputFactory.newInstance();

	public Rows parse(InputStream in) throws XMLStreamException {
		XMLStreamReader reader = f.createXMLStreamReader(new BufferedInputStream(in));

		Rows rows = null;
		while (reader.hasNext()) {
			int code = reader.next();

			switch (code) {
				case XMLEvent.START_ELEMENT:
					String name = reader.getLocalName();
					if (name.equals("rows")) {
						rows = new Rows();
					} else if (name.equals("row")) {
						int count = reader.getAttributeCount();

						Map<String, String> row = new HashMap<>();

						for (int i = 0; i < count; i++) {
							row.put(reader.getAttributeLocalName(i), reader.getAttributeValue(i));
						}

						rows.add(row);
					}

				default:
					break;

			}
		}

		return rows;
	}

}
