package data;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import util.Constants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class XmlComputerRepository implements ComputerRepository {
    private static XmlComputerRepository instance;

    private XmlComputerRepository() {
    }

    public static XmlComputerRepository getInstance() {
        if (instance == null) {
            instance = new XmlComputerRepository();
        }

        return instance;
    }

    @Override
    public List<Computer> getComputers() {
        List<Computer> computers = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(Constants.CATALOG_XML_ASSET_PATH)) {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

            try {
                XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(fileInputStream);

                Map<String, String> xmlValueByKey = new HashMap<>();
                Stack<String> key = new Stack<>();

                while (xmlStreamReader.hasNext()) {
                    int event = xmlStreamReader.next();
                    switch (event) {
                        case XMLStreamConstants.START_ELEMENT -> {
                            if (!"laptops".equalsIgnoreCase(xmlStreamReader.getLocalName())
                                    && !"laptop".equalsIgnoreCase(xmlStreamReader.getLocalName())) {
                                key.push(xmlStreamReader.getLocalName());
                            }

                            if (xmlStreamReader.getAttributeCount() > 0
                                    && !xmlValueByKey.containsKey(key.toString())) {
                                if ("screen".equalsIgnoreCase(xmlStreamReader.getLocalName())) {
                                    String xmlTouchscreenFlag = xmlStreamReader.getAttributeValue(0);

                                    xmlValueByKey.put(
                                            key.toString(),
                                            "yes".equalsIgnoreCase(xmlTouchscreenFlag)
                                                    ? Constants.YES
                                                    : "no".equalsIgnoreCase(xmlTouchscreenFlag) ? Constants.NO : ""
                                    );
                                } else {
                                    xmlValueByKey.put(key.toString(), xmlStreamReader.getAttributeValue(0));
                                }
                            }
                        }
                        case XMLStreamConstants.CHARACTERS -> {
                            if (!xmlValueByKey.containsKey(key.toString())) {
                                xmlValueByKey.put(key.toString(), xmlStreamReader.getText().trim());
                            }
                        }
                        case XMLStreamConstants.END_ELEMENT -> {
                            if (key.size() > 0) {
                                key.pop();
                            }

                            if ("laptop".equalsIgnoreCase(xmlStreamReader.getLocalName())) {
                                computers.add(
                                        new ComputerBuilder()
                                                .fromMap(xmlValueByKey)
                                                .createComputer()
                                );

                                xmlValueByKey.clear();
                            }
                        }
                    }
                }
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return computers;
    }

    @Override
    public boolean saveComputers(List<Computer> computers) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(Constants.CATALOG_XML_ASSET_PATH)) {
            try {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();

                Element laptopsElement = document.createElement("laptops");

                String timestampPattern = "yyyy-MM-dd' T 'HH:mm";
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timestampPattern);
                laptopsElement.setAttribute("moddate", dateTimeFormatter.format(LocalDateTime.now()));

                for (int i = 0; i < computers.size(); i++) {
                    Element laptopElement = document.createElement("laptop");
                    laptopElement.setAttribute("id", String.valueOf(i + 1));

                    Computer computer = computers.get(i);

                    Element manufacturerElement = document.createElement("manufacturer");
                    manufacturerElement.setTextContent(computer.getManufacturerName());
                    laptopElement.appendChild(manufacturerElement);

                    Element screenElement = document.createElement("screen");
                    if (!computer.getTouchscreenFlag().isBlank()) {
                        screenElement.setAttribute(
                                "touch",
                                "tak".equalsIgnoreCase(computer.getTouchscreenFlag())
                                        ? "yes"
                                        : "no"
                        );
                    }

                    Element sizeElement = document.createElement("size");
                    sizeElement.setTextContent(computer.getDiagonalScreenSize());
                    screenElement.appendChild(sizeElement);

                    Element resolutionElement = document.createElement("resolution");
                    resolutionElement.setTextContent(computer.getScreenResolution());
                    screenElement.appendChild(resolutionElement);

                    Element typeElement = document.createElement("type");
                    typeElement.setTextContent(computer.getScreenSurfaceType());
                    screenElement.appendChild(typeElement);

                    laptopElement.appendChild(screenElement);

                    Element processorElement = document.createElement("processor");

                    Element processorNameElement = document.createElement("name");
                    processorNameElement.setTextContent(computer.getCpu());
                    processorElement.appendChild(processorNameElement);

                    Element physicalCoresElement = document.createElement("physical_cores");
                    physicalCoresElement.setTextContent(computer.getNumberOfCpuCores());
                    processorElement.appendChild(physicalCoresElement);

                    Element clockSpeedElement = document.createElement("clock_speed");
                    clockSpeedElement.setTextContent(computer.getClockFrequency());
                    processorElement.appendChild(clockSpeedElement);

                    laptopElement.appendChild(processorElement);

                    Element ramElement = document.createElement("ram");
                    ramElement.setTextContent(computer.getRam());
                    laptopElement.appendChild(ramElement);

                    Element discElement = document.createElement("disc");
                    if (!computer.getDiscType().isBlank()) {
                        discElement.setAttribute("type", computer.getDiscType());
                    }

                    Element storageElement = document.createElement("storage");
                    storageElement.setTextContent(computer.getDiscSize());
                    discElement.appendChild(storageElement);

                    laptopElement.appendChild(discElement);

                    Element graphicElement = document.createElement("graphic_card");

                    Element graphicNameElement = document.createElement("name");
                    graphicNameElement.setTextContent(computer.getGpu());
                    graphicElement.appendChild(graphicNameElement);

                    Element memoryElement = document.createElement("memory");
                    memoryElement.setTextContent(computer.getGpuMemory());
                    graphicElement.appendChild(memoryElement);

                    laptopElement.appendChild(graphicElement);

                    Element osElement = document.createElement("os");
                    osElement.setTextContent(computer.getOperatingSystem());
                    laptopElement.appendChild(osElement);

                    Element discReaderElement = document.createElement("disc_reader");
                    discReaderElement.setTextContent(computer.getPhysicalDriveType());
                    laptopElement.appendChild(discReaderElement);

                    laptopsElement.appendChild(laptopElement);
                }

                document.appendChild(laptopsElement);

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");

                transformer.transform(
                        new DOMSource(document),
                        new StreamResult(fileOutputStream)
                );
            } catch (TransformerException | ParserConfigurationException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
